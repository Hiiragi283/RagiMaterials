package hiiragi283.api.event

import hiiragi283.api.capability.material.IMaterialHandler
import hiiragi283.api.material.MaterialStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.Event

sealed class MaterialStackEvent(
    val stack: MaterialStack,
    val world: World,
    val pos: BlockPos
) : Event() {

    companion object {
        @JvmStatic
        fun fireEvent(event: MaterialStackEvent) = MinecraftForge.EVENT_BUS.post(event)
    }

    class Insert(
        stack: MaterialStack,
        world: World,
        pos: BlockPos,
        val handler: IMaterialHandler
    ) : MaterialStackEvent(stack, world, pos)

    class Extract(
        stack: MaterialStack,
        world: World,
        pos: BlockPos,
        val handler: IMaterialHandler
    ) : MaterialStackEvent(stack, world, pos)

}