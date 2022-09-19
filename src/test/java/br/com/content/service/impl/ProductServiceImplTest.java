package br.com.content.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.content.domain.Product;
import br.com.content.dto.ProductInputDTO;
import br.com.content.dto.ProductOutputDTO;
import br.com.content.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productServiceImpl;

	@Test
	@DisplayName("when create product service is call with valid data a product is returned")
	void createProductSuccessTest() {

		Product product = new Product(1L, "product name", 10.00, 10);
		ProductInputDTO inputDTO = new ProductInputDTO("product name", 10.00, 10);

		when(productRepository.save(any())).thenReturn(product);
		
		// Call service
		ProductOutputDTO outputDTO = this.productServiceImpl.create(inputDTO);
		
		Assertions.assertThat(outputDTO)
			.usingRecursiveComparison()
				.isEqualTo(product);
	}

}