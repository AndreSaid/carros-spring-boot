package com.carrosnick.carro.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.carrosnick.carro.domain.Carro;

@Service
public class CarroService {

	
		public List<Carro> getCarros(){
			List<Carro>carros = new ArrayList<>();
			
			carros.add(new Carro(null,"Fusca"));
			carros.add(new Carro(null,"Brasilia"));
			carros.add(new Carro(null,"Chevette"));
			
			return carros;
		}
	

}
