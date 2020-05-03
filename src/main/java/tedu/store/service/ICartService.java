package tedu.store.service;

import tedu.store.entity.Cart;
import tedu.store.entity.vo.CartVO;
import tedu.store.service.ex.*;

import java.util.List;

public interface ICartService {

    /**
     * 将商品添加到购物车
     * @param cart 购物车数据
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @throws InsertException 插入数据异常
     * @throws UpdateException 更新数据异常
     */
    void addToCart(Integer uid, String username, Cart cart) throws InsertException, UpdateException;

    /**
     * 删除购物车中的商品数据
     * @param uid 用户id
     * @param cids 购物车id
     * @throws DeleteException 删除异常
     * @throws AccessDeniedException 拒绝访问异常
     * @throws CartNotFoundException 购物车商品不存在异常
     */
    void deleteCart(Integer uid,Integer []cids)throws DeleteException,AccessDeniedException,CartNotFoundException;



    /**
     * 购物车商品数量增加
     * @param uid 用户id
     * @param username 用户名
     * @param cid 购物车商品id
     * @return 修改后的购物车的商品数量
     * @throws CartNotFoundException 购物车商品数据不存在
     * @throws AccessDeniedException 访问他人数据
     * @throws UpdateException 更新异常
     */
    Integer add(Integer uid,String username,Integer cid)throws CartNotFoundException, AccessDeniedException,

                                                            UpdateException;

    /**
     * 购物车商品数量减少
     * @param uid
     * @param username
     * @param cid
     * @return 修改后的购物车商品数量
     * @throws CartNotFoundException
     * @throws AccessDeniedException
     * @throws UpdateException
     */
    Integer reduce(Integer uid,String username,Integer cid)throws CartNotFoundException,AccessDeniedException,
                                                            UpdateException;

    /**
     * 根据数据id获取购物车数据id列表
     * @param cids
     * @param uid
     * @return 匹配的购物车数据列表
     */
    List<CartVO> getByCids(Integer []cids,Integer uid);

    /**
     * 获取某用户的购物车数据列表
     * @param uid 用户的id
     * @return 该用户的购物车数据列表
     */
    List<CartVO> getByUid(Integer uid);


}
