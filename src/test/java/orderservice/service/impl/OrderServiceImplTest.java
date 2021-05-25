package orderservice.service.impl;

import orderservice.dto.OrderDtoIn;
import orderservice.dto.OrderDtoOut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Sql("OrderServiceImplTest.sql")
class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    void placeOrderTest() {
        final Long customerId = 1L;
        final Long warehouseId = 1L;
        OrderDtoIn orderDtoIn = new OrderDtoIn()
                .setCustomerId(customerId)
                .setProductId(warehouseId);
        OrderDtoOut placedOrder = orderService.placeOrder(orderDtoIn);

        assertThat(placedOrder).isNotNull();
        assertThat(placedOrder.getCustomerId()).isEqualTo(customerId);
        assertThat(placedOrder.getWarehouseId()).isEqualTo(warehouseId);
        assertThat(placedOrder.getDistance()).isEqualTo(0.0);
    }

}