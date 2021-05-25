package orderservice.service.impl;

import lombok.RequiredArgsConstructor;
import orderservice.model.Warehouse;
import orderservice.repository.WarehouseRepository;
import orderservice.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

import static orderservice.repository.WarehouseSpecifications.findByContainingProduct;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public List<Warehouse> findWarehousesContainingProduct(Long productId) {
        return warehouseRepository.findAll(findByContainingProduct(productId));
    }

}
