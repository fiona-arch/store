package tedu.store.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import tedu.store.entity.Goods;
import tedu.store.service.IGoodsService;
import tedu.store.service.ex.GoodsNotFoundException;
import tedu.store.service.ex.UpdateException;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class GoodsMapperTests {

    @Autowired
    GoodsMapper mapper;
    @Autowired
    IGoodsService service;

    @Test
    public void testFindHotList(){
        List<Goods> list=mapper.findHotList();
        for(Goods goods:list){
            System.err.println(goods);
        }

    }

    @Test
    public void testFindNewList(){
        List<Goods> list=mapper.findNewList();
        for(Goods goods:list){
            System.err.println(goods);
        }
    }


    @Test
    public void testGetHotList(){
        List<Goods> list=service.getHotList();
        for(Goods goods:list){
            System.err.println(goods);
        }
    }

    @Test
    public void testGetNewList(){
        List<Goods> list=service.getNewList();
        for(Goods goods:list){
            System.err.println(goods);
        }
    }

    @Test
    public void testFindById(){
        Long id=10000005l;
        Goods goods=mapper.findById(id);
        System.err.println(goods);
    }

    @Test
    public void testGetById(){
        Long id=10000005l;
        Goods goods=service.getById(id);
        System.err.println(goods);
    }

    @Test
    public void testUpdateNum(){
        Integer num=100;
        Long id=10000001L;
        String modifiedUser="美人鱼";
        Integer rows=mapper.updateNum(id,num,modifiedUser,new Date());
        System.err.println(rows);
    }

    @Test
    public void testUpdateGoodsNum(){
        try {
            Long id=10000001L;
            Integer num=10000;
            String username="她的微笑";
            service.updateGoodsNum(id,username,num);
        } catch (UpdateException e) {
            System.err.println(e.getMessage());
        } catch (GoodsNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
