package com.techtrove.productservice.infrastructure.repository;

import com.techtrove.productservice.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findBySku(String sku);

}
