package com.toni.ferreiro.models.daoInterface;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.toni.ferreiro.models.entity.Cliente;

public interface ClienteDaoInterface extends PagingAndSortingRepository<Cliente, Long> {
}
