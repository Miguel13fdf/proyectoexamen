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

import com.example.proyecto.dao.HistoriaClinicaDaoImp;
import com.example.proyecto.entity.HistoriasClinicas;


@Controller
@RequestMapping("/historia")
public class HistoriaClinicaController {

	@Autowired
	private HistoriaClinicaDaoImp historiaDao;

	@GetMapping("/listado")
	public String mostrarListadoHistorias(Model model) {
		List<HistoriasClinicas> historiaC = historiaDao.findAll();
		model.addAttribute("historias", historiaC);
		return "listadoHistorias";
	}

	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditarHistoria(@PathVariable Long id, Model model) {
		HistoriasClinicas historiaC = historiaDao.findOne(id);
		model.addAttribute("historia", historiaC);
		return "formularioEditarHistoria";
	}

	@PostMapping("/editar/{id}")
	public ResponseEntity<String> editarHistoria(@PathVariable("id") Long id, @RequestBody
			HistoriasClinicas historiasClinicas) {
		if (id != null && id > 0) {
			HistoriasClinicas existingHistoria = historiaDao.findOne(id);
            if (existingHistoria != null) {
            	existingHistoria.setNombrePaciente(historiasClinicas.getNombrePaciente());
            	existingHistoria.setApellidoPaciente(historiasClinicas.getApellidoPaciente());
            	existingHistoria.setFechaNacimientoPaciente(historiasClinicas.getFechaNacimientoPaciente());
                return new ResponseEntity<>("Historia actualizada correctamente", HttpStatus.OK);
            }
            return new ResponseEntity<>("No se encontró la Historia", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("ID de Historia inválido", HttpStatus.BAD_REQUEST);
	}

    @PostMapping("/guardar")
    public String guardarHistoria(@ModelAttribute HistoriasClinicas historiasClinicas) {
        historiaDao.save(historiasClinicas);
        return "redirect:/historia/listado";
    }

	@GetMapping("/eliminar/{id}")
	public String eliminarHistoria(@PathVariable Long id) {
		historiaDao.delete(id);
		return "redirect:/historia/listado";
	}

	@GetMapping("/crear")
	public String mostrarFormularioCrearHistoria(Model model) {
		model.addAttribute("historia", new HistoriasClinicas());
		return "formularioCrearHistoria";
	}
	
}
