package tedu.store.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tedu.store.entity.Address;
import tedu.store.service.IAddressService;
import tedu.store.service.ex.AccessDeniedException;
import tedu.store.service.ex.AddressNotFountException;
import tedu.store.service.ex.DeleteException;
import tedu.store.service.ex.UpdateException;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class AddressMapperTestCase {

    @Autowired
    AddressMapper mapper;
    @Autowired
    IAddressService service;

    @Test
    public void testInsert(){
        Address address=new Address();
        address.setUid(110);
        address.setName("甄平");
        address.setAddress("江左盟");
        address.setModifiedTime(new Date());
        Integer rows=mapper.insert(address);
        System.err.println(rows);
    }

    @Test
    public void testCountByUid(){
        Integer uid=100;
        Integer count=mapper.countByUid(uid);
        System.err.println(count);
    }

    @Test
    public void testAddNew(){
        try {
            Address address=new Address();
            address.setUid(110);
            address.setName("蒙郅");
            address.setModifiedTime(new Date());
            service.addNew(address,110,"我");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    @Test
    public void testFindByUid(){
        Integer uid=17;
        List<Address> list=mapper.findByUid(uid);
        for(Address address:list){
            System.err.println(address);
        }
    }

    @Test
    public void testGetByUid(){
        Integer uid=17;
        List<Address> list=service.getByUid(uid);
        for(Address address:list){
            System.err.println(address);
        }
    }

    @Test
    public void testFindByAid(){
        Integer aid=11;
        Address address=mapper.findByAid(aid);
        System.err.println(address);
    }

    @Test
    public void testUpdateNonDefault(){
        Integer uid=17;
        Integer rows=mapper.updateNonDefault(uid);
        System.err.println(rows);
    }

    @Test
    public void testUpdateDefautl(){
        Integer aid=7;
        Integer rows=mapper.updateDefault(aid,"灭绝师太",new Date());
        System.err.println(rows);
    }

    @Test
    public void testSetDefault(){
        try {
            Integer aid=17;
            Integer uid=17;
            String username="灭绝师太";
            service.setDefault(aid,uid,username);
            System.err.println("完了");
        } catch (AccessDeniedException e) {
            System.err.println("拒绝访问");
        } catch (AddressNotFountException e) {
            System.err.println("地址未找到");
        } catch (UpdateException e) {
            System.err.println("未知错误");
        }
    }

    @Test
    public void testFindLastMofidied(){
        Integer uid=6;
        Address address=mapper.findLastModified(uid);
        System.err.println(address);
    }


    @Test
    public void testDeleteByAid(){
        Integer aid=24;
        Integer rows=mapper.deleteByAid(aid);
        System.err.println(rows);
    }

    @Test
    public void testDelete(){
        try {
            Integer aid=20;
            Integer uid=6;
            String username="皇后";
            service.delete(uid,username,aid);
        } catch (AccessDeniedException e) {
            System.err.println(e.getMessage());
        } catch (UpdateException e) {
            System.err.println(e.getMessage());
        } catch (DeleteException e) {
            System.err.println(e.getMessage());
        } catch (AddressNotFountException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testUpdateAddressByAid(){
        Address address=new Address();
        address.setAid(11);
        address.setAreaName("天堂");
        address.setName("为啥啊");
        Integer rows=mapper.updateAddressByAid(address);
        System.err.println(rows);

    }

}
