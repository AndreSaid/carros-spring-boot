package com.carrosnick.carro.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.carrosnick.carro.domain.Carro;
import com.carrosnick.carro.domain.dto.CarroDTO;
import com.carrosnick.carro.repository.CarroRepository;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository repo;

	public List<CarroDTO> getCarros() {
		List<Carro> carros = repo.findAll();
		//lambdas
		return repo.findAll().stream().map(CarroDTO::new).collect(Collectors.toList());
		
//		List<CarroDTO> list = new ArrayList<>();
//		for (Carro c : carros) {
//			list.add(new CarroDTO(c));
//		}
//		return list;//acha todos os carros;
	}
	
	public Optional<Carro> getCarrosById(Long id) {
		return repo.findById(id);//acha carros por Id;
	}
	
	public List<Carro> getCarrosfake() {
		List<Carro> carros = new ArrayList<>();

		carros.add(new Carro(null, "Fusca"));
		carros.add(new Carro(null, "Brasilia"));
		carros.add(new Carro(null, "Chevette"));

		return carros;
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {
		return repo.findByTipo(tipo).stream().map(CarroDTO::new).collect(Collectors.toList());//pega carros por Tipo;
	}

	public Carro insert(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possível inserir o registro");
		return repo.save(carro);//salva um novo carro
		
	}

	public Carro update(Carro carro, Long id) {
		Assert.notNull(id,  "Não foi possível atualizar o registro");
		
		//Busca o carro no bacno de dados
		Optional<Carro> optional = getCarrosById(id);
		if(optional.isPresent()) {
			Carro db = optional.get();
			//Copiar as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id "+ db.getId());
			
			//Atualiza o carro
			repo.save(db);
			return db;
		}else {
			throw new RuntimeException("Não foi possível atualizar o registro");
		}
	}
	
//	getCarroById(id).map(db ->{ //metodo atualizar com lambda
//		//Copiar as propriedades
//		db.setNome(carro.getNome());
//		db.setTipo(carro.getTipo());
//		System.out.println("Carro id "+ db.getId());
//		
//		//Atualiza o carro
//		repo.save(db);
//		return db;
//		}).orElseThrow(()-> new RuntimeException("Não foi possivel atualizar o registro"));

	public void delete(Long id) {
		Optional<Carro> carro = getCarrosById(id);
		if(carro.isPresent()) {
			repo.deleteById(id);
		}
		
	}

}
