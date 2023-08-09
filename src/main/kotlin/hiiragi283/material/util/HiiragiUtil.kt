@file:JvmName("HiiragiUtil")

package hiiragi283.material.util

import hiiragi283.material.RMReference
import hiiragi283.material.RagiMaterials
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.WorldClient
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.command.ICommandSender
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.EnumRarity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundCategory
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.IRarity
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.oredict.OreDictionary
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryModifiable
import java.util.*
import java.util.function.BiPredicate

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

//    Enchantment    //

fun addEnchantment(enchantment: Enchantment, level: Int, stack: ItemStack) {
    val map = EnchantmentHelper.getEnchantments(stack)
    map[enchantment] = level
    EnchantmentHelper.setEnchantments(map, stack)
}

fun addEnchantments(vararg pairs: Pair<Enchantment, Int>, stack: ItemStack) {
    val map = EnchantmentHelper.getEnchantments(stack)
    for (pair in pairs) {
        map[pair.first] = pair.second
    }
    EnchantmentHelper.setEnchantments(map, stack)
}

fun hasEnchantment(enchantment: Enchantment, stack: ItemStack) =
    EnchantmentHelper.getEnchantmentLevel(enchantment, stack) > 0

//    FluidStack    //

val FluidStack_EMPTY = FluidStack(FluidRegistry.WATER, 0)

fun Pair<String, Int>.toFluidStack(): FluidStack? = FluidRegistry.getFluidStack(this.first, this.second)

fun FluidStack.isEmpty(): Boolean = this.isFluidStackIdentical(FluidStack_EMPTY)

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

//    Model    //

fun Item.setModel() {
    //itemが耐久値を使用しない，かつhasSubtypesがtrueの場合
    if (this.getMaxDamage(ItemStack(this)) == 0 && this.hasSubtypes) {
        //メタデータが最大値になるまで処理を繰り返す
        (0..this.getMetadata(32768)).forEach {
            ModelLoader.setCustomModelResourceLocation(
                this,
                it,
                ModelResourceLocation(this.registryName!!.append("_$it"), "inventory")
            )
        }
    } else {
        //1つだけ登録する
        this.setModelSame()
    }
}

fun Block.setModel() {
    val item = Item.getItemFromBlock(this)
    if (item != Items.AIR) item.setModel()
}

//メタデータによらず特定のモデルファイルだけを利用させるメソッド
fun Item.setModelSame() {
    ModelLoader.setCustomMeshDefinition(this) { ModelResourceLocation(this.registryName!!, "inventory") }
}

fun Block.setModelSame() {
    val item = Item.getItemFromBlock(this)
    if (item != Items.AIR) item.setModelSame()
}

//    Ore Dictionary    //

fun registerOreDict(oredict: String, item: Item?, meta: Int = 0, share: String? = null) {
    item?.let { OreDictionary.registerOre(oredict, ItemStack(it, 1, meta)) }
    share?.let { shareOredict(oredict, it) }
}

fun registerOreDict(oredict: String, block: Block?, meta: Int = 0, share: String? = null) {
    block?.let { OreDictionary.registerOre(oredict, ItemStack(it, 1, meta)) }
    share?.let { shareOredict(oredict, it) }
}

fun shareOredict(oredict1: String, oredict2: String) {
    OreDictionary.getOres(oredict1).forEach { OreDictionary.registerOre(oredict2, it) }
    OreDictionary.getOres(oredict2).forEach { OreDictionary.registerOre(oredict1, it) }
}

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

fun ItemStack.toMetaLocation(): MetaResourceLocation = MetaResourceLocation(this.item.registryName!!, this.metadata)

fun IBlockState.toLocation(): ResourceLocation =
    this.block.registryName!!.append(":" + this.block.getMetaFromState(this))

fun IBlockState.toMetaLocation(): MetaResourceLocation =
    MetaResourceLocation(this.block.registryName!!, this.block.getMetaFromState(this))

fun FluidStack.toLocation(addAmount: Boolean): ResourceLocation {
    val location = ResourceLocation("fluid", this.fluid.name)
    if (addAmount) location.append(":" + this.amount)
    return location
}

//ResourceLocationの末尾に付け足す関数
fun ResourceLocation.append(path: String) = ResourceLocation(this.namespace, this.path + path)

//    Result    //

private val WORLD_CLIENT: WorldClient by lazy { Minecraft.getMinecraft().world }
private val PLAYER_CLIENT by lazy { Minecraft.getMinecraft().player }

fun printResult(block: Block, player: ICommandSender = PLAYER_CLIENT, predicate: BiPredicate<Block, ICommandSender>) {
    if (predicate.test(block, player)) succeeded(block, player) else failed(block, player)
}

fun printResult(
    world: IBlockAccess = WORLD_CLIENT,
    pos: BlockPos,
    player: ICommandSender = PLAYER_CLIENT,
    predicate: TriPredicate<IBlockAccess, BlockPos, ICommandSender>
) {
    if (predicate.test(world, pos, player)) succeeded(world, pos, player) else failed(world, pos, player)
}

fun printResult(
    tile: TileEntity,
    player: ICommandSender = PLAYER_CLIENT,
    predicate: BiPredicate<TileEntity, ICommandSender>
) {
    if (predicate.test(tile, player)) succeeded(tile, player) else failed(tile, player)
}

fun succeeded(block: Block, player: ICommandSender = PLAYER_CLIENT) {
    player.sendMessage(TextComponentTranslation("info.ragi_materials.succeeded", block.localizedName))
}

fun succeeded(world: IBlockAccess = WORLD_CLIENT, pos: BlockPos, player: ICommandSender = PLAYER_CLIENT) {
    succeeded(world.getBlockState(pos).block, player)
}

fun succeeded(tile: TileEntity, player: ICommandSender = PLAYER_CLIENT) {
    succeeded(tile.world, tile.pos, player)
}

fun failed(block: Block, player: ICommandSender = PLAYER_CLIENT) {
    player.sendMessage(TextComponentTranslation("info.ragi_materials.failed", block.localizedName))
}

fun failed(world: IBlockAccess = WORLD_CLIENT, pos: BlockPos, player: ICommandSender = PLAYER_CLIENT) {
    failed(world.getBlockState(pos).block, player)
}

fun failed(tile: TileEntity, player: ICommandSender = PLAYER_CLIENT) {
    failed(tile.world, tile.pos, player)
}

//    Sound    //

fun getSound(location: ResourceLocation): SoundEvent {
    return ForgeRegistries.SOUND_EVENTS.getValue(location)
        ?: ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation("ambient.cave"))!!
}

fun getSound(registryName: String): SoundEvent {
    return getSound(ResourceLocation(registryName))
}

fun playSound(
    world: World,
    pos: BlockPos,
    soundEvent: SoundEvent,
    volume: Float = 1.0f,
    pitch: Float = 1.0f,
    soundCategory: SoundCategory = SoundCategory.MASTER,
    player: EntityPlayer? = null
) {
    world.playSound(player, pos, soundEvent, soundCategory, volume, pitch)
}

fun playSound(
    tile: TileEntity,
    soundEvent: SoundEvent,
    volume: Float = 1.0f,
    pitch: Float = 1.0f,
    soundCategory: SoundCategory = SoundCategory.MASTER
) {
    playSound(tile.world, tile.pos, soundEvent, volume, pitch, soundCategory)
}

fun playSoundHypixel(world: World, pos: BlockPos) {
    world.playSound(null, pos, getSound("minecraft:entity.player.levelup"), SoundCategory.BLOCKS, 1.0f, 0.5f)
}

fun playSoundHypixel(tile: TileEntity) {
    playSoundHypixel(tile.world, tile.pos)
}

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