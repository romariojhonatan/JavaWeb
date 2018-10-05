package br.com.senac.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.dominio.Categoria;
import br.com.senac.dominio.Curso;
import br.com.senac.servico.CategoriaService;
import br.com.senac.servico.CursoService;

@Controller
public class CursoController {

	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/adicionarCurso")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("curso/paginaAdicionarCurso");
		mv.addObject("curso", new Curso());
		mv.addObject("categorias", categoriaService.listaCategorias());
		return mv;
	}
	
	@PostMapping("/salvarCurso")
	public ModelAndView inserir(Curso curso) {
		cursoService.inserir(curso);
		return listarCurso();
	}

	@GetMapping("/listarCurso")
	public ModelAndView listarCurso() {
		ModelAndView mv = new ModelAndView("curso/paginaCursos");
		mv.addObject("cursos", cursoService.listar());
		return mv;
	}
	
	@GetMapping("/excluirCurso/{id}")
	public ModelAndView delete(@PathVariable("id") Integer id) {
		cursoService.excluir(id);
		return listarCurso();
	}
	
	@GetMapping("/alterarCurso/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("curso/paginaAlterarCurso");
		mv.addObject("curso", cursoService.buscar(id));
		return mv;
	}
	
	@GetMapping("/alterarCurso")
	public ModelAndView alterar(Curso curso) {
		cursoService.alterar(curso);
		return listarCurso();
	}
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("curso/index");
		mv.addObject("curso", cursoService.listar());
		return null;
	}
}
