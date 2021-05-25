package orderservice.service;

import orderservice.model.Warehouse;

import java.util.List;

public interface WarehouseService {

    public List<Warehouse> findWarehousesContainingProduct(Long productId);

}
