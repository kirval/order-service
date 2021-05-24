package orderservice.service;

import lombok.RequiredArgsConstructor;
import orderservice.model.Warehouse;
import orderservice.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static orderservice.repository.WarehouseSpecifications.findByContainingProduct;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public List<Warehouse> findWarehousesContainingProduct(Long productId) {
        return warehouseRepository.findAll(findByContainingProduct(productId));
    }

}
