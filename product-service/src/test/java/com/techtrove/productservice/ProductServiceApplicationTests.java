package com.techtrove.productservice;

import com.techtrove.productservice.domain.Product;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() throws JSONException {
		final var product = Product.builder()
				.id("6521edebb9ae5f1b1d01d5a8")
				.sku("1112123891")
				.title("title")
				.description("description")
				.availability(true)
				.expirationDate(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC")))
				.price(new BigDecimal("50300.0"))
				.build();

		Product responsePost = restTemplate.postForObject("/v1/products", product, Product.class);

		String responseGet = restTemplate.getForObject("/v1/products/{id}", String.class, responsePost.getId());
		JSONAssert.assertEquals("""
			{"sku":"1112123891"}
			""", responseGet, false);

		restTemplate.delete("/v1/products/{id}", responsePost.getId());

		ProductServiceApplication.main(new String[]{});
	}

}
