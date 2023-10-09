package techtrovepom.orderservice.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import techtrovepom.orderservice.application.converter.OrderConverter;
import techtrovepom.orderservice.domain.Order;
import techtrovepom.orderservice.domain.OrderItem;
import techtrovepom.orderservice.domain.Payment;
import techtrovepom.orderservice.domain.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(OrderController.class)
@ContextConfiguration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class OrderControllerTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    private Order order;
    private String expectedResponse;

    @BeforeEach
    public void init() throws JsonProcessingException {
        order = Order.builder()
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

        expectedResponse = objectMapper.writeValueAsString(OrderConverter.INSTANCE.toResponse(order));
    }

    @Test
    @WithMockUser
    void getOrderById() throws Exception {

        when(orderService.getOrderById(any())).thenReturn(order);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/v1/orders/{id}", "6521edebb9ae5f1b1d01d5a8")
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    @WithMockUser
    void createOrder() throws Exception {

        when(orderService.createOrder(any())).thenReturn(order);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/v1/orders")
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());

        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    @WithMockUser
    void updateOrder() throws Exception {

        when(orderService.getOrderById(any())).thenReturn(order);
        when(orderService.updateOrder(any())).thenReturn(order);

        RequestBuilder request = MockMvcRequestBuilders
                .put("/v1/orders/{id}", "6521edebb9ae5f1b1d01d5a8")
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    @WithMockUser
    void deleteOrderById() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .delete("/v1/orders/{id}", "6521edebb9ae5f1b1d01d5a8")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
    }

}
