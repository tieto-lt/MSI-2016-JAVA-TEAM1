package lt.tieto.msi2016.order.service;

import lt.tieto.msi2016.order.model.OrderModel;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public OrderModel createOrder(OrderModel order){
        //Please insert me to DB
        return new OrderModel();
    }
}
