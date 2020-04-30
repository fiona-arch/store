package tedu.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tedu.store.entity.Address;
import tedu.store.entity.District;
import tedu.store.mapper.AddressMapper;
import tedu.store.service.IAddressService;
import tedu.store.service.IDistrictService;
import tedu.store.service.ex.*;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {


    @Autowired
    private AddressMapper mapper;
    @Autowired
    private IDistrictService districtService;
    @Override
    public void addNew(Address address, Integer uid, String username) throws InsertException, AddressCountLimitException {
        //获取用户的已有地址数量
        Integer count=countByUid(uid);
        //地址数量超出限制  抛出异常
        if(count>=ADDRESS_MAX_COUNT){
            throw new AddressCountLimitException("您的地址数量已超出"+ADDRESS_MAX_COUNT+"条,禁止添加");
        }
        address.setUid(uid);
        //是否为默认
        Integer isDefaut=count==0?1:0;
        address.setIsDefault(isDefaut);
        //TODO 补全省市区
        District province=districtService.getByCode(address.getProvinceCode());
        District city=districtService.getByCode(address.getCityCode());
        District area=districtService.getByCode(address.getAreaCode());
        if(province==null){
            address.setProvinceCode(null);
        }else{
            address.setProvinceName(province.getName());
        }

        if(city==null){
            address.setCityCode(null);
        }else{
            address.setCityName(city.getName());
        }

        if(area==null){
            address.setAreaCode(null);
        }else{
            address.setAreaName(area.getName());
        }
        Date now=new Date();
        address.setCreateUser(username);
        address.setCreateTime(now);
        address.setModifiedUser(username);
        address.setModifiedTime(now);
        insert(address);
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        return findByUid(uid);
    }

    @Override
    @Transactional
    public void setDefault(Integer aid, Integer uid, String username) throws AccessDeniedException, AddressNotFountException, UpdateException {
        //根据aid查询收获地址是否存在
        Address address=findByAid(aid);
        //不存在抛异常
        if(address==null){
            throw new AddressNotFountException("失败!尝试删除地址不存在");
        }
        //根据用户aid查询Address中的uid,如果和参数uid不相等 抛异常
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("拒绝访问他人数据");
        }
        //全部设为非默认
        updateNonDefault(uid);
        //封装日志数据
        Date now=new Date();
        //根据aid设为默认
        updateDefault(aid,username,now);
    }

    @Override
    @Transactional
    public void delete(Integer uid, String username, Integer aid) throws AccessDeniedException, UpdateException, DeleteException, AddressNotFountException {
        //检查数据是否存在
        Address address=findByAid(aid);
        if(address==null){
            throw new AddressNotFountException("删除地址失败!地址不存在");
        }
        //检查数据归属
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("数据访问失败");
        }
        //删除地址
        deleteByAid(aid);
        //判断删除地址是否为默认,不是默认  直接return
        if(address.getIsDefault()!=1){
            return;
        }
        //地址数量是否为零
        Integer count=countByUid(uid);
        //地址数量为零  return
        if(count==0){
            return;
        }
        //将最新修改的地址设为默认
        Address result=findLastModified(uid);
        Date now=new Date();
        updateDefault(result.getAid(),username,now);
    }

    @Override
    public Address getByAid(Integer aid) {
        return findByAid(aid);
    }

    @Override
    public void changeAddress(Address address, Integer uid, String username) throws AccessDeniedException, UpdateException, AddressNotFountException {
        //验证地址是否存在
        Integer aid=address.getAid();
        Address result=findByAid(aid);
        if(result==null){
            throw new AddressNotFountException("修改地址失败!地址不存在");
        }
        //验证数据归属
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("数据访问失败");
        }
        //补全省市区
        District province=districtService.getByCode(address.getProvinceCode());
        District city=districtService.getByCode(address.getCityCode());
        District area=districtService.getByCode(address.getAreaCode());
        if(province==null){
            address.setProvinceCode(null);
        }else{
            address.setProvinceName(province.getName());
        }

        if(city==null){
            address.setCityCode(null);
        }else{
            address.setCityName(city.getName());
        }

        if(area==null){
            address.setAreaCode(null);
        }else{
            address.setAreaName(area.getName());
        }
        //补全日志修改数据
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        //执行更新
        updateAddressByAid(address);
    }


    private Integer countByUid(Integer uid){
        Integer count=mapper.countByUid(uid);
        return count;
    }

    private void insert(Address address){
        Integer rows=mapper.insert(address);
        if(rows!=1){
            throw new InsertException("发生未知错误!插入新地址失败!");
        }
    }

    private List<Address> findByUid(Integer uid){
        return mapper.findByUid(uid);
    }


    private void updateNonDefault(Integer uid){
        Integer rows=mapper.updateNonDefault(uid);
        if(rows==0){
            throw new UpdateException("");
        }
    }
    private Address findByAid(Integer aid){
        Address address=mapper.findByAid(aid);
        return address;
    }

    private void updateDefault(Integer aid,String modifiedUser,Date modifiedTime){
        Integer rows=mapper.updateDefault(aid,modifiedUser,modifiedTime);
        if(rows!=1){
            throw new UpdateException("操作失败!发生了未知错误");
        }
    }

    private Address findLastModified(Integer uid){
        Address address=mapper.findLastModified(uid);
        return address;
    }

    private void deleteByAid(Integer aid){
        Integer rows=mapper.deleteByAid(aid);
        if(rows!=1){
            throw new DeleteException("删除地址失败!发生了未知错误");
        }
    }

    private void updateAddressByAid(Address address){
        Integer rows=mapper.updateAddressByAid(address);
        if(rows!=1){
            throw new UpdateException("操作失败!发生了未知错误");
        }
    }


}
