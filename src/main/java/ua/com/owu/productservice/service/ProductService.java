package ua.com.owu.productservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import ua.com.owu.productservice.dto.CreateProductDto;
import ua.com.owu.productservice.dto.PatchProductDto;
import ua.com.owu.productservice.dto.ProductDto;
import ua.com.owu.productservice.dto.UpdateProductDto;
import ua.com.owu.productservice.mapper.ProductMapper;
import ua.com.owu.productservice.model.Product;
import ua.com.owu.productservice.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final UserService userService;

    public ProductDto createProduct(CreateProductDto createProductDto) {
        String shopId = createProductDto.shopId();

        if (!userService.getUserAssignedShopIds().contains(shopId)) {
            throw new ResourceAccessException("User is not assigned to this shop");
        }

        Product product = productMapper.toProduct(createProductDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.toProductDto(savedProduct);
    }

    public Optional<ProductDto> findProduct(String id) {
        return productRepository.findById(id).map(productMapper::toProductDto);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductDto> updateProduct(String id, UpdateProductDto updateProductDto) {
        return productRepository.findById(id)
                .map(product -> {
                    if (!userService.getUserAssignedShopIds().contains(product.getShopId())) {
                        throw new ResourceAccessException("User is not assigned to this shop");
                    }

                    productMapper.updateProduct(product, updateProductDto);
                    return productRepository.save(product);
                })
                .map(productMapper::toProductDto);
    }

    public Optional<ProductDto> patchProduct(String id, PatchProductDto patchProductDto) {
        return productRepository.findById(id)
                .map(product -> {
                    if (!userService.getUserAssignedShopIds().contains(product.getShopId())) {
                        throw new ResourceAccessException("User is not assigned to this shop");
                    }

                    productMapper.patchProduct(product, patchProductDto);
                    return productRepository.save(product);
                })
                .map(productMapper::toProductDto);
    }

    public void deleteProduct(String id) {
        productRepository.findById(id)
                .ifPresent(product -> {
                    if (!userService.getUserAssignedShopIds().contains(product.getShopId())) {
                        throw new ResourceAccessException("User is not assigned to this shop");
                    }

                    productRepository.deleteById(product.getId());
                });
    }
}
