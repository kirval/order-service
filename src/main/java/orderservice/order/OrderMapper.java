package orderservice.order;

import orderservice.common.mapper.OptionalMapper;
import orderservice.order.dto.OrderDtoIn;
import orderservice.order.dto.OrderDtoOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

import static orderservice.common.mapper.OptionalMapper.UNWRAP_OPTIONAL;

@Mapper(uses = OptionalMapper.class)
interface OrderMapper {

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
