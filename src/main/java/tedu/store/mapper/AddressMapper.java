package tedu.store.mapper;

import org.apache.ibatis.annotations.Param;
import tedu.store.entity.Address;

import java.util.Date;
import java.util.List;

public interface AddressMapper {
    /**
     * 统计一个用户的地址数量
     * @param uid
     * @return
     */
    Integer countByUid(Integer uid);

    /**
     * 插入收货地址信息
     * @param address
     * @return 1代表插入成功,否则插入失败
     */
    Integer insert(Address address);


    /**
     * 将某用户的所有收获地址设为非默认
     * @param uid 用户Id
     * @return 受影响的行数
     */
    Integer updateNonDefault(Integer uid);


    /**
     * 根据地址aid删除地址
     * @param aid
     * @return 1代表删除成功  否则代表删除失败
     */
    Integer deleteByAid(Integer aid);


    /**
     * 将选择的地址设为默认
     * @param aid
     * @return 受影响的行数
     */
    Integer updateDefault(@Param("aid") Integer aid,
                          @Param("modifiedUser")String modifiedUser,
                          @Param("modifiedTime")Date modifiedTime);


    /**
     * 修改地址信息
     * @param address
     * @return 1代表修改成功  0代表修改失败
     */
    Integer updateAddressByAid(Address address);
    /**
     * 根据用户id查询最后修改的地址信息
     * @param uid
     * @return 最后修改的地址信息
     */
    Address findLastModified(Integer uid);



    /**
     * 查询用户的所有地址列表
     * @param uid
     * @return 受影响的行数
     */
    List<Address> findByUid(Integer uid);

    /**
     * 查询选中的地址是否还存在
     * @param aid
     * @return
     */
    Address findByAid(Integer aid);



}
