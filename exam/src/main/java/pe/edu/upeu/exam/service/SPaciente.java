package pe.edu.upeu.exam.service;

import java.util.List;

import pe.edu.upeu.exam.model.Paciente;


public interface SPaciente {
	Paciente create(Paciente p);
	List<Paciente> readAll();
	Paciente read(Long id);
	void delete(Long id);
	Paciente update(Paciente p);
}
