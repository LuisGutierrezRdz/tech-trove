package com.techtrove.productservice.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techtrove.productservice.application.converter.ProductConverter;
import com.techtrove.productservice.domain.Product;
import com.techtrove.productservice.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(ProductController.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class ProductControllerTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    private Product product;
    private String expectedResponse;

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

        expectedResponse = objectMapper.writeValueAsString(ProductConverter.INSTANCE.toResponse(product));
    }

    @Test
    @WithMockUser
    void getProductById() throws Exception {

        when(productService.getProductById(any())).thenReturn(product);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/v1/products/{id}", "6521edebb9ae5f1b1d01d5a8")
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    @WithMockUser
    void createProduct() throws Exception {

        when(productService.createProduct(any())).thenReturn(product);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/v1/products")
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());

        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    @WithMockUser
    void updateProduct() throws Exception {

        when(productService.getProductById(any())).thenReturn(product);
        when(productService.updateProduct(any())).thenReturn(product);

        RequestBuilder request = MockMvcRequestBuilders
                .put("/v1/products/{id}", "6521edebb9ae5f1b1d01d5a8")
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    @WithMockUser
    void deleteProductById() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .delete("/v1/products/{id}", "6521edebb9ae5f1b1d01d5a8")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
    }

}
