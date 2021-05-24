package orderservice.dto;

import lombok.Data;

@Data
public class OrderDtoOut {

    private Long orderId;
    private Long customerId;
    private Long warehouseId;
    private Double distance;

}