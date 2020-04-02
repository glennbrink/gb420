package com.glenn.tools.gb0420;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.glenn.tools.gb0420.model.ToolReceipt;
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
		ToolReceipt reciept = ToolUtil.checkout("LADW", "7/2/20", 3, "10%");
		reciept.print();
		assertTrue(reciept.isSuccess());
	}

	@Test
	void test3() {
		ToolReceipt reciept = ToolUtil.checkout("CHNS", "7/2/15", 5, "0%");
		reciept.print();
		assertTrue(reciept.isSuccess());
	}

	@Test
	void test4() {
		ToolReceipt reciept = ToolUtil.checkout("JAKD", "9/3/15", 6, "0%");
		reciept.print();
		assertTrue(reciept.isSuccess());
	}

	@Test
	void test5() {
		ToolReceipt reciept = ToolUtil.checkout("JAKR", "7/2/15", 9, "0%");
		reciept.print();
		assertTrue(reciept.isSuccess());
	}

	@Test
	void test6() {
		ToolReceipt reciept = ToolUtil.checkout("JAKR", "7/2/20", 4, "50%");
		reciept.print();
		assertTrue(reciept.isSuccess());
	}

}
