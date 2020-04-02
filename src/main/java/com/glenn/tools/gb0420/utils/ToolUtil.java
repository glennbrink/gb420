package com.glenn.tools.gb0420.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.glenn.tools.gb0420.model.Tool;
import com.glenn.tools.gb0420.model.ToolCharge;
import com.glenn.tools.gb0420.model.ToolReceipt;
import com.glenn.tools.gb0420.model.ToolType;

public class ToolUtil {


	private static HashMap<ToolType, ToolCharge> toolChargeMap = new HashMap<ToolType, ToolCharge>();
	private static HashMap<String, Tool> toolMap = new HashMap<String, Tool>();
	private static SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy");
	private static DecimalFormat decimalFormatter = new DecimalFormat("$#,##0.00");

	static {
		// add charges for each tool type
		toolChargeMap.put(ToolType.Ladder, new ToolCharge(ToolType.Ladder, 1.99, true, true, false));
		toolChargeMap.put(ToolType.Chainsaw, new ToolCharge(ToolType.Chainsaw, 1.49, true, false, true));
		toolChargeMap.put(ToolType.Jackhammer, new ToolCharge(ToolType.Jackhammer, 2.99, true, false, false));

		// add all the tools
		toolMap.put("LADW", new Tool(ToolType.Ladder, "Werner", "LADW"));
		toolMap.put("CHNS", new Tool(ToolType.Chainsaw, "Stihl", "CHNS"));
		toolMap.put("JAKR", new Tool(ToolType.Jackhammer, "Ridgid", "JAKR"));
		toolMap.put("JAKD", new Tool(ToolType.Jackhammer, "DeWalt", "jAKD"));

	}

	public static ToolReceipt checkout(String toolCode, String dateStr, int days, String discountStr) {

		Tool tool = toolMap.get(toolCode);
		ToolCharge toolCharge = toolChargeMap.get(tool.getToolType());

		double totalPrice = 0;
		int chargedDays = 0;
		String returnDate = "";
		if (days < 1) {
			return new ToolReceipt("Sorry Rental Days must be equalt to 1 or greater");
		}
		
		double discount = Double.parseDouble(discountStr.replaceAll("%", ""));
		
		if(discount > 100 || discount < 0) {
			return new ToolReceipt("Invalid Discount %");
		}

		try {
			Date date = sdf.parse(dateStr);
			Calendar cal = null;
			for (int i = 0; i < days; i++) {
				boolean shouldCharge = true;
				
				cal = Calendar.getInstance();
				cal.setTime(date);

				cal.add(Calendar.DATE, i);

//				System.out.println("cal: " + sdf.format(new Date(cal.getTimeInMillis())));
				// weekend check
				if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
						|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
					if (!toolCharge.isWeekendCharge()) {
						shouldCharge = false;
					}
				// weekday check
				} else {
					if (!toolCharge.isWeekdayCharge()) {
						shouldCharge = false;
					}
				}
				
				//Laborday Check
				if(isLaborDay(cal)) {
					shouldCharge = false;
				}
				
				//Independence Day Check
				if(isIndependenceDay(cal)) {
					shouldCharge = false;
				}
				
				if (shouldCharge) {
					chargedDays++;
					totalPrice += toolCharge.getDailyCharge();
				}
				
			}

			cal = Calendar.getInstance();
			cal.setTime(date);

			cal.add(Calendar.DATE, days - 1);
			
			returnDate = sdf.format(new Date(cal.getTimeInMillis()));
		} catch (ParseException e) {
			return new ToolReceipt("Sorry Date Format Issue (MM/dd/yy)");
		}
		
		

		return new ToolReceipt(tool, toolCharge, days, dateStr, returnDate, chargedDays, totalPrice, discount);
	}
	
	public static boolean isLaborDay(Calendar cal) {
		
		if(cal.get(Calendar.MONTH) == Calendar.SEPTEMBER) {
			if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				if(cal.get(Calendar.DAY_OF_MONTH) <= 7) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isIndependenceDay(Calendar cal) {
		if(cal.get(Calendar.MONTH) == Calendar.JULY) {
			//4th
			if(cal.get(Calendar.DAY_OF_MONTH) == 4 && (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && Calendar.DAY_OF_WEEK != Calendar.SUNDAY)){
				return true;
			}
			
			//3rd and Friday
			if(cal.get(Calendar.DAY_OF_MONTH) == 3 && cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
				return true;
			}
			
			//5th and Monday
			if(cal.get(Calendar.DAY_OF_MONTH) == 5 && cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
				return true;
			}
			
		}
		return false;
	}
	
	public static String formatCurency(double m) {
		return decimalFormatter.format(m);
	}
}
