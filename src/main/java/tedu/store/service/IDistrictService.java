package tedu.store.service;

import tedu.store.entity.District;

import java.util.List;

public interface IDistrictService {

    List<District> getByParent(String parent);

    District getByCode(String code);
}
