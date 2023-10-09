package techtrovepom.orderservice.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import techtrovepom.orderservice.domain.Order;

import java.util.Optional;

@Repository
public interface MongoOrderRepository extends MongoRepository<Order, String> {

    Optional<Order> findByReferenceId(String referenceId);

}
