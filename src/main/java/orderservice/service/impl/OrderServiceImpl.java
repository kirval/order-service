package orderservice.service.impl;

import lombok.RequiredArgsConstructor;
import orderservice.dto.OrderDtoIn;
import orderservice.dto.OrderDtoOut;
import orderservice.mapper.OrderMapper;
import orderservice.model.Customer;
import orderservice.model.Order;
import orderservice.model.Warehouse;
import orderservice.repository.OrderRepository;
import orderservice.service.CustomerService;
import orderservice.service.OrderService;
import orderservice.service.WarehouseService;
import org.javatuples.Pair;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final WarehouseService warehouseService;
    private final CustomerService customerService;

    public OrderDtoOut placeOrder(@Valid OrderDtoIn orderDtoIn) {
        Customer customer = customerService.findById(orderDtoIn.getCustomerId());
        List<Warehouse> warehouses = warehouseService.findWarehousesContainingProduct(orderDtoIn.getProductId());
        Pair<Warehouse, Double> closestWarehouseWithDistance = getClosestWarehouseToCoordinates(warehouses,
                customer.getCoordinateX(), customer.getCoordinateY());
        Order persistedOrder = persistOrder(orderMapper.mapDtoToOrder(orderDtoIn,
                closestWarehouseWithDistance.getValue0().getId(), Optional.empty()));
        return orderMapper.mapOrderToDto(persistedOrder, closestWarehouseWithDistance.getValue1());
    }

    private Order persistOrder(Order order) {
        return orderRepository.save(order);
    }

    private Pair<Warehouse, Double> getClosestWarehouseToCoordinates(List<Warehouse> warehouses,
                                                                     int coordinateX, int coordinateY) {
        if (warehouses == null || warehouses.size() == 0) {
            throw new IllegalStateException("No Warehouses with requested Product in stock.");
        }
        Warehouse closestWarehouse = null;
        Double closestDistance = null;
        for (Warehouse warehouse : warehouses) {
            double distance = calculateDistanceWithPythagorasTheorem(
                    warehouse.getCoordinateX(), warehouse.getCoordinateY(), coordinateX, coordinateY);
            if (closestDistance == null) {
                closestDistance = distance;
                closestWarehouse = warehouse;
            } else if (distance < closestDistance) {
                closestDistance = distance;
                closestWarehouse = warehouse;
            }
        }
        return new Pair<>(closestWarehouse, closestDistance);
    }

    private double calculateDistanceWithPythagorasTheorem(int x1, int y1, int x2, int y2) {
        int lengthX = Math.abs(x2 - x1);
        int lengthY = Math.abs(y2 - y1);
        return Math.hypot(lengthX, lengthY);
    }

}