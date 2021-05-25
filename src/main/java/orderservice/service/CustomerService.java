package orderservice.service;

import orderservice.dto.CustomerDto;
import orderservice.model.Customer;
import orderservice.validation.group.OnCreate;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

public interface CustomerService {

    @Validated(OnCreate.class)
    CustomerDto addNewCustomer(@Valid CustomerDto newCustomerDto);

    Customer findById(Long id);

}
