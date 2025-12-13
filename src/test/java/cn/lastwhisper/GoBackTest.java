package cn.lastwhisper;

import cn.lastwhisper.core.jifen.jiFen;
import cn.lastwhisper.core.jifen.jiFens;
import cn.lastwhisper.core.task.GoBackTask;
import cn.lastwhisper.modular.mapper.hxProductListMapper;
import cn.lastwhisper.modular.pojo.hxProductList;
import cn.lastwhisper.modular.vo.EmailInfo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 
 * @author lastwhisper
 * @date 2020/2/16
 */

public class GoBackTest {


    public static void main(String[] args){



        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
       /* jiFens jiFens = context.getBean(jiFens.class);
        jiFen jiFen = context.getBean(jiFen.class);
        hxProductListMapper hxProductListMapper =context.getBean(hxProductListMapper.class);
        List<hxProductList> hxPLM = hxProductListMapper.selectProductlist();
        //jiFens.findCard(hxPLM.get(0));//叮咚积分查询,同步到库，无则创建会员用户
        //jiFens.cyjftbu(hxPLM.get(0));//差异积分同步
        JSONObject josn = jiFen.chaXun("19329766501",hxPLM.get(0).getDdkey(),7110775,7570712);
        System.out.println(josn.toString());

        */
        GoBackTask mysqlTask = context.getBean(GoBackTask.class);
        mysqlTask.execute();
        //jiFens mysqlTask = context.getBean(jiFens.class);
       //mysqlTask.integralCallback("7570712", "7996145",1);


    }
    
}
