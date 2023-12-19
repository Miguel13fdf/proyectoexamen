package com.example.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.proyecto.dao.HistoriaClinicaDaoImp;
import com.example.proyecto.dao.RegistroParteDiarioDaoImp;
import com.example.proyecto.dao.UnidadOperativaDaoImp;
import com.example.proyecto.entity.HistoriasClinicas;
import com.example.proyecto.entity.RegistroParteDiario;
import com.example.proyecto.entity.UnidadOperativa;




@Controller
@RequestMapping("/registroparte")
public class RegistroParteDiarioController {

	@Autowired
	private RegistroParteDiarioDaoImp registroDao;

	@Autowired
	private UnidadOperativaDaoImp unidadDao;
	
	@Autowired
	private HistoriaClinicaDaoImp historiaDao;
	
	@GetMapping("/listado")
	public String mostrarListadoRegistroParte(Model model) {
		List<RegistroParteDiario> registroPa = registroDao.findAll();
		model.addAttribute("registros", registroPa);
		return "listadoRegistros";
	}

	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditarRegistros(@PathVariable Long id, Model model) {
		RegistroParteDiario registroPa = registroDao.findOne(id);
		model.addAttribute("registro", registroPa);
		List<HistoriasClinicas> histo = historiaDao.findAll();
		model.addAttribute("historia", histo);
		List<UnidadOperativa> uni = unidadDao.findAll();
		model.addAttribute("unidad", uni);
		return "formularioEditarRegistro";
	}

	@PostMapping("/editar/{id}")
	public ResponseEntity<String> editarRegistro(@PathVariable("id") Long id, @RequestBody
			RegistroParteDiario registroParteDiario) {
		if (id != null && id > 0) {
			RegistroParteDiario existingRegistro = registroDao.findOne(id);
            if (existingRegistro != null) {
            	existingRegistro.setFechaRegistro(registroParteDiario.getFechaRegistro());
            	existingRegistro.setNumeroVisita(registroParteDiario.getNumeroVisita());
            	existingRegistro.setPesoPaciente(registroParteDiario.getPesoPaciente());
            	existingRegistro.setTallaPaciente(registroParteDiario.getTallaPaciente());
                return new ResponseEntity<>("Registro actualizado correctamente", HttpStatus.OK);
            }
            return new ResponseEntity<>("No se encontró la Registro", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("ID de Registro inválido", HttpStatus.BAD_REQUEST);
	}

    @PostMapping("/guardar")
    public String guardarRegistro(@ModelAttribute RegistroParteDiario registroParteDiario, 
    		@RequestParam("unidadID") Long unidadID, @RequestParam("historiaID") Long historiaID) {
        try {
			UnidadOperativa uni = unidadDao.findOne(unidadID);
			registroParteDiario.setUnidadOperativa(uni);
			HistoriasClinicas his = historiaDao.findOne(historiaID);
			registroParteDiario.setHistoriaClinica(his);
			registroDao.save(registroParteDiario);
	        return "redirect:/registroparte/listado";
		} catch (Exception e) {
			return "error";
		}
    }

	@GetMapping("/eliminar/{id}")
	public String eliminarRegistro(@PathVariable Long id) {
		registroDao.delete(id);
		return "redirect:/registroparte/listado";
	}

	@GetMapping("/crear")
	public String mostrarFormularioCrearRegistro(Model model) {
		model.addAttribute("registro", new RegistroParteDiario());
		List<UnidadOperativa> unidades = unidadDao.findAll();
		model.addAttribute("unidades", unidades);
        model.addAttribute("unidad", new UnidadOperativa());
		List<HistoriasClinicas> historias = historiaDao.findAll();
		model.addAttribute("historias", historias);
        model.addAttribute("historia", new HistoriasClinicas());
		return "formularioCrearRegistro";
	}
	
}
