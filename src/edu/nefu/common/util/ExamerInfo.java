package edu.nefu.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import edu.nefu.icec.exam.bean.ExamerBean;

public class ExamerInfo {
	public static String outputFile = "test.xls"; 
	public String downloadExamer(String downloadPath,List<ExamerBean> listBeans){
		FileOutputStream fos = null;
		try {
			// 创建新的Excel 工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel工作簿中建一工作表，其名为缺省值
			// 如要新建一名为"效益指标"的工作表，其语句为：
			// HSSFSheet sheet = workbook.createSheet("效益指标");
			HSSFSheet sheet = workbook.createSheet("考生信息");
			sheet.setColumnWidth(0, 2000);
			sheet.setColumnWidth(1, 4000);
			sheet.setColumnWidth(2, 4000);
			sheet.setColumnWidth(3, 4000);
			sheet.setColumnWidth(4, 4000);
			
			HSSFRow row =sheet.createRow(0);
			 
			HSSFCellStyle cellstyle = workbook.createCellStyle();
			cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			// 设置单元格边框
			cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		 
			// 在索引0的位置创建行（最顶端的行）  
			HSSFCell ce1 = row.createCell(0);
			ce1.setCellValue("编号");
			ce1.setCellStyle(cellstyle);
			
			HSSFCell ce2 = row.createCell(1);
			ce2.setCellValue("考生学号");
			ce2.setCellStyle(cellstyle);
			
			HSSFCell ce3 = row.createCell(2);
			ce3.setCellValue("考生姓名");
			ce3.setCellStyle(cellstyle);
			
			HSSFCell ce4 = row.createCell(3);
			ce4.setCellValue("考生班级");
			ce4.setCellStyle(cellstyle);

			HSSFCell ce5 = row.createCell(4);
			ce5.setCellValue("考生密码");
			ce5.setCellStyle(cellstyle);
			
			for(int i = 1 ; i<=listBeans.size();i++){
				HSSFRow row1 =sheet.createRow(i);
				
				// 在索引0的位置创建行（最顶端的行）
				HSSFCell cell1 = row1.createCell(0);
				cell1.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell1.setCellStyle(cellstyle);
				cell1.setCellValue(i);
				
				HSSFCell cell2 = row1.createCell(1);
				cell2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell2.setCellStyle(cellstyle);
				cell2.setCellValue(Integer.parseInt(listBeans.get(i-1).getNumber()));
				
				HSSFCell cell3 = row1.createCell(2);
				cell3.setCellValue(listBeans.get(i-1).getName());
				cell3.setCellStyle(cellstyle);
				
				HSSFCell cell4 = row1.createCell(3); 
				cell4.setCellStyle(cellstyle);
				cell4.setCellValue(listBeans.get(i-1).getClassName());
				
				HSSFCell cell5 = row1.createCell(4);
				cell5.setCellType(HSSFCell.CELL_TYPE_NUMERIC); 
				cell5.setCellStyle(cellstyle);
				cell5.setCellValue(Integer.parseInt(listBeans.get(i-1).getPassword()));
			}
			
			fos = new FileOutputStream(downloadPath);
			workbook.write(fos);
			fos.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
