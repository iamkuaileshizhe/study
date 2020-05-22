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
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

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



    @RequestMapping(value = "/getStockInfo" ,method = RequestMethod.POST)
    public  @ResponseBody  String getStockInfo(HttpServletRequest req,@RequestBody Map<String,String> map){
        String jsonStr = "";

        String code = map.get("report_p_code");
        List<Financial> list = financialRepository.findAllByCode(code);
        List<String> xData = Lists.newArrayList();
        List<String> amountList = Lists.newArrayList();
        List<String> priceList = Lists.newArrayList();
        List<String> amountTotalList = Lists.newArrayList();
        list.stream().sorted((f1,f2) -> DateUtil.compareTo(f2.getTime(),f1.getTime(),DateUtil.YYYYMMDD) ? 1 : -1).forEach(financial -> {
            xData.add(financial.getTime());
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



}
