package hiiragi283.ragi_materials.util

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialBuilder
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.command.ICommandSender
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.potion.Potion
import net.minecraft.potion.PotionEffect
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundCategory
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.ItemStackHandler
import net.minecraftforge.oredict.OreDictionary

object RagiUtil {

    //    Block    //

    fun getBlock(registryName: String): Block {
        return ForgeRegistries.BLOCKS.getValue(ResourceLocation(registryName))?: Blocks.BARRIER
    }

    fun getState(registryName: String, meta: Int): IBlockState {
        val block = getBlock(registryName)
        return block.getStateFromMeta(meta)
    }

    fun getState(block: Block, meta: Int): IBlockState {
        return block.getStateFromMeta(meta)
    }

    //    Command    //

    fun executeCommand(sender: ICommandSender?, command: String) {
        if (Reference.SERVER !== null && sender !== null) {
            Reference.SERVER.getCommandManager().executeCommand(sender, command)
        }
    }

    fun setTitle(player: EntityPlayer?, title: String, subtitle: String) {
        //コマンドの実行結果を出力しないようにする
        executeCommand(player, "gamerule sendCommandFeedback false")
        //titleの設定
        executeCommand(player, "title @p times 20 60 20")
        executeCommand(player, "title @p title [{\"translate\":\"$title\"}]")
        executeCommand(player, "title @p subtitle {\"translate\":\"$subtitle\"}")
    }

    //    Fluid    //

    fun getFluidStack(name: String, amount: Int): FluidStack {
        return FluidStack(FluidRegistry.getFluid(name), amount)
    }

    fun setFluidToNBT(tag: NBTTagCompound, fluidStack: FluidStack): NBTTagCompound {
        val tagFluid = NBTTagCompound()
        fluidStack.writeToNBT(tagFluid)
        tag.setTag("Fluid", tagFluid)
        return tag
    }

    //    Item    //

    fun getItem(registryName: String): Item {
        return ForgeRegistries.ITEMS.getValue(ResourceLocation(registryName))?: Item.getItemFromBlock(Blocks.BARRIER)
    }

    fun getStack(registryName: String, amount: Int, meta: Int): ItemStack {
        val item = getItem(registryName)
        return ItemStack(item, amount, meta)
    }

    fun getStack(id: String): ItemStack {
        return getStack("${id.split(":")[0]}:${id.split(":")[1]}", 1, id.split(":")[2].toInt())
    }

    fun getFilledBottle(fluidStack: FluidStack, count: Int = 1): ItemStack {
        val stack = ItemStack(RagiInit.ItemFullBottle, count, 0)
        stack.tagCompound = setFluidToNBT(NBTTagCompound(), fluidStack)
        return stack
    }

    fun getFilledBottle(name: String, amount: Int = 1000, count: Int = 1): ItemStack = getFilledBottle(getFluidStack(name, amount), count)

    fun getFilledBottle(material: MaterialBuilder, amount: Int = 1000, count: Int = 1): ItemStack = getFilledBottle(material.name, amount, count)

    /*
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

    fun ItemStack.toBracket(): String {
        val item: Item = this.item
        val location: ResourceLocation? = item.registryName
        val amount: Int = this.count
        val meta: Int = this.metadata
        return "<$location:$meta> * $amount"
    }

    //    Potion    //

    fun getPotion(registryName: String): Potion {
        return ForgeRegistries.POTIONS.getValue(ResourceLocation(registryName))?: Potion.getPotionById(1)!!
    }

    fun getPotionEffect(registryName: String, time: Int, level: Int): PotionEffect {
        return getPotion(registryName).let { PotionEffect(it, time, level) }
    }

    //    Sound    //

    @SideOnly(Side.CLIENT)
    fun getSound(registryName: String): SoundEvent {
        return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation(registryName))?: ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation("ambient.cave"))!!
    }

    fun soundHypixel(world: World, pos: BlockPos) {
        world.playSound(null, pos, getSound("minecraft:entity.player.levelup"), SoundCategory.BLOCKS, 1.0f, 0.5f)
    }

    //    Other    //

    fun convertListToMap(list: Array<String>, map: MutableMap<String, String>): MutableMap<String, String> {
        for (key in list) {
            map[key.split(";")[0]] = key.split(";")[1]
            RagiLogger.infoDebug("${key.split(";")[0]} to ${map[key.split(";")[0]]}")
        }
        return map
    }

    fun setOreDict(oreDict: String, stack: ItemStack) {
        OreDictionary.registerOre(oreDict, stack)
        //RagiLogger.infoDebug("New ore dictionary <ore:" + oreDict + "> was added to " + stack.toBracket())
    }

    fun Boolean.toInt(): Int {
        return if (this) 1 else 0
    }

    fun spawnItemAtPlayer(world: World, player: EntityPlayer, stack: ItemStack) {
        if (!world.isRemote) {
            val posPlayer = player.position
            dropItem(world, posPlayer, stack)
        }
    }

    fun dropInventoryItems(world: World, pos: BlockPos, inventory: ItemStackHandler) {
        for (i in 0 until inventory.slots) {
            val stack = inventory.getStackInSlot(i)
            dropItem(world, pos, stack)
        }
    }

    fun dropItem(world: World, pos: BlockPos, stack: ItemStack) {
        if (!stack.isEmpty) {
            val drop = EntityItem(world, pos.x.toDouble() + 0.5f, pos.y.toDouble() + 1.0f, pos.z.toDouble() + 0.5f, stack)
            drop.setPickupDelay(0) //即座に回収できるようにする
            drop.motionX = 0.0
            drop.motionY = 0.25
            drop.motionZ = 0.0 //ドロップ時の飛び出しを防止
            world.spawnEntity(drop) //ドロップアイテムをスポーン
        }
    }
}