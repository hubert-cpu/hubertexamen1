package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pacientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String dni;
	private String nombres;
	private String apellidos;
	private String direccion;
	private String telefono;
}
