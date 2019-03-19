package com.toni.ferreiro.models.daoInterface;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.toni.ferreiro.models.entity.Producto;

public interface ProductoDaoInterface extends CrudRepository<Producto, Long>{

	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String term);
	
	public List<Producto> findByNombreLikeIgnoreCase(String term);
}
