package com.glenn.tools.gb0420.model;

public class ToolCharge {

	private ToolType toolType;
	private double dailyCharge;
	private boolean weekdayCharge;
	private boolean weekendCharge;
	private boolean holidayCharge;
	
	
	public ToolCharge(ToolType toolType, double dailyCharge, boolean weekdayCharge, boolean weekendCharge,
			boolean holidayCharge) {
		super();
		this.toolType = toolType;
		this.dailyCharge = dailyCharge;
		this.weekdayCharge = weekdayCharge;
		this.weekendCharge = weekendCharge;
		this.holidayCharge = holidayCharge;
	}
	
	public ToolType getToolType() {
		return toolType;
	}
	public void setToolType(ToolType toolType) {
		this.toolType = toolType;
	}
	public double getDailyCharge() {
		return dailyCharge;
	}
	public void setDailyCharge(double dailyCharge) {
		this.dailyCharge = dailyCharge;
	}
	public boolean isWeekdayCharge() {
		return weekdayCharge;
	}
	public void setWeekdayCharge(boolean weekdayCharge) {
		this.weekdayCharge = weekdayCharge;
	}
	public boolean isWeekendCharge() {
		return weekendCharge;
	}
	public void setWeekendCharge(boolean weekendCharge) {
		this.weekendCharge = weekendCharge;
	}
	public boolean isHolidayCharge() {
		return holidayCharge;
	}
	public void setHolidayCharge(boolean holidayCharge) {
		this.holidayCharge = holidayCharge;
	}
	
	
}
