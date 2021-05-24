package orderservice.customer;

import orderservice.customer.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
interface CustomerMapper {

    Customer mapDtoToCustomer(CustomerDto customerDto);

    CustomerDto mapCustomerToDto(Customer customer);

}
