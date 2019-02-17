package com.app.service.impl;

import com.app.model.ConverterToEntity;
import com.app.model.income.OrderDto;
import com.app.model.income.UserProductDto;
import com.app.model.entity.OrderEntity;
import com.app.model.entity.ProductEntity;
import com.app.repository.OrderRepository;
import com.app.repository.ProductRepository;
import com.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ConverterToEntity converterToEntity;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, ConverterToEntity converterToEntity) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.converterToEntity = converterToEntity;
    }

    @Override
    public boolean addOrder(OrderDto orderDto) {
        OrderEntity orderEntity = converterToEntity.convertToOrderEntity(orderDto);
        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
        return savedOrderEntity.getId() != null;
    }

    @Override
    public List<OrderEntity> getAllUserOrders(Long id) {
        return orderRepository.getAllOrdersByUserId(id);
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.readAll();
    }

    @Override
    public OrderEntity getOrderById(Long id) {
        return orderRepository.readById(id);
    }

    @Override
    public OrderEntity updateOrder(OrderDto orderDto, Long id) {
        OrderEntity orderEntityFromDb = orderRepository.readById(id);
        changeFieldsOrderDtoToOrderEntity(orderDto, orderEntityFromDb);
        return orderRepository.update(orderEntityFromDb);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    private void changeFieldsOrderDtoToOrderEntity(OrderDto orderDto, OrderEntity orderEntityFromDb) {
        String title = orderDto.getTitle();
        if (title != null) {
            orderEntityFromDb.setTitle(title);
        }
        List<UserProductDto> productsDto = orderDto.getProducts();
        if (!productsDto.isEmpty()) {
            List<UserProductDto> products = orderDto.getProducts();
            List<String> uuids = products.stream().map(item -> item.getUuid()).collect(Collectors.toList());
            List<ProductEntity> productEntities = productRepository.readProductsByUuids(uuids);
            orderEntityFromDb.setProducts(productEntities);
        }
    }
}
