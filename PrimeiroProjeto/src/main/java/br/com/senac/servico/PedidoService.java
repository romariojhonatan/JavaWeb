package br.com.senac.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.dominio.Pedido;
import br.com.senac.exception.ObjectNotFoundException;
import br.com.senac.repositorio.PedidoRepositorio;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepositorio repoPed;
	
	public List<Pedido> listaPedidos() {
		return repoPed.findAll();
	}

	public Pedido buscar(Integer id) {
		Optional<Pedido> objPedido = repoPed.findById(id);
		return objPedido.orElseThrow(() -> new ObjectNotFoundException(
				"Pedido não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
}
