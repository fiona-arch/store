package tedu.store.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tedu.store.entity.Goods;
import tedu.store.mapper.GoodsMapper;
import tedu.store.service.IGoodsService;
import tedu.store.service.ex.GoodsNotFoundException;
import tedu.store.service.ex.UpdateException;

import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private GoodsMapper mapper;

    @Override
    public void updateGoodsNum(Long id, String username, Integer num) throws UpdateException, GoodsNotFoundException {
        //根据id查找商品
        Goods goods=mapper.findById(id);
        //商品不存在抛出异常
        if(goods==null){
            throw new GoodsNotFoundException("修改商品库存失败!商品不存在");
        }
        //商品的状态不为1 抛出异常
        if(goods.getStatus()!=1){
            throw new GoodsNotFoundException("修改商品库存失败!商品已下架或删除");
        }
        //修改库存
        updateNum(id,num,username,new Date());
    }


    @Override
    public List<Goods> getHotList() {
        return findHotList();
    }

    @Override
    public List<Goods> getNewList() {
        return findNewList();
    }

    @Override
    public Goods getById(Long id) {
       return findById(id);
    }



    private List<Goods> findHotList(){
        return mapper.findHotList();
    }
    private List<Goods> findNewList(){return mapper.findNewList();}
    private Goods findById(Long id){return mapper.findById(id);}
    private void updateNum(Long id,Integer num,String modifiedUser,Date modifiedTime){
        Integer rows=mapper.updateNum(id,num,modifiedUser,modifiedTime);
        if(rows!=1){
            throw new UpdateException("修改商品库存失败!发生了未知错误");
        }
    }



}
