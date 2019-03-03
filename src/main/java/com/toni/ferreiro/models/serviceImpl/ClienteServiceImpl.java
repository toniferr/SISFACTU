package com.toni.ferreiro.models.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toni.ferreiro.models.daoInterface.ClienteDaoInterface;
import com.toni.ferreiro.models.daoInterface.ProductoDaoInterface;
import com.toni.ferreiro.models.entity.Cliente;
import com.toni.ferreiro.models.entity.Producto;
import com.toni.ferreiro.models.serviceInterface.ClienteServiceInterface;

@Service
public class ClienteServiceImpl implements ClienteServiceInterface {

	@Autowired
	private ClienteDaoInterface clienteDao;
	
	@Autowired
	private ProductoDaoInterface productoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return clienteDao.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.delete(id);;
	}

	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombre(term);
	}

}
