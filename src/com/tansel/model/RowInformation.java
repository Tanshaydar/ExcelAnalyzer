package com.tansel.model;

import java.text.DecimalFormat;

public class RowInformation {

	private int no;
	private double startX;
	private double startY;
	private double startZ;
	private double endX;
	private double endY;
	private double endZ;
	private double length;
	private String layer;
	private String zone;
	
	public RowInformation() {}

	public RowInformation(int no, double startX, double startY, double startZ, double endX, double endY, double endZ,
			double length, String layer, String zone) {
		super();
		this.no = no;
		this.startX = startX;
		this.startY = startY;
		this.startZ = startZ;
		this.endX = endX;
		this.endY = endY;
		this.endZ = endZ;
		this.length = length;
		this.layer = layer;
		this.zone = zone;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public double getStartX() {
		return startX;
	}

	public void setStartX(double startX) {
		this.startX = startX;
	}

	public double getStartY() {
		return startY;
	}

	public void setStartY(double startY) {
		this.startY = startY;
	}

	public double getStartZ() {
		return startZ;
	}

	public void setStartZ(double startZ) {
		this.startZ = startZ;
	}

	public double getEndX() {
		return endX;
	}

	public void setEndX(double endX) {
		this.endX = endX;
	}

	public double getEndY() {
		return endY;
	}

	public void setEndY(double endY) {
		this.endY = endY;
	}

	public double getEndZ() {
		return endZ;
	}

	public void setEndZ(double endZ) {
		this.endZ = endZ;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}
	
	@Override
	public String toString() {
		String toReturn = "";
		toReturn += "No: " + getNo() + "\t";
		toReturn += "Start X: " + getStartX() + "\t";
		toReturn += "Start Y: " + getStartY() + "\t";
		toReturn += "Start Z: " + getStartZ() + "\t";
		toReturn += "End X:" + getEndX() + "\t";
		toReturn += "End Y:" + getEndY() + "\t";
		toReturn += "End Z:" + getEndZ() + "\t";
		toReturn += "Length: " + getLength() + "\t";
		toReturn += "Layer: " + getLayer() + "\t";
		toReturn += "Zone: " + getZone() + "\t";
		return toReturn;
	}
	
	public String csvFormat(){
		DecimalFormat decimalFormat = new DecimalFormat("#.0000");
		String csv = "";
		csv += getNo() + ";";
		csv += decimalFormat.format(getStartX()) + ";";
		csv += decimalFormat.format(getStartY()) + ";";
		csv += decimalFormat.format(getStartZ()) + ";";
		csv += decimalFormat.format(getEndX()) + ";";
		csv += decimalFormat.format(getEndY()) + ";";
		csv += decimalFormat.format(getEndZ()) + ";";
		csv += decimalFormat.format(getLength()) + ";";
		csv += getLayer() + ";";
		csv += getZone();
		return csv;
	}
	
}
