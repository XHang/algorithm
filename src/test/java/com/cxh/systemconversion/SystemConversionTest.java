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
		String binary = "1110110111";
		int decimal = SystemConversion.binarySystemConversionDecimal(binary);
		System.out.println("二进制"+binary+"十进制："+decimal);
	}
	
	@Test
	public void octalConversionDecimalTest() {
		String ocatl = "777777";
		int decimal = SystemConversion.octalConversionDecemal(ocatl);
		System.out.println("八进制"+ocatl+"转成十进制的结果是"+decimal);
	}
	
	@Test
	public void hexToDecimalTest() {
		String hex = "AA1778";
		int decimal = SystemConversion.hexaDecimalToDecimal(hex);
		System.out.println("十六进制"+hex+"转十进制的结果是"+decimal);
	}
	
	@Test
	public void decimalToBinaryTest() {
		int decimal = 5;
		int binary = SystemConversion.decimalToBinary(decimal);
		System.out.println(decimal+"十进制二进制的结果是"+binary);
	}
	
	@Test
	public void decimalToHex() {
		int decimal = 99999;
		String hex = SystemConversion.decimalToHex(decimal);
		System.out.println("十进制"+decimal+"十六进制的结果是"+hex);
	}
	@Test
	public void binaryToOctal() {
		String binary = "11010111";
		String octal = SystemConversion.binaryToOctal(binary);
		System.out.println("二进制"+binary+"转成的八进制是"+octal);
	}
	
	
}
