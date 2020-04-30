package tedu.store.service;

import tedu.store.entity.Order;
import tedu.store.service.ex.InsertException;

public interface IOderService {
    /**
     * 插入新的订单数据
     * @param aid 地址id
     * @param cids 购物车id
     * @param uid 用户id
     * @param username 用户名
     * @return 订单数据
     * @throws InsertException 插入异常
     */
    Order create(Integer aid,Integer[]cids,Integer uid,String username) throws InsertException;

}
