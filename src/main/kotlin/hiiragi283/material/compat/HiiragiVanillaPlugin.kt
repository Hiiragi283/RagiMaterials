package hiiragi283.material.compat

import hiiragi283.material.api.part.PartDictionary
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.init.materials.MaterialCompats
import hiiragi283.material.init.materials.MaterialElements
import hiiragi283.material.util.registerOreDict
import hiiragi283.material.util.shareOredict
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.EnumDyeColor
import net.minecraftforge.fml.common.event.FMLInitializationEvent

object HiiragiVanillaPlugin : HiiragiPluginBase("minecraft", "Minecraft", { true }) {

    override fun registerMaterial() {
        MaterialCompats.REDSTONE.register()
        MaterialCompats.LAPIS.register()
        MaterialCompats.GLOWSTONE.register()
        MaterialCompats.ENDER_PEARL.register()
    }

    override fun onInit(event: FMLInitializationEvent) {
        PartDictionary.add(Blocks.STONE, 0, HiiragiShapes.STONE.getPart(MaterialCommons.STONE))
        PartDictionary.add(Blocks.NETHERRACK, 0, HiiragiShapes.STONE.getPart(MaterialCommons.NETHERRACK))
        PartDictionary.add(Blocks.END_STONE, 0, HiiragiShapes.STONE.getPart(MaterialCommons.END_STONE))

        PartDictionary.add(Items.COAL, 0, HiiragiShapes.GEM.getPart(MaterialCommons.COAL))
        PartDictionary.add(Items.COAL, 1, HiiragiShapes.GEM.getPart(MaterialCommons.CHARCOAL))
        PartDictionary.add(Items.DYE, EnumDyeColor.WHITE.metadata, HiiragiShapes.DUST.getPart(MaterialCommons.APATITE))
        PartDictionary.add(Items.ENDER_PEARL, 0, HiiragiShapes.GEM.getPart(MaterialCompats.ENDER_PEARL))
        PartDictionary.add(Items.GUNPOWDER, 0, HiiragiShapes.DUST.getPart(MaterialCommons.GUNPOWDER))
        PartDictionary.add(Items.STICK, 0, HiiragiShapes.STICK.getPart(MaterialCommons.WOOD))
        PartDictionary.add(Items.SUGAR, 0, HiiragiShapes.DUST.getPart(MaterialCommons.SUGAR))
        /*registerOreDict(
            HiiragiShapes.DUST.getOreDict(MaterialCommons.GUNPOWDER),
            Items.GUNPOWDER,
            share = "gunpowder"
        )
        registerOreDict(HiiragiShapes.DUST.getOreDict(MaterialCommons.SUGAR), Items.SUGAR, share = "sugar")
        registerOreDict(HiiragiShapes.GEM.getOreDict(MaterialCommons.CHARCOAL), Items.COAL, 1, share = "charcoal")
        registerOreDict(HiiragiShapes.GEM.getOreDict(MaterialCommons.COAL), Items.COAL, share = "coal")
        registerOreDict(
            HiiragiShapes.GEM.getOreDict(MaterialCompats.ENDER_PEARL),
            Items.ENDER_PEARL,
            share = "enderpearl"
        )
        registerOreDict(HiiragiShapes.STICK.getOreDict(MaterialCommons.WOOD), Items.STICK, share = "stick")

        shareOredict("fuelCoke", "gemCoke")*/
    }

}