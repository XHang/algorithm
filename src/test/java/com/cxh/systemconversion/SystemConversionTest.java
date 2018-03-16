package com.cxh.systemconversion;

import org.junit.Test;

/**
 * 
 * @author cxh
 * 
 */
public class SystemConversionTest {

	@Test
	public void binarySystemConversionDecimalTest() {
		int decimal = SystemConversion.binarySystemConversionDecimal("111011011");
		System.out.println("十进制："+decimal);
	}
	@Test
	public void test() {
		Integer num = 387;
		System.out.println(num == 387);
	}
}
