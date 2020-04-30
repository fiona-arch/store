package tedu.store.mapper;

import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tedu.store.entity.Cart;
import tedu.store.entity.vo.CartVO;
import tedu.store.service.ICartService;
import tedu.store.service.ex.AccessDeniedException;
import tedu.store.service.ex.CartNotFoundException;
import tedu.store.service.ex.InsertException;
import tedu.store.service.ex.UpdateException;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class CartMapperTests {
    @Autowired
    CartMapper mapper;
    @Autowired
    ICartService service;
    @Test
    public void testInsert(){
        Cart cart=new Cart();
        cart.setUid(9);
        cart.setGid(10000009L);
        cart.setNum(1);
        cart.setCreateUser("小雏菊");
        cart.setCreateTime(new Date());
        Integer rows=mapper.insert(cart);
        System.err.println(rows);
    }

    @Test
    public void testFindByUidAndGid(){
        Integer uid=9;
        Long gid=10000009L;
        Cart cart=mapper.findByUidAndGid(uid,gid);
        System.err.println(cart);
    }


    @Test
    public void testUpdateNum(){
        Integer cid=1;
        Integer num=2;
        String modifiedUser="伊丽莎白";
        Integer rows=mapper.updateNum(cid,num,modifiedUser,new Date());
        System.err.println(rows);
    }

    @Test
    public void testAddToCart(){
        try {
            Integer uid=9;
            String username="小路";
            Cart cart=new Cart();
            cart.setGid(10000013l);
            cart.setNum(1);
            service.addToCart(uid,username,cart);
        } catch (UpdateException e) {
           System.err.println(e.getMessage());
        } catch (InsertException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testFindByCid(){
        Integer cid=5;
        Cart cart=mapper.findByCid(cid);
        System.err.println(cart);
    }

    @Test
    public void testAdd(){
        try {
            Integer cid=2;
            Integer uid=9;
            String username="世界之王";
            Integer num=service.add(uid,username,cid);
            System.err.println(num);
        } catch (CartNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (AccessDeniedException e) {
            System.err.println(e.getMessage());
        } catch (UpdateException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testFindByCids(){
        Integer []cids={6,2,3,9};
        List<CartVO> list=mapper.findByCids(cids);
        for(CartVO c:list){
            System.err.println(c);
        }
    }

    @Test
    public void testGetByIds(){
        Integer []cids={6,7,3,9};
        Integer uid=19;
        List<CartVO> list=service.getByCids(cids,uid);
        for(CartVO item:list){
            System.err.println(item);
        }

    }
}
