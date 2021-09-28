package pe.edu.upeu.exam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upeu.exam.model.Paciente;
import pe.edu.upeu.exam.service.SPaciente;




@RestController
@RequestMapping("/paciente")
public class PacienteController {
	@Autowired
	private SPaciente sPaciente;

	
	
	@PostMapping("/")
	public ResponseEntity<Paciente> save(@RequestBody Paciente prod){
		try {
			Paciente p = sPaciente.create(new Paciente(prod.getIdpaciente(), prod.getDni(), prod.getNombres(), prod.getApellidos(), prod.getDireccion(), prod.getTelefono()));
			return new ResponseEntity<>(p, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Paciente>> getAlumnos(){
		try {
			List<Paciente> list = new ArrayList<>();
			list = sPaciente.readAll();
			if(list.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> getUser(@PathVariable("id") long id){
		Paciente paciente = sPaciente.read(id);
			if(paciente.getIdpaciente()>0) {
				return new ResponseEntity<>(paciente, HttpStatus.OK);
			}else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} 
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id){
		try {
			sPaciente.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Paciente> update(@RequestBody Paciente pacient, @PathVariable("id") long id){
		try {
			Paciente p = sPaciente.read(id);
			if(p.getIdpaciente()>0) {
				p.setDni(pacient.getDni());
				p.setNombres(pacient.getNombres());
				p.setApellidos(pacient.getApellidos());
				p.setDireccion(pacient.getDireccion());
				p.setTelefono(pacient.getTelefono());
				return new ResponseEntity<>(sPaciente.create(p),HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}			

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
