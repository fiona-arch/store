package tedu.store.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tedu.store.entity.Cart;
import tedu.store.entity.vo.CartVO;
import tedu.store.mapper.CartMapper;
import tedu.store.service.ICartService;
import tedu.store.service.ex.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper mapper;
    @Override
    public void addToCart(Integer uid, String username, Cart cart) throws UpdateException, InsertException {
        //根据gid和uid查询购物车信息
        Cart result=findByUidAndGid(uid,cart.getGid());
        //返回Null  插入整条购物车商品信息
        Date now=new Date();
        cart.setModifiedUser(username);
        cart.setModifiedTime(now);
        cart.setUid(uid);
        if(result==null){
            cart.setCreateUser(username);
            cart.setCreateTime(now);
            insert(cart);
            return;
        }
        //返回不为null  得到cid和num
        Integer cid=result.getCid();
        Integer num=result.getNum();
        //原数量和新数量相加
        num=num+cart.getNum();
        //更新购物车数量
        updateNum(cid,num,username,now);
    }

    @Override
    public List<CartVO> getByUid(Integer uid) {
        return findByUid(uid);
    }

    @Override
    public Integer add(Integer uid, String username, Integer cid) throws CartNotFoundException, AccessDeniedException, UpdateException {
        //根据cid查询购物车商品数据
        Cart cart=findByCid(cid);
        //从结果中得到用户id,与参数用户id比较  不一致抛出异常
        if(uid!=cart.getUid()){
            throw new AccessDeniedException("添加商品数量失败!您无法操作他人数据");
        }
        //取出商品的原来的数量
        Integer oldNum=cart.getNum();
        //原数量+1得到新数量
        Integer num=oldNum+1;
        //更新购物车商品数量
        updateNum(cid,num,username,new Date());
        //将数据库的结果返回给客户端
        Cart result=findByCid(cid);
        return result.getNum();
    }

    @Override
    public void deleteCart(Integer uid, Integer[] cids) throws DeleteException, AccessDeniedException, CartNotFoundException {
        //根据cids查找对应的购物车数据
        List<Cart> carts=findCartByCids(cids);
        //比对uid uid不一致抛出异常
        if(carts.size()!=cids.length){
            throw new CartNotFoundException("不存在!失败");
        }
        Iterator<Cart> iterator=carts.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getUid()!=uid){
                throw new AccessDeniedException("删除数据失败!您无法操作他人数据");
            }
        }
        //删除购物车数据
        deleteByCids(cids);

    }


    @Override
    public Integer reduce(Integer uid, String username, Integer cid) throws CartNotFoundException, AccessDeniedException, UpdateException {
       Cart cart=findByCid(cid);
       if(cart.getUid()!=uid){
           throw new AccessDeniedException("减少购物车商品数量失败!您无法操作他人数据");
       }
       Integer oldNum=cart.getNum();
       Integer num=oldNum-1;
       updateNum(cid,num,username,new Date());
       Cart result=findByCid(cid);
       return result.getNum();
    }

    @Override
    public List<CartVO> getByCids(Integer[] cids, Integer uid) {
        List<CartVO> carts=findByCids(cids);
        Iterator<CartVO> iterator=carts.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getUid()!=uid){
                iterator.remove();
            }
        }
        return carts;
    }

    /**私有化持久层方法*/
    private void insert(Cart cart){
        Integer rows=mapper.insert(cart);
        if(rows!=1){
            throw new InsertException("加入购物车失败!发生了未知错误");
        }
    }


    private Cart findByUidAndGid(Integer uid,Long gid){
        return mapper.findByUidAndGid(uid,gid);
    }

    private void updateNum(Integer cid,Integer num,String modifiedUser,Date modifiedTime){
        Integer rows=mapper.updateNum(cid,num,modifiedUser,modifiedTime);
        if(rows!=1){
            throw new UpdateException("加入购物车失败!发生了未知错误");
        }
    }

    private List<CartVO> findByUid(Integer uid){
        return mapper.findByUid(uid);
    }

    private Cart findByCid(Integer cid){
        Cart cart=mapper.findByCid(cid);
        if(cart==null){
            throw new CartNotFoundException("添加数量失败!您尝试操作书数据不存在");
        }
        return cart;
    }

    private List<CartVO> findByCids(Integer []cids){
        return mapper.findByCids(cids);
    }

    private void deleteByCids(Integer []cids){
        Integer rows=mapper.deleteByCids(cids);
        if(rows==0){
            throw new DeleteException("删除购物车商品数据失败!发生了未知错误");
        }
    }

    private List<Cart> findCartByCids(Integer []cids){
        return mapper.findCartByCids(cids);
    }
}
