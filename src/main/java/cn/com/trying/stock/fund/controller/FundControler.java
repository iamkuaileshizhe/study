package cn.com.trying.stock.fund.controller;

import cn.com.trying.stock.financial.bean.Financial;
import cn.com.trying.stock.financial.bean.StockBean;
import cn.com.trying.stock.fund.bean.FundBean;
import cn.com.trying.stock.fund.dao.FundRespository;
import cn.com.trying.utils.DateUtil;
import cn.com.trying.utils.MathUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
* @Title: FundControler
* @Description: 资金流水操作控制器
* @author huxx
* @date 2020/6/1 上午9:32
* @update
*/
@RestController
@RequestMapping("/fund")
public class FundControler {

    @Autowired
    private FundRespository fundRespository;
    /**
    * @Title:
    * @Description: 保存资金流水
    * @param
    * @return
    * @author huxx
    * @date 2020/6/1 上午9:41
    * @update
    */
    @RequestMapping(value = "/save" ,method = RequestMethod.POST)
    public  @ResponseBody  String save(HttpServletRequest req, @RequestBody Map<String,String> map){
        Map<String,String> rMap = Maps.newHashMap();
        rMap.put("res","true");
        rMap.put("message","保存成功");
        String opTime = map.get("opTime");
        double amount = Double.valueOf(map.get("amount"));
        FundBean fundBean = new FundBean();
        fundBean.setAmount(amount);
        fundBean.setOpTime(opTime);
        fundBean.setCreateTime(DateUtil.getDateTime());

        FundBean bean = fundRespository.save(fundBean);

        return JSON.toJSONString(rMap);
    }


    @RequestMapping(value = "/getFund" ,method = RequestMethod.POST)
    public  @ResponseBody  String getFund(HttpServletRequest req,@RequestBody Map<String,String> map){
        String jsonStr = "";

        String startTime = map.get("report_p_startTime");
        String endTime = map.get("report_p_endTime");
        if(StringUtils.isEmpty(startTime)){
            startTime="1999-01-01";
        }
        if (StringUtils.isEmpty(endTime)) {

            endTime = DateUtil.getDateTime();
        }
        List<FundBean> list =  list = fundRespository.findAllByStartTimeAndEndTime(startTime,endTime);
        List<String> xData = Lists.newArrayList();
        List<String> amountList = Lists.newArrayList();
        List<String> amountTotalList = Lists.newArrayList();
        list.stream().sorted((f1,f2) ->{
            int flag = DateUtil.compareTo(f2.getOpTime(),f1.getOpTime(),DateUtil.YYYYMMDD);
            return flag;
        } ).forEach(fundBean -> {
                xData.add(fundBean.getOpTime());

            amountList.add(String.valueOf(fundBean.getAmount()));
        });
        for(int i=0; i< amountList.size(); i++){
            String  total = "";
            if( i == 0){
                total = amountList.get(0);
            }else{
                for(int j = 0 ; j <= i; j++){
                    total = MathUtil.add(total, amountList.get(j),2);
                }
            }
            amountTotalList.add(total);
        }

        String title = "";


        Map<String, String> dataMap = Maps.newHashMap();
        dataMap.put("xData",JSON.toJSONString(xData));
        dataMap.put("amountData",JSON.toJSONString(amountList));
        dataMap.put("amountTotalData",JSON.toJSONString(amountTotalList));
        dataMap.put("title",title);

        Map<String,String> varMap = Maps.newHashMap();
        varMap.put("###xData###","xData");
        varMap.put("###amountData###","amountData");
        varMap.put("###amountTotalData###","amountTotalData");
        varMap.put("###title###","title");
        dataMap.put("reportVariableInfo",JSON.toJSONString(varMap));

        jsonStr = JSON.toJSONString(dataMap);
        return jsonStr;
    }
}
