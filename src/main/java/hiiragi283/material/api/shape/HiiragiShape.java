package hiiragi283.material.api.shape;

import com.github.bsideup.jabel.Desugar;
import com.google.common.base.CaseFormat;
import hiiragi283.material.api.event.HiiragiRegistryEvent;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.registry.HiiragiRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Desugar
public record HiiragiShape(String name, int scale) {

    @NotNull
    public String getOreDict(HiiragiMaterial material) {
        return getOreDictPrefix() + material.getOreDictName();
    }

    public List<String> getOreDicts(HiiragiMaterial material) {
        List<String> list = new ArrayList<>();
        list.add(getOreDict(material));
        if (!material.oreDictAlt.isEmpty()) {
            for (String oreDict : material.oreDictAlt) {
                list.add(getOreDictPrefix() + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, oreDict));
            }
        }
        return list;
    }

    @NotNull
    public String getOreDictPrefix() {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
    }

    @NotNull
    public String getTranslatedName(HiiragiMaterial material) {
        return I18n.format("hiiragi_shape." + name, material.getTranslatedName());
    }

    public boolean hasScale() {
        return scale > 0;
    }

    public boolean isValid(HiiragiMaterial material) {
        return material.shapeType.shapes().contains(this);
    }

    //    General    //

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof HiiragiShape)) return false;
        return Objects.equals(((HiiragiShape) obj).name, this.name);
    }

    @Override
    public String toString() {
        return "Shape:" + name;
    }

    //    Registry    //

    public static HiiragiRegistry<String, HiiragiShape> REGISTRY = new HiiragiRegistry<>("Shape");

    public static void registerShapes() {
        var event = new HiiragiRegistryEvent.Shape(HiiragiShape.REGISTRY);
        MinecraftForge.EVENT_BUS.post(event);
        HiiragiShape.REGISTRY.setUnmodifiable();
    }

}