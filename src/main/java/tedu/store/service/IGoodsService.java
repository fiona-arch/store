package tedu.store.service;

import tedu.store.entity.Goods;
import tedu.store.service.ex.GoodsNotFoundException;
import tedu.store.service.ex.UpdateException;

import java.util.List;

public interface IGoodsService {

    /**
     * 查询热销商品
     * @return
     */
    List<Goods> getHotList();

    /**
     * 查询新到好货
     * @return
     */
    List<Goods> getNewList();

    /**
     * 查询商品详情
     * @param id
     * @return
     */
    Goods getById(Long id);

    /**
     * 修改商品库存量
     * @param username 用户名
     * @param num 库存量
     * @throws UpdateException
     * @throws GoodsNotFoundException 商品不存在或已下架或删除
     */
    void updateGoodsNum(Long id,String username,Integer num)throws UpdateException, GoodsNotFoundException;

}
