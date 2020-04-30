package tedu.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.tree.VoidDescriptor;
import tedu.store.entity.Address;
import tedu.store.entity.User;
import tedu.store.service.IAddressService;
import tedu.store.util.JsonResult;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController{
    @Autowired
    IAddressService service;


    @RequestMapping("/")
    public JsonResult<List<Address>> getByUid(HttpSession session){
        Integer uid=getUidFromSession(session);
        List<Address> addresses=service.getByUid(uid);
        return new JsonResult<>(SUCCESS,addresses);
    }

    @RequestMapping("add_new")
    public JsonResult<Void> addNew(HttpSession session, Address address){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        service.addNew(address,uid,username);
        return new JsonResult<>(SUCCESS);
    }

    @GetMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(HttpSession session,@PathVariable("aid") Integer aid){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        service.setDefault(aid,uid,username);
        return new JsonResult<>(SUCCESS);
    }
    @GetMapping("{aid}/delete")
    public JsonResult<Void> delete(HttpSession session,@PathVariable("aid")Integer aid){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        service.delete(uid,username,aid);
        return new JsonResult<>(SUCCESS);
    }

    @GetMapping("getByAid")
    public JsonResult<Address> getByAid(Integer aid){
        Address address=service.getByAid(aid);
        return new JsonResult<>(SUCCESS,address);
    }

    @RequestMapping("change_address")
    public JsonResult<Void> changeAddress(HttpSession session,Address address){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        service.changeAddress(address,uid,username);
        return new JsonResult<>(SUCCESS);
    }
}
