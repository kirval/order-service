package orderservice.customer;

import orderservice.common.exception.EntityManagementException;
import orderservice.common.exception.QueryException;
import orderservice.customer.dto.CustomerDto;
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
    void addNewCustomerTest() throws EntityManagementException {
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
    void findByIdTest() throws QueryException {
        Customer actualCustomer = customerService.findById(1L);

        assertThrows(QueryException.class, () -> customerService.findById(2L));
        assertThat(actualCustomer.getId()).isEqualTo(1L);
    }

}