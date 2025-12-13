package cn.lastwhisper.modular.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



import org.springframework.stereotype.Repository;

import cn.lastwhisper.modular.pojo.HxVipCards;
@Mapper
@Repository
public interface HxVipCardsMapper {
    int deleteByPrimaryKey(Integer vipid);

    HxVipCards selectByPrimaryKey(Integer vipid);

  int updateByPrimaryKeySelective(HxVipCards record);
  int updateByPrimaryKey(HxVipCards record);
  int insertVipCards (@Param("lists") List<HxVipCards> cards);

  int selectCount (@Param("company") Integer company);
 List <HxVipCards> selectAll (@Param("company") Integer company,@Param("start") Integer start,@Param("end")  Integer end);

 int updateByVipId (@Param("vipid") Integer vipid,@Param("ptjfen")Integer ptjfen,@Param("userid") Integer userid);

  int updatehuangtai();

 int selectJfCount (@Param("company") Integer company);
 List <HxVipCards> selectJf (@Param("company") Integer company,@Param("start") Integer start,@Param("end")  Integer end);

 HxVipCards selectVipid (@Param("ptuserid")String ptuserid,@Param("companyId")String  storeId);


 List<HxVipCards> selectByCompanyId(@Param("companyId")Integer  storeId);





}
