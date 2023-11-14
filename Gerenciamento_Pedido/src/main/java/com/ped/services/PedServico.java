package com.ped.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ped.entities.Pedido;
import com.ped.repository.PedRepository;

@Service
public class PedServico {
	
	private final PedRepository pedRepository;
	
	@Autowired
	public PedServico (PedRepository pedRepository) {
		this.pedRepository = pedRepository;
	}
	public List<Pedido> buscaTodosPedidos(){
		return pedRepository.findAll();
		
	}
	public Pedido buscaPedidoId(Long id) {
		Optional<Pedido> Pedido = pedRepository.findById(id);
		return Pedido.orElse(null);
	}
	public Pedido alterarPedido(Long id, Pedido alterarPedido) {
		Optional <Pedido> existePedido = pedRepository.findById(id);
		if(existePedido.isPresent()) {
			alterarPedido.setId(id);
			return pedRepository.save(alterarPedido);
		}
		return null;
	}
	public boolean apagarPedido(Long id) {
		Optional <Pedido> existePedido = pedRepository.findById(id);
		if(existePedido.isPresent()) {
			pedRepository.deleteById(id);
			return true;
		}
		return false;
	}
	public Pedido salvaPedido(Pedido pedido) {
		return pedRepository.save(pedido);
	}

}
