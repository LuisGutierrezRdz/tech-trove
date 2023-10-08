package techtrovepom.orderservice.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import techtrovepom.orderservice.domain.Order;
import techtrovepom.orderservice.domain.respository.OrderRepository;

import java.util.Optional;

@Component
@Primary
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderRepositoryImpl implements OrderRepository {

    private final MongoOrderRepository repository;

    @Override
    public Optional<Order> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Order> findByReferenceId(final String sku) {
        return repository.findByReferenceId(sku);
    }

    @Override
    public Order save(final Order order) {
        return repository.save(order);
    }

    @Override
    public void deleteById(final String id) {
        repository.deleteById(id);
    }

}
