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
         * 	id int��
         * 	.......��
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
        //����Excel�ļ�
        HSSFWorkbook workbook = new HSSFWorkbook();
        //����һ��ҳǩ
        HSSFSheet sheet = workbook.createSheet();
        //����ҳǩ�ı���
        workbook.setSheetName(0,"����1");
        HashMap headMap = new HashMap();
        headMap.put("taxid","˰��ID");
        headMap.put("taxitemid","˰����ϸID");
        //������һ��
        HSSFRow row = sheet.createRow(0);
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        //����������ʽ
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFFont.COLOR_NORMAL);
        font.setBold(true);
        //���õ�Ԫ����ʽ
        HSSFCellStyle cellStyle= workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER );
        //������һ����Ԫ����ʾ��Ϣ
        HSSFComment comment0 = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short)4, 2, (short) 6, 0));
        comment0.setString(new HSSFRichTextString("taxid"));
        comment0.setAuthor("manjg");
        //������һ����Ԫ��
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellStyle(cellStyle);
        cell0.setCellComment(comment0);
        cell0.setCellValue((String)headMap.get("taxid"));

        //�����ڶ�����Ԫ��
        HSSFCell cell1 = row.createCell(1);
        //������һ����Ԫ����ʾ��Ϣ
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