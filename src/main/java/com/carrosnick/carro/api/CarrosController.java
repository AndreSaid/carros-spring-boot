package com.carrosnick.carro.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrosnick.carro.domain.Carro;
import com.carrosnick.carro.domain.dto.CarroDTO;
import com.carrosnick.carro.services.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	@Autowired
	private CarroService service;

	@GetMapping() //busca toda a lista de carros
	public ResponseEntity<List<CarroDTO>> get() {
		return ResponseEntity.ok(service.getCarros());
		//return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}") //busca carro por id
	public ResponseEntity<Carro> get(@PathVariable ("id")Long id) {
		 Optional<Carro>carro = service.getCarrosById(id);
		 
		 return carro
				 .map(ResponseEntity::ok)//Usando lambdas
				 .orElse(ResponseEntity.notFound().build());
		 
//		 return carro.isPresent() ? //Usando o if ternario
//				ResponseEntity.ok(carro.get()):
//				ResponseEntity.notFound().build();
		 
		 
		 //		 if(carro.isPresent()) {
//			 return ResponseEntity.ok(carro.get());
//		 }else {
//			 return ResponseEntity.notFound().build();
//		 }
	}
	
	@GetMapping("/tipo/{tipo}") //busca carro por tipo
	public ResponseEntity getCarrosByTipo(@PathVariable ("tipo")String tipo) {
		List<CarroDTO> carros = service.getCarrosByTipo(tipo);
		
		return carros.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(carros);
	}	
	
	@PostMapping //salva um novo carro
	public String post(@RequestBody Carro carro) {
		Carro c = service.insert(carro);
		
		return "Carro salvo com sucesso: "+ c.getId();
	}
	
	@PutMapping("/{id}")//atualiza um carro
	public String put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		Carro c = service.update(carro, id);
		return "Carro atualizado com sucesso: " + c.getId();
	}
	
	@DeleteMapping("/{id}")//deleta um carro por id
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		return "Carro deletado com sucesso";
	}
}
