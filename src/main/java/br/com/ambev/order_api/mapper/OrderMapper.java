package br.com.ambev.order_api.mapper;

import br.com.ambev.order_api.model.Order;
import br.com.ambev.order_api.model.Product;
import br.com.ambev.order_api.model.dto.OrderDto;
import br.com.ambev.order_api.model.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "products", target = "products")
    @Mapping(source = "status", target = "status")
    Order toModel(OrderDto orderDto);

    @Mapping(source = "amount", target = "amount")
    Product toModel(ProductDto productDto);

    @Mapping(source = "amount", target = "amount")
    ProductDto toDto(Product product);

    List<Product> toModelProductList(List<ProductDto> productDtos);
}

