package tedu.store.mapper;

import tedu.store.entity.District;

import java.util.List;

public interface DistrictMapper {
    List<District>  findByParent(String parent);

    District findByCode(String code);
}
