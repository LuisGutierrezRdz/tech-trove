package techtrovepom.orderservice.application;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import techtrovepom.orderservice.application.converter.OrderConverter;
import techtrovepom.orderservice.application.request.CreateOrderRequest;
import techtrovepom.orderservice.application.request.UpdateOrderRequest;
import techtrovepom.orderservice.application.response.OrderResponse;
import techtrovepom.orderservice.domain.service.OrderService;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Get order by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "404", description = "Order not found.")
    })
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrderById(@PathVariable final String id) {

        final var order = orderService.getOrderById(id);

        return OrderConverter.INSTANCE.toResponse(order);
    }

    @Operation(summary = "Create order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "order created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid fields in request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "409", description = "Order already exist.")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@Valid @RequestBody final CreateOrderRequest request) {

        final var order = orderService.createOrder(OrderConverter.INSTANCE.toOrder(request));

        return OrderConverter.INSTANCE.toResponse(order);
    }

    @Operation(summary = "Update order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "404", description = "Order not found.")
    })
    @PutMapping(value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse updateOrder(@PathVariable final String id,
                                       @Valid @RequestBody final UpdateOrderRequest request) {

        final var order = orderService.getOrderById(id);
        order.setUpdatedAt(ZonedDateTime.now());
        OrderConverter.INSTANCE.update(order, request);
        final var updatedOrder = orderService.updateOrder(order);

        return OrderConverter.INSTANCE.toResponse(updatedOrder);
    }

    @Operation(summary = "Delete order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Order deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Order not found.")
    })
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderById(@PathVariable final String id) {

        orderService.deleteOrderById(id);
    }


}
