package hiiragi283.material.util;

import hiiragi283.material.api.material.HiiragiMaterial;
import net.minecraft.util.NonNullList;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;

public abstract class HiiragiCollectors {

    private static <E> BinaryOperator<List<E>> getListCombiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    private static <K, V> BinaryOperator<Map<K, V>> getMapCombiner() {
        return (map1, map2) -> {
            map1.putAll(map2);
            return map1;
        };
    }

    //    Color    //

    public static final Collector<Map.Entry<HiiragiMaterial, Integer>, Map<Color, Integer>, Color> MATERIAL_COLOR_COLLECTOR = Collector.of(
            HashMap::new,
            (map, entry) -> map.put(new Color(entry.getKey().color()), entry.getValue()),
            getMapCombiner(),
            HiiragiColor::blendColor
    );

    //    Formula    //

    public static final Collector<Map.Entry<HiiragiMaterial, Integer>, StringBuilder, String> COMPOUND_FORMULA_COLLECTOR = Collector.of(
            StringBuilder::new,
            (builder, entry) -> {
                String rawFormula = entry.getKey().formula();
                int weight = entry.getValue();
                builder.append(rawFormula);
                if (weight > 1) {
                    //化学式の下付き数字の桁数調整
                    int weight1 = weight % 10;
                    int weight10 = weight / 10;
                    Character char1 = (char) ('₀' + weight1);
                    Character char10 = (char) ('₀' + weight10);
                    //2桁目が0でない場合，下付き数字を2桁にする
                    var builderInternal = new StringBuilder();
                    if (weight10 != 0) {
                        builderInternal.append(char10);
                    }
                    builderInternal.append(char1);
                    builder.append(builderInternal);
                }
            },
            StringBuilder::append,
            StringBuilder::toString
    );

    public static final Collector<HiiragiMaterial, StringBuilder, String> MIXTURE_FORMULA_COLLECTOR = Collector.of(
            () -> new StringBuilder("("),
            (builder, material) -> builder.append(material.formula()).append(", "),
            StringBuilder::append,
            builder -> {
                if (builder.length() >= 2) {
                    builder.setLength(builder.length() - 2);
                }
                builder.append(")");
                return builder.toString();
            }
    );

    //    Molar    //

    public static final Collector<Map.Entry<HiiragiMaterial, Integer>, List<Double>, Double> MOLAR_COLLECTOR = Collector.of(
            ArrayList::new,
            (list, entry) -> list.add(entry.getKey().molar() * entry.getValue()),
            getListCombiner(),
            list -> list.stream().mapToDouble(d -> d).sum()
    );

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


    //    Temperature    //

    public static final Collector<Map.Entry<HiiragiMaterial, Integer>, List<Integer>, Integer> TEMP_MELT_COLLECTOR = Collector.of(
            ArrayList::new,
            (list, entry) -> list.add(entry.getKey().tempMelt() * entry.getValue()),
            getListCombiner(),
            list -> list.stream().mapToInt(i -> i).sum()
    );

    public static final Collector<Map.Entry<HiiragiMaterial, Integer>, List<Integer>, Integer> TEMP_BOIL_COLLECTOR = Collector.of(
            ArrayList::new,
            (list, entry) -> list.add(entry.getKey().tempBoil() * entry.getValue()),
            getListCombiner(),
            list -> list.stream().mapToInt(i -> i).sum()
    );

}