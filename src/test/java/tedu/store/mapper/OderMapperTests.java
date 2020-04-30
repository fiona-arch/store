package tedu.store.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tedu.store.entity.Order;
import tedu.store.entity.OrderItem;
import tedu.store.service.IOderService;
import tedu.store.service.ex.InsertException;

@SpringBootTest
public class OderMapperTests {
    @Autowired
    OrderMapper mapper;
    @Autowired
    IOderService orderService;

    @Test
    public void testInsertOrder(){
        Order order=new Order();
        order.setUid(8);
        order.setRecvName("林杏璇");
        order.setRecvPhone("13131313131");
        order.setState(0);
        Integer rows=mapper.insertOrder(order);
        System.err.println(rows);
    }

    @Test
    public void testInsertOrderItem(){
        OrderItem orderItem=new OrderItem();
        orderItem.setOid(1);
        orderItem.setGid(1000l);
        orderItem.setTitle("Mac");
        Integer rows=mapper.insertOrderItem(orderItem);
        System.err.println(rows);
    }
    @Test
    public void testCreate(){
        try {
            Integer aid=13;
            Integer []cids={8,9};
            Integer uid=19;
            orderService.create(aid,cids,uid,"超级管理员");
            System.err.println("OK");
        } catch (InsertException e) {
            System.err.println(e.getMessage());
        }
    }
}
