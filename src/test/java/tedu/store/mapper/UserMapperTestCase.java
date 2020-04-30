package tedu.store.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import tedu.store.entity.User;
import tedu.store.service.IUserService;
import tedu.store.service.ex.NullUserException;
import tedu.store.service.ex.UpdateException;
import tedu.store.service.ex.WrongPasswordException;

import java.util.Date;

@SpringBootTest
public class UserMapperTestCase {
    @Autowired
    UserMapper userMapper;
    @Autowired
    IUserService userService;
    @Test
    public void testFindUserByUsername(){
        User user=userMapper.findUserByUsername("女娲");
        System.err.println(user);
    }
    @Test
    public void testInsert(){
        User user=new User();
        user.setUsername("陈洁");
        user.setPassword("1234");
        Integer rows=userMapper.insert(user);
        System.err.println(rows);
    }

    @Test
    public void reg(){
        User user=new User();
        user.setUsername("夏天");
        user.setPassword("1234");
        userService.reg(user);
        System.err.println("hao");
    }
    @Test
    public void login(){

        try {
            userService.login("秋天","1234");
            System.err.println("hao");
        } catch (NullUserException e) {
            System.err.println(e.getMessage());
        } catch (WrongPasswordException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testFindById(){
        User user=userMapper.findById(4);
        System.err.println(user);
    }

    @Test
    public void testUpdateInfo(){
        User user=new User();
        user.setUid(4);
        user.setPhone("18188947985");
        user.setEmail("14545455@qq.com");
        user.setModifiedUser("蒙");
        Date now=new Date();
        user.setModifiedTime(now);
        Integer rows=userMapper.updateInfo(user);
        System.err.println(rows);
    }

    @Test
    public void testChangeInfo(){
        try {
            User user=new User();
            user.setUid(5);
            Date now=new Date();
            user.setModifiedUser("超级管理");
            user.setModifiedTime(now);
            userService.changeInfo(user);
            System.err.println("hao");
        } catch (NullUserException e) {
          System.err.println(e.getMessage());
        } catch (WrongPasswordException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testUpdateAvatar(){
        Integer uid=4;
        String modifiedUser="idea";
        Date now=new Date();
        String avatar="这是头像啊";
        Integer rows=userMapper.updateAvatar(avatar,modifiedUser,now,uid);
        System.err.println(rows);
    }

    @Test
    public void testChangeAvatar(){
        try {
            Integer uid=5;
            String modifiedUser="idea";
            Date now=new Date();
            String avatar="这又是头像啊";
            userService.changeAvatar(avatar,modifiedUser,uid);
        } catch (NullUserException e) {
            System.err.println(e.getMessage());
        } catch (UpdateException e) {
            System.err.println(e.getMessage());
        }
    }
}
