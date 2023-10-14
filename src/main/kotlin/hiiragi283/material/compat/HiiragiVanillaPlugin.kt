package hiiragi283.material.compat

import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.init.materials.MaterialCompats
import hiiragi283.material.util.registerOreDict
import hiiragi283.material.util.shareOredict
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraftforge.fml.common.event.FMLInitializationEvent

object HiiragiVanillaPlugin : HiiragiPluginBase("minecraft", "Minecraft", { true }) {

    override fun registerMaterial() {
        MaterialCompats.REDSTONE.register()
        MaterialCompats.LAPIS.register()
        MaterialCompats.GLOWSTONE.register()
        MaterialCompats.ENDER_PEARL.register()
    }

    override fun onInit(event: FMLInitializationEvent) {
        registerOreDict(HiiragiShapes.STONE.getOreDict(MaterialCommons.STONE), Blocks.STONE)
        registerOreDict(HiiragiShapes.STONE.getOreDict(MaterialCommons.NETHERRACK), Blocks.NETHERRACK)
        registerOreDict(HiiragiShapes.STONE.getOreDict(MaterialCommons.END_STONE), Blocks.END_STONE)

        registerOreDict(
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

        shareOredict("fuelCoke", "gemCoke")
    }

}