package com.techtrove.productservice.domain.service;

import com.techtrove.productservice.domain.Product;

public interface ProductService {

    Product getProductById(String id);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProductById(String id);

}
