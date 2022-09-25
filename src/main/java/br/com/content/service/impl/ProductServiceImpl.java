package br.com.content.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.content.domain.Product;
import br.com.content.dto.ProductInputDTO;
import br.com.content.dto.ProductOutputDTO;
import br.com.content.exception.AlreadyExistsException;
import br.com.content.exception.NotFoundException;
import br.com.content.repository.ProductRepository;
import br.com.content.service.IProductService;
import br.com.content.util.ProductConverterUtil;

@Service
public class ProductServiceImpl implements IProductService {

	private final ProductRepository repository;

	public ProductServiceImpl(ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public ProductOutputDTO create(ProductInputDTO inputDTO) {
		this.checkDuplicity(inputDTO.getName());
		Product product = ProductConverterUtil.ProductInputDTOToProduct(inputDTO);
		product = this.repository.save(product);
		return ProductConverterUtil.productToProductOutputDTO(product);
	}

	@Override
	public ProductOutputDTO findById(Long id) {
		Product product = this.repository.findById(id)
			.orElseThrow(() -> new NotFoundException(id));
		return ProductConverterUtil.productToProductOutputDTO(product);
	}

	@Override
	public void delete(Long id) {
		Product product = this.repository.findById(id)
				.orElseThrow(() -> new NotFoundException(id));
		this.repository.delete(product);
	}

	@Override
	public List<ProductOutputDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void checkDuplicity(String name) {
		  this.repository.findByNameIgnoreCase(name)
          .ifPresent(e -> {
              throw new AlreadyExistsException(name);
          });
	}

}