package com.cxh.systemconversion;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 进制转换
 * @author cxh
 *
 */
public class SystemConversion {

	/**
	 * 二进制转十进制
	 * @param binary 二进制
	 * @return
	 * 基本思路是把二进制从右往左开始数起，每一位作为相乘的一位因数，去乘以２^n
	 * n则是因数在二进制数从右往左数起的顺序，从０开始．
	 */
	public static int binarySystemConversionDecimal(String binary) {
		char[] charArr = binary.toCharArray();
		int sum=0;
		int exp=0;
		for(int i=charArr.length-1;i>=0;i--) {
			char c = charArr[i];
			Integer bit = string2Binary(c+"");
			if(bit!=-1)
			sum=(int) (sum+(bit*Math.pow(2, exp)));
			exp++;
		}
		return sum;
	}
	private static Integer string2Binary(String num) {
		try {
			Integer bit = new Integer(num);
			if(bit ==0 || bit ==1) {
				return bit;
			}
			throw new ArithmeticException("您输入的数字中"+bit+"不是规范的二进制格式,请确认");
		} catch (NumberFormatException e) {
			throw new ArithmeticException("您输入的数字中"+num+"不是规范的数字格式,请确认");
		}
	}
	
	
	/**
	 * 八进制转十进制，其思路也跟二进制转十进制的一样
	 * @param octal 八进制
	 * @return
	 */
	public static int octalConversionDecemal(String octal) {
		char[] charArray = octal.toCharArray();
		int sum = 0;
		int exp = 0;
		for(int i=charArray.length-1;i>=0;i--,exp++) {
			Integer bit = stringToOctal(charArray[i]+"");
			//该式子其实类似于３*8^0这样子
			sum+=bit*(Math.pow(8, exp));
		}
		return sum;
	}
	
	private static Integer stringToOctal(String num) {
		try {
			Integer octal = new Integer(num);
			if(octal>0 && octal<8) {
				return octal;
			}
			throw new ArithmeticException("您输入的数字中"+octal+"不是规范的八进制格式,请确认");
		} catch (NumberFormatException e) {
			throw new ArithmeticException("您输入的数字中"+num+"不是规范的数字格式,请确认");
		}
	}
	
	
	
	/**
	 * 十六进制转十进制
	 * @param hex
	 * @return
	 */
	public static int hexaDecimalToDecimal(String hex) {
		char[] charArray = hex.toCharArray();
		int exp = 0;
		int sum = 0;
		for(int i=charArray.length-1;i>=0;i--,exp++) {
			int bit = stringToHex(charArray[i]+"");
			sum+=bit*Math.pow(16, exp);
		}
		return sum;
	}
	private static int stringToHex(String hex) {
		try {
			Integer num = Integer.parseInt(hex);
			return num;
		} catch (NumberFormatException e) {
			switch(hex) {
				case "A":return 10;
				case "B":return 11;
				case "C":return 12;
				case "D":return 13;
				case "E":return 14;
				case "F":return 15;
				default : throw new ArithmeticException("你输入的数字中"+hex+"不是正确的十六进制");
			}
		}
	}
	
	/**
	 * 十进制转二进制
	 * @param num
	 */
	public static int decimalToBinary(int num) {
		Stack<String> stack = new Stack<String>();
		while(true) {
			int remainder = num%2;
			num=num/2;
			stack.push(remainder+"");
			if(num == 0) {
				break;
			}
		}
		return Integer.parseInt(stackToString(stack));
	}
	
	//十进制转八进制请查阅数据结构git仓库的栈分支
	
	/**
	 * 十进制转十六进制
	 * @param num
	 * @return
	 */
	public static String decimalToHex(int num) {
		Stack<String> stack = new Stack<String>();
		while(num !=0) {
			int remainder = num%16;
			stack.push(decimalToHexStr(remainder));
			num = num/16;
		}
		return stackToString(stack);
	}
	private static String decimalToHexStr(int num) {
		if(num<10) {
			return num+"";
		}
		switch(num) {
			case 10: return "A";
			case 11: return "B";
			case 12: return "C";
			case 13: return "D";
			case 14: return "E";
			case 15: return "F";
			default : throw new ArithmeticException("内部出错,转换的"+num+"不属于十六进制");
		}
	}
	/**
	 * 把栈里面的数据一个个取出来,联成String
	 * @param stack
	 * @return
	 */
	private static String stackToString(Stack<String> stack) {
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			String binary = stack.pop();
			sb.append(binary);
		}
		return sb.toString();
	}
	
	private static String queutToString(Queue<String> queue) {
		StringBuilder sb = new StringBuilder();
		while(!queue.isEmpty()) {
			sb.append(queue.poll());
		}
		return sb.toString();
	}
	
	/**
	 * 二进制转八进制
	 * @param binary
	 * @return
	 */
	public static String binaryToOctal(String binary) {
		//先把二进制按小数点切成两块
		String[] strArray = binary.split("\\.");
		if(strArray.length>2) {
			throw new ArithmeticException("您的小数点多了哦");
		}
		char[] left;
		if(strArray.length != 0) {
			left  = strArray[0].toCharArray();
		}else {
			left  = binary.toCharArray();
		}
		
		//存放每三个一组的二进制
		StringBuilder sb = new StringBuilder();
		//存放左边的二进制转把八进制的结果.
		Stack<String> stack = new Stack<>();
		//从二进制的左边向右边遍历
		for(int i=left.length-1,count=1;i>=0;i--,count++) {
			sb.append(left[i]);
			//每计满三个二进制,或者循环到末尾时,将其转成十进制,压入栈中.ps:转的十进制值其实也是八进制
			if(count % 3 ==0 || i==0) {
				int decimal = binarySystemConversionDecimal(sb.toString());
				stack.push(decimal+"");
				sb = new StringBuilder();
			}
		}
		String octalStr = stackToString(stack);
		//如果按小数点分割后长度为2,说明存在小数点,需要处理小数点右边的二进制
		if(strArray.length==2) {
			char [] right = strArray[1].toCharArray();
			//存放小数点右边二进制数组,每三位一组计算后的十进制,所以长度是二进制数组的长度除以3+1(为避免二进制数组不能整除3,还多出一组)
			Queue<String> queut = new ArrayBlockingQueue<>(right.length/3+1);
			sb = new StringBuilder();
			//从二进制的左边向右边便利
			for(int i=0;i<right.length;i++) {
				sb.append(right[i]);
				if((i+1)%3 == 0 || i == right.length-1) {
					fillingBinary(sb);
					int decimal = binarySystemConversionDecimal(sb.toString());
					queut.add(decimal+"");
					sb = new StringBuilder();
				}
			}
			octalStr=octalStr+"."+queutToString(queut);
		}
		return octalStr;
	}
	//填充二进制,如果位数不足3,则补上0
	private static void  fillingBinary(StringBuilder sb) {
		int difference = 3-sb.length();
		for(int i=0;i<difference;i++) {
			sb.append(0);
		}
		
	}
	
}
