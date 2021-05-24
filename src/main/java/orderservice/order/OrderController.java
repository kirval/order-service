package orderservice.order;

import lombok.RequiredArgsConstructor;
import orderservice.common.exception.EntityManagementException;
import orderservice.order.dto.OrderDtoIn;
import orderservice.order.dto.OrderDtoOut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDtoOut> placeOrder(@Valid @RequestBody OrderDtoIn dto) throws EntityManagementException {
        OrderDtoOut placedOrder = orderService.placeOrder(dto);
        return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
    }

}
