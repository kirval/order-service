package orderservice.warehouse;

import lombok.RequiredArgsConstructor;
import orderservice.common.exception.QueryException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static orderservice.warehouse.WarehouseSpecifications.findByContainingProduct;

@Service
@Validated
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public List<Warehouse> findWarehousesContainingProduct(Long productId) throws QueryException {
        try {
            return warehouseRepository.findAll(findByContainingProduct(productId));
        } catch (Exception e) {
            throw new QueryException(
                    String.format("Failed find Warehouses with Product id = {%s} " + e.getMessage(), productId));
        }
    }

}
