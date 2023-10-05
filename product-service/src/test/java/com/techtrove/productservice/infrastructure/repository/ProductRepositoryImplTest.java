package com.techtrove.productservice.infrastructure.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techtrove.productservice.domain.Product;
import com.techtrove.productservice.domain.respository.ProductRepository;
import com.techtrove.productservice.domain.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryImplTest {

    @InjectMocks
    private ProductRepositoryImpl productRepositoryImpl;

    @Mock
    private MongoProductRepository mongoProductRepository;

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
    void findProductById() {

        var productOpt = Optional.of(product);

        when(mongoProductRepository.findById(any())).thenReturn(productOpt);

        final var result = productRepositoryImpl.findById("6521edebb9ae5f1b1d01d5a8");

        verify(mongoProductRepository).findById(any());

        assertEquals(productOpt, result);
    }

    @Test
    void findProductBySKU() {

        var productOpt = Optional.of(product);

        when(mongoProductRepository.findBySku(any())).thenReturn(productOpt);

        final var result = productRepositoryImpl.findBySku("123445");

        verify(mongoProductRepository).findBySku(any());

        assertEquals(productOpt, result);
    }

    @Test
    void saveProduct() {

        when(mongoProductRepository.save(any())).thenReturn(product);

        final var result = productRepositoryImpl.save(product);

        verify(mongoProductRepository).save(any());

        assertEquals(product, result);
    }

    @Test
    void deleteProductById() {

        productRepositoryImpl.deleteById("6521edebb9ae5f1b1d01d5a8");

        verify(mongoProductRepository).deleteById(any());
    }
}