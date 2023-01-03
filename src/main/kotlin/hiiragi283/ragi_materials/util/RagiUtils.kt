package hiiragi283.ragi_materials.util

import hiiragi283.ragi_materials.Reference
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.command.ICommandSender
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.potion.Potion
import net.minecraft.potion.PotionEffect
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundCategory
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.oredict.OreDictionary

object RagiUtils {
    //コマンドを実行するメソッド
    fun executeCommand(sender: ICommandSender?, command: String) {
        if (Reference.SERVER !== null && sender !== null) {
            Reference.SERVER.getCommandManager().executeCommand(sender, command)
        }
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

    fun getBlock(domain: String, path: String): Block {
        return getBlock("$domain:$path")
    }

    //液体名からFluidを取得するメソッド
    //Fluidがnullの場合は水を返す
    fun getFluid(name: String): Fluid {
        val fluid: Fluid = FluidRegistry.getFluid(name)
        return if (fluid !== null) fluid else {
            RagiLogger.warnDebug("The fluid <fluid:$name> was not found...")
            FluidRegistry.getFluid("water")
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

    fun getItem(domain: String, path: String): Item {
        return getItem("$domain:$path")
    }

    //ResourceLocationなどからItemStackを取得するメソッド
    //ItemStackがnullの場合はバリアブロックを返す
    fun getStack(registryName: String, amount: Int, meta: Int): ItemStack {
        val item: Item? = ForgeRegistries.ITEMS.getValue(ResourceLocation(registryName))
        return if (item !== null) ItemStack(item, amount, meta) else {
            RagiLogger.warnDebug("The item stack <$registryName:$meta> * $amount was not found...")
            ItemStack(getItem("minecraft", "barrier"), amount, 0)
        }
    }

    fun getStack(domain: String, path: String, amount: Int, meta: Int): ItemStack {
        return getStack("$domain:$path", amount, meta)
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

    fun getState(domain: String, path: String, meta: Int): IBlockState {
        return getState("$domain:$path", meta)
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

    fun getPotion(domain: String, path: String): Potion {
        return getPotion("$domain:$path")
    }

    //ResourceLocationなどからPotionEffectを取得するメソッド
    fun getPotionEffect(domain: String, path: String, time: Int, level: Int): PotionEffect {
        return PotionEffect(getPotion(domain, path), time, level)
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

    fun getSound(domain: String, path: String): SoundEvent {
        return getSound("$domain:$path")
    }

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
        RagiLogger.infoDebug("New ore dictionary <ore:" + oreDict + "> was added to " + stackToBracket(stack))
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
        world.playSound(null, pos, getSound("minecraft", "entity.player.levelup"), SoundCategory.BLOCKS, 1.0f, 0.5f)
    }

    //ItemStackをCrTのブラケット記法に変換するメソッド (ログ出力用)
    fun stackToBracket(stack: ItemStack): String {
        val item: Item = stack.item
        val location: ResourceLocation? = item.registryName
        val amount: Int = stack.count
        val meta: Int = stack.metadata
        return "<$location:$meta> * $amount"
    }
}