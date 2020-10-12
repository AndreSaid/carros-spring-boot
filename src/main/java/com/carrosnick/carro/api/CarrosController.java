package com.carrosnick.carro.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrosnick.carro.domain.Carro;
import com.carrosnick.carro.services.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	@Autowired
	private CarroService service;

	@GetMapping() //busca toda a lista de carros
	public Iterable<Carro> get() {
		return service.getCarros();
	}
	
	@GetMapping("/{id}") //busca carro por id
	public Optional<Carro> get(@PathVariable ("id")Long id) {
		return service.getCarrosById(id);
	}
	
	

}
