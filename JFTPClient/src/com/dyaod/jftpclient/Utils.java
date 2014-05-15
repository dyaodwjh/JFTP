package com.dyaod.jftpclient;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.apache.log4j.Logger;

public class Utils {

	private static Logger log = Logger.getLogger(Utils.class);


	/**
	 * @名称: 
	 * @作者: wang.jianhua
	 * @创建于: 2014年5月14日
	 * @param length
	 * @param i
	 * @return
	 * @描述: 将长度值转换为指定格式内容的字节数组
	 * 例如 : 长度 是200 ,byteLength 是3
	 * 则转换为 byte[0] = 0 ,byte[1]=1,byte[2]=73
	 *  其中 byte[0]x127^2 + byte[1]*127^1+byte[0]*127^0 = 0 + 1 *127 + 73 = 200
	 */
	public static byte[] encodeLength(int num, int byteLength) {
		byte[] result = new byte[byteLength];

		int temp = 0;
		for(int i = byteLength-1;i>=0;i--){
			temp = (int)Math.pow(Byte.MAX_VALUE, i);
			result[i] = (byte) (num / temp);
			num = num % temp;
		}
		
		return result;
	}
	
	/**
	 * @名称: 
	 * @作者: wang.jianhua
	 * @创建于: 2014年5月14日
	 * @param lenBytes
	 * @return
	 * @描述: 对应于 encodeLength ,作用是将 encodeLength 转换的byte数组转换回数字
	 */
	public static int decodeLength(byte[] lenBytes) {
		
		int byteLength = lenBytes.length;
		
		int result = 0;
		
		for(int i = 0;i<byteLength;i++){
			result += lenBytes[i]*Math.pow(Byte.MAX_VALUE, i);
		}
		
		return result;
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
//		int a =335554432;
//		
//		
//		long start = System.currentTimeMillis();
//		byte[] result = encodeLength(a,5);
//		long end = System.currentTimeMillis();
//		System.out.println(end-start);
//		for(int i = result.length-1;i>=0;i--){
//			System.out.print(result[i]+" ");
//		}
//		System.out.println();
//		
//		System.out.println(decodeLength(result));
		
		String[] a = "1,2,3,4,5,6".split(",");
		
		String[] b = Arrays.copyOfRange(a, 1, a.length);
		
		for(int i = b.length-1;i>=0;i--){
		System.out.print(b[i]+" ");
	    }
	}
}
