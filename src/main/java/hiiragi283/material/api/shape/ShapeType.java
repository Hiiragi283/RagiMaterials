package hiiragi283.material.api.shape;

import com.github.bsideup.jabel.Desugar;
import hiiragi283.material.api.event.HiiragiEventFactory;

import java.util.*;
import java.util.function.Consumer;

@Desugar
public record ShapeType(String name, Set<HiiragiShape> shapes) {

    public ShapeType {
        HiiragiEventFactory.onShapeTypeCreate(name, shapes);
    }

    private ShapeType(String name, Collection<HiiragiShape> shapes) {
        this(name, new HashSet<>(shapes));
    }

    public static final ShapeType INTERNAL = new ShapeType("internal", Collections.singletonList(HiiragiShapes.BOTTLE));

    public static final ShapeType WILDCARD = new ShapeType("wildcard", Collections.emptyList());

    public static final ShapeType SOLID = INTERNAL.child("solid", Arrays.asList(
            HiiragiShapes.CLUSTER,
            HiiragiShapes.CRUSHED,
            HiiragiShapes.DUST,
            HiiragiShapes.DUST_TINY,
            HiiragiShapes.ORE,
            HiiragiShapes.ORE_DENSE,
            HiiragiShapes.ORE_POOR,
            HiiragiShapes.PURIFIED,
            HiiragiShapes.SOLID
    ));

    public static final ShapeType GAS = INTERNAL.child("gas", Collections.singletonList(HiiragiShapes.GAS));

    private static final ShapeType GEM_4x = SOLID.child("gem_4x", Collections.singletonList(HiiragiShapes.GEM));

    private static final ShapeType GEM_4x_ADVANCED = GEM_4x.child("gem_4x_advanced", Arrays.asList(
            HiiragiShapes.PLATE,
            HiiragiShapes.PLATE_DENSE,
            HiiragiShapes.STICK
    ));

    private static final ShapeType GEM_9x = GEM_4x.child("gem_9x", Arrays.asList(HiiragiShapes.BLOCK, HiiragiShapes.SLAB));

    private static final ShapeType GEM_9x_ADVANCED = GEM_9x.child("gem_4x_advanced", Arrays.asList(
            HiiragiShapes.PLATE,
            HiiragiShapes.PLATE_DENSE,
            HiiragiShapes.STICK
    ));

    public static final ShapeType GEM_AMORPHOUS = GEM_9x.child("amorphous", Collections.emptyList());
    public static final ShapeType GEM_COAL = GEM_9x.child("coal", Collections.emptyList());
    public static final ShapeType GEM_CUBIC = GEM_9x.child("cubic", Collections.emptyList());
    public static final ShapeType GEM_DIAMOND = GEM_9x_ADVANCED.child("diamond", Collections.emptyList());
    public static final ShapeType GEM_EMERALD = GEM_9x_ADVANCED.child("emerald", Collections.emptyList());
    public static final ShapeType GEM_LAPIS = GEM_9x_ADVANCED.child("lapis", Collections.emptyList());
    public static final ShapeType GEM_QUARTZ = GEM_4x_ADVANCED.child("quartz", Collections.emptyList());
    public static final ShapeType GEM_RUBY = GEM_9x_ADVANCED.child("ruby", Collections.emptyList());

    public static final ShapeType LIQUID = INTERNAL.child("liquid", Collections.singletonList(HiiragiShapes.LIQUID));

    public static final ShapeType METAL_COMMON = SOLID.child("metal_common", Arrays.asList(
            HiiragiShapes.BLOCK,
            HiiragiShapes.CLUMP,
            HiiragiShapes.CLUSTER,
            HiiragiShapes.CRYSTAL,
            HiiragiShapes.DUST_DIRTY,
            HiiragiShapes.INGOT,
            HiiragiShapes.METAL,
            HiiragiShapes.NUGGET,
            HiiragiShapes.SHARD,
            HiiragiShapes.SLAB
    ));

    public static final ShapeType METAL_ADVANCED = METAL_COMMON.child("metal_advanced", Arrays.asList(
            HiiragiShapes.BALL,
            HiiragiShapes.COIN,
            HiiragiShapes.FENCE,
            HiiragiShapes.GEAR,
            HiiragiShapes.PLATE,
            HiiragiShapes.SCAFFOLDING,
            HiiragiShapes.SHEETMETAL,
            HiiragiShapes.SLAB_SHEETMETAL,
            HiiragiShapes.STICK,
            HiiragiShapes.WIRE
    ));

    public static final ShapeType SAND = SOLID.child("sand", Collections.singletonList(HiiragiShapes.SAND));

    public static final ShapeType STONE = SOLID.child("stone", Arrays.asList(
            HiiragiShapes.GEAR,
            HiiragiShapes.PLATE,
            HiiragiShapes.STICK,
            HiiragiShapes.STONE
    ));

    public static final ShapeType WOOD = SOLID.child("wood", Arrays.asList(
            HiiragiShapes.FENCE,
            HiiragiShapes.GEAR,
            HiiragiShapes.LOG,
            HiiragiShapes.PLANK,
            HiiragiShapes.PLATE,
            HiiragiShapes.SCAFFOLDING,
            HiiragiShapes.STICK
    ));

    public ShapeType child(String name, Collection<HiiragiShape> shapes) {
        List<HiiragiShape> list = new ArrayList<>(this.shapes());
        list.addAll(shapes);
        Set<HiiragiShape> set = new HashSet<>(list);
        HiiragiEventFactory.onShapeTypeCopy(name, set, this);
        return new ShapeType(name, set);
    }

    public ShapeType copyAndEdit(Consumer<Set<HiiragiShape>> consumer) {
        Set<HiiragiShape> set = new HashSet<>(shapes);
        consumer.accept(set);
        return new ShapeType(name, set);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof ShapeType)) return false;
        return Objects.equals(((ShapeType) obj).name, this.name);
    }

}