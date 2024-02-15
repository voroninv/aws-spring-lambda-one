package com.base;

import com.base.domain.Order;
import com.base.repositories.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SpringBootApplication
public class AwsSpringLambdaOneApplication {

    @Autowired
    private OrderDao orderDao;

    public static void main(String[] args) {
        SpringApplication.run(AwsSpringLambdaOneApplication.class, args);

    }

    @Bean
    public Supplier<List<Order>> orders() {
        return () -> orderDao.buildOrders();
    }

    @Bean
    public Function<String, List<Order>> findOrderByName() {
        return (input) -> orderDao.buildOrders().stream()
                .filter(order -> order.getName().equals(input))
                .collect(Collectors.toList());
    }
}
