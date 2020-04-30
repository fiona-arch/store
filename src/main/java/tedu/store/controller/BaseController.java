package tedu.store.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import tedu.store.controller.ex.*;
import tedu.store.service.ex.*;
import tedu.store.util.JsonResult;

import javax.servlet.http.HttpSession;

public class BaseController {
    public static final Integer SUCCESS=2000;
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> jr=new JsonResult<>();
        jr.setMessage(e.getMessage());
        if(e instanceof UserDublicationException){
            jr.setState(4000);
        }else if(e instanceof RegException){
            jr.setState(4001);
        }else if(e instanceof UpdateException){
            jr.setState(5001);
        }else if(e instanceof InsertException){
            jr.setState(5004);
        }else if(e instanceof WrongPasswordException){
            jr.setState(5002);
        }else if(e instanceof NullUserException){
            jr.setState(5003);
        }else if(e instanceof FileEmptyException){
            jr.setState(6000);
        }else if(e instanceof FileSizeException){
            jr.setState(6001);
        }else if(e instanceof FileTypeException){
            jr.setState(6002);
        }else if(e instanceof FileUploadStateException){
            jr.setState(6003);
        }else if(e instanceof FileUploadIOException){
            jr.setState(6004);
        }else if(e instanceof AddressCountLimitException){
            jr.setState(6005);
        }else if(e instanceof DeleteException){
            jr.setState(6006);
        }else if(e instanceof AddressNotFountException){
            jr.setState(6007);
        }else if(e instanceof AccessDeniedException){
            jr.setState(6008);
        }else if(e instanceof CartNotFoundException){
            jr.setState(6009);
        }
        return jr;
    }

    /**
     * session中获取当前登录用户的id
     * @param session
     * @return
     */
    protected final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 从session 中获取用户名
     * @param session
     * @return
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
