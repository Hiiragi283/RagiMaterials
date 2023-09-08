package hiiragi283.material.util;

import net.minecraft.util.NonNullList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;

public abstract class HiiragiCollectors {

    public static <E> BinaryOperator<List<E>> getListCombiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    public static <K, V> BinaryOperator<Map<K, V>> getMapCombiner() {
        return (map1, map2) -> {
            map1.putAll(map2);
            return map1;
        };
    }

    //    NonNullList    //

    public static <T> Collector<NonNullList<T>, List<T>, List<T>> NON_NULL_LIST_COLLECTOR() {
        return Collector.of(
                ArrayList::new,
                List::addAll,
                getListCombiner(),
                list -> list
        );
    }

    //    String Serialization    //

    public static final Collector<String, StringBuilder, String> STRING_SERIALIZATION_COLLECTOR = Collector.of(
            StringBuilder::new,
            (builder, value) -> builder.append(value).append(";"),
            StringBuilder::append,
            builder -> {
                builder.setLength(builder.length() -1);
                return builder.toString();
            }
    );

}