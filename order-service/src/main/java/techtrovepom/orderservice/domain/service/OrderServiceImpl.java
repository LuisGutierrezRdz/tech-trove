package techtrovepom.orderservice.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import techtrovepom.orderservice.domain.Order;
import techtrovepom.orderservice.domain.respository.OrderRepository;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Found"));
    }

    @Override
    public Order createOrder(Order order) {
        orderRepository.findByReferenceId(order.getReferenceId())
                .ifPresent(prod -> {throw new ResponseStatusException(HttpStatus.CONFLICT, "Order Already Exist"); });

        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {

         return orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(String id) {
        var order = orderRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "order Not Found"));

        orderRepository.deleteById(order.getId());
    }
}
