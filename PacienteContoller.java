package com.example.demo.controller;

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

import com.example.demo.model.Paciente;
import com.example.demo.serviceImpl.PacienteServiceImpl;


@RestController
@RequestMapping("/api/pacientes")
public class PacienteContoller {
	@Autowired
	private PacienteServiceImpl pacienteService;
	@PostMapping("/agregar")
	public ResponseEntity<Paciente> save(@RequestBody Paciente paciente){
		try {
			Paciente pc = pacienteService.create(new Paciente(paciente.getId(),paciente.getDni(),paciente.getNombres(),paciente.getApellidos(),paciente.getDireccion(),paciente.getTelefono()));
			return new ResponseEntity<>(pc, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/listar")
	public ResponseEntity<List<Paciente>> getPacientes(){
		try {
			List<Paciente> list = new ArrayList<>();
			list = pacienteService.readAll();
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
		Paciente paciente = pacienteService.read(id);
			if(paciente.getId()>0) {
				return new ResponseEntity<>(paciente, HttpStatus.OK);
			}else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} 
	}
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id){
		try {
			pacienteService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Paciente> update(@RequestBody Paciente pc, @PathVariable("id") long id){
		try {
			Paciente ppc = pacienteService.read(id);
			if(ppc.getId()>0) {
				ppc.setNombres(pc.getNombres());
				return new ResponseEntity<>(pacienteService.create(ppc),HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}			

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
