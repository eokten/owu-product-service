package ua.com.owu.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.productservice.api.rest.model.ProductResponseDto;
import ua.com.owu.productservice.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByPriceGreaterThan(BigDecimal price);
}
