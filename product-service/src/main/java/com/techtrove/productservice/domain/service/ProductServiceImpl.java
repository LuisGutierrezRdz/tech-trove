package com.techtrove.productservice.domain.service;

import com.techtrove.productservice.domain.Product;
import com.techtrove.productservice.domain.respository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));
    }

    @Override
    public Product createProduct(Product product) {
        productRepository.findBySku(product.getSku())
                .ifPresent(prod -> {throw new ResponseStatusException(HttpStatus.CONFLICT, "Product Already Exist"); });

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {

         return productRepository.save(product);
    }

    @Override
    public void deleteProductById(String id) {
        var product = productRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));

        productRepository.deleteById(product.getId());
    }
}
