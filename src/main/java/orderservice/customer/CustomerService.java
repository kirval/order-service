package orderservice.customer;

import lombok.RequiredArgsConstructor;
import orderservice.common.exception.EntityManagementException;
import orderservice.common.exception.PersistenceException;
import orderservice.common.exception.QueryException;
import orderservice.common.validation.group.OnCreate;
import orderservice.customer.dto.CustomerDto;
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
    public CustomerDto addNewCustomer(@Valid CustomerDto newCustomerDto) throws EntityManagementException {
        try {
            return customerMapper.mapCustomerToDto(
                    persistCustomer(customerMapper.mapDtoToCustomer(newCustomerDto)));
        } catch (Exception e) {
            throw new EntityManagementException("Failed to add Customer. " + e.getMessage());
        }
    }

    public Customer findById(Long id) throws QueryException {
        return customerRepository.findById(id).orElseThrow(() ->
                new QueryException(String.format("Customer with id = {%s} is not found!", id))
        );
    }

    private Customer persistCustomer(Customer customer) throws PersistenceException {
        try {
            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new PersistenceException("Failed to save Customer. " + e.getMessage());
        }
    }

}
