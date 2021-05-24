package orderservice.mapper;

import orderservice.dto.OrderDtoIn;
import orderservice.dto.OrderDtoOut;
import orderservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

import static orderservice.mapper.OptionalMapper.UNWRAP_OPTIONAL;

@Mapper(uses = OptionalMapper.class)
public interface OrderMapper {

    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "customerId", source = "order.customer.id")
    @Mapping(target = "warehouseId", source = "order.warehouse.id")
    OrderDtoOut mapOrderToDto(Order order, Double distance);

    @Mapping(target = "id", source = "orderId", qualifiedByName = UNWRAP_OPTIONAL)
    @Mapping(target = "product.id", source = "orderDtoId.productId")
    @Mapping(target = "customer.id", source = "orderDtoId.customerId")
    @Mapping(target = "warehouse.id", source = "warehouseId")
    Order mapDtoToOrder(OrderDtoIn orderDtoId, Long warehouseId, Optional<Long> orderId);

}
