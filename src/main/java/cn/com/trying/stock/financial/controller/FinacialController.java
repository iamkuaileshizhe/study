package cn.com.trying.stock.financial.controller;


import cn.com.trying.stock.financial.bean.Financial;
import cn.com.trying.stock.financial.bean.StockBean;
import cn.com.trying.stock.financial.dao.FinancialRepository;
import cn.com.trying.stock.financial.dao.StockInfoRepository;
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

@RestController
@RequestMapping("/financial")
public class FinacialController {

    @Autowired
    FinancialRepository financialRepository;
    @Autowired
    private StockInfoRepository stockInfoRepository;
    /**
    * @Title:
    * @Description: 统计指定编码对象的价格流入资金及总流入资金的趋势统计
    * @param
    * @return
    * @author huxx
    * @date 2020/5/21 下午3:50
    * @update
    */
    @RequestMapping(value = "/test" ,method = RequestMethod.POST,consumes = "application/json")
    public  @ResponseBody  String test(HttpServletRequest req,@RequestBody Map<String,String> map){
       return  getStockInfo(req, map);
    }



    /**
    * @Title:
    * @Description: 获取指定编码的数据信息  report_p_time存在时进行指定时间的数据统计，不存在时进行历史数据的统计查询
    * @param
    * @return
    * @author huxx
    * @date 2020/5/25 上午10:47
    * @update
    */
    @RequestMapping(value = "/getStockInfo" ,method = RequestMethod.POST)
    public  @ResponseBody  String getStockInfo(HttpServletRequest req,@RequestBody Map<String,String> map){
        String jsonStr = "";

        String code = map.get("report_p_code");
        String time = map.get("report_p_time");
        String startTime = map.get("report_p_startTime");
        String endTime = map.get("report_p_endTime");
        if(StringUtils.isEmpty(startTime)){
            startTime="1999-01-01";
        }
        if (StringUtils.isEmpty(endTime)) {

            endTime = DateUtil.getDateTime();
        }
        List<Financial> list = null;
        if(StringUtils.isEmpty(time)){
            list = financialRepository.findAllMaxByCodeAndStartTimeAndEndTime(code,startTime,endTime);
        }else{
            list = financialRepository.findAllByCodeAndTime(code,time);
        }
        List<String> xData = Lists.newArrayList();
        List<String> amountList = Lists.newArrayList();
        List<String> priceList = Lists.newArrayList();
        List<String> amountTotalList = Lists.newArrayList();
        list.stream().sorted((f1,f2) ->{
            int flag = DateUtil.compareTo(f2.getTime(),f1.getTime(),DateUtil.YYYYMMDD);
            if(flag == 0){
                if(f1.getNum() > f2.getNum()){
                    flag = 1;
                }else if(f1.getNum() == f2.getNum()){
                    flag = 0;
                }else{
                    flag = -1;
                }
            }
            return flag;
        } ).forEach(financial -> {
            if(StringUtils.isEmpty(time)){
                xData.add(financial.getTime());
            }else{
                xData.add(String.valueOf(financial.getNum()));
            }

            amountList.add(String.valueOf(financial.getAmount()));
            priceList.add(String.valueOf(financial.getPrice()));
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

        StockBean stockBean = stockInfoRepository.findByCode(code);
        String title = stockBean.getName();


        Map<String, String> dataMap = Maps.newHashMap();
        dataMap.put("xData",JSON.toJSONString(xData));
        dataMap.put("amountData",JSON.toJSONString(amountList));
        dataMap.put("priceData",JSON.toJSONString(priceList));
        dataMap.put("amountTotalData",JSON.toJSONString(amountTotalList));
        dataMap.put("title",title);

        Map<String,String> varMap = Maps.newHashMap();
        varMap.put("###xData###","xData");
        varMap.put("###amountData###","amountData");
        varMap.put("###priceData###","priceData");
        varMap.put("###amountTotalData###","amountTotalData");
        varMap.put("###title###","title");
        dataMap.put("reportVariableInfo",JSON.toJSONString(varMap));

        jsonStr = JSON.toJSONString(dataMap);
        return jsonStr;
    }



    /**
    * @Title:
    * @Description: 保存资金数据,如果code、time、num相同就进行更新操作
    * @param
    * @return
    * @author huxx
    * @date 2020/5/25 上午10:51
    * @update
    */
    @RequestMapping(value = "/saveFinacial" ,method = RequestMethod.POST)
    public  @ResponseBody  String saveFinacial(HttpServletRequest req,@RequestBody Map<String,String> map){
        Map<String,String> rMap = Maps.newHashMap();
        rMap.put("res","true");
        rMap.put("message","保存成功");
        String code = map.get("code");
        long num = Long.valueOf(map.get("num"));
        double amount = Double.valueOf(map.get("amount"));
        double price =  Double.valueOf(map.get("price"));
        String time = map.get("time");

        Financial f = financialRepository.findByCodeAndTimeAndNum(code,time,num);
        if(f != null ){
            f.setAmount(amount);
            f.setPrice(price);
        }else{
             f = new Financial();
            f.setCode(code);
            f.setAmount(amount);
            f.setPrice(price);
            f.setTime(time);
            f.setNum(num);
        }

        Financial financial1 = financialRepository.save(f);

        return JSON.toJSONString(rMap);
    }



}
