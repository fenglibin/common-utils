package com.eeeffff.common.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Pattern;

/**
 * 数值处理方法类
 * @author fenglibin
 *
 */
public class NumberUtil {
	
	public static boolean isShort(int val){
		if(val>=-32768 && val<=32767){
			return true;
		}
		return false;
	}
	
	public static boolean isPositiveShort(int val){
		if(val >=0 && val<=32767){
			return true;
		}
		return false;
	}
	
	//浮点数正则处理
	//正浮点数："^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";
	public static boolean isPositiveFload(String s){
		String reg = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";
		Pattern pattern = Pattern.compile(reg);
		return pattern.matcher(s).matches();
	}
	//是否浮点数
	public static boolean isFload(String s){
		String reg = "^(-?\\d+)(\\.\\d+)?$";
		Pattern pattern = Pattern.compile(reg);
		return pattern.matcher(s).matches();
	}
	
	//正整数
	public static boolean isPositiveInteger(String s){
		String reg = "^[0-9]*[1-9][0-9]*$";
		Pattern pattern = Pattern.compile(reg);
		return pattern.matcher(s).matches();
	}
	
	/**
	 * 乘法
	 * 
	 * @param int1
	 * @param int2
	 * @return
	 */
	public static Integer multiply(Integer int1,Integer int2)
	{
		BigDecimal bd1 = new BigDecimal(int1);
		BigDecimal bd2 = new BigDecimal(int2);
		return bd1.multiply(bd2).intValue();
	}
	
	/**
	 * truncate long to INT
	 * */
	public static Integer truncateLong(Long longNum){
		if(longNum==null){
			return 0;
		}
		return longNum.intValue();
	}
		
	/**
	 * 截短小数
	 * 
	 * 
	 * @param num 需被截短的数
	 * @param scale 小数点后保留位数
	 * @return
	 */
	public static BigDecimal truncateDigit(BigDecimal num, int scale) 
	{
		DecimalFormat formater = new DecimalFormat();
		formater.setMaximumFractionDigits(2);
		formater.setGroupingSize(0);
		formater.setRoundingMode(RoundingMode.FLOOR);
		String value = formater.format(num.doubleValue());
		return new BigDecimal(value);
	}
	
	/**
	 * 四舍五入
	 * 
	 * 
	 * @param num 需四舍五入的数
	 * @param scale 小数点后保留位数
	 * @return
	 */
	public static BigDecimal scaleDigit(BigDecimal num, int scale) 
	{
		if(num == null)
			return num;
		return num.setScale(scale,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 相除
	 * @param divider 被除数
	 * @param divisor 除数
	 * @param scale
	 * @return
	 */
	public static double divide(long divider,long divisor, int scale)
	{
		BigDecimal bddivider = new BigDecimal(divider);
		BigDecimal bddivisor = new BigDecimal(divisor);
		return bddivider.divide(bddivisor,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * Integer数字相除
	 * 
	 * @param divider 除数
	 * @param divisor 被除数
	 * @param scale 小数保留位数
	 * @return 运算结果
	 */
	public static Double divideLong(long divider, int divisor,int scale)
	{
		BigDecimal bdDivider = new BigDecimal(String.valueOf(divider));
		BigDecimal bdDivisor = new BigDecimal(String.valueOf(divisor));
		BigDecimal result = bdDivider.divide(bdDivisor, scale,BigDecimal.ROUND_HALF_UP);
		return result.doubleValue();
	}


	public static BigDecimal stringConverBigdecimal (String str) {
		if (str == null) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal(str);
	}


	public static BigDecimal calcRate(BigDecimal data1, BigDecimal data2) {
		return calcRate(data1,data2,true);
	}


	public static BigDecimal calcRate(BigDecimal data1, BigDecimal data2,boolean limit) {
		if (data1 == null) {
			data1 = new BigDecimal(0);
		}
		if (data2 == null) {
			data2 = new BigDecimal(0);
		}
		if (data1.doubleValue() == 0 && data2.doubleValue() == 0) {
			return new BigDecimal(0);
		} else if (data1.doubleValue() <= 0 && data2.doubleValue() >= 0) {
			return new BigDecimal(-100);
		} else if (data2.doubleValue() <= 0 && data1.doubleValue() >= 0) {
			return new BigDecimal(100);
		}

		double val = 0;
		if (data1.doubleValue() < 0 && data2.doubleValue() < 0) {
			val = (data1.add(data2)).divide(data2.negate(), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)
			).doubleValue();
		} else {
			/**
			 * 假设原来的数量是A,现在的数量是B.
			 * 那么当B大于A时,增长率＝（B-A)除以A；
			 * 当B小于A时,减少率＝（A-B）除以A.
			 */
			if (data1.doubleValue() > data2.doubleValue()) {
				// 增长
				val = (data1.subtract(data2)).divide(data2, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)
				).doubleValue();
			} else {
				// 下降
				val = -((data2.subtract(data1)).divide(data2, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal
						(100)).doubleValue());
			}
		}
		if(limit){
			val = val > 100 ? 100 : val;
			val = (val < 0 && val < -100) ? -99.99 : val;
		}
		return new BigDecimal(val);
	}

	public static BigDecimal getBigDecimal( Object value ) {
		BigDecimal ret = null;
		if( value != null ) {
			if( value instanceof BigDecimal ) {
				ret = (BigDecimal) value;
			} else if( value instanceof String ) {
				ret = new BigDecimal( (String) value );
			} else if( value instanceof BigInteger ) {
				ret = new BigDecimal( (BigInteger) value );
			} else if( value instanceof Number ) {
				ret = new BigDecimal( ((Number)value).doubleValue() );
			} else {
				throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
			}
		}
		return ret;
	}


	/**
	 * 同比增长率 year-on-year.（ 结果乘100）.
	 * <p>本期统计数据与上期比较，例如2014年7月份与2014年6月份相比较，叫环比.</p>
	 * <p>与历史同时期比较，例如2014年7月份与2013年7月份相比，叫同比.</p>
	 * <p>1）环比增长率=（本期数－上期数）/上期数×100%.</p>
	 * <p>2）同比增长率=（本期数－同期数）/同期数×100%.</p>
	 * @author jiang chao
	 * @param current
	 *         [当年的指标值]
	 * @param before
	 *         [去年同期的值]
	 * @return double
	 */
	public static BigDecimal calYoY(BigDecimal current, BigDecimal before) {

		if (null == current || null == before
				|| before.compareTo(BigDecimal.ZERO) == 0 || before.compareTo(BigDecimal.ZERO) == -1) {
			return new BigDecimal(0.00);
		}

		BigDecimal result = new BigDecimal(0.00);
		if (before.compareTo(BigDecimal.ZERO) != 0) {
			result = (current.subtract(before)).divide(before, 4, BigDecimal.ROUND_HALF_UP);
		} else if (current.compareTo(BigDecimal.ZERO) == 1) {
			result = new BigDecimal(1.00);
		}
		return result.multiply(new BigDecimal(100));
	}

	/**
	 * calYoYRate 计算同比比率.
	 *
	 * @author jiang chao
	 * @param current
	 *         [current]
	 * @param before
	 *         [before]
	 * @return java.math.BigDecimal
	 */
	public static BigDecimal calYoYRate(BigDecimal current, BigDecimal before) {

		if (null == current || null == before
				|| before.compareTo(BigDecimal.ZERO) == 0 || before.compareTo(BigDecimal.ZERO) == -1) {
			return new BigDecimal(0.00);
		}

		BigDecimal result = new BigDecimal(0.00);
		if (before.compareTo(BigDecimal.ZERO) != 0) {
			result = (current.subtract(before)).divide(before, 4, BigDecimal.ROUND_HALF_UP);
		} else if (current.compareTo(BigDecimal.ZERO) == 1) {
			result = new BigDecimal(1.00);
		}
		return result;
	}
	/**
	 * 环比增长率 month-on-month（ 结果乘100）.
	 * <p>本期统计数据与上期比较，例如2014年7月份与2014年6月份相比较，叫环比.</p>
	 * <p>与历史同时期比较，例如2014年7月份与2013年7月份相比，叫同比.</p>
	 * <p>1）环比增长率=（本期数－上期数）/上期数×100%.</p>
	 * <p>2）同比增长率=（本期数－同期数）/同期数×100%.</p>
	 * @param current [本期数]
	 * @param before [上期数]
	 * @return java.math.BigDecimal
	 * @author jiang chao
	 */
	public static BigDecimal calMoM(BigDecimal current, BigDecimal before) {

		BigDecimal rate = BigDecimal.valueOf(0.00);
		if (current != null && before != null) {
			if (current.floatValue() > 0 && before.floatValue() > 0) {
				BigDecimal subtract = current.subtract(before);
				if (before.compareTo(BigDecimal.valueOf(0.00)) < 0) {
					before = before.abs();
				}
				rate=  subtract.divide(before, 4, BigDecimal.ROUND_HALF_UP);
			}


		}
		return rate.multiply(new BigDecimal(100));

	}



	/**
	 * 环比增长率 month-on-month  本期数－上期数）/上期数.
	 * 返回值没取绝对值ABS.
	 * @param current [本期数]
	 * @param before [上期数]
	 * @return java.math.BigDecimal
	 * @author jiang chao
	 */
	public static Double calMoMRate(BigDecimal current, BigDecimal before) {

		Double rate =  Double.valueOf(0.00);
		if (current != null && before != null && before.compareTo(BigDecimal.ZERO)!=0) {
			rate = current.subtract(before).divide(before,4,BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return rate;

	}


	/**
	 * formatPercentStr 将小数格式化为百分比保留小数点后两位.
	 *
	 * @author jiang chao
	 * @param val
	 *         [要转换的值]
	 * @param fractionDigits
	 *        [保留小数点后的位数]
	 * @return java.lang.String
	 */
	public static  String  formatPercentStr(Double val,Integer fractionDigits){
    if(val==null){
    	val = Double.valueOf(0.00);
		}
		if(fractionDigits==null){
			fractionDigits =2;
		}
		NumberFormat numberFormat = NumberFormat.getPercentInstance();
		numberFormat.setMaximumFractionDigits(fractionDigits);
		numberFormat.setMinimumFractionDigits(fractionDigits);
		return  numberFormat.format(val);
	}

	public static  String  formatPercentStr(BigDecimal val){

		if(val==null){
			return formatPercentStr(0D,2);
		}else {
			return formatPercentStr(val.doubleValue(),2);
		}

	}

	public static  String  formatPercentStr(Double val){
		/**
		 * formatPercentStr 将小数格式化为百分比保留小数点后两位.
		 *
		 * @author jiang chao
		 * @param val
		 *         [要转换的值]
		 * @return java.lang.String
		 * @throws
		 */
		return formatPercentStr(val,2);
	}
	
	/**
     * 判断字符串是否为正整数
     *
     * @param str 要检查的字符串
     * @return boolean
     * @author jianglin
     */
    public static boolean isInteger(String str) {
    	if(null==str || str.length()==0) {
    		return false;
    	}
        String integerPattern = "^[\\d]*$";
        Pattern pattern = Pattern.compile(integerPattern);
        return pattern.matcher(str).matches();
    }

}
