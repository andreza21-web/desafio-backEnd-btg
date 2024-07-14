package andreza.dev.btgpactual.orderms.service;

import andreza.dev.btgpactual.orderms.entity.OrderEntity;
import andreza.dev.btgpactual.orderms.entity.OrderItem;
import andreza.dev.btgpactual.orderms.listener.OrderCreatedListener;
import andreza.dev.btgpactual.orderms.listener.dto.OrderCreatedEvent;
import andreza.dev.btgpactual.orderms.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public void save(OrderCreatedEvent event){
        var entity = new OrderEntity();

        entity.setOrderId(event.codigoPedido());
        entity.setCustomerId(event.codigoCliente());

        entity.setItems(getOrderItems(event));
        entity.setTotal(getTotal(event));
        orderRepository.save(entity);
    }

    private BigDecimal getTotal(OrderCreatedEvent event) {
        return event.itens().stream().map(
                i -> i.preco().multiply(BigDecimal.valueOf(i.quantidade())))
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    private List<OrderItem> getOrderItems(OrderCreatedEvent event) {
        return event.itens().stream()
                .map(i -> new OrderItem(i.produto(), i.quantidade(), i.preco()))
                .toList();
    }
}
