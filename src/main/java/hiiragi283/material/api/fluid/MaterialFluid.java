package hiiragi283.material.api.fluid;

import hiiragi283.material.api.material.HiiragiMaterial;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class MaterialFluid extends Fluid {

    public final HiiragiMaterial material;

    public MaterialFluid(HiiragiMaterial material) {
        this(material, new ResourceLocation("blocks/concrete_white"));
    }

    public MaterialFluid(HiiragiMaterial material, ResourceLocation still) {
        this(material, still, still);
    }

    public MaterialFluid(HiiragiMaterial material, ResourceLocation still, ResourceLocation flowing) {
        super(material.name, still, flowing, material.color);
        this.material = material;
        material.getFluidBlock().ifPresent(block -> this.block = block);
        //融点と沸点が有効 -> 標準状態に基づいてプロパティを補完
        if (material.hasTempMelt() && material.hasTempBoil()) {
            //標準状態で固体 -> 温度は融点に等しい
            if (material.isSolid()) {
                temperature = material.tempMelt;
                luminosity = 15;
            }
            //標準状態で液体 -> 温度は298 K
            else if (material.isLiquid()) {
                temperature = 298;
            } else if (material.isGas()) {
                temperature = 298;
                isGaseous = true;
                density *= -1; //密度を負にすると上に落ちる
            }
        }
        //とりあえず温度を298 Kにする
        else {
            temperature = 298;
        }
    }

    @Override
    public SoundEvent getEmptySound() {
        return material.isSolid() ? SoundEvents.ITEM_BUCKET_EMPTY_LAVA : SoundEvents.ITEM_BUCKET_EMPTY;
    }

    @Override
    public SoundEvent getFillSound() {
        return material.isSolid() ? SoundEvents.ITEM_BUCKET_FILL_LAVA : SoundEvents.ITEM_BUCKET_FILL;
    }

    @Override
    public String getUnlocalizedName() {
        return material.translationKey;
    }

    public static void register() {
        HiiragiMaterial.REGISTRY.getValues().forEach(HiiragiMaterial::registerFluid);
    }

}