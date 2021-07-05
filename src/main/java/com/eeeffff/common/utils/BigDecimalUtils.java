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
}