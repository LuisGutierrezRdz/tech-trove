package com.techtrove.productservice.application.converter;

import com.techtrove.productservice.application.request.CreateProductRequest;
import com.techtrove.productservice.application.request.UpdateProductRequest;
import com.techtrove.productservice.application.response.ProductResponse;
import com.techtrove.productservice.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;

@Mapper(imports = {ZonedDateTime.class})
public interface ProductConverter {

    ProductConverter INSTANCE = Mappers.getMapper( ProductConverter.class );

    @Mapping(target = "createdAt", expression = "java(ZonedDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(ZonedDateTime.now())")
    Product toProduct(CreateProductRequest request);

    ProductResponse toResponse(Product product);

    void update(@MappingTarget Product product, UpdateProductRequest updateProductRequest);

}
