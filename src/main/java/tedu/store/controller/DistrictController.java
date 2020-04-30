package tedu.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tedu.store.entity.District;
import tedu.store.service.IDistrictService;
import tedu.store.util.JsonResult;

import java.util.List;
@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{
    @Autowired
    IDistrictService service;

    @GetMapping("/")
    public JsonResult<List<District>> getByParent(String parent){
        List<District> list=service.getByParent(parent);
        return new JsonResult<>(SUCCESS,list);
    }
}
