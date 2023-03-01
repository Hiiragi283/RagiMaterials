package hiiragi283.ragi_materials.util

import hiiragi283.ragi_materials.Reference
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.command.ICommandSender
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
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
import net.minecraftforge.oredict.OreDictionary

object RagiUtils {
    //コマンドを実行するメソッド
    fun executeCommand(sender: ICommandSender?, command: String) {
        if (Reference.SERVER !== null && sender !== null) {
            Reference.SERVER.getCommandManager().executeCommand(sender, command)
        }
    }

    //String型のlist/arrayをmapに変換するメソッド
    fun convertListToMap(list: Array<String>, map: MutableMap<String, String>): MutableMap<String, String> {
        for (key in list) {
            map[key.split(";")[0]] = key.split(";")[1]
            RagiLogger.infoDebug("${key.split(";")[0]} to ${map[key.split(";")[0]]}")
        }
        return map
    }

    //ResourceLocationからBlockを取得するメソッド
    //Blockがnullの場合はバリアブロックを返す
    fun getBlock(registryName: String): Block {
        val block: Block? = ForgeRegistries.BLOCKS.getValue(ResourceLocation(registryName))
        return if (block !== null) block else {
            RagiLogger.warnDebug("The block <$registryName> was not found...")
            ForgeRegistries.BLOCKS.getValue(ResourceLocation("minecraft:barrier"))!!
        }
    }

    //ResourceLocationからItemを取得するメソッド
    //Itemがnullの場合はバリアブロックを返す
    fun getItem(registryName: String): Item {
        val item: Item? = ForgeRegistries.ITEMS.getValue(ResourceLocation(registryName))
        return if (item !== null) item else {
            RagiLogger.warnDebug("The item <$registryName> was not found...")
            ForgeRegistries.ITEMS.getValue(ResourceLocation("minecraft:barrier"))!!
        }
    }

    //ResourceLocationなどからItemStackを取得するメソッド
    //ItemStackがnullの場合はバリアブロックを返す
    fun getStack(registryName: String, amount: Int, meta: Int): ItemStack {
        val item: Item? = ForgeRegistries.ITEMS.getValue(ResourceLocation(registryName))
        return if (item !== null) ItemStack(item, amount, meta) else {
            RagiLogger.warnDebug("The item stack <$registryName:$meta> * $amount was not found...")
            ItemStack(getItem("minecraft:barrier"), amount, 0)
        }
    }

    //config用
    fun getStack(id: String): ItemStack {
        return getStack("${id.split(":")[0]}:${id.split(":")[1]}", 1, id.split(":")[2].toInt())
    }

    //液体入りバケツを返すメソッド
    fun getFilledBucket(fluid: String): ItemStack {
        val bucket = getStack("forge:bucketfilled", 1, 0)
        val tag = NBTTagCompound()
        tag.setString("FluidName", fluid)
        tag.setInteger("Amount", 1000)
        bucket.tagCompound = tag
        return bucket
    }

    //液体名と量からFluidStackを返すメソッド
    fun getFluidStack(name: String, amount: Int): FluidStack {
        return FluidStack(FluidRegistry.getFluid(name), amount)
    }

    //ResourceLocationなどからIBlockStateを取得するメソッド
    //IBlockStateがnullの場合はバリアブロックを返す
    fun getState(registryName: String, meta: Int): IBlockState {
        val block = getBlock(registryName)
        val state: IBlockState = block.getStateFromMeta(meta)
        return if (block !== getBlock("minecraft:barrier")) state else {
            RagiLogger.warnDebug("The blockstate <blockstate:$block:$meta> was not found...")
            getBlock("minecraft:barrier").defaultState
        }
    }

    fun getState(block: Block, meta: Int): IBlockState {
        val state: IBlockState = block.getStateFromMeta(meta)
        return if (block !== getBlock("minecraft:barrier")) state else {
            RagiLogger.warnDebug("The blockstate <blockstate:$block:$meta> was not found...")
            getBlock("minecraft:barrier").defaultState
        }
    }

    //ResourceLocationからPotionを取得するメソッド
    //Potionがnullの場合は不運を返す
    fun getPotion(registryName: String): Potion {
        val potion: Potion? = ForgeRegistries.POTIONS.getValue(ResourceLocation(registryName))
        return if (potion !== null) potion else {
            RagiLogger.warnDebug("The potion <potion:$registryName> was not found...")
            ForgeRegistries.POTIONS.getValue(ResourceLocation("minecraft:unluck"))!!
        }
    }

    //ResourceLocationなどからPotionEffectを取得するメソッド
    fun getPotionEffect(registryName: String, time: Int, level: Int): PotionEffect {
        return PotionEffect(getPotion(registryName), time, level)
    }

    //ResourceLocationからSoundEventを取得するメソッド
    //SoundEventがnullの場合はレベルアップの音を返す
    fun getSound(registryName: String): SoundEvent {
        val sound: SoundEvent? = ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation(registryName))
        return if (sound !== null) sound else {
            RagiLogger.warnDebug("The sound <soundevent:$registryName> was not found...")
            ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation("minecraft:entity.player.levelup"))!!
        }
    }

    /*
      Thanks to defeatedcrow!
      Source: https://github.com/defeatedcrow/HeatAndClimateLib/blob/1.12.2_v3/main/java/defeatedcrow/hac/core/util/DCUtil.java#L130
    */
    //2つのItemStackが同じかどうかを判断するメソッド
    fun isSameStack(stack1: ItemStack, stack2: ItemStack): Boolean {
        val isNotSame = false
        if (stack1.isEmpty && stack2.isEmpty) return isNotSame
        val item1 = stack1.item
        val item2 = stack2.item
        if (item1.registryName == item2.registryName && stack1.metadata == stack2.metadata) return true
        return isNotSame
    }

    //鉱石辞書を追加するメソッド
    fun setOreDict(oreDict: String, stack: ItemStack) {
        OreDictionary.registerOre(oreDict, stack)
        RagiLogger.infoDebug("New ore dictionary <ore:" + oreDict + "> was added to " + stack.toBracket())
    }

    //titleコマンドをより簡潔に実行するメソッド
    fun setTitle(player: EntityPlayer?, title: String, subtitle: String) {
        //コマンドの実行結果を出力しないようにする
        executeCommand(player, "gamerule sendCommandFeedback false")
        //titleの設定
        executeCommand(player, "title @p times 20 60 20")
        executeCommand(player, "title @p title [{\"translate\":\"$title\"}]")
        executeCommand(player, "title @p subtitle {\"translate\":\"$subtitle\"}")
    }

    //Hypixelで慣れ親しんだこの音声を再び聞いたとき，感動で泣きそうになりました
    fun soundHypixel(world: World, pos: BlockPos) {
        world.playSound(null, pos, getSound("minecraft:entity.player.levelup"), SoundCategory.BLOCKS, 1.0f, 0.5f)
    }

    //ItemStackをCrTのブラケット記法に変換するメソッド (ログ出力用)
    fun ItemStack.toBracket(): String {
        val item: Item = this.item
        val location: ResourceLocation? = item.registryName
        val amount: Int = this.count
        val meta: Int = this.metadata
        return "<$location:$meta> * $amount"
    }

    //BooleanをIntに変換するメソッド
    fun Boolean.toInt(): Int {
        return if (this) 1 else 0
    }

    //EntityItemをプレイヤーの足元にスポーンさせるメソッド
    fun spawnItemAtPlayer(
        world: World,
        player: EntityPlayer,
        stack: ItemStack
    ) {
        if (!world.isRemote) {
            val posPlayer = player.position
            val drop = EntityItem(
                world, posPlayer.x.toDouble(), posPlayer.y.toDouble(), posPlayer.z.toDouble(), stack
            ) //空のバケツのEntityItem
            drop.setPickupDelay(0) //即座に回収できるようにする
            drop.motionX = 0.0
            drop.motionY = 0.0
            drop.motionZ = 0.0 //ドロップ時の飛び出しを防止
            world.spawnEntity(drop) //ドロップアイテムをスポーン
        }
    }
}