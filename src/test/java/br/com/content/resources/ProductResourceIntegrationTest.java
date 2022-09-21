package br.com.content.resources;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import br.com.content.ProductRequest;
import br.com.content.ProductResponse;
import br.com.content.ProductServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@DirtiesContext
class ProductResourceIntegrationTest {

	@GrpcClient("inProcess")
	private ProductServiceGrpc.ProductServiceBlockingStub serviceBlockingStub;
	
	@Test
	@DisplayName("when valid data is provided a product is created")
	void createProductSuccessTest() {
		
		ProductRequest request = ProductRequest.newBuilder()
				.setName("Product name")
				.setPrice(10.00)
				.setQuantityInStock(100)
			.build();
		
		ProductResponse response = serviceBlockingStub.create(request);
		
		assertThat(request)
			.usingRecursiveComparison()
			.comparingOnlyFields("name", "price", "quantityInStock_")
				.isEqualTo(response);
	}
	
	
}