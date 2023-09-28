package hiiragi283.material.api.multiblock

import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.function.BiPredicate

class MultiblockPattern() : BiPredicate<World, BlockPos> {

    private val map: HashMap<BlockPos, MultiblockComponent> = hashMapOf()

    constructor(vararg pairs: Pair<BlockPos, MultiblockComponent>) : this() {
        pairs.forEach(::putPredicate)
    }

    fun getPoses(origin: BlockPos): Collection<BlockPos> = map.keys.map(origin::add)

    fun getComponent(pos: BlockPos): MultiblockComponent? = map[pos]

    fun hasComponent(pos: BlockPos): Boolean = map.containsKey(pos)

    private fun putPredicate(pair: Pair<BlockPos, MultiblockComponent>) {
        putPredicate(pair.first, pair.second)
    }

    fun putPredicate(pos: BlockPos, component: MultiblockComponent) {
        if (map.containsKey(pos)) {
            throw IllegalStateException("This relative position already has component!")
        }
        map[pos] = component
    }

    //    BiPredicate    //

    override fun test(t: World, u: BlockPos): Boolean = map
        .map { (pos: BlockPos, component: MultiblockComponent) -> u.add(pos) to component }
        .all { (pos: BlockPos, component: MultiblockComponent) -> component.test(t, pos) }

}