package br.com.senac.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.dominio.Item;
import br.com.senac.servico.CursoService;

@Controller
public class CarrinhoController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	CursoService cursoService;

	@GetMapping("/carrinho/comprar/{id}")
	public String comprar(@PathVariable("id") Integer id, HttpSession session) {
		if (session.getAttribute("cart") == null) {
			List<Item> listaCarrinho = new ArrayList<Item>();
			listaCarrinho.add(new Item(cursoService.buscar(id), 1));
			session.setAttribute("cart", listaCarrinho);
		} else {
			@SuppressWarnings("unchecked")
			List<Item> listaCarrinho = (List<Item>) session.getAttribute("cart");
			int index = isExists(id, listaCarrinho);
			//se for -1 o item é novo, caso contrário item já existe no carrinho
			if (index == -1) {
				listaCarrinho.add(new Item(cursoService.buscar(id), 1));
			} else {
				int quantidade = listaCarrinho.get(index).getQuantidade() + 1;
				listaCarrinho.get(index).setQuantidade(quantidade);
			}
			session.setAttribute("cart", listaCarrinho);
		}
		return "redirect:/indexCarrinho";
	}

	private int isExists(int id, List<Item> listaCarrinho) {
		for (int i = 0; i < listaCarrinho.size(); i++) {
			if (listaCarrinho.get(i).getCurso().getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	@GetMapping("indexCarrinho")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("carrinho/index");
		return mv;
	}
	
	@GetMapping("finalizar")
	public ModelAndView finalizar() {
		ModelAndView mv = new ModelAndView("carrinho/finalizarCompra");
		return mv;
	}
	
	@GetMapping("/carrinho/remover/{id}")
	public String remover(@PathVariable("id") Integer id, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<Item> listaCarrinho = (List<Item>) session.getAttribute("cart");
		int index = isExists(id, listaCarrinho);
		listaCarrinho.remove(index);
		session.setAttribute("cart", listaCarrinho);
		return "redirect:/indexCarrinho";
	}

}
