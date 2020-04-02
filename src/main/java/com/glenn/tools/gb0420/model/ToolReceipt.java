package com.glenn.tools.gb0420.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.glenn.tools.gb0420.utils.ToolUtil;

public class ToolReceipt {

	private String toolCode; // Tool code - Specified at checkout
	private String toolType; // Tool type - From tool info
	private String toolBrand; // Tool brand - From tool info
	private int rentalDays; // Rental days - Specified at checkout
	private String checkoutDate; // Check out date - Specified at checkout
	private String dueDate; // Due date - Calculated from checkout date and rental days.
	private BigDecimal dailyRentalCharge; // Daily rental charge - Amount per day, specified by the tool type.
	private int chargeDays; // Charge days - Count of chargeable days, from day after checkout through and
							// including due date, excluding “no charge” days as specified by the tool type.
	private BigDecimal preDiscountCharge; // Pre-discount charge - Calculated as charge days X daily charge. Resulting
										// total rounded half up to cents.
	private BigDecimal discountPercent; // Discount percent - Specified at checkout.
	private BigDecimal discountAmount; // Discount amount - calculated from discount % and pre-discount charge.
									// Resulting amount rounded half up to cents.
	private BigDecimal finalCharge; // Final charge - Calculated as pre-discount charge - discount amount.

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
		this.dailyRentalCharge = new BigDecimal(toolCharge.getDailyCharge());
		this.chargeDays = chargeDays;
		this.preDiscountCharge = new BigDecimal(preDiscountCharge);
		this.discountPercent = new BigDecimal(discountPercent);
		this.discountAmount = this.preDiscountCharge.multiply(this.discountPercent.divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_EVEN);
		this.finalCharge = this.preDiscountCharge.subtract(this.discountAmount).setScale(2, RoundingMode.HALF_EVEN);

		this.success = true;
	}
	
	

	public ToolReceipt(String toolCode, ToolType toolType, String toolBrand, int rentalDays, String checkoutDate,
			String dueDate, double dailyRentalCharge, int chargeDays, double preDiscountCharge,
			double discountPercent, double discountAmount, double finalCharge) {
		super();
		this.toolCode = toolCode;
		this.toolType = toolType.toString();
		this.toolBrand = toolBrand;
		this.rentalDays = rentalDays;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.dailyRentalCharge = new BigDecimal(dailyRentalCharge);
		this.chargeDays = chargeDays;
		this.preDiscountCharge = new BigDecimal(preDiscountCharge);
		this.discountPercent = new BigDecimal(discountPercent);
		this.discountAmount = new BigDecimal(discountAmount).setScale(2, RoundingMode.HALF_EVEN);
		this.finalCharge = new BigDecimal(finalCharge).setScale(2, RoundingMode.HALF_EVEN);
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
			System.out.println("Daily rental charge: " + ToolUtil.formatCurency(dailyRentalCharge.doubleValue()));
			System.out.println("Charge Days: " + chargeDays);
			System.out.println("Pre-discount charge: " + ToolUtil.formatCurency(preDiscountCharge.doubleValue()));
			System.out.println("Discount Percent: " + discountPercent + "%");
			System.out.println("Discount Amount: " + ToolUtil.formatCurency(discountAmount.doubleValue()));
			System.out.println("Final Charge: " + ToolUtil.formatCurency(finalCharge.doubleValue()));
			System.out.println("****************************************");
		} else {
			System.out.println("");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println(errorMessage);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
	}
	
	public boolean compare(ToolReceipt other) {
		boolean same = true;
		
		same = this.toolCode.equals(other.getToolCode()) && same;
		same = this.toolType.equals(other.getToolType()) && same;
		same = this.toolBrand.equals(other.getToolBrand()) && same;
		same = this.rentalDays == other.getRentalDays() && same;
		same = this.checkoutDate.equals(other.getCheckoutDate()) && same;
		same = this.dueDate.equals(other.getDueDate()) && same;
		same = this.dailyRentalCharge.equals(other.getDailyRentalCharge()) && same;
		same = this.chargeDays == other.getChargeDays() && same;
		same = this.preDiscountCharge.equals(other.getPreDiscountCharge()) && same;
		same = this.discountPercent.equals(other.getDiscountPercent()) && same;
		same = this.discountAmount.equals(other.getDiscountAmount() ) && same;
		same = this.finalCharge.equals(other.getFinalCharge()) && same;
		
		return same;
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

	public BigDecimal getDailyRentalCharge() {
		return dailyRentalCharge;
	}

	public void setDailyRentalCharge(double dailyRentalCharge) {
		this.dailyRentalCharge = new BigDecimal(dailyRentalCharge);
	}

	public int getChargeDays() {
		return chargeDays;
	}

	public void setChargeDays(int chargeDays) {
		this.chargeDays = chargeDays;
	}

	public BigDecimal getPreDiscountCharge() {
		return preDiscountCharge;
	}

	public void setPreDiscountCharge(double preDiscountCharge) {
		this.preDiscountCharge = new BigDecimal(preDiscountCharge);
	}

	public BigDecimal getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = new BigDecimal(discountPercent);
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = new BigDecimal(discountAmount);
	}

	public BigDecimal getFinalCharge() {
		return finalCharge;
	}

	public void setFinalCharge(double finalCharge) {
		this.finalCharge = new BigDecimal(finalCharge);
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
