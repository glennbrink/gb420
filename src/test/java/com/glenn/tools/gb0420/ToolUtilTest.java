package com.glenn.tools.gb0420;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.glenn.tools.gb0420.model.ToolReceipt;
import com.glenn.tools.gb0420.model.ToolType;
import com.glenn.tools.gb0420.utils.ToolUtil;

class ToolUtilTest {

	@Test
	void test1() {
		ToolReceipt reciept = ToolUtil.checkout("JAKR", "9/3/15", 5, "101%");
		reciept.print();
		assertFalse(reciept.isSuccess());
	}

	@Test
	void test2() {
		ToolReceipt tr = new ToolReceipt("LADW", ToolType.Ladder, "Werner", 3, "7/2/20", "7/4/20", 1.99, 2, 3.98, 10, .398, 3.58);
		
		ToolReceipt reciept = ToolUtil.checkout("LADW", "7/2/20", 3, "10%");
		reciept.print();
		assertTrue(reciept.isSuccess() && tr.compare(reciept));
	}

	@Test
	void test3() {
		ToolReceipt tr = new ToolReceipt("CHNS", ToolType.Chainsaw, "Stihl", 5, "7/2/15", "7/6/15", 1.49, 2, 2.98, 0, 0, 2.98);
		
		ToolReceipt reciept = ToolUtil.checkout("CHNS", "7/2/15", 5, "0%");
		reciept.print();
		assertTrue(reciept.isSuccess() && tr.compare(reciept));
	}

	@Test
	void test4() {
		ToolReceipt tr = new ToolReceipt("JAKD", ToolType.Jackhammer, "DeWalt", 6, "9/3/15", "9/8/15", 2.99, 3, 8.97, 0, 0, 8.97);
		
		ToolReceipt reciept = ToolUtil.checkout("JAKD", "9/3/15", 6, "0%");
		reciept.print();
		assertTrue(reciept.isSuccess() && tr.compare(reciept));
	}

	@Test
	void test5() {
		ToolReceipt tr = new ToolReceipt("JAKR", ToolType.Jackhammer, "Ridgid", 9, "7/2/15", "7/10/15", 2.99, 6, 17.94, 0, 0, 17.94);
		
		ToolReceipt reciept = ToolUtil.checkout("JAKR", "7/2/15", 9, "0%");
		reciept.print();
		assertTrue(reciept.isSuccess() && tr.compare(reciept));
	}

	@Test
	void test6() {
		ToolReceipt tr = new ToolReceipt("JAKR", ToolType.Jackhammer, "Ridgid", 4, "7/2/20", "7/5/20", 2.99, 1, 2.99, 50, 1.50, 1.49);
		
		ToolReceipt reciept = ToolUtil.checkout("JAKR", "7/2/20", 4, "50%");
		reciept.print();
		assertTrue(reciept.isSuccess() && tr.compare(reciept));
	}

}
