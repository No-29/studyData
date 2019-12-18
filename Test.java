import com.hjsj.hrms.utils.PubFunc;
import com.hrms.frame.codec.SafeCode;

import org.apache.log4j.Category;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.applet.Main;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:TODO
 * @Author hjsoft
 * @Date 2019/12/17 13:58
 * @Version V1.0
 **/
public class Test {

    public static final Category cat = Category.getInstance(Test.class);
    /*public static void main(String[] args) {
        *//*List<HashMap> fieldList = new ArrayList<HashMap>();
        List<HashMap> constraint = new ArrayList<HashMap>();
        HashMap field1 = getField("uid","int");
        HashMap field2 = getField("uname","varchar(20)");
        fieldList.add(field1);
        fieldList.add(field2);
        String tableName = "users";
        createTable(tableName,fieldList);*//*
        exportExcel();
    }*/

    private static void createTable(String tableName,List<HashMap> fieldList){
        StringBuffer createSql = new StringBuffer();
        createSql.append("create table ").append(tableName).append("(");
        for (HashMap field : fieldList){
            for (Object key:field.keySet()){
                createSql.append(key).append(" ").append(field.get(key)).append(",");
            }
        }
        createSql.setLength(createSql.length()-1);
        createSql.append(");");
        System.out.println(createSql);

        /**
         * create table tablename(
         * 	id int，
         * 	.......，
         * 	primary key(id)
         * 	)
         */
    }

    private static HashMap getField(String fieldName,String fieldType){
        HashMap field = new HashMap();
        field.put(fieldName,fieldType);
        return field;
    }

    private static void exportExcel(){
        //创建Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个页签
        HSSFSheet sheet = workbook.createSheet();
        //设置页签的标题
        workbook.setSheetName(0,"测试1");
        HashMap headMap = new HashMap();
        headMap.put("taxid","税率ID");
        headMap.put("taxitemid","税率明细ID");
        //创建第一行
        HSSFRow row = sheet.createRow(0);
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        //设置字体样式
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFFont.COLOR_NORMAL);
        font.setBold(true);
        //设置单元格样式
        HSSFCellStyle cellStyle= workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER );
        //创建第一个单元格提示信息
        HSSFComment comment0 = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short)4, 2, (short) 6, 0));
        comment0.setString(new HSSFRichTextString("taxid"));
        comment0.setAuthor("manjg");
        //创建第一个单元格
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellStyle(cellStyle);
        cell0.setCellComment(comment0);
        cell0.setCellValue((String)headMap.get("taxid"));

        //创建第二个单元格
        HSSFCell cell1 = row.createCell(1);
        //创建第一个单元格提示信息
        HSSFComment comment1 = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short)4, 2, (short) 6, 0));
        comment1.setString(new HSSFRichTextString("taxitemid"));
        comment1.setAuthor("manjg");
        cell1.setCellStyle(cellStyle);
        cell1.setCellComment(comment1);
        cell1.setCellValue((String) headMap.get("taxitemid"));

        row = sheet.createRow(1);
        cell0 = row.createCell(0);
        cell0.setCellStyle(cellStyle);
        cell0.setCellComment(comment0);
        cell0.setCellValue("1001");

        cell1 = row.createCell(1);
        cell1.setCellStyle(cellStyle);
        cell1.setCellComment(comment0);
        cell1.setCellValue("100101");
        try {
            FileOutputStream fileOut = new FileOutputStream(System.getProperty("java.io.tmpdir")+System.getProperty("file.separator")+"cs.xls");
            workbook.write(fileOut);
            String outName = SafeCode.encode(PubFunc.encrypt("cs.xls"));

        }catch (Exception e){

        }

    }
}