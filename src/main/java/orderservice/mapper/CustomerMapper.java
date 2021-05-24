package orderservice.mapper;

import orderservice.dto.CustomerDto;
import orderservice.model.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer mapDtoToCustomer(CustomerDto customerDto);

    CustomerDto mapCustomerToDto(Customer customer);

}
