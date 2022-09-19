package br.com.content.util;

import br.com.content.domain.Product;
import br.com.content.dto.ProductInputDTO;
import br.com.content.dto.ProductOutputDTO;

public class ProductConverterUtil {

	public static ProductOutputDTO productToProductOutputDTO(Product product) {
		return new ProductOutputDTO(product.getId(), product.getName(), product.getPrice(),
				product.getQuantityInStock());
	}
	
	public static Product ProductInputDTOToProduct(ProductInputDTO product) {
		return new Product(null, product.getName(), product.getPrice(),
				product.getQuantityInStock());
	}

}