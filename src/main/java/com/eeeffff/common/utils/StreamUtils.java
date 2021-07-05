package com.eeeffff.common.utils;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: mcw
 * @Date: 2019/06/12 11:29
 * @Description: Collection Stream 辅助类
 */
public class StreamUtils {

    /**
     * 功能描述 : 集合转Set方法
     * @param collection 集合对象
     * @param f 转换方法
     *
     * @return {@link Set<T>}
     *
     * @author yaoyuming
     * @date 2020/12/16 0016
     */
    public static <T, V> Set<T> toSet(Collection<V> collection, Function<V, T> f) {
        if (CollectionUtils.isNotEmpty(collection)) {
            return toStream(collection, f).collect(Collectors.toSet());
        } else {
            return Collections.emptySet();
        }
    }

    /**
     * 功能描述 : 集合转List方法
     * @param collection 集合对象
     * @param f 转换方法
     *
     * @return {@link List<T>}
     *
     * @author yaoyuming
     * @date 2020/12/16 0016
     */
    public static <T, V> List<T> toList(Collection<V> collection, Function<V, T> f) {
        if (CollectionUtils.isNotEmpty(collection)) {
            return toStream(collection, f).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * 功能描述 : 集合转Map方法
     * @param collection 集合对象
     * @param key 转换方法
     * @param value 转换方法
     *
     * @return {@link Map<K, V>}
     *
     * @author yaoyuming
     * @date 2020/12/16 0016
     */
    public static <T, K, V> Map<K, V> toMap(Collection<T> collection, Function<T, K> key, Function<T, V> value) {
        if (CollectionUtils.isNotEmpty(collection)) {
            return collection.stream().collect(Collectors.toMap(key, value, (o, n) -> o));
        } else {
            return Collections.emptyMap();
        }
    }

    /**
     * 功能描述 : 集合转Map方法,value为集合泛型对象
     * @param collection 集合对象
     * @param key 转换方法
     *
     * @return {@link Map<K, T>}
     *
     * @author yaoyuming
     * @date 2020/12/16 0016
     */
    public static <T, K> Map<K, T> toMapIdentity(Collection<T> collection, Function<T, K> key) {
        if (CollectionUtils.isNotEmpty(collection)) {
            return collection.stream().collect(Collectors.toMap(key, Function.identity(), (o, n) -> o));
        } else {
            return Collections.emptyMap();
        }
    }

    private static <K, V> Stream<K> toStream(Collection<V> collection, Function<V, K> f) {
        return toStream(collection, f, false);
    }

    private static <K, V> Stream<K> toStream(Collection<V> collection, Function<V, K> f, boolean parallel) {
        Stream<K> kStream = collection.stream().filter(Objects::nonNull).map(f).filter(Objects::nonNull);
        return parallel ? kStream.parallel() : kStream;
    }
}
