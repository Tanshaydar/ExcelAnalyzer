package com.tansel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;

import com.tansel.controller.ExcelReaderController;
import com.tansel.model.RowInformation;

public class ExcelReader {

	public static void main(String[] args) {
		File file = new File("metrajNetwork-orjinal.xls");
		
		ExcelReaderController controller = new ExcelReaderController();

		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;
			
			int rows;
			rows = sheet.getPhysicalNumberOfRows();
			
			int cols = 0;
			int tmp = 0;
			
			for(int i = 0; i < 10 || i < rows; i++){
				row = sheet.getRow(i);
				if(row != null){
					tmp = sheet.getRow(i).getPhysicalNumberOfCells();
					if(tmp > cols) {
						cols = tmp;
					}
				}
			}
			
			for(int j = 0; j < rows; j++){
				row = sheet.getRow(j);
				if(j == 0){
					continue;
				}
				
				if(row != null){
					row.getCell(7).setCellType(CellType.STRING);
					row.getCell(9).setCellType(CellType.STRING);
					controller.addInformation(new RowInformation(
							(int)Double.parseDouble(row.getCell(0).toString()),
							Double.parseDouble(row.getCell(1).getStringCellValue()),
							Double.parseDouble(row.getCell(2).getStringCellValue()),
							Double.parseDouble(row.getCell(3).getStringCellValue()),
							Double.parseDouble(row.getCell(4).getStringCellValue()),
							Double.parseDouble(row.getCell(5).getStringCellValue()), 
							Double.parseDouble(row.getCell(6).getStringCellValue()), 
							Double.parseDouble(row.getCell(7).getStringCellValue()), 
							row.getCell(8).getStringCellValue(), 
							row.getCell(9).getStringCellValue()));
					/*
					for(int k = 0; k < cols; k++){
						cell = row.getCell(k);
						if(cell != null){
							System.out.println(k + " - " + cell + " -> " + j);
						}
					}*/
				}
			}
			wb.close();
			fs.close();;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		controller.printAll();
		controller.analyzeData();
		controller.writeToFile();
	}

}
