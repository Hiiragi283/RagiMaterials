package hiiragi283.ragi_materials.world

import hiiragi283.ragi_materials.block.BlockOreMaterial
import hiiragi283.ragi_materials.init.RagiBlock
import hiiragi283.ragi_materials.util.RagiStateMatcher
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.world.gen.feature.WorldGenMinable
import net.minecraft.world.gen.feature.WorldGenerator
import net.minecraftforge.event.terraingen.OreGenEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class OreGenRegistry {

    @SubscribeEvent
    fun generateOrePre(event: OreGenEvent.Pre) {
        generateOre(event, 8, getGenerator(0, Blocks.STONE), 63, 15)
    }

    private fun getGenerator(meta: Int, base: IBlockState): WorldGenMinable {
        val state = RagiBlock.BlockOre1.defaultState.withProperty(BlockOreMaterial.TYPE, meta / BlockOreMaterial.TYPE.allowedValues.last())
        return WorldGenMinable(state, 8, RagiStateMatcher(base))
    }

    private fun getGenerator(meta: Int, base: Block): WorldGenMinable = getGenerator(meta, base.defaultState)

    private fun generateOre(event: OreGenEvent.Pre, count: Int, generator: WorldGenerator, minHeight: Int, maxHeight: Int) {
        for (i in 0..count) {
            val world = event.world
            val random = event.rand
            val pos = event.pos
            generator.generate(world, random, pos.add(
                    random.nextInt(16),
                    random.nextInt(maxHeight - minHeight) + minHeight,
                    random.nextInt(16)
            ))
        }
    }
}