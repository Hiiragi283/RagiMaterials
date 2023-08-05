@file:JvmName("HiiragiUtil")

package hiiragi283.core.util

import hiiragi283.core.RagiMaterials
import hiiragi283.material.RMReference
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.client.Minecraft
import net.minecraft.command.ICommandSender
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumRarity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.IRarity
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryModifiable
import java.util.*

//    Calendar    //

//4月1日かを判定するメソッド
fun isAprilFools(): Boolean =
    RagiMaterials.CALENDAR.get(Calendar.MONTH) + 1 == 4 && RagiMaterials.CALENDAR.get(Calendar.DATE) == 1

//    Drop    //

fun dropItemAtPlayer(player: EntityPlayer, stack: ItemStack, x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) {
    val world = player.world
    if (!world.isRemote) {
        val posPlayer = player.position
        dropItem(world, posPlayer, stack, x, y, z)
    }
}

fun dropInventoryItems(
    world: World,
    pos: BlockPos,
    inventory: IItemHandler,
    x: Double = 0.0,
    y: Double = 0.0,
    z: Double = 0.0
) {
    for (i in 0 until inventory.slots) {
        val stack = inventory.getStackInSlot(i)
        dropItem(world, pos, stack, x, y, z)
    }
}

fun dropItemFromTile(
    world: World,
    pos: BlockPos,
    stack: ItemStack,
    tile: TileEntity,
    x: Double = 0.0,
    y: Double = 0.0,
    z: Double = 0.0
) {
    stack.getOrCreateSubCompound("BlockEntityTag").merge(tile.updateTag)
    dropItem(world, pos, stack, x, y, z)
}

fun dropItem(world: World, pos: BlockPos, stack: ItemStack, x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) {
    if (!world.isRemote && !stack.isEmpty) {
        val drop = EntityItem(world, pos.x.toDouble() + 0.5f, pos.y.toDouble(), pos.z.toDouble() + 0.5f, stack)
        drop.setPickupDelay(0) //即座に回収できるようにする
        drop.motionX = x
        drop.motionY = y
        drop.motionZ = z
        world.spawnEntity(drop) //ドロップアイテムをスポーン
    }
}

//    FluidStack    //

fun Pair<String, Int>.toFluidStack(): FluidStack =
    FluidStack(FluidRegistry.getFluidStack(this.first, this.second), this.second)

//    ItemStack    //

fun getItemStack(location: String, amount: Int, meta: Int): ItemStack? {
    val block = getBlock(location)
    if (block !== null) return ItemStack(block, amount, meta)
    val item = getItem(location)
    return if (item !== null) ItemStack(item, amount, meta) else null
}

fun IBlockState.toItemStack(amount: Int = 1): ItemStack =
    ItemStack(this.block, amount, this.block.getMetaFromState(this))

//ItemとMetadataのみで比較
fun ItemStack.isSameWithoutCount(other: ItemStack): Boolean =
    this.item == other.item && (this.metadata == other.metadata || this.metadata == 32767 || other.metadata == 32767)

//Item, Count, Metadataで比較
fun ItemStack.isSame(other: ItemStack): Boolean = this.isSameWithoutCount(other) && this.count == other.count

//NBTタグも含めて比較
fun ItemStack.isSameWithNBT(other: ItemStack): Boolean = this.isSame(other) && this.tagCompound == other.tagCompound

//    Registry    //

fun getBlock(location: String): Block? = ForgeRegistries.BLOCKS.getValue(ResourceLocation(location))

fun getItem(location: String): Item? = ForgeRegistries.ITEMS.getValue(ResourceLocation(location))

fun getBlockState(location: String, meta: Int): IBlockState? = getBlock(location)?.getStateFromMeta(meta)

fun removeCrafting(registryName: ResourceLocation) {
    CraftingManager.getRecipe(registryName)?.let {
        removeRegistryEntry(ForgeRegistries.RECIPES, registryName)
    } ?: RagiMaterials.LOGGER.debug("The recipe {} was not found...", registryName)
}

fun removeCrafting(registryName: String) {
    removeCrafting(ResourceLocation((registryName)))
}

fun removeRegistryEntry(registry: IForgeRegistry<*>, registryName: ResourceLocation): Boolean {
    return if (registry is IForgeRegistryModifiable<*>) {
        registry.remove(registryName)
        RagiMaterials.LOGGER.warn("The entry $registryName was removed from ${registry::class.java.name}!")
        true
    } else {
        RagiMaterials.LOGGER.warn("The registry ${registry::class.java.name} is not implementing IForgeRegistryModifiable!")
        false
    }
}

fun removeRegistryEntry(registry: IForgeRegistry<*>, registryName: String): Boolean =
    removeRegistryEntry(registry, ResourceLocation(registryName))

//    ResourceLocation    //

fun hiiragiLocation(path: String): ResourceLocation = ResourceLocation(RMReference.MOD_ID, path)

fun ItemStack.toLocation(split: String = ":"): ResourceLocation = this.item.registryName!!.append(split + this.metadata)

fun IBlockState.toLocation(): ResourceLocation =
    this.block.registryName!!.append(":" + this.block.getMetaFromState(this))

fun FluidStack.toLocation(addAmount: Boolean): ResourceLocation {
    val location = ResourceLocation("fluid", this.fluid.name)
    if (addAmount) location.append(":" + this.amount)
    return location
}

//ResourceLocationの末尾に付け足す関数
fun ResourceLocation.append(path: String) = ResourceLocation(this.namespace, this.path + path)

//    TileEntity    //

@Suppress("UNCHECKED_CAST")
fun <T : TileEntity> getTile(world: IBlockAccess?, pos: BlockPos?): T? = pos?.let { world?.getTileEntity(it) } as? T

//    Misc    //

fun executeCommand(sender: ICommandSender, command: String) {
    Minecraft.getMinecraft().integratedServer?.getCommandManager()?.executeCommand(sender, command)
}

fun getEnumRarity(name: String): IRarity {
    return when (name) {
        "Uncommon" -> EnumRarity.UNCOMMON
        "Rare" -> EnumRarity.RARE
        "Epic" -> EnumRarity.EPIC
        else -> EnumRarity.COMMON
    }
}