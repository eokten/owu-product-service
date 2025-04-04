package ua.com.owu.productservice.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ua.com.owu.productservice.api.rest.model.CreateProductRequestDto;
import ua.com.owu.productservice.api.rest.model.PatchProductRequestDto;
import ua.com.owu.productservice.api.rest.model.ProductResponseDto;
import ua.com.owu.productservice.api.rest.model.UpdateProductRequestDto;
import ua.com.owu.productservice.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(CreateProductRequestDto createProductDto);

    ProductResponseDto toProductDto(Product product);

    void updateProduct(@MappingTarget Product product, UpdateProductRequestDto updateProductDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchProduct(@MappingTarget Product product, PatchProductRequestDto patchProductDto);
}
