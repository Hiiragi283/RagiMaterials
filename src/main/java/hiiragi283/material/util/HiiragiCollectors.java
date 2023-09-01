package hiiragi283.material.util;

import hiiragi283.material.api.material.HiiragiMaterial;

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

    public static final Collector<Color, Map<String, Integer>, Color> COLOR_COLLECTOR = Collector.of(
            HashMap::new,
            (map, color) -> {
            },
            getMapCombiner(),
            map -> HiiragiColor.WHITE,
            Collector.Characteristics.IDENTITY_FINISH
    );

    public static final Collector<Map.Entry<HiiragiMaterial, Integer>, Map<Color, Integer>, Color> MATERIAL_COLOR_COLLECTOR = Collector.of(
            HashMap::new,
            (map, entry) -> map.put(new Color(entry.getKey().color()), entry.getValue()),
            getMapCombiner(),
            HiiragiColor::blendColor,
            Collector.Characteristics.IDENTITY_FINISH
    );

    //    Formula    //

    public static final Collector<Map.Entry<HiiragiMaterial, Integer>, StringBuilder, String> FORMULA_COLLECTOR = Collector.of(
            StringBuilder::new,
            (builder, entry) -> {
                String rawFormula = entry.getKey().formula();
                int weight = entry.getValue();
                builder.append(rawFormula);
                //値が1の場合はパス
                if (weight != 1) {
                    //化学式の下付き数字の桁数調整
                    int weight1 = weight % 10;
                    int weight10 = weight / 10;
                    Character char1 = (char) ('₀' + weight1);
                    Character char10 = (char) ('₀' + weight10);
                    //2桁目が0でない場合，下付き数字を2桁にする
                    var builderInternal = new StringBuilder();
                    builderInternal.append(char1);
                    if (weight10 != 0) {
                        builderInternal.append(char10);
                    }
                    builder.append(builderInternal);
                }
            },
            (builder1, builder2) -> {
                builder1.append(builder2);
                return builder1;
            },
            StringBuilder::toString,
            Collector.Characteristics.IDENTITY_FINISH
    );

    //    Molar    //

    public static final Collector<Map.Entry<HiiragiMaterial, Integer>, List<Double>, Double> MOLAR_COLLECTOR = Collector.of(
            ArrayList::new,
            (list, entry) -> list.add(entry.getKey().molar() * entry.getValue()),
            getListCombiner(),
            list -> list.stream().mapToDouble(d -> d).sum(),
            Collector.Characteristics.IDENTITY_FINISH
    );

    //    Temperature    //

    public static final Collector<Map.Entry<HiiragiMaterial, Integer>, List<Integer>, Integer> TEMP_MELT_COLLECTOR = Collector.of(
            ArrayList::new,
            (list, entry) -> list.add(entry.getKey().tempMelt() * entry.getValue()),
            getListCombiner(),
            list -> list.stream().mapToInt(i -> i).sum(),
            Collector.Characteristics.IDENTITY_FINISH
    );

    public static final Collector<Map.Entry<HiiragiMaterial, Integer>, List<Integer>, Integer> TEMP_BOIL_COLLECTOR = Collector.of(
            ArrayList::new,
            (list, entry) -> list.add(entry.getKey().tempBoil() * entry.getValue()),
            getListCombiner(),
            list -> list.stream().mapToInt(i -> i).sum(),
            Collector.Characteristics.IDENTITY_FINISH
    );

}