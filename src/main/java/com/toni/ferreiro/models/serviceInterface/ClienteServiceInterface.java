package com.toni.ferreiro.models.serviceInterface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.toni.ferreiro.models.entity.Cliente;

public interface ClienteServiceInterface {

	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);

	public void save(Cliente cliente);

	public Cliente findOne(Long id);
	
	public void delete(Long id);
}
