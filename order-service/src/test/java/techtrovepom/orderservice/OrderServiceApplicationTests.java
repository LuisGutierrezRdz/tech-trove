package techtrovepom.orderservice;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

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
