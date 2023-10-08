package techtrovepom.orderservice.application.converter;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import techtrovepom.orderservice.application.request.CreateOrderRequest;
import techtrovepom.orderservice.application.request.UpdateOrderRequest;
import techtrovepom.orderservice.application.response.OrderResponse;
import techtrovepom.orderservice.domain.Order;

import java.time.ZonedDateTime;

@Mapper(imports = {ZonedDateTime.class})
public interface OrderConverter {

    OrderConverter INSTANCE = Mappers.getMapper( OrderConverter.class );

    @Mapping(target = "createdAt", expression = "java(ZonedDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(ZonedDateTime.now())")
    Order toOrder(CreateOrderRequest request);

    OrderResponse toResponse(Order order);

    void update(@MappingTarget Order order, UpdateOrderRequest updateOrderRequest);

}
