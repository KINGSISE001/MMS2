package cn.lastwhisper.modular.mapper;

import java.util.List;

import cn.lastwhisper.modular.pojo.hxProductList;


public interface hxProductListMapper {
    int deleteByPrimaryKey(hxProductList key);

    List<hxProductList> selectProductlistByName(hxProductList hxProductList);

    int updateByPrimaryKeySelective(hxProductList record);

    int updateByPrimaryKey(hxProductList record);
    
    List<hxProductList> selectProductlist();
    
   hxProductList  selectProductlistByUserId(String  userId);
    
}