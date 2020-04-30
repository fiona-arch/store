package tedu.store.mapper;

import org.apache.ibatis.annotations.Param;
import tedu.store.entity.User;

import java.util.Date;

public interface UserMapper {
    /**
     * 根据用户名查找用户信息
     * @param username
     * @return 用户对象
     */
    User findUserByUsername(String username);

    /**
     * 插入用户
     * @param user
     * @return 1代表用户插入成功  0代表插入失败
     */
    Integer insert(User user);

    /**
     * 根据id查找用户对象
     * @param uid
     * @return 返回用户对象
     */
    User findById(Integer uid);

    Integer updatePassword(@Param("uid") Integer uid,@Param("password")String password,
                           @Param("modifiedUser")String modifiedUser,
                           @Param("modifiedTime") Date modifiedTime);

    Integer updateInfo(User user);

    /**
     * 更改头像
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @param uid
     * @return 1代表更改成功  否则代表更改失败
     */
    Integer updateAvatar(@Param("avatar") String avatar,@Param("modifiedUser")String modifiedUser,
                         @Param("modifiedTime")Date modifiedTime,@Param("uid")Integer uid);
}
