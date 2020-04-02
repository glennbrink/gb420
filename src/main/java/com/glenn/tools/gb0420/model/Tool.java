package com.glenn.tools.gb0420.model;

public class Tool {
	private ToolType toolType;
	private String brand;
	private String toolCode;
	
	public Tool(ToolType toolType, String brand, String toolCode) {
		super();
		this.toolType = toolType;
		this.brand = brand;
		this.toolCode = toolCode;
	}
	
	public ToolType getToolType() {
		return toolType;
	}
	public void setToolType(ToolType toolType) {
		this.toolType = toolType;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getToolCode() {
		return toolCode;
	}
	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}
	
}
