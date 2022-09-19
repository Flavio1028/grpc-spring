package br.com.content.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.content.domain.Product;
import br.com.content.dto.ProductInputDTO;
import br.com.content.dto.ProductOutputDTO;

class ProductConverterUtilTest {

	@Test
	void productToProductOutputDTOTest() {

		Product product = new Product(1L, "product name", 10.00, 10);

		ProductOutputDTO productOutputDTO = ProductConverterUtil.productToProductOutputDTO(product);

		Assertions.assertThat(product)
			.usingRecursiveComparison()
				.isEqualTo(productOutputDTO);
	}

	@Test
	void ProductInputDTOToProductTest() {
		
		ProductInputDTO productDto = new ProductInputDTO("product name", 10.00, 10);
		
		Product product = ProductConverterUtil.ProductInputDTOToProduct(productDto);
		
		Assertions.assertThat(productDto)
		.usingRecursiveComparison()
			.isEqualTo(product);		
	}

}