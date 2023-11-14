package com.ped.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ped.entities.Pedido;
import com.ped.services.PedServico;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Pedidos", description = "API REST DE GERENCIAMENTO DE PEDIDPS")
@RestController
@RequestMapping("/pedido")
public class PedController {
	
	private final PedServico pedServico;
	
	@Autowired
	public PedController(PedServico pedServico) {
		this.pedServico = pedServico;
	}
	@GetMapping("/{id}")
	@Operation (summary = "Localiza pedido por ID")
	public ResponseEntity<Pedido> buscaPedidoControlId(@PathVariable Long id){
		Pedido pedido = pedServico.buscaPedidoId(id);
		if(pedido != null) {
			return ResponseEntity.ok(pedido);
		}
		else{
			return ResponseEntity.notFound().build();
	
		}
	}
	@GetMapping("/")
	@Operation (summary = "Apresenta todos os pedidos")
	public ResponseEntity<List<Pedido>> buscaTodosPedidosControl(){
		List<Pedido> pedidos = pedServico.buscaTodosPedidos();
		return ResponseEntity.ok(pedidos);
	}
	@PostMapping("/")
	@Operation (summary = "Cadastra um pedido")
	public ResponseEntity<Pedido> salvaPedidosControl (@RequestBody @Valid Pedido pedido){
		Pedido salvaPedido = pedServico.salvaPedido(pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaPedido);
	}
	@PutMapping("/{id}")
	@Operation (summary = "Altera pedido")
	public ResponseEntity<Pedido> alteraPedidoControl (@PathVariable Long id, @RequestBody @Valid Pedido pedido){
		Pedido alteraPedido = pedServico.alterarPedido(id, pedido);
		if(alteraPedido != null) {
			return ResponseEntity.ok(pedido);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation (summary = "Exclui pedido")
	public ResponseEntity<Pedido> apagaPedidoControl (@PathVariable Long id){
		boolean apagar = pedServico.apagarPedido(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
