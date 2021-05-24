package orderservice.repository;

import orderservice.model.Warehouse;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

public class WarehouseSpecifications {

    public static Specification<Warehouse> findByContainingProduct(Long productId) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.join("products", JoinType.LEFT).get("id"), productId));
    }

}
