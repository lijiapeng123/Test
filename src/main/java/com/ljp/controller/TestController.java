package com.ljp.controller;

import com.ljp.entity.ExcelEntity;
import com.ljp.entity.Student;
import com.ljp.service.TestService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/skipJsp")
    @ResponseBody
    public Student toJsp(Student s){
        Student sd = new Student();
        BeanUtils.copyProperties(s,sd);
        System.out.println(sd);
        return sd;
    }

    @RequestMapping("/test2")
    @ResponseBody
    public void test2(@RequestParam("paperId") String paperId,HttpServletResponse response, HttpServletRequest request){

        List<ExcelEntity> list = testService.getList(paperId);

        //装答题成绩的
        Map<String,List<ExcelEntity>> questionMap = new HashMap<>();
        //装门店信息的
        Map<String,ExcelEntity> shopMap = new HashMap<>();

        for (ExcelEntity ex:list){
            //不为NULL的情况下是已经为这个门店开启一个MAPKEY值
            if (questionMap.get(ex.getShopCode()+"_Question")!=null){
                //获取这个门店的试题集合
                List<ExcelEntity> ShopQuestionList = questionMap.get(ex.getShopCode() + "_Question");
                for (int i = 0;i<ShopQuestionList.size();i++){
                    //如果结果集的PARENTID与门店的问题ID相对应则计入备注
                    if (ex.getParent_id()!=null&&ex.getParent_id().equals(ShopQuestionList.get(i).getQuestionId())){
                        //将附加题的答案叠加在这个门店的Map里面
                        ShopQuestionList.get(i).setRemark(ShopQuestionList.get(i).getRemark()+","+ex.getAnswer());
                    }else if(ex.getParent_id()==null){//如果这个题不是附加题 创建新实体 加入集合
                        ExcelEntity excelEntity = new ExcelEntity();
                        if (ShopQuestionList.get(i).getScore_appealed()!=null){  //判断是否是申诉分数优先选择
                            excelEntity.setScore(ShopQuestionList.get(i).getScore_appealed());
                        }else if (ShopQuestionList.get(i).getScore_reviewed()!=null){//判断是否是复核分数2选择
                            excelEntity.setScore(ShopQuestionList.get(i).getScore_reviewed());
                        }//都不是的话直接用Score
                        excelEntity.setAnswer(ex.getAnswer());
                        ShopQuestionList.add(excelEntity);//报错原因修改了LIST 迭代数据异常
                    }
                }
                /*for (ExcelEntity qList:ShopQuestionList){

                }*/
            }
            //为NULL  则没有个门店的答题记录
            else if (questionMap.get(ex.getShopCode()+"_Question")==null){
                List<ExcelEntity> newList = new ArrayList<>();
                ExcelEntity excelEntity = new ExcelEntity();
                //如果PARENTID不为NULL则为附加题
                if (ex.getParent_id()!=null){
                    //为这个设置答题ID
                    excelEntity.setQuestionId(ex.getQuestionId());
                    //将答案设置为备注
                    excelEntity.setRemark(ex.getAnswer());
                }
                excelEntity.setQuestionId(ex.getQuestionId());
                excelEntity.setAnswer(ex.getAnswer());
                newList.add(excelEntity);
                questionMap.put(ex.getShopCode()+"_Question",newList);
            }
            //如果等于NULL的话说明还没有这个门店的信息
            if (shopMap.get(ex.getShopCode()+"_Shop")==null){
                ExcelEntity excelEntity = new ExcelEntity();
                excelEntity.setPaperTitle(ex.getPaperTitle());
                excelEntity.setWarArea(ex.getWarArea());
                excelEntity.setShopCode(ex.getShopCode());
                excelEntity.setShopName(ex.getShopName());
                excelEntity.setProvince(ex.getProvince());
                excelEntity.setLongitude(ex.getLongitude());
                excelEntity.setLatitude(ex.getLatitude());
                excelEntity.setAddress(ex.getAddress());
                excelEntity.setUrgetName(ex.getUrgetName());
                excelEntity.setTotal_score(ex.getTotal_score());
                excelEntity.setTotal_score_reviewed(ex.getTotal_score_reviewed());
                excelEntity.setTotal_score_appealed(ex.getTotal_score_appealed());
                excelEntity.setAnswer_start_time(ex.getAnswer_start_time());
                excelEntity.setAnswer_end_time(ex.getAnswer_end_time());
                shopMap.put(ex.getShopCode()+"_Shop",excelEntity);
            }
        }

        Iterator<Map.Entry<String, ExcelEntity>> entries = shopMap.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry<String, ExcelEntity> next = entries.next();
            String key = next.getKey();
            ExcelEntity value = next.getValue();
            System.out.println(key+":"+value);
        }

    }
}
