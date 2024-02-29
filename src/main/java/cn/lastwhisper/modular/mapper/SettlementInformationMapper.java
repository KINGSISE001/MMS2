package cn.lastwhisper.modular.mapper;

import cn.lastwhisper.modular.pojo.SettlementInformation;

public interface SettlementInformationMapper {
    int deleteByPrimaryKey(Integer id);
    int insertSettlementInformation (SettlementInformation  settlementinformation);
    SettlementInformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SettlementInformation record);

    int updateByPrimaryKey(SettlementInformation record);
}