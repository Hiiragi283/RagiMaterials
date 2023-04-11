package hiiragi283.ragi_materials.integration

import hiiragi283.ragi_materials.capability.heat.CapabilityHeat
import hiiragi283.ragi_materials.util.RagiLogger
import mcp.mobius.waila.api.*
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
Thanks to defeatedcrow!
Source: https://github.com/Hiiragi283/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/plugin/waila/DCWailaPlugin.java
 */

@WailaPlugin
class PluginWaila : IWailaPlugin {

    override fun register(registrar: IWailaRegistrar) {

        registrar.registerBodyProvider(HUDHandlerHeat, TileEntity::class.java)
        registrar.registerNBTProvider(HUDHandlerHeat, TileEntity::class.java)
        registrar.addConfig("Capability", "capability.heatinfo", false)

        RagiLogger.info("The integration for Waila/Hwyla has loaded!")
    }

    object HUDHandlerHeat : IWailaDataProvider {

        //HUDに表示する情報を取得するメソッド
        override fun getWailaBody(itemStack: ItemStack, tooltip: MutableList<String>, accessor: IWailaDataAccessor, config: IWailaConfigHandler): MutableList<String> {
            if (config.getConfig("capability.heatinfo")) {
                val tile = accessor.tileEntity
                if (tile !== null && tile.hasCapability(CapabilityHeat.HEAT, null)) {
                    val tag = accessor.nbtData.getCompoundTag("forgeHeat")
                    tooltip.add(String.format("%d / %d FE", tag.getInteger("stored"), tag.getInteger("capacity")))
                }
            }
            return tooltip
        }

        //HUDを表示する対象のNBTタグを取得するメソッド
        override fun getNBTData(player: EntityPlayerMP, tile: TileEntity?, tag: NBTTagCompound, world: World, pos: BlockPos): NBTTagCompound {
            if (tile !== null && tile.hasCapability(CapabilityHeat.HEAT, null)) {
                val heatStorage = tile.getCapability(CapabilityHeat.HEAT, null)!!
                tag.setTag("forgeHeat", NBTTagCompound().apply {
                    this.setInteger("capacity", heatStorage.getMaxHeatStored())
                    this.setInteger("stored", heatStorage.getHeatStored())
                })
            }
            return tag
        }

    }
}