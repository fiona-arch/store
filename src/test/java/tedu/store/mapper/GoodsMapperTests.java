package tedu.store.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tedu.store.entity.Goods;
import tedu.store.service.IGoodsService;

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
}
