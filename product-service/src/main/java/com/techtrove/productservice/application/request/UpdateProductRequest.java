package com.techtrove.productservice.application.request;

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
public class UpdateProductRequest {

    private String sku;

    private String title;

    private String description;

    private String link;

    private Boolean availability;

    private ZonedDateTime availabilityDate;

    private ZonedDateTime expirationDate;

    private BigDecimal price;

}
