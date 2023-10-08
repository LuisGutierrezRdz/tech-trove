package techtrovepom.orderservice.domain.service;


import techtrovepom.orderservice.domain.Order;

public interface OrderService {

    Order getOrderById(String id);

    Order createOrder(Order order);

    Order updateOrder(Order order);

    void deleteOrderById(String id);

}
