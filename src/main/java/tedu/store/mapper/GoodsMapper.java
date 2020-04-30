package tedu.store.mapper;

import tedu.store.entity.Goods;

import java.util.List;

public interface GoodsMapper {

    /**
     * 获取热销产品列表
     * @return
     */
    List<Goods> findHotList();

    /**
     * 获取新到好货
     * @return
     */
    List<Goods> findNewList();

    /**
     * 查询商品详情
     * @param id
     * @return
     */
    Goods findById(Long id);
}
