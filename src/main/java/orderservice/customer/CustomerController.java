package orderservice.customer;

import lombok.RequiredArgsConstructor;
import orderservice.common.exception.EntityManagementException;
import orderservice.customer.dto.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> addNewCustomer(@Valid @RequestBody CustomerDto dto) throws EntityManagementException {
        CustomerDto addedCustomerDto = customerService.addNewCustomer(dto);
        return new ResponseEntity<>(addedCustomerDto, HttpStatus.CREATED);
    }

}
