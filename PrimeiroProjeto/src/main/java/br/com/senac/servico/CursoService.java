package br.com.senac.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.senac.dominio.Curso;
import br.com.senac.exception.ObjectNotFoundException;
import br.com.senac.repositorio.CursoRepositorio;

@Service
public class CursoService {

	private CursoRepositorio cursoRepositorio;
	
	public Curso inserir(Curso objCurso) {
		// estou colocando um objeto novo entao o id precisa ser null
		objCurso.setId(null);
		return cursoRepositorio.save(objCurso);
	}

	public List<Curso> listar() {
		return cursoRepositorio.findAll();
	}
	
	public Curso buscar(Integer id) {
		Optional<Curso> objCurso = cursoRepositorio.findById(id);
		return objCurso.orElseThrow(() -> new ObjectNotFoundException(
				"Curso não encontrada! id: "+ id +"tipo: "+ Curso.class.getName()));
	}
}
