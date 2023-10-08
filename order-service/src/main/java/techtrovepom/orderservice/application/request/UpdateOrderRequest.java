package techtrovepom.orderservice.application.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import techtrovepom.orderservice.domain.OrderItem;
import techtrovepom.orderservice.domain.Payment;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderRequest {

    private String referenceId;

    private String userId;

    private ZonedDateTime date;

    private Payment payment;

    private List<OrderItem> products;

    private BigDecimal total;

}
