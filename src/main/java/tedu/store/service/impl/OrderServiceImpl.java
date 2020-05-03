package tedu.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tedu.store.entity.Address;
import tedu.store.entity.Order;
import tedu.store.entity.OrderItem;
import tedu.store.entity.vo.CartVO;
import tedu.store.mapper.OrderMapper;
import tedu.store.service.IAddressService;
import tedu.store.service.ICartService;
import tedu.store.service.IGoodsService;
import tedu.store.service.IOderService;
import tedu.store.service.ex.InsertException;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IGoodsService goodsService;
    @Override
    @Transactional
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) throws InsertException {
        //创建时间对象
        Date now=new Date();
        //insert order
        Order order=new Order();
        order.setUid(uid);
        Address address=addressService.getByAid(aid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        String provinceName=address.getProvinceName();
        String cityName=address.getCityName();
        String areaName=address.getAreaName();
        order.setRecvAddress(provinceName+cityName+areaName);
        List<CartVO> carts=cartService.getByCids(cids,uid);
        Long totalPrice=0l;
        for(CartVO cart:carts){
            Long price=cart.getPrice();
            Integer num=cart.getNum();
            totalPrice+=price*num;
        }
        order.setTotalPrice(totalPrice);
        order.setOrderTime(now);
        order.setPayTime(null);
        order.setState(0);
        order.setCreateUser(username);
        order.setCreateTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);
        insertOrder(order);
        //insert orderItem
        for(CartVO cart:carts){
            OrderItem orderItem=new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setGid(cart.getGid());
            orderItem.setTitle(cart.getTitle());
            orderItem.setImage(cart.getImage());
            orderItem.setPrice(cart.getPrice());
            orderItem.setNum(cart.getNum());
            orderItem.setCreateUser(username);
            orderItem.setCreateTime(now);
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(now);
            insertOrderItem(orderItem);
            //获取商品原数量
            //修改对应商品的库存量
            Integer goodsOldNum=goodsService.getById(cart.getGid()).getNum();
            goodsService.updateGoodsNum(cart.getGid(),username,goodsOldNum-cart.getNum());
        }
        //删除购物车商品
        cartService.deleteCart(uid,cids);
        return order;
    }

    private void insertOrder(Order order){
        Integer rows=orderMapper.insertOrder(order);
        if(rows!=1){
            throw new InsertException("创建订单失败!发生了未知错误");
        }
    }

    private void insertOrderItem(OrderItem orderItem){
        Integer rows=orderMapper.insertOrderItem(orderItem);
        if(rows!=1){
            throw new InsertException("创建订单失败!发生了未知错误");
        }
    }

}
