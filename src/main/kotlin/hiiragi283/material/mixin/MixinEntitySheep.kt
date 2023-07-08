package hiiragi283.material.mixin

import net.minecraft.entity.passive.EntityAnimal
import net.minecraft.entity.passive.EntitySheep
import net.minecraft.init.Blocks
import net.minecraft.init.SoundEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.IShearable
import org.spongepowered.asm.mixin.Mixin

@Mixin(EntitySheep::class)
abstract class MixinEntitySheep(world: World) : EntityAnimal(world), IShearable {

    override fun onSheared(item: ItemStack, world: IBlockAccess, pos: BlockPos, fortune: Int): List<ItemStack> {
        @Suppress("CAST_NEVER_SUCCEEDS")
        (this as EntitySheep).sheared = true
        val i = 1 + this.rand.nextInt(3)
        val ret: MutableList<ItemStack> = mutableListOf()
        for (j in 0 until i) ret.add(
            ItemStack(
                Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY),
                1,
                this.fleeceColor.metadata
            )
        )
        this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0f, 1.0f)
        return ret
    }

}