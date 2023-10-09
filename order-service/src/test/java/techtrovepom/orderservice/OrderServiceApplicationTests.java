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

		String responseGet = restTemplate
				.withBasicAuth("user", "passwd")
				.getForObject("/v1/orders/{id}", String.class, "responsePost.getId()");
		JSONAssert.assertEquals(null, responseGet, false);

		OrderServiceApplication.main(new String[]{});
	}

}
