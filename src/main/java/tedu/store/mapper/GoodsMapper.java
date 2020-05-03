package tedu.store.mapper;

import org.apache.ibatis.annotations.Param;
import tedu.store.entity.Goods;

import java.util.Date;
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

    /**
     * 修改商品的库存量
     * @param id 商品Id
     * @return 受影响的行数
     */
    Integer updateNum(@Param("id")Long id,@Param("num")Integer num,
                      @Param("modifiedUser")String modifiedUser,@Param("modifiedTime")Date modifiedTime);
}
