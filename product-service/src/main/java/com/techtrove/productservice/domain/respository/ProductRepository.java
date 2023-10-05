package com.techtrove.productservice.domain.respository;

import com.techtrove.productservice.domain.Product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(String id);

    Optional<Product> findBySku(String sku);

    Product save(Product product);

    void deleteById(String id);

}
