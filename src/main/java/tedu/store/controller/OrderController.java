package tedu.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tedu.store.entity.Order;
import tedu.store.service.IOderService;
import tedu.store.util.JsonResult;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController{
    @Autowired
    private IOderService service;
    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid,Integer []cids, HttpSession session){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        Order order=service.create(aid,cids,uid,username);
        return new JsonResult<>(SUCCESS,order);
    }
}
