package com.devsuperior.demolazy.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.devsuperior.demolazy.util.Convertible;

@Service
public class GenericService<T extends Convertible<DTO>, DTO, ID> {

	@Autowired
	private JpaRepository<T, ID> repository;
	
	public DTO findById(ID id) {
		Optional<T> result = repository.findById(id);
		return result.get().convert();
	}
	
	public List<DTO> findAll() {
		List<T> list = repository.findAll();
		List<DTO> resultDTO = list.stream().map(x -> x.convert()).collect(Collectors.toList());
		return resultDTO;
	}
}
