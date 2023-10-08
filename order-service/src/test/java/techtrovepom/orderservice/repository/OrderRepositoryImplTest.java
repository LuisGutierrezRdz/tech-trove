package techtrovepom.orderservice.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import techtrovepom.orderservice.domain.Order;
import techtrovepom.orderservice.domain.OrderItem;
import techtrovepom.orderservice.domain.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderRepositoryImplTest {

    @InjectMocks
    private OrderRepositoryImpl orderRepositoryImpl;

    @Mock
    private MongoOrderRepository mongoOrderRepository;

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
    void findOrderById() {

        var orderOpt = Optional.of(order);

        when(mongoOrderRepository.findById(any())).thenReturn(orderOpt);

        final var result = orderRepositoryImpl.findById("6521edebb9ae5f1b1d01d5a8");

        verify(mongoOrderRepository).findById(any());

        assertEquals(orderOpt, result);
    }

    @Test
    void findOrderBySKU() {

        var orderOpt = Optional.of(order);

        when(mongoOrderRepository.findByReferenceId(any())).thenReturn(orderOpt);

        final var result = orderRepositoryImpl.findByReferenceId("123445");

        verify(mongoOrderRepository).findByReferenceId(any());

        assertEquals(orderOpt, result);
    }

    @Test
    void saveOrder() {

        when(mongoOrderRepository.save(any())).thenReturn(order);

        final var result = orderRepositoryImpl.save(order);

        verify(mongoOrderRepository).save(any());

        assertEquals(order, result);
    }

    @Test
    void deleteOrderById() {

        orderRepositoryImpl.deleteById("6521edebb9ae5f1b1d01d5a8");

        verify(mongoOrderRepository).deleteById(any());
    }
}