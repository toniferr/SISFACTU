package com.toni.ferreiro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toni.ferreiro.app.view.xml.ClienteList;
import com.toni.ferreiro.models.serviceInterface.ClienteServiceInterface;

@RestController //todos los metodos son responseBody
@RequestMapping("/api/clientes")
public class ClienteRestController {
	
	@Autowired
	private ClienteServiceInterface clienteService;
	
	@GetMapping(value = "/listar")
	public ClienteList listar() {
		return new ClienteList(clienteService.findAll());
	}

}
