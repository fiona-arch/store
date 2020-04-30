package tedu.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tedu.store.entity.Cart;
import tedu.store.entity.vo.CartVO;
import tedu.store.service.ICartService;
import tedu.store.util.JsonResult;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("carts")
@RestController
public class CartController extends BaseController {
    @Autowired
    private ICartService service;

    @RequestMapping("/add_to_cart")
    public JsonResult<Void> addToCart(HttpSession session, Cart cart){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        service.addToCart(uid,username,cart);
        return new JsonResult<>(SUCCESS);
    }
    @GetMapping("/")
    public JsonResult<List<CartVO>> getByUid(HttpSession session){
        Integer uid=getUidFromSession(session);
        List<CartVO> list=service.getByUid(uid);
        return new JsonResult<>(SUCCESS,list);
    }

    @RequestMapping("{cid}/add")
    public JsonResult<Integer> add(HttpSession session,@PathVariable("cid") Integer cid){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        Integer num=service.add(uid,username,cid);
        return new JsonResult<>(SUCCESS,num);
    }

    @GetMapping("get_by_cids")
    public JsonResult<List<CartVO>> getByCids(HttpSession session,Integer []cids){
        Integer uid=getUidFromSession(session);
        List<CartVO> result=service.getByCids(cids,uid);
        return new JsonResult<>(SUCCESS,result);
    }
}
