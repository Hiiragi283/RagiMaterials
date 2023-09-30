package hiiragi283.material.api.multiblock

import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.function.BiPredicate

class MultiblockPattern : BiPredicate<World, BlockPos> {

    val map: Map<BlockPos, MultiblockComponent>

    constructor(map: Map<BlockPos, MultiblockComponent> = mapOf()) : super() {
        this.map = map
    }

    constructor(vararg list: Pair<BlockPos, MultiblockComponent>) {
        map = list.toMap()
    }

    fun getAbsolutePoses(origin: BlockPos): Collection<BlockPos> = getRelativePoses().map(origin::add)

    fun getRelativePoses(): Collection<BlockPos> = map.keys

    fun getComponent(pos: BlockPos): MultiblockComponent? = map[pos]

    fun getComponents(): Collection<MultiblockComponent> = map.values

    fun hasComponent(pos: BlockPos): Boolean = map.containsKey(pos)

    //    BiPredicate    //

    override fun test(t: World, u: BlockPos): Boolean = map
        .map { (pos: BlockPos, component: MultiblockComponent) -> u.add(pos) to component }
        .all { (pos: BlockPos, component: MultiblockComponent) -> component.test(t, pos) }

}