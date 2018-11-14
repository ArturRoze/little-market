package com.app.service.serviceImpl;

import com.app.model.OrderEntity;
import com.app.model.UserEntity;
import com.app.repository.OrderRepository;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.readById(id);
    }

    @Override
    public List<OrderEntity> getAllUserOrders(Long id) {
        return orderRepository.getAllOrdersByUserId(id);
    }
}
