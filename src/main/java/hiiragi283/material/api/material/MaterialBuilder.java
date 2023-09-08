package hiiragi283.material.api.material;

import hiiragi283.material.api.shape.ShapeType;
import hiiragi283.material.util.HiiragiCollectors;
import hiiragi283.material.util.HiiragiColor;
import hiiragi283.material.util.HiiragiUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collector;

public abstract class MaterialBuilder {

    //    Element    //

    private static HiiragiMaterial create(String name, int index) {
        return create(name, index, HiiragiUtil.getEmptyConsumer());
    }

    public static HiiragiMaterial create(String name, int index, Consumer<HiiragiMaterial> consumer) {
        var material = new HiiragiMaterial(name, index);
        consumer.accept(material);
        return material;
    }

    //    Isotope    //

    public static HiiragiMaterial createIsotope(String name, int index, HiiragiMaterial parent, Consumer<HiiragiMaterial> consumer) {
        var isotope = create(name, index);
        isotope.color = parent.color;
        isotope.shapeType = parent.shapeType;
        isotope.tempBoil = parent.tempBoil;
        isotope.tempMelt = parent.tempMelt;
        consumer.accept(isotope);
        return isotope;
    }

    //    Compound    //

    public static HiiragiMaterial createCompound(String name, int index, Map<HiiragiMaterial, Integer> components) {
        return createCompound(name, index, components, HiiragiUtil.getEmptyConsumer());
    }

    public static HiiragiMaterial createCompound(String name, int index, Map<HiiragiMaterial, Integer> components, Consumer<HiiragiMaterial> consumer) {
        var compound = create(name, index);
        initCompound(compound, components);
        consumer.accept(compound);
        return compound;
    }

    private static void initCompound(HiiragiMaterial compound, Map<HiiragiMaterial, Integer> components) {
        compound.color = components.entrySet().stream().collect(MATERIAL_COLOR_COLLECTOR).getRGB();
        compound.formula = components.entrySet().stream()
                .filter(entry -> entry.getKey().hasFormula())
                .collect(COMPOUND_FORMULA_COLLECTOR);
        compound.molar = components.entrySet().stream()
                .filter(entry -> entry.getKey().hasMolar())
                .collect(MOLAR_COLLECTOR);
    }

    private static final Collector<Map.Entry<HiiragiMaterial, Integer>, Map<Color, Integer>, Color> MATERIAL_COLOR_COLLECTOR = Collector.of(
            HashMap::new,
            (map, entry) -> map.put(new Color(entry.getKey().color), entry.getValue()),
            HiiragiCollectors.getMapCombiner(),
            HiiragiColor::blendColor
    );

    private static final Collector<Map.Entry<HiiragiMaterial, Integer>, StringBuilder, String> COMPOUND_FORMULA_COLLECTOR = Collector.of(
            StringBuilder::new,
            (builder, entry) -> {
                String rawFormula = entry.getKey().formula;
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

    private static final Collector<Map.Entry<HiiragiMaterial, Integer>, List<Double>, Double> MOLAR_COLLECTOR = Collector.of(
            ArrayList::new,
            (list, entry) -> list.add(entry.getKey().molar * entry.getValue()),
            HiiragiCollectors.getListCombiner(),
            list -> list.stream().mapToDouble(d -> d).sum()
    );

    //    Solution    //

    public static HiiragiMaterial createSolution(String name, int index, Map<HiiragiMaterial, Integer> components) {
        return createSolution(name, index, components, HiiragiUtil.getEmptyConsumer());
    }

    public static HiiragiMaterial createSolution(String name, int index, Map<HiiragiMaterial, Integer> components, Consumer<HiiragiMaterial> consumer) {
        var solution = create(name, index);
        solution.tempBoil = CommonMaterials.WATER.tempBoil;
        solution.tempMelt = CommonMaterials.WATER.tempMelt;
        initCompound(solution, components);
        consumer.accept(solution);
        return solution;
    }

    //    Alloy    //


    public static HiiragiMaterial createAlloy(String name, int index, Map<HiiragiMaterial, Integer> components, Consumer<HiiragiMaterial> consumer) {
        var alloy = create(name, index);
        initCompound(alloy, components);
        initTempBoil(alloy, components);
        initTempMelt(alloy, components);
        consumer.accept(alloy);
        return alloy;
    }

    private static void initTempBoil(HiiragiMaterial alloy, Map<HiiragiMaterial, Integer> components) {
        alloy.tempMelt = components.entrySet().stream()
                .filter(entry -> entry.getKey().hasTempBoil())
                .collect(TEMP_BOIL_COLLECTOR);
    }

    private static void initTempMelt(HiiragiMaterial alloy, Map<HiiragiMaterial, Integer> components) {
        alloy.tempMelt = components.entrySet().stream()
                .filter(entry -> entry.getKey().hasTempMelt())
                .collect(TEMP_MELT_COLLECTOR);
    }

    private static final Collector<Map.Entry<HiiragiMaterial, Integer>, List<Integer>, Integer> TEMP_BOIL_COLLECTOR = Collector.of(
            ArrayList::new,
            (list, entry) -> list.add(entry.getKey().tempBoil * entry.getValue()),
            HiiragiCollectors.getListCombiner(),
            list -> list.stream().mapToInt(i -> i).sum()
    );

    private static final Collector<Map.Entry<HiiragiMaterial, Integer>, List<Integer>, Integer> TEMP_MELT_COLLECTOR = Collector.of(
            ArrayList::new,
            (list, entry) -> list.add(entry.getKey().tempMelt * entry.getValue()),
            HiiragiCollectors.getListCombiner(),
            list -> list.stream().mapToInt(i -> i).sum()
    );

    //    Mixture    //

    public static HiiragiMaterial createMixture(String name, int index, List<HiiragiMaterial> components) {
        return createMixture(name, index, components, HiiragiUtil.getEmptyConsumer());
    }

    public static HiiragiMaterial createMixture(String name, int index, List<HiiragiMaterial> components, Consumer<HiiragiMaterial> consumer) {
        var mixture = create(name, index);
        initMixture(mixture, components);
        consumer.accept(mixture);
        return mixture;
    }

    private static void initMixture(HiiragiMaterial mixture, List<HiiragiMaterial> components) {
        mixture.formula = components.stream()
                .filter(HiiragiMaterial::hasFormula)
                .collect(MIXTURE_FORMULA_COLLECTOR);
        mixture.molar = 0.0;
    }

    private static final Collector<HiiragiMaterial, StringBuilder, String> MIXTURE_FORMULA_COLLECTOR = Collector.of(
            () -> new StringBuilder("("),
            (builder, material) -> builder.append(material.formula).append(", "),
            StringBuilder::append,
            builder -> {
                if (builder.length() >= 2) {
                    builder.setLength(builder.length() - 2);
                }
                builder.append(")");
                return builder.toString();
            }
    );


    //    Steel    //

    public static HiiragiMaterial createSteel(String name, int index, List<HiiragiMaterial> components, Consumer<HiiragiMaterial> consumer) {
        var steel = create(name, index);
        initMixture(steel, components);
        steel.shapeType = ShapeType.METAL_ADVANCED;
        steel.tempBoil = ElementMaterials.IRON.tempBoil;
        steel.tempMelt = ElementMaterials.IRON.tempMelt;
        consumer.accept(steel);
        return steel;
    }

    //    Hydrate    //

    public static HiiragiMaterial createHydrate(String name, int index, HiiragiMaterial parent, int waterAmount, Consumer<HiiragiMaterial> consumer) {
        var hydrate = create(name, index);
        hydrate.molar = parent.molar + waterAmount * CommonMaterials.WATER.molar;
        hydrate.formula = parent.formula + "・" + waterAmount + CommonMaterials.WATER.formula;
        consumer.accept(hydrate);
        return hydrate;
    }

    //    Polymer    //

    public static HiiragiMaterial createPolymer(String name, int index, Map<HiiragiMaterial, Integer> monomar, Consumer<HiiragiMaterial> consumer) {
        var polymer = create(name, index);
        initCompound(polymer, monomar);
        polymer.formula = "(" + polymer.formula + ")n";
        polymer.molar = 0.0;
        consumer.accept(polymer);
        return polymer;
    }

}