package br.com.content.resources;

import br.com.content.ProductRequest;
import br.com.content.ProductResponse;
import br.com.content.ProductServiceGrpc.ProductServiceImplBase;
import br.com.content.dto.ProductInputDTO;
import br.com.content.dto.ProductOutputDTO;
import br.com.content.service.IProductService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ProductResource extends ProductServiceImplBase {

	private final IProductService productService;

	public ProductResource(IProductService productService) {
		this.productService = productService;
	}

	@Override
	public void create(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {

		ProductInputDTO productInput = new ProductInputDTO(request.getName(), request.getPrice(),
				request.getQuantityInStock());
		
		ProductOutputDTO productOutput = this.productService.create(productInput);

		responseObserver.onNext(ProductResponse.newBuilder()
				.setId(productOutput.getId())
				.setName(productOutput.getName())
				.setPrice(productOutput.getPrice())
				.setQuantityInStock(productOutput.getQuantityInStock())
			.build());
		responseObserver.onCompleted();		
	}

}