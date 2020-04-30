package tedu.store.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tedu.store.entity.District;

import java.util.List;

@SpringBootTest
public class DistrictMapperTestCase {
    @Autowired
    DistrictMapper mapper;

    @Test
    public void testFindByParent(){
        String parent="86";
        List<District> list=mapper.findByParent(parent);
        System.err.println(list);
    }

    @Test
    public void testFindByCode(){
        String code="110108";
       District list=mapper.findByCode(code);
        System.err.println(list);
    }
}
