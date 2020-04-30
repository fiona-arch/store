package tedu.store.service;

import tedu.store.entity.Goods;

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


}
