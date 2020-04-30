package tedu.store.service;

import tedu.store.entity.Address;
import tedu.store.service.ex.*;

import java.util.List;

public interface IAddressService {

    /**最大地址数量*/
    public static final Integer ADDRESS_MAX_COUNT=20;

    /**
     * 插入新地址
     * @param address
     * @throws InsertException 插入失败
     * @throws AddressCountLimitException 收获地址上限异常
     */
    void addNew(Address address,Integer uid,String username)throws InsertException, AddressCountLimitException;

    /**
     * 查询用户的地址列表
     * @param uid
     * @return
     */
    List<Address> getByUid(Integer uid);

    void setDefault(Integer aid,Integer uid,String username)throws AccessDeniedException, AddressNotFountException, UpdateException;

    void delete(Integer uid,String username,Integer aid)throws AccessDeniedException,UpdateException,DeleteException,AddressNotFountException;

    void changeAddress(Address address,Integer uid,String username)throws AccessDeniedException,UpdateException,AddressNotFountException;

    Address getByAid(Integer aid);

}
