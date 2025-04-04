package ua.com.owu.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import ua.com.owu.productservice.api.rest.controller.ProductApi;
import ua.com.owu.productservice.api.rest.model.CreateProductRequestDto;
import ua.com.owu.productservice.api.rest.model.PatchProductRequestDto;
import ua.com.owu.productservice.api.rest.model.ProductResponseDto;
import ua.com.owu.productservice.api.rest.model.UpdateProductRequestDto;
import ua.com.owu.productservice.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;

    @PreAuthorize("hasRole('SHOP_MANAGER')")
    @Override
    public ResponseEntity<ProductResponseDto> createProduct(CreateProductRequestDto createProductRequestDto) {
        return ResponseEntity.ok(productService.createProduct(createProductRequestDto));
    }

    @Override
    public ResponseEntity<ProductResponseDto> deleteProduct(String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProductResponseDto> getProduct(String productId) {
        return ResponseEntity.of(productService.findProduct(productId));
    }

    @Override
    public ResponseEntity<List<ProductResponseDto>> getProducts(BigDecimal minPrice) {
        if (minPrice == null) {
            return ResponseEntity.ok(productService.findAllProducts());
        } else {
            return ResponseEntity.ok(productService.findAllProductsWithPriceGreaterThan(minPrice));
        }
    }

    @Override
    public ResponseEntity<ProductResponseDto> patchProduct(String productId, PatchProductRequestDto patchProductRequestDto) {
        return ResponseEntity.of(productService.patchProduct(productId, patchProductRequestDto));
    }

    @Override
    public ResponseEntity<ProductResponseDto> updateProduct(String productId, UpdateProductRequestDto updateProductRequestDto) {
        return ResponseEntity.of(productService.updateProduct(productId, updateProductRequestDto));
    }
}
