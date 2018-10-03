package br.com.senac.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
		ModelAndView mv = new ModelAndView("curso/paginaAdicionar");
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
	private ModelAndView listarCurso() {
		ModelAndView mv = new ModelAndView("curso/paginaListar");
		mv.addObject("cursos", cursoService.listar());
		return null;
	}
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("curso/index");
		mv.addObject("curso", cursoService.listar());
		return null;
	}
}
