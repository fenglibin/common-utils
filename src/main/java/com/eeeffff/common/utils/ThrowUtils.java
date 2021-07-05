//package com.eeeffff.common.utils;
//
//import com.laidian.erp.common.enums.IApiMsgEnum;
//import com.laidian.erp.common.exception.BusinessException;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang.StringUtils;
//
//import java.util.Collection;
//import java.util.concurrent.CompletionException;
//import java.util.function.Function;
//import java.util.function.Predicate;
//import java.util.function.Supplier;
//
///**
// * 对象校验工具类
// */
//public class ThrowUtils {
//
//    /**
//     * 非空校验
//     * @param data    校验对象
//     * @param message 空对象则抛出异常信息
//     * @return: V
//     */
//    public static <V> V isNull(V data, Object message) {
//        isTrue(data == null, "%s", message);
//        return data;
//    }
//
//    public static String isBlank(String data, IApiMsgEnum errorCodeEnum) {
//        isTrue(StringUtils.isBlank(data), errorCodeEnum);
//        return data;
//    }
//
//    /**
//     * 非空校验
//     * @param data     校验对象
//     * @param template 信息模版
//     * @param message  空对象则抛出异常信息
//     * @return: V
//     */
//    public static <V> V isNull(V data, String template, Object... message) {
//        isTrue(data == null, template, message);
//        return data;
//    }
//
//    public static <ID, VALUE> VALUE ifAbsent(ID id, Function<ID, VALUE> f, String name) {
//        VALUE apply = f.apply(id);
//        if (apply == null) {
//            throw new BusinessException(String.format("%s不存在", name));
//        }
//        return apply;
//    }
//
//    /**
//     * 集合非空校验
//     * @param data    校验对象
//     * @param message 空对象则抛出异常信息
//     * @return: V
//     */
//    public static <V> void isEmpty(Collection<V> data, String message) {
//        isTrue(CollectionUtils.isEmpty(data), message);
//    }
//
//    /**
//     * ture校验
//     * @param expression  表达式
//     * @param template    信息模版
//     * @param message     true则抛出异常信息
//     * @return: void
//     */
//    public static void isTrue(boolean expression, String template, Object... message) {
//        if (expression) {
//            throw new BusinessException(String.format(template, message));
//        }
//    }
//
//    public static void isTrue(boolean expression, IApiMsgEnum errorCodeEnum) {
//        if (expression) {
//            throw new BusinessException(errorCodeEnum.getResDes());
//        }
//    }
//
//    /**
//     * 非ture校验
//     * @param expression 表达式
//     * @param message    非true则抛出异常信息
//     * @return: void
//     */
//    public static void isFalse(boolean expression, String message) {
//        isTrue(!expression, message);
//    }
//
//    /**
//     * 有可能在CompletableFuture线程中抛出的BusinessException, 会被封装成CompletionException, 得拆开判断
//     */
//    public static <V> V catchBusiness(Supplier<V> supplier, Exception e) {
//        if (e instanceof BusinessException
//            || (e instanceof CompletionException && e.getCause() instanceof BusinessException)) {
//            return supplier.get();
//        }
//        throw new RuntimeException(e);
//    }
//
//    /**
//     * 自定义表达式校验
//     * @param message 非true则抛出异常信息
//     * @return: void
//     */
//    public static <V> V isPredicate(V data, Predicate<V> predicate, String message) {
//        isTrue(predicate.test(data), message);
//        return data;
//    }
//}
