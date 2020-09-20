package com.carrosnick.carro.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrosnick.carro.domain.Carro;
import com.carrosnick.carro.repository.CarroRepository;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository repo;

	public Iterable<Carro> getCarros() {
		return repo.findAll();
	}
	
	
	public List<Carro> getCarrosfake() {
		List<Carro> carros = new ArrayList<>();

		carros.add(new Carro(null, "Fusca"));
		carros.add(new Carro(null, "Brasilia"));
		carros.add(new Carro(null, "Chevette"));

		return carros;
	}

}
