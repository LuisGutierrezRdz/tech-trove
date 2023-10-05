package com.techtrove.productservice.infrastructure.repository;

import com.techtrove.productservice.domain.Product;
import com.techtrove.productservice.domain.respository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Primary
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductRepositoryImpl implements ProductRepository {

    private final MongoProductRepository repository;

    @Override
    public Optional<Product> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Product> findBySku(final String sku) {
        return repository.findBySku(sku);
    }

    @Override
    public Product save(final Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteById(final String id) {
        repository.deleteById(id);
    }

}
