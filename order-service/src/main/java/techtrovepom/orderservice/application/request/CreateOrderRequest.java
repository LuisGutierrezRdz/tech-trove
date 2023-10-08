package techtrovepom.orderservice.application.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import techtrovepom.orderservice.domain.OrderItem;
import techtrovepom.orderservice.domain.Payment;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @NotBlank(message = "referenceId is required")
    private String referenceId;

    private String userId;

    @NotNull(message = "date is required")
    private ZonedDateTime date;

    @NotNull(message = "payment is required")
    private Payment payment;

    @NotNull(message = "products is required")
    @Size(min = 1, message = "products must have at least one")
    @Valid
    private List<OrderItem> products;

    @NotNull(message = "total is required")
    private BigDecimal total;

}
