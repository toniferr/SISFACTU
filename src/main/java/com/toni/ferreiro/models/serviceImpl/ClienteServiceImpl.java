package com.toni.ferreiro.models.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toni.ferreiro.models.daoInterface.ClienteDaoInterface;
import com.toni.ferreiro.models.daoInterface.FacturaDaoInterface;
import com.toni.ferreiro.models.daoInterface.ProductoDaoInterface;
import com.toni.ferreiro.models.entity.Cliente;
import com.toni.ferreiro.models.entity.Producto;
import com.toni.ferreiro.models.serviceInterface.ClienteServiceInterface;
import com.toni.ferreiro.models.entity.Factura;

@Service
public class ClienteServiceImpl implements ClienteServiceInterface {

	@Autowired
	private ClienteDaoInterface clienteDao;
	
	@Autowired
	private ProductoDaoInterface productoDao;
	
	@Autowired
	private FacturaDaoInterface facturaDao;
	
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
	@Transactional(readOnly = true)
	public Cliente fetchByIdWithFacturas(Long id) {
		return clienteDao.fetchByIdWithFacturas(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.delete(id);;
	}

	@Override
	@Transactional (readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	@Transactional (readOnly = true)
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombre(term);
	}

	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		facturaDao.save(factura);
	}

	@Override
	@Transactional (readOnly = true)
	public Producto findProductoById(Long id) {
		return productoDao.findOne(id); //spring 5 cambia a findById
	}

	@Override
	@Transactional (readOnly = true)
	public Factura findFacturaById(Long id) {
		return facturaDao.findOne(id);
	}

	@Override
	@Transactional
	public void deleteFactura(Long id) {
		facturaDao.delete(id);  //facturaDao.deleteById(id) con spring boot 2
		
	}

	@Override
	@Transactional (readOnly = true)
	public Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id) {
		return facturaDao.fetchByIdWithClienteWithItemFacturaWithProducto(id);
	}
	
}
