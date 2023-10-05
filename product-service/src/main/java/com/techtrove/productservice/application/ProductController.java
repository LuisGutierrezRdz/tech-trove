package com.techtrove.productservice.application;

import com.techtrove.productservice.application.converter.ProductConverter;
import com.techtrove.productservice.application.request.CreateProductRequest;
import com.techtrove.productservice.application.request.UpdateProductRequest;
import com.techtrove.productservice.application.response.ProductResponse;
import com.techtrove.productservice.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get product by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "404", description = "Product not found.")
    })
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable final String id) {

        final var product = productService.getProductById(id);

        return ProductConverter.INSTANCE.toResponse(product);
    }

    @Operation(summary = "Create product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid fields in request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "409", description = "Product already exist.")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@Valid @RequestBody final CreateProductRequest request) {

        final var product = productService.createProduct(ProductConverter.INSTANCE.toProduct(request));

        return ProductConverter.INSTANCE.toResponse(product);
    }

    @Operation(summary = "Update product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "404", description = "Product not found.")
    })

    @PutMapping(value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse updateProduct(@PathVariable final String id,
                                         @Valid @RequestBody final UpdateProductRequest request) {

        final var product = productService.getProductById(id);
        ProductConverter.INSTANCE.update(product, request);
        final var updatedProduct = productService.updateProduct(product);

        return ProductConverter.INSTANCE.toResponse(updatedProduct);
    }

    @Operation(summary = "Delete product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Product not found.")
    })
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable final String id) {

        productService.deleteProductById(id);
    }


}
