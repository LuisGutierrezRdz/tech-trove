package techtrovepom.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Order {

    @Id
    private String id;

    @Indexed
    private String referenceId;

    @Indexed
    private String userId;

    private ZonedDateTime date;

    private Payment payment;

    private List<OrderItem> products;

    private BigDecimal total;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

}
