package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialCompat
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.registerOreDict
import hiiragi283.material.util.shareOredict
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraftforge.fml.common.event.FMLInitializationEvent

object HiiragiVanillaPlugin : HiiragiPluginBase("minecraft", "Minecraft", { true }) {

    override fun registerMaterial() {
        MaterialCompat.REDSTONE.register()
        MaterialCompat.LAPIS.register()
        MaterialCompat.GLOWSTONE.register()
        MaterialCompat.ENDER_PEARL.register()
    }

    override fun onInit(event: FMLInitializationEvent) {
        registerOreDict(HiiragiShapes.STONE.getOreDict(MaterialCommon.STONE), Blocks.STONE)
        registerOreDict(HiiragiShapes.STONE.getOreDict(MaterialCommon.NETHERRACK), Blocks.NETHERRACK)
        registerOreDict(HiiragiShapes.STONE.getOreDict(MaterialCommon.END_STONE), Blocks.END_STONE)

        registerOreDict(
            HiiragiShapes.DUST.getOreDict(MaterialCommon.GUNPOWDER),
            Items.GUNPOWDER,
            share = "gunpowder"
        )
        registerOreDict(HiiragiShapes.DUST.getOreDict(MaterialCommon.SUGAR), Items.SUGAR, share = "sugar")
        registerOreDict(HiiragiShapes.GEM.getOreDict(MaterialCommon.CHARCOAL), Items.COAL, 1, share = "charcoal")
        registerOreDict(HiiragiShapes.GEM.getOreDict(MaterialCommon.COAL), Items.COAL, share = "coal")
        registerOreDict(
            HiiragiShapes.GEM.getOreDict(MaterialCompat.ENDER_PEARL),
            Items.ENDER_PEARL,
            share = "enderpearl"
        )
        registerOreDict(HiiragiShapes.STICK.getOreDict(MaterialCommon.WOOD), Items.STICK, share = "stick")

        shareOredict("fuelCoke", "gemCoke")
    }

}