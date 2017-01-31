package com.tansel.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.tansel.model.RowInformation;

public class ExcelReaderController {
	
	private static final String header = "No;Start X;Start Y;Start Z;End X;End Y;End Z;Length;Layer;Zone";

	private List<RowInformation> rowInformations;
	private List<RowInformation> listOfMissingBothXY;
	private List<RowInformation> listOfMissingStartX;
	private List<RowInformation> listOfMissingStartY;
	private List<RowInformation> listOfMissingStart;
	private List<RowInformation> listOfMissingEndX;
	private List<RowInformation> listOfMissingEndY;
	private List<RowInformation> listOfMissingEnd;
	
	public ExcelReaderController() {
		rowInformations = new ArrayList<>();
		listOfMissingBothXY = new ArrayList<>();
		listOfMissingStartX = new ArrayList<>();
		listOfMissingStartY = new ArrayList<>();
		listOfMissingStart = new ArrayList<>();
		listOfMissingEndX = new ArrayList<>();
		listOfMissingEndY = new ArrayList<>();
		listOfMissingEnd = new ArrayList<>();
	}
	
	public void addInformation(RowInformation rowInformation){
		rowInformations.add(rowInformation);
	}

	public void printAll() {
		for (RowInformation rowInformation : rowInformations) {
			System.out.println(rowInformation);
		}
	}
	
	public void analyzeData(){
		for (RowInformation row : rowInformations) {
			boolean hasStartX = false;
			boolean hasStartY = false;
			boolean hasEndX = false;
			boolean hasEndY = false;
			for (RowInformation reverse : rowInformations) {
				if(row.getStartX() == reverse.getEndX()) {
					hasStartX = true;
				}
				if(row.getStartY() == reverse.getEndY()) {
					hasStartY = true;
				}
				if(row.getEndX() == reverse.getStartX()) {
					hasEndX = true;
				}
				if(row.getEndY() == reverse.getStartY()) {
					hasEndY = true;
				}
			}
			
			if(!hasStartX && !hasStartY && !hasEndX && !hasEndY){
				System.out.println("Hi�bir koordinatta hi�bir yere ba�l� de�il: ");
				System.out.println(row);
				listOfMissingBothXY.add(row);
			} else {
				if(!hasStartX && !hasStartY) {
					System.out.println("Hi�bir koordinatta ba�lang�c� hi�bir yere ba�l� de�il: ");
					System.out.println(row);
					listOfMissingStart.add(row);
				} else if(!hasStartX) {
					System.out.println("Sadece X koordinat�nda ba�lang�c� hi�bir yere ba�l� de�il: ");
					System.out.println(row);
					listOfMissingStartX.add(row);
				} else if(!hasStartY) {
					System.out.println("Sadece Y koordinat�nda ba�lang�c� hi�bir yere ba�l� de�il: ");
					System.out.println(row);
					listOfMissingStartY.add(row);
				}
				if(!hasEndX && !hasEndY) {
					System.out.println("Hi�bir koordinatta sonu hi�bir yere ba�l� de�il: ");
					System.out.println(row);
					listOfMissingEnd.add(row);
				} else if(!hasEndX){
					System.out.println("Sadece X koordinat�nda sonu hi�bir yere ba�l� de�il: ");
					System.out.println(row);
					listOfMissingEndX.add(row);
				} else if(!hasEndY) {
					System.out.println("Sadece Y koordinat�nda sonu hi�bir yere ba�l� de�il: ");
					System.out.println(row);
					listOfMissingEndY.add(row);
				}
			}
		}
		
		System.out.println("Hi�bir koordinatta hi�bir yere ba�l� olmayanlar�n say�s�: " + listOfMissingBothXY.size());
		System.out.println("Hi�bir koordinatta ba�lang�c� hi�bir yere ba�l� olmayanlar�n say�s�: " + listOfMissingStart.size());
		System.out.println("Sadece X koordinat�nda ba�lang�c� olmayanlar�n say�s�: " + listOfMissingStartX.size());
		System.out.println("Sadece Y koordinat�nda ba�lang�c� olmayanlar�n say�s�: " + listOfMissingStartY.size());
		System.out.println("Hi�bir koordinatta sonu hi�bir yere ba�l� olmayanlar�n say�s�: " + listOfMissingEnd.size());
		System.out.println("Sadece X koordinat�nda sonu hi�bir yere ba�l� olmayanlar�n say�s�: " + listOfMissingEndX.size());
		System.out.println("Sadece Y koordinat�nda sonu hi�bir yere ba�l� olmayanlar�n say�s�: " + listOfMissingEndY.size());
	}
	
	public void writeToFile(){
		List<String> toWrite = new ArrayList<>();
		toWrite.add(header);
		for (RowInformation row : listOfMissingBothXY) {
			toWrite.add(row.csvFormat());
		}
		Path file = Paths.get("Hi�bir koordinatta hi�bir yere ba�l� olmayanlar.csv");
		try {
			Files.write(file, toWrite, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		toWrite = new ArrayList<>();
		toWrite.add(header);
		for (RowInformation row : listOfMissingStart) {
			toWrite.add(row.csvFormat());
		}
		file = Paths.get("Hi�bir koordinatta ba�lang�c� hi�bir yere ba�l� olmayanlar.csv");
		try {
			Files.write(file, toWrite, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}


		toWrite = new ArrayList<>();
		toWrite.add(header);
		for (RowInformation row : listOfMissingStartX) {
			toWrite.add(row.csvFormat());
		}
		file = Paths.get("Sadece X koordinat�nda ba�lang�c� olmayanlar.csv");
		try {
			Files.write(file, toWrite, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		toWrite = new ArrayList<>();
		toWrite.add(header);
		for (RowInformation row : listOfMissingStartY) {
			toWrite.add(row.csvFormat());
		}
		file = Paths.get("Sadece Y koordinat�nda ba�lang�c� olmayanlar.csv");
		try {
			Files.write(file, toWrite, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		toWrite = new ArrayList<>();
		toWrite.add(header);
		for (RowInformation row : listOfMissingEnd) {
			toWrite.add(row.csvFormat());
		}
		file = Paths.get("Hi�bir koordinatta sonu hi�bir yere ba�l� olmayanlar.csv");
		try {
			Files.write(file, toWrite, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		toWrite = new ArrayList<>();
		toWrite.add(header);
		for (RowInformation row : listOfMissingEndX) {
			toWrite.add(row.csvFormat());
		}
		file = Paths.get("Sadece X koordinat�nda sonu hi�bir yere ba�l� olmayanlar.csv");
		try {
			Files.write(file, toWrite, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		toWrite = new ArrayList<>();
		toWrite.add(header);
		for (RowInformation row : listOfMissingEndY) {
			toWrite.add(row.csvFormat());
		}
		file = Paths.get("Sadece Y koordinat�nda sonu hi�bir yere ba�l� olmayanlar.csv");
		try {
			Files.write(file, toWrite, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
