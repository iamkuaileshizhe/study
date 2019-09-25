package cn.com.trying.io;


import org.apache.commons.lang.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class ExportDataFromExcel {
	public static void main(String[] args)   {
		generateSql();
	}

	public static void generateSql(){
		try {
			//File file = new File("e:/123.xls");
			File file = new File("user.xlsx");
			InputStream is = new FileInputStream(file.getAbsolutePath());
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getLastRowNum(); // 获取总行数

			String str = "";
			for(int i=1;i<=rowCount;i++) {
				Row row = sheet.getRow(i);
				if (row == null) {
					continue;
				}

				String col1=getStringCell(row,0);//账号
				String col2=getStringCell(row,1);//姓名
				String col3=getStringCell(row,2);//电话
				String col4=getStringCell(row,3);//部门
				String col5=getStringCell(row,4);//角色
//				str += col1+"--------------"+col2+";";
				String userId =  UUID.randomUUID().toString().replaceAll("-", "");
				String infoId =  UUID.randomUUID().toString().replaceAll("-", "");
				String roId =  UUID.randomUUID().toString().replaceAll("-", "");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dTime = df.format(new Date());
				str +="insert into \"S_USER\"(\"ID\",\"USERCODE\",\"PASSWORD\",\"STATUS\",\"CDT\",\"UDT\",\"CUSER\",\"UUSER\")values('"+userId+"','"+col1+"','98830C3F22C10D2C6B67D4B2115AFE7C','1','"+dTime+"','"+dTime+"','17c4520f6cfd1ab53d8745e84681eb49','17c4520f6cfd1ab53d8745e84681eb49');";
				str +="insert into \"S_USERINFO\"(\"ID\",\"NAME\",\"USERID\",\"SEX\",\"MOBILE\",\"STATUS\")values('"+infoId+"','"+col2+"','"+userId+"','1','"+col3+"','1');";
				str +="insert into \"S_USERDEPT\"(\"USERID\",\"DEPTID\",\"STATUS\")values('"+userId+"','"+col4+"','1');";
				str +="insert into \"P_USERROLE\"(\"ID\",\"ROLEID\",\"USERID\",\"STATUS\")values('"+roId+"','"+col5+"','"+userId+"','1');";
			}
//			File desFile = new File("/home/trying/2.txt");

			FileWriter fw=new FileWriter("sql.txt");
			fw.write(str);
			fw.close();

		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String getStringCell(Row row,int num ){
		String str = "";
		Cell cell = row.getCell(num);
		if(cell != null){
			cell.setCellType(Cell.CELL_TYPE_STRING);
			str=cell.getStringCellValue();
		}

		return str;
	}

	private static String getStringEmpty(String s){
		if(StringUtils.isNotBlank(s)){
			return "'"+s+"'";
		}else{
			return "''";
		}
	}
}
