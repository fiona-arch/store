package tedu.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tedu.store.controller.ex.*;
import tedu.store.entity.User;
import tedu.store.service.IUserService;
import tedu.store.util.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{

    public static final int AVATAR_MAX_SIZE=2*1024*1024;
    public static final List<String> AVATAR_CONTENT_TYPES=new ArrayList<>();
    public static final String AVATAR_DIR ="upload" ;

    static {
        AVATAR_CONTENT_TYPES.add("image/jpeg");
        AVATAR_CONTENT_TYPES.add("image/gif");
    }

    @Autowired
    private IUserService service;
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        service.reg(user);
        return new JsonResult<>(SUCCESS);
    }
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User user= service.login(username,password);
        session.setAttribute("uid",user.getUid());
        session.setAttribute("username",user.getUsername());
        return new JsonResult<>(SUCCESS,user);
    }
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid= getUidFromSession(session);
        String username= getUsernameFromSession(session);
        service.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<Void>(SUCCESS);
    }
    @GetMapping("get_info")
    public JsonResult<User> getByUid(HttpSession session){
        Integer uid=getUidFromSession(session);
        User user=service.getById(uid);
        return new JsonResult<>(SUCCESS,user);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session) {
        Integer uid= getUidFromSession(session);
        String username= getUsernameFromSession(session);
        user.setUid(uid);
        user.setUsername(username);
        service.changeInfo(user);
        return new JsonResult<Void>(SUCCESS);
    }

    @PostMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpServletRequest request, MultipartFile file){

        //处理异常
        if(file.isEmpty()){
            throw new FileEmptyException("您选择的文件为空,请重新选择");
        }
        if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("请选择小于"+AVATAR_MAX_SIZE/1024+"KB的文件");
        }

        String contentType=file.getContentType();
        if(! AVATAR_CONTENT_TYPES.contains(contentType)){
            throw new FileTypeException("只能选择"+AVATAR_CONTENT_TYPES+"类型文件");
        }
        //确定文件夹
        String dirPath=request.getServletContext().getRealPath(AVATAR_DIR);
        File dir=new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //确定文件名
        String originalFilname=file.getOriginalFilename();//获取文件原名
        int index=originalFilname.lastIndexOf(".");
        String suffix="";//文件后缀名
        if(index!=-1){
            suffix=originalFilname.substring(index);
        }
        String fileName= UUID.randomUUID().toString()+suffix;
        File dest=new File(dir,fileName);
        try {
            file.transferTo(dest);
        }catch(IllegalStateException e){
            throw new FileUploadStateException("上传失败,您选择的文件可能被移动");
        } catch (IOException e) {
            throw new FileUploadIOException("上传失败!发生了未知错误!");
        }

        String avatar="/"+AVATAR_DIR+"/"+fileName;
        Integer uid=getUidFromSession(request.getSession());
        String username=getUsernameFromSession(request.getSession());
        service.changeAvatar(avatar,username,uid);
        return new JsonResult<>(SUCCESS,avatar);
    }
}
