package orderservice.service;

import orderservice.dto.OrderDtoIn;
import orderservice.dto.OrderDtoOut;

import javax.validation.Valid;

public interface OrderService {

    OrderDtoOut placeOrder(@Valid OrderDtoIn orderDtoIn);

}
