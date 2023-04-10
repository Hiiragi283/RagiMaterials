package hiiragi283.ragi_materials.util

import hiiragi283.ragi_materials.Reference
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.command.ICommandSender
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.EnumRarity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.potion.Potion
import net.minecraft.potion.PotionEffect
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.common.IRarity
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.oredict.OreDictionary

fun ItemStack.toBracket(): String {
    val item: Item = this.item
    val location: ResourceLocation? = item.registryName
    val amount: Int = this.count
    val meta: Int = this.metadata
    return "<$location:$meta> * $amount"
}

fun Boolean.toInt() = if (this) 1 else 0

object RagiUtil {

    //    Block    //

    fun getBlock(registryName: String): Block {
        return ForgeRegistries.BLOCKS.getValue(ResourceLocation(registryName)) ?: Blocks.BARRIER
    }

    fun getState(registryName: String, meta: Int): IBlockState {
        val block = getBlock(registryName)
        return block.getStateFromMeta(meta)
    }

    fun getState(block: Block, meta: Int): IBlockState {
        return block.getStateFromMeta(meta)
    }

    //    Command    //

    fun executeCommand(sender: ICommandSender, command: String) {
        Reference.SERVER?.getCommandManager()?.executeCommand(sender, command)
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

    fun getItem(registryName: String): Item {
        return ForgeRegistries.ITEMS.getValue(ResourceLocation(registryName)) ?: Item.getItemFromBlock(Blocks.BARRIER)
    }

    fun getStack(registryName: String, amount: Int, meta: Int): ItemStack {
        val item = getItem(registryName)
        return ItemStack(item, amount, meta)
    }

    fun getStack(id: String): ItemStack {
        return getStack("${id.split(":")[0]}:${id.split(":")[1]}", 1, id.split(":")[2].toInt())
    }

    /**
    Thanks to defeatedcrow!
    Source: https://github.com/defeatedcrow/HeatAndClimateLib/blob/1.12.2_v3/main/java/defeatedcrow/hac/core/util/DCUtil.java#L130
     */

    fun isSameStack(stack1: ItemStack, stack2: ItemStack, useCount: Boolean): Boolean {
        var result = stack1.isEmpty && stack2.isEmpty
        if (!stack1.isEmpty && !stack2.isEmpty) {
            val isSameItem = stack1.item == stack2.item
            val isSameMeta = stack1.metadata == stack2.metadata
            val tag1 = stack1.tagCompound
            val tag2 = stack2.tagCompound
            val isSameTag = if (tag1 == null && tag2 == null) true else tag1 == tag2
            result = isSameItem && isSameMeta && isSameTag
            if (useCount) {
                val isSameCount = stack1.count == stack2.count
                result = result && isSameCount
            }
        }
        return result
    }

    //    Potion    //

    fun getPotion(registryName: String): Potion {
        return ForgeRegistries.POTIONS.getValue(ResourceLocation(registryName)) ?: Potion.getPotionById(1)!!
    }

    fun getPotionEffect(registryName: String, time: Int, level: Int): PotionEffect {
        return PotionEffect(getPotion(registryName), time, level)
    }

    //    Other    //

    fun convertArrayTomMap(array: Array<String>): Map<String, String> = array.associate { it.split(";")[0] to it.split(";")[1] }

    fun setOreDict(oreDict: String, stack: ItemStack) {
        OreDictionary.registerOre(oreDict, stack)
        //RagiLogger.infoDebug("New ore dictionary <ore:" + oreDict + "> was added to " + stack.toBracket())
    }

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