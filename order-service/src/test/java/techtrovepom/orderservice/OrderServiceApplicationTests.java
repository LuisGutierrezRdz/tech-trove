package techtrovepom.orderservice;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import techtrovepom.orderservice.domain.Order;
import techtrovepom.orderservice.domain.OrderItem;
import techtrovepom.orderservice.domain.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() throws JSONException {
		final var order = Order.builder()
				.id("6523262fdf346f233146a544")
				.referenceId("f0885721-fb2e-4c81-9a32-eaa4a9e40c1b")
				.date(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC")))
				.payment(Payment.CASH)
				.products(new ArrayList<>(Arrays.asList(
						OrderItem.builder().productId("6523262fdf346f233146a545").quantity(11L).build(),
						OrderItem.builder().productId("6523262fdf346f233146a546").quantity(1L).build()
				)))
				.total(new BigDecimal("23600.0"))
				.build();

		Order responsePost = restTemplate.postForObject("/v1/orders", order, Order.class);

		String responseGet = restTemplate.getForObject("/v1/orders/{id}", String.class, responsePost.getId());
		JSONAssert.assertEquals("""
			
				{"referenceId":"f0885721-fb2e-4c81-9a32-eaa4a9e40c1b"}
			""", responseGet, false);

		restTemplate.delete("/v1/orders/{id}", responsePost.getId());

		OrderServiceApplication.main(new String[]{});
	}

}
