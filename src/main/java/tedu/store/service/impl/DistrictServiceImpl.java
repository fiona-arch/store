package tedu.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tedu.store.entity.District;
import tedu.store.mapper.DistrictMapper;
import tedu.store.service.IDistrictService;

import java.util.List;
@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    DistrictMapper mapper;
    @Override
    public List<District> getByParent(String parent) {
        return findByParent(parent);
    }

    @Override
    public District getByCode(String code) {
      return  findByCode(code);
    }

    private District findByCode(String code){return  mapper.findByCode(code);}
    private List<District> findByParent(String parent){
        return mapper.findByParent(parent);
    }
}
