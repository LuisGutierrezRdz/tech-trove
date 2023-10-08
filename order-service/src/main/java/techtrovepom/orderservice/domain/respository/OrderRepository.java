package techtrovepom.orderservice.domain.respository;


import techtrovepom.orderservice.domain.Order;

import java.util.Optional;

public interface OrderRepository {

    Optional<Order> findById(String id);

    Optional<Order> findByReferenceId(String sku);

    Order save(Order order);

    void deleteById(String id);

}
