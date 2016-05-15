package edu.nefu.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import edu.nefu.icec.exam.domain.Student;

public class StudentRegistTool {
	public List<Student> readXls(String filePath) throws IOException {
		File file = new File(filePath);
		if (file.isFile()) {
			String[] fileInfo = file.getName().split("\\.");
			if (fileInfo[1].equals("xls")) {
				InputStream is = new FileInputStream(filePath);
				HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
				Student student = null;
				List<Student> list = new ArrayList<Student>();
				// 循环工作表Sheet
				for (int numSheet = 0; numSheet < hssfWorkbook
						.getNumberOfSheets(); numSheet++) {
					HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
					if (hssfSheet == null) {
						continue;
					}
					// 循环行Row
					for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
						HSSFRow hssfRow = hssfSheet.getRow(rowNum);
						if (hssfRow != null) {
							student = new Student();
							HSSFCell no = hssfRow.getCell(0);
							HSSFCell name = hssfRow.getCell(1);
							HSSFCell className = hssfRow.getCell(2);
							Integer a = (int) Double.parseDouble(getValue(no));
							student.setNumber(a.toString());
							student.setName(getValue(name));
							student.setClassname(getValue(className));
							list.add(student);
						}
					}
				}
				return list;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			// 返回布尔类型的值
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			// 返回数值类型的值
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			// 返回字符串类型的值
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
}
