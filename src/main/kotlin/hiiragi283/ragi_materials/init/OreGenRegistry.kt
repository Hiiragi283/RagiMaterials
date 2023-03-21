package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.block.BlockOreMaterial
import net.minecraft.world.gen.feature.WorldGenMinable
import net.minecraft.world.gen.feature.WorldGenerator
import net.minecraftforge.event.terraingen.OreGenEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class OreGenRegistry {

    private val gen = WorldGenMinable(RagiBlock.BlockOre1.defaultState.withProperty(BlockOreMaterial.TYPE, 0), 8)

    @SubscribeEvent
    fun generateOrePre(event: OreGenEvent.Pre) {
        generateOre(event, 8, gen, 63, 15)
    }

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