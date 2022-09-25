package br.com.content.resources;

import java.util.List;
import java.util.stream.Collectors;

import br.com.content.Empty;
import br.com.content.ProductRequest;
import br.com.content.ProductResponse;
import br.com.content.ProductResponseList;
import br.com.content.ProductServiceGrpc.ProductServiceImplBase;
import br.com.content.RequestById;
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

	@Override
	public void findById(RequestById request, StreamObserver<ProductResponse> responseObserver) {
		ProductOutputDTO productOutput = this.productService.findById(request.getId());
		
		responseObserver.onNext(ProductResponse.newBuilder()
				.setId(productOutput.getId())
				.setName(productOutput.getName())
				.setPrice(productOutput.getPrice())
				.setQuantityInStock(productOutput.getQuantityInStock())
			.build());
		responseObserver.onCompleted();		
	}

	@Override
	public void delete(RequestById request, StreamObserver<Empty> responseObserver) {
		this.productService.delete(request.getId());
		responseObserver.onNext(Empty.newBuilder().build());
		responseObserver.onCompleted();
	}

	@Override
	public void findAll(Empty request, StreamObserver<ProductResponseList> responseObserver) {
		List<ProductOutputDTO> outputDTOList = this.productService.findAll();

		List<ProductResponse> productResponseList = outputDTOList.stream()
				.map(product -> ProductResponse.newBuilder()
					.setId(product.getId())
					.setName(product.getName())
					.setPrice(product.getPrice())
					.setQuantityInStock(product.getQuantityInStock())
				.build())
			.collect(Collectors.toList());

		responseObserver.onNext(ProductResponseList.newBuilder()
				.addAllProducts(productResponseList)
			.build());
		responseObserver.onCompleted();
	}		

}