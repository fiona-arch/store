package tedu.store.mapper;

import tedu.store.entity.Order;
import tedu.store.entity.OrderItem;

public interface OrderMapper {
    /**
     * 插入新订单
     * @param order
     * @return 1代表插入成功 否则代表插入失败
     */
    Integer insertOrder(Order order);

    /**
     *插入新的订单商品数据
     * @param orderItem 订单商品
     * @return 1代表插入成功 否则代表插入失败
     */
    Integer  insertOrderItem(OrderItem orderItem);

}
