package com.carrosnick.carro.repository;

import org.springframework.data.repository.CrudRepository;

import com.carrosnick.carro.domain.Carro;

public interface CarroRepository extends CrudRepository<Carro, Long> {
	Iterable<Carro> findByTipo(String tipo);
}
