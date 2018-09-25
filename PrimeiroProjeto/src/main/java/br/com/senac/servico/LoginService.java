package br.com.senac.servico;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.senac.dominio.Aluno;
import br.com.senac.repositorio.AlunoRepositorio;

@Service
public class LoginService {

	
	private AlunoRepositorio repoAlu;
	
	public Aluno buscar(String email, String nome) {
		Aluno objAluno = repoAlu.findByEmailAndNome(email, nome);
		return objAluno;
	}
	
}
