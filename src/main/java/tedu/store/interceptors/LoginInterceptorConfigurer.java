package tedu.store.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
@Component
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor=new LoginInterceptor();
        List<String> excludePaths=new ArrayList<>();
        excludePaths.add("/users/reg");
        excludePaths.add("/users/login");
        excludePaths.add("/web/register.html");
        excludePaths.add("/web/login.html");
        excludePaths.add("/web/index.html");
        excludePaths.add("/web/product.html");
        excludePaths.add("/bootstrap3/**");
        excludePaths.add("/css/**");
        excludePaths.add("/images/**");
        excludePaths.add("/js/**");
        excludePaths.add("/districts/");
        excludePaths.add("/goods/**");
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(excludePaths);
    }
}
