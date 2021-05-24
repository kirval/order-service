package orderservice.service;

import orderservice.dto.CustomerDto;
import orderservice.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@Sql("CustomerServiceTest.sql")
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void addNewCustomerTest() {
        CustomerDto customerToSave = new CustomerDto()
                .setName("C1")
                .setCoordinateX(1)
                .setCoordinateY(1);
        CustomerDto addedCustomer = customerService.addNewCustomer(customerToSave);

        assertThat(addedCustomer.getId()).isNotNull();
        assertThat(addedCustomer.getName()).isEqualTo(customerToSave.getName());
        assertThat(addedCustomer.getCoordinateX()).isEqualTo(customerToSave.getCoordinateX());
        assertThat(addedCustomer.getCoordinateY()).isEqualTo(customerToSave.getCoordinateY());
    }

    @Test
    void findByIdTest() {
        Customer actualCustomer = customerService.findById(1L);

        assertThrows(IllegalArgumentException.class, () -> customerService.findById(2L));
        assertThat(actualCustomer.getId()).isEqualTo(1L);
    }

}