package techtrovepom.orderservice.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @NotNull(message = "productId is required")
    private String productId;

    @NotNull(message = "quantity is required")
    private Long quantity;

}