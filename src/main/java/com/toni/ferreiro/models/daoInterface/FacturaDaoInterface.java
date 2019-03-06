package com.toni.ferreiro.models.daoInterface;

import org.springframework.data.repository.CrudRepository;

import com.toni.ferreiro.models.entity.Factura;

public interface FacturaDaoInterface  extends CrudRepository<Factura, Long>{

}
