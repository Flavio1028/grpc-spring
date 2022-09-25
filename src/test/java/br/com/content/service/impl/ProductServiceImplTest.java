package br.com.content.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.content.domain.Product;
import br.com.content.dto.ProductInputDTO;
import br.com.content.exception.AlreadyExistsException;
import br.com.content.exception.NotFoundException;
import br.com.content.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productServiceImpl;
	
	@Test
    @DisplayName("when create product service is call with valid data a product is returned")
    public void createProductSuccessTest() {
        var productCreated = new Product(1L, "product name", 10.00, 10);

        when(productRepository.save(any())).thenReturn(productCreated);

        var productInput = new ProductInputDTO("product name", 10.00, 10);

        var productOutputDTO = productServiceImpl.create(productInput);

        assertThat(productOutputDTO)
                .usingRecursiveComparison()
                .isEqualTo(productCreated);
    }

    @Test
    @DisplayName("when create product service is call with duplicated name, throw AlreadyExistsException")
    public void createProductExceptionTest() {
        var product = new Product(1L, "product name", 10.00, 10);

        when(productRepository.findByNameIgnoreCase(any())).thenReturn(Optional.of(product));

        var productInput = new ProductInputDTO("product name", 10.00, 10);

        assertThatExceptionOfType(AlreadyExistsException.class)
                .isThrownBy(() -> productServiceImpl.create(productInput));
    }

    @Test
    @DisplayName("when findById product service is call with valid id a product is returned")
    public void findProductByIdSuccessTest() {
        var id = 1L;
        var productCreated = new Product(1L, "product name", 10.00, 10);

        when(productRepository.findById(any())).thenReturn(Optional.of(productCreated));

        var productOutputDTO = productServiceImpl.findById(id);

        assertThat(productOutputDTO)
                .usingRecursiveComparison()
                .isEqualTo(productCreated);
    }

    @Test
    @DisplayName("when findById product service is call with invalid id, throw NotFoundException")
    public void findProductByIdExceptionTest() {
        var id = 1L;

        when(productRepository.findById(any())).thenReturn(Optional.empty());

        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> productServiceImpl.findById(id));
    }
    
    @Test
    @DisplayName("when delete product service is call with valid id should does not throw")
    public void deleteProductSuccessTest() {
        var id = 1L;
        var product = new Product(1L, "product name", 10.00, 10);

        when(productRepository.findById(any())).thenReturn(Optional.of(product));

        assertThatNoException().isThrownBy(() -> productServiceImpl.delete(id));
    }

    @Test
    @DisplayName("when delete product service is call with invalid id, throw NotFoundException")
    public void deleteProductExceptionTest() {
        var id = 1L;

        when(productRepository.findById(any())).thenReturn(Optional.empty());

        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> productServiceImpl.delete(id));
    }
	
}