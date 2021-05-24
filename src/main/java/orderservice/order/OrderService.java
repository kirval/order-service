package orderservice.order;

import lombok.RequiredArgsConstructor;
import orderservice.common.exception.EntityManagementException;
import orderservice.common.exception.PersistenceException;
import orderservice.customer.Customer;
import orderservice.customer.CustomerService;
import orderservice.order.dto.OrderDtoIn;
import orderservice.order.dto.OrderDtoOut;
import orderservice.warehouse.Warehouse;
import orderservice.warehouse.WarehouseService;
import org.javatuples.Pair;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final WarehouseService warehouseService;
    private final CustomerService customerService;

    public OrderDtoOut placeOrder(@Valid OrderDtoIn orderDtoIn) throws EntityManagementException {
        try {
            Customer customer = customerService.findById(orderDtoIn.getCustomerId());
            List<Warehouse> warehouses = warehouseService.findWarehousesContainingProduct(orderDtoIn.getProductId());
            Pair<Warehouse, Double> closestWarehouseWithDistance = getClosestWarehouseToCoordinates(warehouses,
                    customer.getCoordinateX(), customer.getCoordinateY());
            Order persistedOrder = persistOrder(orderMapper.mapDtoToOrder(orderDtoIn,
                    closestWarehouseWithDistance.getValue0().getId(), Optional.empty()));

            return orderMapper.mapOrderToDto(persistedOrder, closestWarehouseWithDistance.getValue1());
        } catch (Exception e) {
            throw new EntityManagementException("Failed to add Order. " + e.getMessage());
        }
    }

    private Order persistOrder(Order order) throws PersistenceException {
        try {
            return orderRepository.saveAndFlush(order);
        } catch (Exception e) {
            throw new PersistenceException("Failed to save Order. " + e.getMessage());
        }
    }

    private Pair<Warehouse, Double> getClosestWarehouseToCoordinates(List<Warehouse> warehouses,
                                                                     int coordinateX,
                                                                     int coordinateY) throws Exception {
        if (warehouses == null || warehouses.size() == 0) {
            throw new Exception("No Warehouses with requested Product in stock.");
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