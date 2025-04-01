package ua.com.owu.productservice.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ua.com.owu.productservice.dto.CreateProductDto;
import ua.com.owu.productservice.dto.PatchProductDto;
import ua.com.owu.productservice.dto.ProductDto;
import ua.com.owu.productservice.dto.UpdateProductDto;
import ua.com.owu.productservice.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(CreateProductDto createProductDto);

    ProductDto toProductDto(Product product);

    void updateProduct(@MappingTarget Product product, UpdateProductDto updateProductDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchProduct(@MappingTarget Product product, PatchProductDto patchProductDto);
}
