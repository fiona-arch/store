package tedu.store.mapper;

import org.apache.ibatis.annotations.Param;
import tedu.store.entity.Cart;
import tedu.store.entity.vo.CartVO;

import java.util.Date;
import java.util.List;

public interface CartMapper {

    /**
     * 购物车增加新数据
     * @param cart
     * @return
     */
    Integer insert(Cart cart);


    /**
     * 查询购物车里是否已存在商品
     * @param uid 用户id
     * @param gid 商品id
     * @return null代表不存在
     */
    Cart findByUidAndGid(@Param("uid") Integer uid, @Param("gid")Long gid);

    /**
     * 根据购物车数据查询详情啊
     * @param cid 购物车id
     * @return 返回匹配的购物车数据
     */
    Cart findByCid(Integer cid);

    /**
     * 更新购物车中已存在的商品数量
     * @param cid 购物车商品id
     * @param num 购物车商品数量
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 1代表更改购物车商品数量成功  否则代表失败
     */
    Integer  updateNum(@Param("cid")Integer cid, @Param("num")Integer num,
                       @Param("modifiedUser")String modifiedUser, @Param("modifiedTime")Date modifiedTime);

    List<CartVO> findByUid(Integer uid);

    /**
     *
     * @param cid
     * @return
     */
    List<CartVO> findByCids(Integer[] cids);
}
