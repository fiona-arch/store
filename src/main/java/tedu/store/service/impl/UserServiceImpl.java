package tedu.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tedu.store.entity.User;
import tedu.store.mapper.UserMapper;
import tedu.store.service.IUserService;
import tedu.store.service.ex.*;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
        //根据id查询用户用户是否存在
        User result=userMapper.findUserByUsername(user.getUsername());
        //返回值不为null  代表用户存在 抛出异常
        if(result!=null){
            throw new UserDublicationException("注册失败!用户名已存在");
        }
        String salt= UUID.randomUUID().toString();
        //加密密码
        String md5Password=getMd5Password(user.getPassword(),salt);
        user.setSalt(salt);
        user.setPassword(md5Password);
        user.setIsDeleted(0);
        user.setCreateUser(user.getUsername());
        Date now=new Date();
        user.setModifiedUser(user.getUsername());
        user.setCreateTime(now);
        user.setModifiedTime(now);
        Integer rows=userMapper.insert(user);
        if(rows!=1){
            throw new RegException("注册失败!发生了未知错误,请联系系统管理员");
        }

        //封装日志属性


    }


    @Override
    public User login(String username, String password) throws NullUserException, WrongPasswordException {
        User result=userMapper.findUserByUsername(username);
        if(result==null){
            throw new NullUserException("登录失败!查无此人");
        }
        if(result.getIsDeleted()==1){
            throw new NullUserException("登录失败!查无此人");
        }
        String salt=result.getSalt();
        String md5Password=getMd5Password(password,salt);
        if(!result.getPassword().equals(md5Password)){
           throw new WrongPasswordException("登录失败!密码错误");
        }
        result.setPassword(null);
        result.setSalt(null);
        result.setIsDeleted(0);
        return result;
    }

    @Override
    public void changePassword(Integer uid,String username,String oldPassword, String newPassword) throws NullUserException, WrongPasswordException, UpdateException {
        User user=userMapper.findById(uid);
        if(user==null){
            throw new NullUserException("查无此人,修改失败");
        }

        if(user.getIsDeleted()==1){
            throw new NullUserException("查无此人,修改失败");
        }
        String salt=user.getSalt();
        String oldMd5Password=getMd5Password(oldPassword,salt);
        if(user.getPassword().equals(oldMd5Password)){
            throw new WrongPasswordException("修改失败!密码验证失败!");
        }
        String newMd5Password=getMd5Password(newPassword,salt);
        Date now=new Date();
        user.setModifiedTime(now);
        user.setModifiedUser(username);
        Integer rows=userMapper.updatePassword(uid,newMd5Password,username,now);
        if(rows!=1){
            throw new UpdateException("更改失败!发生未知错误");
        }
    }

    @Override
    public User getById(Integer uid) {
        User user=userMapper.findById(uid);
        if(user!=null){
            user.setPassword(null);
            user.setSalt(null);
            user.setIsDeleted(null);
        }
        return user;
    }

    @Override
    public void changeInfo(User user) throws NullUserException, WrongPasswordException {
        Integer uid=user.getUid();
        User result=userMapper.findById(uid);
        if(result==null){
            throw new NullUserException("修改失败!用户不存在");
        }

        if(result.getIsDeleted()==1){
            throw new NullUserException("修改失败!用户不存在");
        }
        Date now=new Date();
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(now);
        Integer rows=userMapper.updateInfo(user);
        if(rows!=1)
            throw new WrongPasswordException("修改失败!发生了未知错误");
        }

    @Override
    public void changeAvatar(String avatar, String username, Integer uid) throws NullUserException, UpdateException {

        User result=userMapper.findById(uid);
        if(result==null){
            throw new NullUserException("修改失败!用户不存在");
        }

        if(result.getIsDeleted()==1){
            throw new NullUserException("修改失败!用户不存在");
        }
        Date now=new Date();
        Integer rows=userMapper.updateAvatar(avatar,username,now,uid);

        if(rows!=1) {
            throw new WrongPasswordException("修改失败!发生了未知错误");
        }
    }


    /**
     * 密码加密
     * @param password
     * @param salt
     * @return 加密后的密码
     */
    private String getMd5Password(String password,String salt){
        String str=password+salt;
        for (int i=0;i<3;i++){
            str= DigestUtils.md5DigestAsHex(str.getBytes()).toString();
        }
        return str;
    }
}
