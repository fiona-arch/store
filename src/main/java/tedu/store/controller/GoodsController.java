package tedu.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tedu.store.entity.Goods;
import tedu.store.service.IGoodsService;
import tedu.store.util.JsonResult;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("goods")
public class GoodsController extends BaseController{
    @Autowired
    private IGoodsService service;


    @GetMapping("/hot")
    public JsonResult<List<Goods>> getHotList(){
        List<Goods> list=service.getHotList();
        return new JsonResult<>(SUCCESS,list);
    }

    @GetMapping("/new")
    public JsonResult<List<Goods>> getNewList(){
        List<Goods> list=service.getNewList();
        return new JsonResult<>(SUCCESS,list);
    }

    @GetMapping("{id}/details")
    public JsonResult<Goods> getById(@PathVariable("id") Long id){
        Goods goods=service.getById(id);
        return new JsonResult<>(SUCCESS,goods);
    }

    @RequestMapping("{id}/update_goods_num")
    public JsonResult<Void> updateGoodsNum(@PathVariable("id")Long id, HttpSession session,Integer num){
        String username=getUsernameFromSession(session);
        service.updateGoodsNum(id,username,num);
        return new JsonResult<>(SUCCESS);
    }

}
