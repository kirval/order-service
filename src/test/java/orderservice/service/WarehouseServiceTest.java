package orderservice.service;

import orderservice.model.Warehouse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Sql("WarehousePersistenceAdapterTest.sql")
class WarehouseServiceTest {

    @Autowired
    private WarehouseService warehouseService;

    @Test
    void findWarehousesContainingProductTest() {
        List<Warehouse> warehouses = warehouseService.findWarehousesContainingProduct(1L);

        assertThat(warehouses).hasSize(1);
        assertThat(warehouses.get(0).getId()).isEqualTo(1L);
    }

}