package com.example.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.proyecto.dao.UnidadOperativaDaoImp;
import com.example.proyecto.entity.UnidadOperativa;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/unidad")
public class UnidadOperativaController {
	
	@Autowired
	private UnidadOperativaDaoImp unidadDao;

	@GetMapping("/listado")
	public String mostrarListadoUnidad(Model model) {
		List<UnidadOperativa> uniOp = unidadDao.findAll();
		model.addAttribute("unidades", uniOp);
		return "listadoUnidades";
	}

	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditarUnidad(@PathVariable Long id, Model model) {
		UnidadOperativa uniOpe = unidadDao.findOne(id);
		model.addAttribute("unidad", uniOpe);
		return "formularioEditarUnidad";
	}

	@PostMapping("/editar/{id}")
	public ResponseEntity<String> editarUnidad(@PathVariable("id") Long id, @RequestBody UnidadOperativa
			unidadOperativa) {
		if (id != null && id > 0) {
            UnidadOperativa existingUnidad = unidadDao.findOne(id);
            if (existingUnidad != null) {
            	existingUnidad.setNombreUnidadOperativa(unidadOperativa.getNombreUnidadOperativa());
                return new ResponseEntity<>("Unidad actualizada correctamente", HttpStatus.OK);
            }
            return new ResponseEntity<>("No se encontró la unidad", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("ID de unidad inválido", HttpStatus.BAD_REQUEST);
	}

    @PostMapping("/guardar")
    public String guardarUnidad(@ModelAttribute UnidadOperativa unidadOperativa) {
        unidadDao.save(unidadOperativa);
        return "redirect:/unidad/listado";
    }

	@GetMapping("/eliminar/{id}")
	public String eliminarUnidad(@PathVariable Long id) {
		unidadDao.delete(id);
		return "redirect:/unidad/listado";
	}

	@GetMapping("/crear")
	public String mostrarFormularioCrearUnidad(Model model) {
		model.addAttribute("unidad", new UnidadOperativa());
		
		return "formularioCrearUnidad";
	}

}
