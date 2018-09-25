package br.com.senac.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@Autowired
	private LoginController alunoService;

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("aluno/paginaLogin");
		mv.addObject("aluno", null);
		return mv;
	}
}
