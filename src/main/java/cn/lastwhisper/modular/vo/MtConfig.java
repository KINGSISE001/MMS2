package cn.lastwhisper.modular.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sankuai.meituan.shangou.open.sdk.domain.SystemParam;

import cn.lastwhisper.modular.mapper.o2oBrandInfoMapper;
import cn.lastwhisper.modular.pojo.o2oBrandInfo;



@Service
public class MtConfig {
	//static String mtappid = "5430";
	//static String mtkey = "bb971f25af7ce4d9a832d7a4500306dd";

	@Autowired
	o2oBrandInfoMapper o2oBrandInfoMapper;
	
	public SystemParam selectSystemParam(String poicode) {
	o2oBrandInfo BrandInfo=o2oBrandInfoMapper.selectByPrimaryKey(poicode);
		
	return new SystemParam(BrandInfo.getMtappid().toString(),BrandInfo.getMtappkey());
	}
	
	

}
