package pe.edu.upeu.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upeu.exam.model.Paciente;
import pe.edu.upeu.exam.repository.PacienteRepository;

@Service
public class PacienteService implements SPaciente {
	@Autowired
	private PacienteRepository pacienteRepository;
	@Override
	public Paciente create(Paciente p) {
		// TODO Auto-generated method stub
		return pacienteRepository.save(p);
	}

	@Override
	public List<Paciente> readAll() {
		// TODO Auto-generated method stub
		return pacienteRepository.findAll();
	}

	@Override
	public Paciente read(Long id) {
		// TODO Auto-generated method stub
		return pacienteRepository.getById(id).get();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		pacienteRepository.deleteById(id);
	}

	@Override
	public Paciente update(Paciente p) {
		// TODO Auto-generated method stub
		return pacienteRepository.save(p);
	}

}
