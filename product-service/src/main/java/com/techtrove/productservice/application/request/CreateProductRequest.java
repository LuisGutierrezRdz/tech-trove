package com.techtrove.productservice.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotBlank(message = "sku is required")
    private String sku;

    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "description is required")
    private String description;

    private String link;

    @NotNull(message = "availability is required")
    private Boolean availability;

    private ZonedDateTime availabilityDate;

    @NotNull(message = "expirationDate is required")
    private ZonedDateTime expirationDate;

    @NotNull(message = "price is required")
    private BigDecimal price;

}
