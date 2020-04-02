package com.glenn.tools.gb0420.model;

import com.glenn.tools.gb0420.utils.ToolUtil;

public class ToolReceipt {

	private String toolCode; // Tool code - Specified at checkout
	private String toolType; // Tool type - From tool info
	private String toolBrand; // Tool brand - From tool info
	private int rentalDays; // Rental days - Specified at checkout
	private String checkoutDate; // Check out date - Specified at checkout
	private String dueDate; // Due date - Calculated from checkout date and rental days.
	private double dailyRentalCharge; // Daily rental charge - Amount per day, specified by the tool type.
	private int chargeDays; // Charge days - Count of chargeable days, from day after checkout through and
							// including due date, excluding “no charge” days as specified by the tool type.
	private double preDiscountCharge; // Pre-discount charge - Calculated as charge days X daily charge. Resulting
										// total rounded half up to cents.
	private double discountPercent; // Discount percent - Specified at checkout.
	private double discountAmount; // Discount amount - calculated from discount % and pre-discount charge.
									// Resulting amount rounded half up to cents.
	private double finalCharge; // Final charge - Calculated as pre-discount charge - discount amount.

	private boolean success;
	private String errorMessage;

	public ToolReceipt(Tool tool, ToolCharge toolCharge, int rentalDays, String checkoutDate, String dueDate,
			int chargeDays, double preDiscountCharge, double discountPercent) {
		super();
		this.toolCode = tool.getToolCode();
		this.toolType = tool.getToolType().toString();
		this.toolBrand = tool.getBrand();
		this.rentalDays = rentalDays;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.dailyRentalCharge = toolCharge.getDailyCharge();
		this.chargeDays = chargeDays;
		this.preDiscountCharge = preDiscountCharge;
		this.discountPercent = discountPercent;
		this.discountAmount = this.preDiscountCharge * (this.discountPercent/100);
		this.finalCharge = this.preDiscountCharge - this.discountAmount;

		this.success = true;
	}

	public ToolReceipt(String errorMessage) {
		this.success = false;
		this.errorMessage = errorMessage;
	}

	public void print() {

		if (this.success) {
			System.out.println("");
			System.out.println("****************************************");
			System.out.println("Tool code: " + toolCode);
			System.out.println("Tool type: " + toolType);
			System.out.println("Tool Brand: " + toolBrand);
			System.out.println("Rental Days: " + rentalDays);
			System.out.println("Checkout Date: " + checkoutDate);
			System.out.println("Due Date: " + dueDate);
			System.out.println("Daily rental charge: " + ToolUtil.formatCurency(dailyRentalCharge));
			System.out.println("Charge Days: " + chargeDays);
			System.out.println("Pre-discount charge: " + ToolUtil.formatCurency(preDiscountCharge));
			System.out.println("Discount Percent: " + discountPercent + "%");
			System.out.println("Discount Amount: " + ToolUtil.formatCurency(discountAmount));
			System.out.println("Final Charge: " + ToolUtil.formatCurency(finalCharge));
			System.out.println("****************************************");
		} else {
			System.out.println("");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println(errorMessage);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
	}

	public String getToolCode() {
		return toolCode;
	}

	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}

	public String getToolType() {
		return toolType;
	}

	public void setToolType(String toolType) {
		this.toolType = toolType;
	}

	public String getToolBrand() {
		return toolBrand;
	}

	public void setToolBrand(String toolBrand) {
		this.toolBrand = toolBrand;
	}

	public int getRentalDays() {
		return rentalDays;
	}

	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public double getDailyRentalCharge() {
		return dailyRentalCharge;
	}

	public void setDailyRentalCharge(double dailyRentalCharge) {
		this.dailyRentalCharge = dailyRentalCharge;
	}

	public int getChargeDays() {
		return chargeDays;
	}

	public void setChargeDays(int chargeDays) {
		this.chargeDays = chargeDays;
	}

	public double getPreDiscountCharge() {
		return preDiscountCharge;
	}

	public void setPreDiscountCharge(double preDiscountCharge) {
		this.preDiscountCharge = preDiscountCharge;
	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getFinalCharge() {
		return finalCharge;
	}

	public void setFinalCharge(double finalCharge) {
		this.finalCharge = finalCharge;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
