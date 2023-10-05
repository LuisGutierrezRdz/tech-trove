package com.techtrove.productservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Product {

    @Id
    private String id;

    @Indexed
    private String sku;

    private String title;

    private String description;

    private String link;

    private Boolean availability;

    private ZonedDateTime availabilityDate;

    private ZonedDateTime expirationDate;

    private BigDecimal price;

}
