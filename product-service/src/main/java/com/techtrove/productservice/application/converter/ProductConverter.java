package com.techtrove.productservice.application.converter;

import com.techtrove.productservice.application.request.CreateProductRequest;
import com.techtrove.productservice.application.request.UpdateProductRequest;
import com.techtrove.productservice.application.response.ProductResponse;
import com.techtrove.productservice.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductConverter {

    ProductConverter INSTANCE = Mappers.getMapper( ProductConverter.class );

    Product toProduct(CreateProductRequest request);

    ProductResponse toResponse(Product product);

    void update(@MappingTarget Product product, UpdateProductRequest updateProductRequest);

}
