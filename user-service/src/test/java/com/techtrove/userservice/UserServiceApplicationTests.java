package com.techtrove.userservice;

import com.techtrove.userservice.domain.Role;
import com.techtrove.userservice.domain.User;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws JSONException {

        final var user = User.builder()
                .id("6523262fdf346f233146a543")
                .email("example@company.com")
                .firstName("FirstName")
                .lastName("LastName")
                .phone("+524758691256")
                .role(Role.USER)
                .build();

        User responsePost = restTemplate.postForObject("/v1/users", user, User.class);

        String responseGet = restTemplate.getForObject("/v1/users/{id}", String.class, responsePost.getId());
        JSONAssert.assertEquals("""
                {"email":"example@company.com"}
			""", responseGet, false);

        restTemplate.delete("/v1/users/{id}", responsePost.getId());

        UserServiceApplication.main(new String[]{});
    }

}
