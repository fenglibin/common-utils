package com.eeeffff.common.utils;

import org.apache.commons.collections.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * sql查询、操作结果集工具
 * @author yaoyuming
 * @date 2020/7/1 0001
 */
public class SqlUtils {

    /**
     * 功能描述 : 查询对象结果转换
     *
     * @param t 查询参数
     * @param query DAO查询器
     * @param modelToDTO model转dto方法
     * @param <T> 查询参数类型
     * @param <M> model类类型
     * @param <R> dto类类型
     * @author yaoyuming
     * @date 2020/12/16 0016
     * @return {@link M}
     */
    public static <T, R, M> M get(T t, SQLQuery<T, R> query, ModelToDTOConverter<R, M> modelToDTO) {
        return get(t, query, modelToDTO, e -> e);
    }

    /**
     * 功能描述 : 查询对象结果转换
     *
     * @param t 查询参数
     * @param query DAO查询器
     * @param modelToDTO model转dto方法
     * @param mapper dto自转方法
     * @param <T> 查询参数类型
     * @param <M> model类类型
     * @param <R> dto类类型
     * @author yaoyuming
     * @date 2020/12/16 0016
     * @return {@link M}
     */
    public static <T, R, M> M get(T t, SQLQuery<T, R> query, ModelToDTOConverter<R, M> modelToDTO, DTOMapper<M> mapper) {
        R r = query.execute(t);
        if (r != null) {
            return mapper.map(modelToDTO.convert(r));
        }
        return null;
    }

    /**
     * 功能描述 : 列表（无分页）
     *
     * @param t 查询参数
     * @param listQuery DAO查询器
     * @param modelToDTO model转dto方法
     * @param <T> 查询参数类型
     * @param <M> model类类型
     * @param <R> dto类类型
     * @return {@link List<R>}
     * @author yaoyuming
     * @date 2020/7/1 0001
     */
    public static <T, M, R> List<R> getList(T t, SQLListQuery<T, M> listQuery, ModelToDTOConverter<M, R> modelToDTO) {
        return getList(t, listQuery, modelToDTO, e -> e);
    }

    /**
     * 功能描述 : 列表（无分页）
     *
     * @param t 查询参数
     * @param listQuery DAO查询器
     * @param modelToDTO model转dto方法
     * @param mapper dto自转方法
     * @param <T> 查询参数类型
     * @param <M> model类类型
     * @param <R> dto类类型
     * @return {@link List<R>}
     * @author yaoyuming
     * @date 2020/7/1 0001
     */
    public static <T, M, R> List<R> getList(T t, SQLListQuery<T, M> listQuery, ModelToDTOConverter<M, R> modelToDTO, DTOMapper<R> mapper) {
        List<M> modelList = listQuery.getList(t);
        List<R> dtoList = new LinkedList<>();
        if (CollectionUtils.isNotEmpty(modelList)) {
            modelList.stream().map(e -> modelToDTO.convert(e)).map(mapper::map).forEach(dtoList::add);
        }
        return dtoList;
    }

    @FunctionalInterface
    public interface SQLQuery<T, R> {
        R execute(T t);
    }

    @FunctionalInterface
    public interface ModelToDTOConverter<T, R> {
        R convert(T t);
    }

    @FunctionalInterface
    public interface DTOMapper<T> {
        T map(T t);
    }

    @FunctionalInterface
    public interface SQLListQuery<T, R> {
        List<R> getList(T t);
    }
}
