package com.eeeffff.common.utils;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * 计算工具类
 * @author yaoyuming
 */
public class BigDecimalUtils {

      /**
       * 小数位默认取2位
       */
      private static final Integer DEFAULT_SCALE = 2;

      private static final BigDecimal BigDecimal_100 = new BigDecimal("100");

      public static BigDecimal getBigDecimal(Number value) {
            if (value == null) {
                  return BigDecimal.ZERO;
            }
            return new BigDecimal(String.valueOf(value));
      }

      public static BigDecimal getBigDecimal(String value) {
            if (StringUtils.isEmpty(value)) {
                  return BigDecimal.ZERO;
            }
            return new BigDecimal(value);
      }

      /**
       * 加法
       * @param value1
       * @param value2
       *
       * @return: java.math.BigDecimal
       */
      public static BigDecimal plus(Number value1, Number value2) {
            return getBigDecimal(value1).add(getBigDecimal(value2));
      }

      /**
       * 减法
       * @param value1
       * @param value2
       *
       * @return: java.math.BigDecimal
       */
      public static BigDecimal minus(Number value1, Number value2) {
            return getBigDecimal(value1).subtract(getBigDecimal(value2));
      }

      /**
       * 乘法
       * @param value1
       * @param value2
       *
       * @return: java.math.BigDecimal
       */
      public static BigDecimal mul(Number value1, Number value2) {
            return getBigDecimal(value1).multiply(getBigDecimal(value2));
      }

      /**
       * 除法
       * @param value1
       * @param value2
       *
       * @return: java.math.BigDecimal
       */
      public static BigDecimal div(Number value1, Number value2) {
            return div(value1, value2, DEFAULT_SCALE);
      }

      /**
       * 除法
       * @param value1
       * @param value2
       * @param scale
       *
       * @return: java.math.BigDecimal
       */
      public static BigDecimal div(Number value1, Number value2, Integer scale) {
            if (BigDecimal.ZERO.equals(getBigDecimal(value2))) {
                  throw new IllegalArgumentException("The divisor cannot be zero or null !");
            }

            if (scale == null) {
                  scale = DEFAULT_SCALE;
            }

            return getBigDecimal(value1).divide(getBigDecimal(value2), scale, BigDecimal.ROUND_HALF_UP);
      }

      /**
       * 四舍五入
       * @param value
       * @param scale
       *
       * @return: java.math.BigDecimal
       */
      public static BigDecimal round(Number value, Integer scale) {
            if (scale == null || scale < 0) {
//			throw new IllegalArgumentException("The scale must be a positive integer or zero");
                  scale = DEFAULT_SCALE;
            }
            return div(value, BigDecimal.ONE, scale);
      }

      /**
       * 保留两位小数字符串返回
       * @param value
       *
       * @return: java.lang.String
       */
      public static String format(Number value) {
            return round(value, DEFAULT_SCALE).toString();
      }

      /**
       * 返回较小数
       * @param value1
       * @param value2
       *
       * @return: java.math.BigDecimal
       */
      public static BigDecimal min(Number value1, Number value2) {
            return getBigDecimal(value1).min(getBigDecimal(value2));
      }

      /**
       * 返回较大数
       * @param value1
       * @param value2
       *
       * @return: java.math.BigDecimal
       */
      public static BigDecimal max(Number value1, Number value2) {
            return getBigDecimal(value1).max(getBigDecimal(value2));
      }

      /**
       * 百分数
       * @param value
       *
       * @return: java.math.BigDecimal
       */
      public static BigDecimal getPercent(Number value) {
            return div(value, BigDecimal_100);
      }


      /**
       * 由于java语言中对浮点数的二进制转换会产生问题，因此在将浮点数转换为BigDecimal类型时需要先将浮点数转换为String型
       *
       *
       * @date 2013-12-20
       * @param num
       * @return BigDecimal
       */
/*    public static BigDecimal getExactBigDicimal(double num) {
        String numStr = num + "";
        return new BigDecimal(numStr);
    }*/

      /**
       * 校验是否为小于等于零的数字
       *
       *
       * @date 2014-01-13
       * @param num
       * @return boolean
       */
/*    public static boolean isNotPlus(BigDecimal number) {
        if (number == null) {
            return true;
        }
        if (number.compareTo(new BigDecimal(0)) <= 0) {
            return true;
        }
        return false;
    }*/

      /**
       *
       * 功能描述：校验是否大于零的数字
       *
       *
       * @date 2014-10-10
       * @param number
       * @return
       */
    /*public static boolean isGreaterZero(BigDecimal number) {
        if (number == null) {
            return false;
        }
        if (number.compareTo(new BigDecimal(0)) <= 0) {
            return false;
        }
        return true;
    }*/

      /**
       * 功能描述:将可能为空的Integer转为0
       *
       * @param integer
       * @return
       */
/*    public static Integer convertInteger(Integer integer) {
        if (integer == null) {
            integer = new Integer(0);
        }
        return integer;
    }*/

      /**
       * 功能描述：将BigDecimal转为BigDecimal,允许自己定义异常时的返回值
       *
       * @date 2014年1月23日
       * @return BigDecimal
       */
/*    public static BigDecimal convertBigDecimal(BigDecimal num, BigDecimal bd) {
        if (num == null) {
            return bd;
        }
        return num;
    }*/

      /**
       * 功能描述：将BigDecimal转为String
       *
       * @date 2014年1月24日
       * @return String
       */
   /* public static String covertBigDecimalToString(BigDecimal num) {
        if (num == null) {
            return "";
        }
        return num.toString();
    }*/

      /**
       * 功能描述：将BigDecimal转为double
       *
       * @date 2014年2月14日
       * @return String
       */
   /* public static double toDouble(BigDecimal num) {
        if (num == null) {
            return 0;
        }
        return num.doubleValue();
    }*/

      /**
       * 判断bd1是否小于bd2
       *
       *
       * @date 2013-12-18
       * @param bd1
       * @param bd2
       * @return boolean
       *
       */
    /*public static boolean compareBigDecimalMinus(BigDecimal bd1, BigDecimal bd2) {
        boolean returnFlag = false;
        int i1 = bd1.compareTo(bd2);
        if (i1 < 0) {
            returnFlag = true;
        }
        return returnFlag;
    }*/

      /**
       * 判断bd1是否小于等于bd2
       *
       *
       * @date 2013-12-18
       * @param bd1
       * @param bd2
       * @return boolean
       *
       */
    /*public static boolean compareBigDecimalMinusEq(BigDecimal bd1, BigDecimal bd2) {
        boolean returnFlag = false;
        int i1 = bd1.compareTo(bd2);
        if (i1 < 0 || i1 == 0) {
            returnFlag = true;
        }
        return returnFlag;
    }*/

      /**
       * 功能描述：判断bd1是否大于bd2
       *
       *
       * @date 2014-5-15
       * @param bd1
       * @param bd2
       * @return
       */
    /*public static boolean compareBigDecimalPlus(BigDecimal bd1, BigDecimal bd2) {
        boolean returnFlag = false;
        int i1 = bd1.compareTo(bd2);
        if (i1 > 0) {
            returnFlag = true;
        }
        return returnFlag;
    }*/

      /**
       *
       * 功能描述：三目运算
       *
       *
       * @date 2014-3-2
       * @param flag
       * @param amount
       * @return
       */
      /**
       * public static BigDecimal ternaryConditionalCalc (boolean flag,BigDecimal amount) { return
       * flag?amount:BigDecimal.ZERO; }
       */
      /**
       * 功能描述：判断BigDecimal是否为null
       *
       *
       * @date 2014-3-13
       * @param bigDecimal
       * @return
       */
    /*public static boolean isNullBigDecimal(BigDecimal bigDecimal) {
        boolean result = false;

        if (bigDecimal == null) {
            result = true;
        }

        return result;
    }*/

      /**
       * 功能描述：判断BigDecimal是否大于或等于零，为null时返回false
       *
       *
       * @date 2014-3-13
       * @param bigDecimal
       * @return
       */
    /*public static boolean isGreaterOrEqualToZero(BigDecimal bigDecimal) {
        boolean result = false;
        if (BigDecimalUtils.isNullBigDecimal(bigDecimal)) {
            return result;
        }

        if (bigDecimal.compareTo(BigDecimal.ZERO) >= 0) {
            result = true;
        }

        return result;

    }*/

      /**
       *
       * 功能描述：将String类型转换为BigDecimal类型 如果String为空或null则返回BigDecimal.ZERO
       *
       *
       * @date 2013-10-26
       * @param bigDecimalStr
       * @return
       */
    /*	public static BigDecimal getBigDecimal(String bigDecimalStr){
    		BigDecimal result = BigDecimal.ZERO;
    
    		if(!StringUtils.isEmpty(bigDecimalStr)){
    			result = new BigDecimal(bigDecimalStr);
    		}
    
    		return result;
    	}*/

      /**
       *
       * 功能描述：如果bigDecimal等于0则返回1 否则返回bigDecimal
       *
       *
       * @date 2014-04-15
       * @param bigDecimal
       * @return
       */
   /* public static BigDecimal transZEROToONE(BigDecimal bigDecimal) {

        if (BigDecimal.ZERO.compareTo(bigDecimal) == 0) {
            return BigDecimal.ONE;
        } else {
            return bigDecimal;
        }
    }*/

      /**
       *
       * 功能描述：判断两个BigDecimal 是否相等
       *
       *
       * @date 2014-07-09
       * @param BigDecimal
       *            one, BigDecimal two
       * @return
       */
/*    public static boolean isEqual(BigDecimal one, BigDecimal two) {

        return one.compareTo(two) == 0 ? true : false;
    }*/

      /**
       * @Desc 对BigDecimal类型进行四舍五入并返回BigDecimal
       * @param one
       * @param size
       * @return
       *
       * @Date 2015-11-27
       */
/*    public static BigDecimal roundBigDecimal(BigDecimal one, int size) {
        return one.setScale(size, BigDecimal.ROUND_HALF_UP);
    }*/


	/*    public static void main(String[] args) {
        String s = null;
        System.out.println(getBigDecimal(s));

		System.out.println(plus(1.62223, 2));


		System.out.println(minus(null, 2));
		System.out.println(mul(1.6, 2));
		System.out.println(div(2, 2, 2));

		System.out.println(round(1, 2));

		System.out.println(format(null));
		System.out.println(min(555, null));

		System.out.println(getPercent(128));
    }*/
}