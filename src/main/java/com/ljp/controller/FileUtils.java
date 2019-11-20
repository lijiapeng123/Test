package com.ljp.controller;

import com.ljp.entity.ExcelEntity;
import com.ljp.service.TestService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

public class FileUtils {
    /**
     * 下载文件时，针对不同浏览器，进行附件名的编码
     *
     * @param filename
     *            下载文件名
     * @param agent
     *            客户端浏览器
     * @return 编码后的下载附件名
     * @throws IOException
     */
    public static String encodeDownloadFilename(String filename, String agent)
            throws IOException {
        if (agent.contains("Firefox")) { // 火狐浏览器
            filename = "=?UTF-8?B?"
                    + new BASE64Encoder().encode(filename.getBytes("utf-8"))
                    + "?=";
            filename = filename.replaceAll("\r\n", "");
        } else { // IE及其他浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+"," ");
        }
        return filename;
    }

    public static void excelExport(HttpServletResponse response, HttpServletRequest request, HSSFWorkbook hssfWorkbook, String filename){

        // 通过浏览器下载导出
        // 设置表头信息
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel,charset=utf-8");
            String agent = request.getHeader("user-agent");
            filename = encodeDownloadFilename(filename, agent);
            response.setHeader("Content-Disposition", new String(("attachment;filename=" + filename).getBytes(), "iso-8859-1"));
            response.addHeader("Content-Length", String.valueOf(hssfWorkbook.getBytes().length));
            hssfWorkbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭
            try {
                hssfWorkbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createExcel(List<ExcelEntity> list,HttpServletResponse response, HttpServletRequest request){
        Map<String,List<ExcelEntity>> questmap=new HashMap();

        for(int i=0;i<list.size();i++){
            if(questmap.get(list.get(i).getQuestionTitle())==null){
                List<ExcelEntity> curList =new ArrayList<ExcelEntity>();
                curList.add(list.get(i));
                questmap.put(list.get(i).getQuestionTitle(),curList);
            }else{
                questmap.get(list.get(i).getQuestionTitle()).add(list.get(i));
            }
        }


        //生成Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //设置文件名称
        HSSFSheet sheet = wb.createSheet("统计表");
        //设置居中样式
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        //设置字体
        HSSFFont font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 10);//设置字体大小
        font.setBold(true);
        //设置边框
        HSSFCellStyle setBorder = wb.createCellStyle();
        setBorder.setBorderBottom(BorderStyle.THIN);

        //生成表头(题目表头)
        HSSFRow head1Row = sheet.createRow(0);
        //生成第二行属性
        HSSFRow head2Row = sheet.createRow(1);

        head2Row.createCell(0).setCellValue("店检项目名称");
        head2Row.createCell(1).setCellValue("战区");
        head2Row.createCell(2).setCellValue("店面编号");
        head2Row.createCell(3).setCellValue("店面名称");
        head2Row.createCell(4).setCellValue("省份");
        head2Row.createCell(5).setCellValue("答题经度");
        head2Row.createCell(6).setCellValue("答题纬度");
        head2Row.createCell(7).setCellValue("店面地址");
        head2Row.createCell(8).setCellValue("负责业代");
        head2Row.createCell(9).setCellValue("负责督导");
        head2Row.createCell(10).setCellValue("自检得分");
        head2Row.createCell(11).setCellValue("复检得分");
        head2Row.createCell(12).setCellValue("复检得分");
        head2Row.createCell(13).setCellValue("答题开始时间");
        head2Row.createCell(14).setCellValue("答题结束时间");



        Set<Map.Entry<String, List<ExcelEntity>>> entries = questmap.entrySet();
        Iterator<Map.Entry<String, List<ExcelEntity>>> iterator = entries.iterator();
        int i=15;
        int contentRowNum=2;
        while(iterator.hasNext()){
            Map.Entry<String, List<ExcelEntity>> next = iterator.next();
            System.out.println(next);
//            head1Row.createCell(i).setCellValue(next.getKey());
            //生成表头excel
            CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0,i,2+i);
            sheet.addMergedRegion(cellRangeAddress);
            head1Row.createCell(i).setCellValue(next.getKey());
            head2Row.createCell(i).setCellValue("所填答案");
            head2Row.createCell(1+i).setCellValue("备注");
            head2Row.createCell(2+i).setCellValue("得分");
            //获取内容生成excel
            List<ExcelEntity> excelList2 = next.getValue();
            int x = 0;
            for(ExcelEntity excelList:excelList2){
                HSSFRow listContentRow = sheet.createRow(contentRowNum);
                if (excelList.getShopName()!=null){
                    //门店名称
                    listContentRow.createCell(0).setCellValue(excelList.getPaperTitle());
                }
                if (excelList.getWarArea()!=null){
                    //战区
                    listContentRow.createCell(1).setCellValue(excelList.getWarArea());
                }

                if (excelList.getShopCode()!=null){
                    //门店编号
                    listContentRow.createCell(2).setCellValue(excelList.getShopCode());
                }

                //店面名称
                if (excelList.getShopName()!=null){
                    listContentRow.createCell(3).setCellValue(excelList.getShopName());
                }

                //省份
                if (excelList.getProvince()!=null){
                    listContentRow.createCell(4).setCellValue(excelList.getProvince());
                }

                //答题经度
                if (excelList.getLongitude()!=null){
                    listContentRow.createCell(5).setCellValue(excelList.getLongitude());
                }

                //答题纬度
                if (excelList.getLatitude()!=null){
                    listContentRow.createCell(6).setCellValue(excelList.getLatitude());
                }

                //店面地址
                if (excelList.getAddress()!=null){
                    listContentRow.createCell(7).setCellValue(excelList.getAddress());
                }

                //负责业代
                //负责督导
                if (excelList.getUrgetName()!=null){
                    listContentRow.createCell(9).setCellValue(excelList.getUrgetName());
                }

                //自检得分
                if (excelList.getTotal_score()!=null){
                    listContentRow.createCell(10).setCellValue(excelList.getTotal_score());
                }

                //复检得分(判断)
                if (excelList.getTotal_score_reviewed()!=null){
                    listContentRow.createCell(11).setCellValue(excelList.getTotal_score_reviewed());
                }

                //最终分
                if (excelList.getTotal_score_reviewed()!=null){
                    listContentRow.createCell(12).setCellValue(excelList.getTotal_score_reviewed());
                }

                //答题开始时间
                if (excelList.getAnswer_start_time()!=null){
                    listContentRow.createCell(13).setCellValue(excelList.getAnswer_start_time());
                }

                //答题结束时间
                if (excelList.getAnswer_end_time()!=null){
                    listContentRow.createCell(14).setCellValue(excelList.getAnswer_end_time());
                }
                if (excelList.getParent_id()==null){
                    //所填答案
                    if (excelList.getAnswer_end_time()!=null){
                        listContentRow.createCell(i).setCellValue(excelList.getAnswer());
                    }
                }
                //备注（附加题的答案）
                if (excelList.getParent_id()!=null){
                    listContentRow.createCell(i+1).setCellValue(excelList.getAnswer());
                }else{
                    excelList.getShopCode();
                    excelList.getParent_id();
                    //把这两个值传到这个mapper中，返回值就是那个答案了然后放到备注中

                }
                if (excelList.getScore_appealed()!=null){
                    listContentRow.createCell(i+2).setCellValue(excelList.getScore_appealed());
                }else if (excelList.getScore_reviewed()!=null){
                    listContentRow.createCell(i+2).setCellValue(excelList.getTotal_score_reviewed());
                }else if (excelList.getScore()!=null){
                    listContentRow.createCell(i+2).setCellValue(excelList.getScore());
                }
                contentRowNum++;
            }
            i+=3;
        }

        String filename = "成绩统计表.xls";
        FileUtils.excelExport(response, request, wb, filename);
    }



}
