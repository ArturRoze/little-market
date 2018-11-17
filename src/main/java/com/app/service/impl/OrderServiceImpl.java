package com.app.service.impl;

import com.app.domain.OrderDto;
import com.app.domain.UserProductDto;
import com.app.model.OrderEntity;
import com.app.model.ProductEntity;
import com.app.model.UserEntity;
import com.app.repository.OrderRepository;
import com.app.repository.ProductRepository;
import com.app.repository.UserRepository;
import com.app.service.OrderService;
import com.app.utils.DateConverterUtil;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public boolean addOrder(OrderDto orderDto) {
        OrderEntity orderEntity = convertToOrderEntity(orderDto);
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
        if (title != null){
            orderEntityFromDb.setTitle(title);
        }
//        String creationDate = orderDto.getCreationDate();
//        if (creationDate != null){
//            Timestamp timestamp = DateConverterUtil.convertStringDateToTimestamp(creationDate);
//            orderEntityFromDb.setCreationDate(timestamp);
//        }

//        Long userId = orderDto.getUserId();
//        if (userId != null){
//
//        }

        List<UserProductDto> productsDto = orderDto.getProducts();
        if (!productsDto.isEmpty()){
            List<UserProductDto> products = orderDto.getProducts();
            List<String> uuids = products.stream().map(item -> item.getUuid()).collect(Collectors.toList());
            List<ProductEntity> productEntities = productRepository.readProductsByUuids(uuids);
            orderEntityFromDb.setProducts(productEntities);
        }
    }

    private OrderEntity convertToOrderEntity(OrderDto orderDto) {
        String title = orderDto.getTitle();
        Double totalPriceOrder = getTotalPriceOrder(orderDto);
        String creationDate = orderDto.getCreationDate();
        Timestamp timestampCreationDate = DateConverterUtil.convertStringDateToTimestamp(creationDate);
        List<UserProductDto> products = orderDto.getProducts();
        List<String> uuids = products.stream().map(item -> item.getUuid()).collect(Collectors.toList());
        List<ProductEntity> productEntities = productRepository.readProductsByUuids(uuids);
        Long userId = orderDto.getUserId();
        UserEntity userEntity = userRepository.readById(userId);
        return new OrderEntity(title, totalPriceOrder, timestampCreationDate, productEntities, userEntity);
    }

    private Double getTotalPriceOrder(OrderDto orderDto) {
        return orderDto.getProducts().stream().mapToDouble(item -> item.getPrice()).sum();
    }
}
