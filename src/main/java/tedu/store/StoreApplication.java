package tedu.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("tedu.store.mapper")
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

    public MultipartConfigElement getMultipartConfig(){
        MultipartConfigFactory factory=new MultipartConfigFactory();
        DataSize maxFileSize=DataSize.ofMegabytes(50);
        factory.setMaxFileSize(maxFileSize);
        DataSize maxRequestSize=DataSize.ofMegabytes(100);
        factory.setMaxRequestSize(maxRequestSize);
        return factory.createMultipartConfig();
    }

}
