package com.cxh.systemconversion;

import org.junit.Test;

/**
 * 进制转换
 * @author cxh
 *
 */
public class SystemConversion {

	/**
	 * 二进制转十进制
	 * @return
	 * 
	 */
	public static int binarySystemConversionDecimal(String binary) {
		char[] charArr = binary.toCharArray();
		int sum=0;
		int exp=0;
		for(int i=charArr.length-1;i>=0;i--) {
			char c = charArr[i];
			Integer bit = new Integer(c+"");
			if(bit!=-1)
			sum=(int) (sum+(bit*Math.pow(2, exp)));
			exp++;
		}
		return sum;
	}
	
	
}
