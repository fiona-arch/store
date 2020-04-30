package tedu.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tedu.store.entity.Goods;
import tedu.store.mapper.GoodsMapper;
import tedu.store.service.IGoodsService;

import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private GoodsMapper mapper;

    @Override
    public List<Goods> getHotList() {
        return findHotList();
    }

    @Override
    public List<Goods> getNewList() {
        return findNewList();
    }

    @Override
    public Goods getById(Long id) {
       return findById(id);
    }

    private List<Goods> findHotList(){
        return mapper.findHotList();
    }
    private List<Goods> findNewList(){return mapper.findNewList();}
    private Goods findById(Long id){return mapper.findById(id);}
}
