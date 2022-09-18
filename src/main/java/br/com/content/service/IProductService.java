package br.com.content.service;

import java.util.List;

import br.com.content.dto.ProductInputDTO;
import br.com.content.dto.ProductOutputDTO;

public interface IProductService {

	public ProductOutputDTO create(ProductInputDTO inputDTO);

	public ProductOutputDTO findById(Long id);

	public void delete(Long id);

	public List<ProductOutputDTO> findAll();
	
}