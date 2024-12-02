package br.com.ambev.order_api.factory;

import br.com.ambev.order_api.model.Order;
import br.com.ambev.order_api.model.Product;
import br.com.ambev.order_api.model.ResumeOrderTotalPrice;
import br.com.ambev.order_api.model.dto.OrderDto;
import br.com.ambev.order_api.model.dto.ProductDto;
import br.com.ambev.order_api.model.enuns.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderFactory {

    public static OrderDto orderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setName("Pedido Rafael");
        orderDto.setProducts(productDtos());
        orderDto.setStatus(OrderStatus.PROCESSING);
        return orderDto;
    }

    public static Order order() {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID());
        order.setName("Pedido Rafael");
        order.setProducts(products());
        order.setStatus(OrderStatus.PROCESSING);
        return order;
    }

    public static List<ProductDto> productDtos() {
        ProductDto productDto1 = new ProductDto();
        productDto1.setName("Skol");
        productDto1.setAmount(20);
        productDto1.setPrice(5.50);

        ProductDto productDto2 = new ProductDto();
        productDto2.setName("Brahma");
        productDto2.setAmount(16);
        productDto2.setPrice(6.00);

        ProductDto productDto3 = new ProductDto();
        productDto3.setName("Guaraná Antarctica");
        productDto3.setAmount(10);
        productDto3.setPrice(4.50);

        ProductDto productDto4 = new ProductDto();
        productDto4.setName("H2OH");
        productDto4.setAmount(10);
        productDto4.setPrice(3.50);

        ProductDto productDto5 = new ProductDto();
        productDto5.setName("Corona");
        productDto5.setAmount(25);
        productDto5.setPrice(8.00);

        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(productDto1);
        productDtos.add(productDto2);
        productDtos.add(productDto3);
        productDtos.add(productDto4);
        productDtos.add(productDto5);
        return productDtos;
    }

    public static List<Product> products() {
        Product product1 = new Product();
        product1.setName("Skol");
        product1.setAmount(20);
        product1.setPrice(5.50);

        Product product2 = new Product();
        product2.setName("Brahma");
        product2.setAmount(16);
        product2.setPrice(6.00);

        Product product3 = new Product();
        product3.setName("Guaraná Antarctica");
        product3.setAmount(10);
        product3.setPrice(4.50);

        Product product4 = new Product();
        product4.setName("H2OH");
        product4.setAmount(10);
        product4.setPrice(3.50);

        Product product5 = new Product();
        product5.setName("Corona");
        product5.setAmount(25);
        product5.setPrice(8.00);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        return products;
    }

    public static ResumeOrderTotalPrice resumeOrderTotalPrice() {
        ResumeOrderTotalPrice resumeOrderTotalPrice = new ResumeOrderTotalPrice();
        resumeOrderTotalPrice.setOrder(order());
        resumeOrderTotalPrice.setTotalPrice(60.0);
        return resumeOrderTotalPrice;
    }
}
