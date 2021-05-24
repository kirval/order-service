package orderservice.warehouse;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

class WarehouseSpecifications {

    public static Specification<Warehouse> findByContainingProduct(Long productId) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.join("products", JoinType.LEFT).get("id"), productId));
    }

}
