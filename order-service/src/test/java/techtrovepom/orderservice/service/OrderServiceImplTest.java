package techtrovepom.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import techtrovepom.orderservice.domain.Order;
import techtrovepom.orderservice.domain.OrderItem;
import techtrovepom.orderservice.domain.Payment;
import techtrovepom.orderservice.domain.respository.OrderRepository;
import techtrovepom.orderservice.domain.service.OrderServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @Mock
    private OrderRepository orderRepository;

    private Order order;

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
    }

    @Test
    void getOrderByIdSuccessfully() {

        when(orderRepository.findById(any())).thenReturn(Optional.of(order));

        final var result = orderServiceImpl.getOrderById("6521edebb9ae5f1b1d01d5a8");

        verify(orderRepository).findById(any());

        assertEquals(order, result);
    }

    @Test
    void getOrderByIdMistakenly() {

        when(orderRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () ->  orderServiceImpl.getOrderById("6521edebb9ae5f1b1d01d5a8"));
    }

    @Test
    void createOrderSuccessfully() {

        when(orderRepository.save(any())).thenReturn(order);

        final var result = orderServiceImpl.createOrder(order);

        verify(orderRepository).save(any());

        assertEquals(order, result);
    }

    @Test
    void createOrderMistakenly() {

        when(orderRepository.findByReferenceId(any())).thenReturn(Optional.of(order));

        assertThrows(ResponseStatusException.class,
                () ->  orderServiceImpl.createOrder(order));
    }

    @Test
    void updateOrderSuccessfully() {

        when(orderRepository.save(any())).thenReturn(order);

        final var result = orderServiceImpl.updateOrder(order);

        verify(orderRepository).save(any());

        assertEquals(order, result);
    }

    @Test
    void deleteOrderByIdSuccessfully() {

        when(orderRepository.findById(any())).thenReturn(Optional.of(order));

        orderServiceImpl.deleteOrderById("6521edebb9ae5f1b1d01d5a8");

        verify(orderRepository).findById(any());
    }

    @Test
    void deleteOrderByIdMistakenly() {

        when(orderRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () ->  orderServiceImpl.deleteOrderById("6521edebb9ae5f1b1d01d5a8"));
    }
}
