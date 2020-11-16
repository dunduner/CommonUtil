package io.github.ljwlgl.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description: 集合操作工具类
 * @Author: yanchun.mao
 * @Date: created in 10:50 2020/5/11
 */
public final class StreamUtil {

    private StreamUtil() {
    }

    /**
     * 对List每个元素进行计算后，组织成另一个List
     * 如 List idList= StreamUtil.toList(catList, Cat::getId)
     */
    public static <K, V> List<V> toList(Collection<K> list, Function<K, V> mapFunc) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream()
                .map(mapFunc).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 对List每个元素进行计算后，组织成另一个List
     */
    public static <K, V1, V2> List<V2> toList(Collection<K> list, Function<K, V1> mapFunc1, Function<V1, V2> mapFunc2) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream()
                .map(mapFunc1).filter(Objects::nonNull)
                .map(mapFunc2).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 对List每个元素进行计算后，组织成另一个List
     */
    public static <K, V1, V2, V3> List<V3> toList(Collection<K> list, Function<K, V1> mapFunc1, Function<V1, V2> mapFunc2, Function<V2, V3> mapFunc3) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream()
                .map(mapFunc1).filter(Objects::nonNull)
                .map(mapFunc2).filter(Objects::nonNull)
                .map(mapFunc3).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Collection 转 List
     */
    public static <K> List<K> toList(Collection<K> list) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return new ArrayList<>(list);
    }


    /**
     * 转化成List后去重
     */
    public static <K, V> List<V> toDistinctList(Collection<K> list, Function<K, V> mapFunc) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream()
                .map(mapFunc).filter(Objects::nonNull)
                .distinct().collect(Collectors.toList());
    }

    /**
     * 转化成List后去重
     */
    public static <K, V1, V2> List<V2> toDistinctList(Collection<K> list, Function<K, V1> mapFunc1, Function<V1, V2> mapFunc2) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream()
                .map(mapFunc1).filter(Objects::nonNull)
                .map(mapFunc2).filter(Objects::nonNull)
                .distinct().collect(Collectors.toList());
    }

    /**
     * 转化成List后去重
     */
    public static <K, V1, V2, V3> List<V3> toDistinctList(Collection<K> list, Function<K, V1> mapFunc1, Function<V1, V2> mapFunc2, Function<V2, V3> mapFunc3) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream()
                .map(mapFunc1).filter(Objects::nonNull)
                .map(mapFunc2).filter(Objects::nonNull)
                .map(mapFunc3).filter(Objects::nonNull)
                .distinct().collect(Collectors.toList());
    }

    /**
     * flat扁平化处理
     */
    public static <K, V1> List<V1> flatToDistinctList(Collection<K> list, Function<K, List<V1>> mapFunc) {
        return flatToDistinctList(list, mapFunc, i -> i);
    }

    public static <K, V1, V2> List<V2> flatToDistinctList(Collection<K> list, Function<K, List<V1>> mapFunc1, Function<V1, V2> mapFunc2) {
        return flatToList(list, mapFunc1, mapFunc2, true);
    }

    public static <K, V1> List<V1> flatToList(Collection<K> list, Function<K, List<V1>> mapFunc) {
        return flatToList(list, mapFunc, i -> i);
    }


    public static <K, V1, V2> List<V2> flatToList(Collection<K> list, Function<K, List<V1>> mapFunc1, Function<V1, V2> mapFunc2) {
        return flatToList(list, mapFunc1, mapFunc2, false);
    }

    public static <K, V1, V2> List<V2> flatToList(Collection<K> list, Function<K, List<V1>> mapFunc1, Function<V1, V2> mapFunc2, boolean distinct) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        List<V2> result = Lists.newArrayList();
        list.forEach(data -> {
            if (data == null) {
                return;
            }
            List<V1> v1List = mapFunc1.apply(data);
            if (v1List == null || v1List.isEmpty()) {
                return;
            }
            v1List.forEach(v1 -> {
                V2 v2 = mapFunc2.apply(v1);
                if (v2 != null) {
                    result.add(v2);
                }
            });
        });


        return distinct ? distinct(result) : result;
    }


    /**
     * 去重
     */
    public static <K> List<K> distinct(Collection<K> list) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 对Collection每个元素过滤后计算，组织成一个List
     */
    public static <K, V> List<V> filterAndMapToList(Collection<K> list, Predicate<K> filterFunc, Function<K, V> mapFunc) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream()
                .filter(filterFunc)
                .map(mapFunc)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 对List每个元素过滤后组织成另一个List
     */
    public static <K> List<K> filterToList(Collection<K> list, Predicate<K> filterFunc) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list.stream().filter(filterFunc).collect(Collectors.toList());
    }

    /**
     * 对List每个元素进行计算后，按key和value组织成Map
     * 如 Map<Integer, Cat> catIdMap = StreamUtil.toMap(catList, Cat::getId)
     */
    public static <K, V> Map<V, K> toMap(Collection<K> list, Function<K, V> keyMapFunc) {
        return toMap(list, keyMapFunc, v -> v);
    }

    public static <K, V, F> Map<V, F> toMap(Collection<K> list, Function<K, V> keyMapFunc, Function<K, F> valueMapFunc) {
        if (list == null || list.isEmpty()) {
            return Maps.newHashMap();
        }

        Map<V, F> result = new HashMap<>(list.size());
        list.forEach(k -> {
            V mapKey = keyMapFunc.apply(k);
            //不校验是否重复
            result.put(mapKey, valueMapFunc.apply(k));
        });

        return result;
    }

    /**
     * 对List每个元素进行计算后，按key和value组织成Map
     * 如 Map<String, List<Cat>> catNameMap = StreamUtil.groupToMap(catList, Cat::getName);
     */
    public static <K, V> Map<V, List<K>> groupToMap(Collection<K> list, Function<K, V> keyMapFunc) {
        return groupToMap(list, keyMapFunc, v -> v);
    }

    /**
     * 对List每个元素进行计算后，按key和value组织成Map，
     * 如 Map<Integer, List<String>> catNameMap = StreamUtil.groupToMap(catList, Cat::getId, Cat::getName);
     */
    public static <K, V, F> Map<V, List<F>> groupToMap(Collection<K> list,
                                                       Function<K, V> keyMapFunc,
                                                       Function<K, F> valueMapFunc) {
        if (list == null || list.isEmpty()) {
            return Maps.newHashMap();
        }

        Map<V, List<F>> result = new HashMap<>(list.size());
        list.forEach(k -> {
            V mapKey = keyMapFunc.apply(k);
            List<F> valueList = result.computeIfAbsent(mapKey, v -> new ArrayList<>(10));
            valueList.add(valueMapFunc.apply(k));
        });

        return result;
    }

    public static <K> K filterFirst(Collection<K> list, Predicate<K> predicate) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (K key : list) {
            if (predicate.test(key)) {
                return key;
            }
        }

        return null;
    }
}
