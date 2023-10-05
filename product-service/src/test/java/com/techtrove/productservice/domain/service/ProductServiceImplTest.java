package com.techtrove.productservice.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techtrove.productservice.domain.Product;
import com.techtrove.productservice.domain.respository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void init() throws JsonProcessingException {
        product = Product.builder()
                .id("6521edebb9ae5f1b1d01d5a8")
                .sku("1112123899")
                .title("title")
                .description("description")
                .availability(true)
                .expirationDate(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC")))
                .price(new BigDecimal("50300.0"))
                .build();
    }

    @Test
    void getProductByIdSuccessfully() {

        when(productRepository.findById(any())).thenReturn(Optional.of(product));

        final var result = productServiceImpl.getProductById("6521edebb9ae5f1b1d01d5a8");

        verify(productRepository).findById(any());

        assertEquals(product, result);
    }

    @Test
    void getProductByIdMistakenly() {

        when(productRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () ->  productServiceImpl.getProductById("6521edebb9ae5f1b1d01d5a8"));
    }

    @Test
    void createProductSuccessfully() {

        when(productRepository.save(any())).thenReturn(product);

        final var result = productServiceImpl.createProduct(product);

        verify(productRepository).save(any());

        assertEquals(product, result);
    }

    @Test
    void createProductMistakenly() {

        when(productRepository.findBySku(any())).thenReturn(Optional.of(product));

        assertThrows(ResponseStatusException.class,
                () ->  productServiceImpl.createProduct(product));
    }

    @Test
    void updateProductSuccessfully() {

        when(productRepository.save(any())).thenReturn(product);

        final var result = productServiceImpl.updateProduct(product);

        verify(productRepository).save(any());

        assertEquals(product, result);
    }

    @Test
    void deleteProductByIdSuccessfully() {

        when(productRepository.findById(any())).thenReturn(Optional.of(product));

        productServiceImpl.deleteProductById("6521edebb9ae5f1b1d01d5a8");

        verify(productRepository).findById(any());
    }

    @Test
    void deleteProductByIdMistakenly() {

        when(productRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () ->  productServiceImpl.deleteProductById("6521edebb9ae5f1b1d01d5a8"));
    }
}
