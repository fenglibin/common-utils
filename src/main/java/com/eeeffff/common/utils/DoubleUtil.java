package com.eeeffff.common.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class DoubleUtil {

	// 保留两位小数格式
	public static DecimalFormat decimalFormat = new DecimalFormat("0.00");

	/** 
     * 小数点保留三位 
     * @param d 
     * @return 
     */  
    public static double keep3decimal(double d){  
        return keepNDecimal(d,3);
    }

	/**
	 * 保留n位小数
	 * @param d
	 * @param n
	 * @return
	 * @author hfb
	 */
	public static double keepNDecimal(double d,int n){
		if(d != 0){
			d = new BigDecimal(d).setScale(n,BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return d;
	}

	/**
     * @Description:保留指定位数的小数,不进行四舍五入
     * @author wzw
     * @param d 
     * @param index
     * @return
     * @Date 2017年10月17日
     */
    public static double keepDecimal(double d,int index){  
    	double result = d;
       if(d != 0 && index > 0){  
            String str = String.valueOf(d);
            String[] strArray = str.split("\\.");
            String tmpStr = "";
            if(strArray[1].length()>=index){
            	tmpStr = strArray[1].substring(0, index);
            }else{
            	tmpStr = strArray[1];
            }
            
            result = Double.valueOf( strArray[0]+"."+tmpStr);
        }  
        return result;
    } 
    
    /**
     * 将object数据转换成double类型的数据
     * @author liuyang
     * @date 2017年10月16日下午4:58:00
     * @param obj
     * @return
     */
    public static Double getDoubleByObj(Object obj){
    	if(obj != null && StringUtils.isNotBlank(obj+"") && !"null".equalsIgnoreCase(obj+"")){
    		try{
	    		Double d = Double.valueOf(obj+"");
	    		return d;
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	
    	}
    	return 0.0;
    }
    
    /**
     * 将object数据转换成Integer类型的数据
     * @author liuyang
     * @date 2017年10月16日下午4:58:00
     * @param obj
     * @return
     */
    public static Integer getIntegerByObj(Object obj){
    	if(obj != null && StringUtils.isNotBlank(obj+"") &&  StringUtils.isNumeric(obj+"")){
    		try{
    			Integer d = Integer.valueOf(obj+"");
	    		return d;
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}
    	return 0;
    }
    
    /**
     * @Description:获取指定经纬度范围内的随机经纬度
     * @author wzw
     * @param point 参照经、纬度
     * @param radius 半径
     * @return
     * @Date 2017年10月17日
     */
    public static double getRandomPoint(double point,double radius){
    	double result = 0.0;
    	double randoms = Math.random()*(Math.random()>0.5?1:-1);
    	double tmpPoint = randoms*radius+point;
    	DecimalFormat decimalFormat = new DecimalFormat("#.######");//格式化设置
    	String str = decimalFormat.format(tmpPoint);
    	result = Double.valueOf(str);
    	if(result<0){
    		result = Double.valueOf(decimalFormat.format(Math.random()));
    	}
    	return result;
    }
    
    /**
     * @Description:将double类型的数据转换成string类型，避免出现科学技术法的形式
     * @author wzw
     * @param data
     * @return
     * @Date 2017年11月27日
     */
    public static String changeDoubleToString(double data){
    	DecimalFormat decimalFormat = new DecimalFormat("#.######");//格式化设置
    	String str = decimalFormat.format(data);
    	return str;
    }

	/**
	 * 将List String或Double保留n位小数
	 *
	 * @param list
	 * @param n
	 * @return
	 * @author hfb
	 */
	public static List<Object> fixNumber(List<Object> list, int n) {
		if (CollectionUtils.isEmpty(list)) {
			return list;
		}
		List<Object> resultList = new ArrayList<>();
		for (Object t : list) {
			if(t == null || "null".equals(t + "")){
				resultList.add(0);
			}else if (t instanceof String) {
				String tmp = t + "";
				if (tmp.contains(".")) {
					double d = Double.valueOf(tmp);
					d = keepNDecimal(d, n);
					resultList.add(d);
				} else {
					resultList.add(Integer.valueOf(tmp));
				}
			} else if (t instanceof Double) {
				double d = keepNDecimal((Double) t, n);
				resultList.add(d);
			} else {
				resultList.add(t);
			}
		}
		return resultList;
	}
	
	/**
	 * 判断double是否等于0
	 * @param d
	 * @return
	 * @author liuyang
	 * @date 2018年8月22日下午1:51:33
	 */
	public static boolean isZero(Double d){
		if(d == null){
			return true;
		}
		if(d>=(-1e-6)&&d<=(1e-6)){
			//1e-6即10的-6次方
			return true;
		}else{
			return false;
		}
	}
	
	
	 /**
     * 提供精确加法计算的add方法
     * @param value1 被加数
     * @param value2 加数
     * @param scale 精度范围
     * @return 两个参数的和
     */
    public static double add(double value1,double value2,int scale){
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.add(b2).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 提供精确减法运算的sub方法
     * @param value1 被减数
     * @param value2 减数
     * @param scale 精度范围
     * @return 两个参数的差
     */
    public static double sub(double value1,double value2,int scale){
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.subtract(b2).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 提供精确乘法运算的mul方法
     * @param value1 被乘数
     * @param value2 乘数
     * @param scale 精度范围
     * @return 两个参数的积
     */
    public static double mul(double value1,double value2,int scale){
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.multiply(b2).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 提供精确的除法运算方法div
     * @param value1 被除数
     * @param value2 除数
     * @param scale 精确范围
     * @return 两个参数的商
     * @throws IllegalAccessException
     */
    public static double div(double value1,double value2,int scale){
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.divide(b2, scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

	/**
	 * 校验经纬度
	 * @param longitude 纬度
	 * @param latitude 经度
	 * @return
	 * @throws IllegalAccessException
	 */
	public static boolean checkLonAndLat(double longitude,double latitude){
		if (longitude>= -90 && longitude<= 90 && latitude>= -180 && latitude<= -180){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args){
	    /*double d=4.1317422564E9;
		double d1=4.1317422564d;
		double d2=4.13;
		double d3=4d;
		double d4=4.121;
		System.out.println(DoubleUtil.keepNDecimal(d, 3));
		System.out.println(DoubleUtil.keepNDecimal(d1, 3));
		System.out.println(DoubleUtil.keepNDecimal(d2, 3));
		System.out.println(DoubleUtil.keepNDecimal(d3, 3));
		System.out.println(DoubleUtil.keepNDecimal(d4, 3));*/
	    double d=4.0;
        double d1=4.1;
        double d2=4.13;
        System.out.println(d+d1);
        System.out.println(add(d, d1, 3));
        System.out.println(d-d2);
        System.out.println(sub(d, d2, 3));
        System.out.println(d*d1);
		System.out.println(mul(d, d1, 3));
		
		
	}
}
