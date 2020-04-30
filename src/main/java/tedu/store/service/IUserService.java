package tedu.store.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import tedu.store.entity.User;
import tedu.store.service.ex.*;

public interface IUserService {
    /**
     * 用户注册
     *
     * @param user
     */
    void reg(User user);

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @throws NullUserException      查无此人异常
     * @throws WrongPasswordException 密码错误异常
     * @return用户信息
     */
    User login(String username, String password) throws NullUserException, WrongPasswordException;

    void changePassword(@RequestParam("uid") Integer uid,
                        @RequestParam("username") String username,
                        @RequestParam("old_password") String oldPassword,
                        @RequestParam("new_password") String newPassword) throws NullUserException, WrongPasswordException, UpdateException;

    User getById(Integer uid);

    void changeInfo(User user) throws NullUserException, WrongPasswordException;

    void changeAvatar(String avatar, String username, Integer uid) throws NullUserException, UpdateException;

}