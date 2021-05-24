package orderservice.service;

import lombok.RequiredArgsConstructor;
import orderservice.dto.CustomerDto;
import orderservice.mapper.CustomerMapper;
import orderservice.model.Customer;
import orderservice.repository.CustomerRepository;
import orderservice.validation.group.OnCreate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Validated(OnCreate.class)
    public CustomerDto addNewCustomer(@Valid CustomerDto newCustomerDto) {
        Customer addedCustomer = customerRepository.save(customerMapper.mapDtoToCustomer(newCustomerDto));
        return customerMapper.mapCustomerToDto(addedCustomer);
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(String.format("Customer with id = {%s} is not found!", id)));
    }

}
