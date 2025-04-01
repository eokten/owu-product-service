package ua.com.owu.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.productservice.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
