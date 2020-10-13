package com.carrosnick.carro.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.carrosnick.carro.domain.Carro;

public interface CarroRepository extends CrudRepository<Carro, Long> {
	List<Carro> findByTipo(String tipo);
}
