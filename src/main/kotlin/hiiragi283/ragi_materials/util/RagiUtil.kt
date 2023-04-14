package hiiragi283.ragi_materials.util

import net.minecraft.block.state.IBlockState
import net.minecraft.client.Minecraft
import net.minecraft.command.ICommandSender
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.EnumRarity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.common.IRarity
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.items.IItemHandler

fun ItemStack.toBracket(): String {
    val item: Item = this.item
    val location: ResourceLocation? = item.registryName
    val amount: Int = this.count
    val meta: Int = this.metadata
    return "<$location:$meta> * $amount"
}

fun ItemStack.toLocation() = ResourceLocation(this.item.registryName!!.toString() + "_" + this.metadata)

/**
 * Thanks to defeatedcrow!
 * Source: https://github.com/defeatedcrow/HeatAndClimateLib/blob/1.12.2_v3/main/java/defeatedcrow/hac/core/util/DCUtil.java#L130
 */

fun ItemStack.same(stackTo: ItemStack): Boolean {
    var result = this.isEmpty && stackTo.isEmpty
    if (!this.isEmpty && !stackTo.isEmpty) {
        val isSameItem = this.item == stackTo.item
        val isSameMeta = this.metadata == stackTo.metadata
        val tag1 = this.tagCompound
        val tag2 = stackTo.tagCompound
        val isSameTag = if (tag1 == null && tag2 == null) true else tag1 == tag2
        result = isSameItem && isSameMeta && isSameTag
    }
    return result
}

fun ItemStack.sameExact(stackTo: ItemStack): Boolean {
    val isSameCount = this.count == stackTo.count
    return this.same(stackTo) && isSameCount
}

fun Boolean.toInt() = if (this) 1 else 0

object RagiUtil {

    //    Block    //

    fun getState(registryName: String, meta: Int): IBlockState {
        val block = ForgeRegistries.BLOCKS.getValue(ResourceLocation(registryName)) ?: Blocks.BARRIER
        return block.getStateFromMeta(meta)
    }

    //    Command    //

    fun executeCommand(sender: ICommandSender, command: String) {
        Minecraft.getMinecraft().integratedServer?.server?.getCommandManager()?.executeCommand(sender, command)
    }

    fun setTitle(player: EntityPlayer, title: String, subtitle: String) {
        //コマンドの実行結果を出力しないようにする
        executeCommand(player, "gamerule sendCommandFeedback false")
        //titleの設定
        executeCommand(player, "title @p times 20 60 20")
        executeCommand(player, "title @p title [{\"translate\":\"$title\"}]")
        executeCommand(player, "title @p subtitle {\"translate\":\"$subtitle\"}")
    }

    //    Item    //

    fun getStack(registryName: String, amount: Int, meta: Int): ItemStack {
        val item = ForgeRegistries.ITEMS.getValue(ResourceLocation(registryName))
        return item?.let { ItemStack(it, amount, meta) } ?: ItemStack.EMPTY
    }

    //    Other    //

    fun convertArrayTomMap(array: Array<String>): Map<String, String> = array.associate { it.split(";")[0] to it.split(";")[1] }

    fun dropItemAtPlayer(player: EntityPlayer, stack: ItemStack, x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) {
        val world = player.world
        if (!world.isRemote) {
            val posPlayer = player.position
            dropItem(world, posPlayer, stack, x, y, z)
        }
    }

    fun dropInventoryItems(world: World, pos: BlockPos, inventory: IItemHandler, x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) {
        for (i in 0 until inventory.slots) {
            val stack = inventory.getStackInSlot(i)
            dropItem(world, pos, stack, x, y, z)
        }
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

    fun getEnumRarity(name: String): IRarity {
        return when (name) {
            "Uncommon" -> EnumRarity.UNCOMMON
            "Rare" -> EnumRarity.RARE
            "Epic" -> EnumRarity.EPIC
            "Legendary" -> object : IRarity {
                override fun getColor() = TextFormatting.GOLD
                override fun getName() = "Legendary"
            }

            "Mythic" -> object : IRarity {
                override fun getColor() = TextFormatting.RED
                override fun getName() = "Mythic"
            }

            else -> EnumRarity.COMMON
        }
    }
}