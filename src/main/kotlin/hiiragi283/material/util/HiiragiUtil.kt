@file:JvmName("HiiragiUtil")

package hiiragi283.material.util

import hiiragi283.material.RMReference
import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.init.HiiragiRegistries
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.WorldClient
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.command.ICommandSender
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.EnumRarity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundCategory
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.IRarity
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.FMLLaunchHandler
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.oredict.OreDictionary
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryEntry
import net.minecraftforge.registries.IForgeRegistryModifiable
import org.lwjgl.input.Keyboard
import java.lang.reflect.Field
import java.util.*

//    BlockPos    //

fun BlockPos.right(front: EnumFacing, n: Int = 1): BlockPos = this.offset(front.rotateY(), n)

fun BlockPos.left(front: EnumFacing, n: Int = 1): BlockPos = this.offset(front.rotateYCCW(), n)

fun BlockPos.back(front: EnumFacing, n: Int = 1): BlockPos = this.offset(front.opposite, n)

//    Calendar    //

//4月1日かを判定するメソッド
fun isAprilFools(): Boolean =
    RagiMaterials.CALENDAR.get(Calendar.MONTH) + 1 == 4 && RagiMaterials.CALENDAR.get(Calendar.DATE) == 1

//    Collection    //

fun <T> Collection<T>.hasSameElements(other: Collection<T>): Boolean =
    this.containsAll(other) && other.containsAll(this)

fun <K, V> Map<K, V>.reverse(): Map<V, K> = this.toList().associate(Pair<K, V>::reverse)

fun <A, B> Pair<A, B>.reverse(): Pair<B, A> = this.second to this.first

//    Drop    //

fun dropItemAtPlayer(player: EntityPlayer, stack: ItemStack, x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) {
    val world = player.world
    if (!world.isRemote) {
        val posPlayer = player.position
        dropItem(world, posPlayer, stack, x, y, z)
    }
}

fun dropInventoriesItems(
    world: World,
    pos: BlockPos,
    vararg inventories: IItemHandler
) {
    inventories.forEach { inventory: IItemHandler -> dropInventoryItems(world, pos, inventory) }
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

//    Fluid    //

fun FluidStack.copyKt(
    fluid: Fluid = this.fluid,
    amount: Int = this.amount,
    tag: NBTTagCompound? = this.tag
) = FluidStack(fluid, amount, tag)

fun FluidStack.isSameWithAmount(other: FluidStack?): Boolean =
    other !== null && this.isFluidEqual(other) && this.amount == other.amount

fun Fluid.getMaterial(): HiiragiMaterial? = HiiragiRegistries.MATERIAL.getValue(this.name)

fun FluidStack.getMaterial(): HiiragiMaterial? = this.fluid.getMaterial()

//    FML    //

fun isClient(): Boolean = FMLCommonHandler.instance().side.isClient

fun isDeobf(): Boolean = FMLLaunchHandler.isDeobfuscatedEnvironment()

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
    Item.getItemFromBlock(this).takeUnless { item: Item -> item == Items.AIR }?.setModel()
}

//メタデータによらず特定のモデルファイルだけを利用させるメソッド
fun Item.setModelSame() {
    ModelLoader.setCustomMeshDefinition(this) { ModelResourceLocation(this.registryName!!, "inventory") }
}

fun Block.setModelSame() {
    Item.getItemFromBlock(this).takeUnless { item: Item -> item == Items.AIR }?.setModelSame()
}

//異なるResourceLocationを割り当てるメソッド
fun Item.setModelAlt(modelLocation: ModelResourceLocation) {
    ModelLoader.registerItemVariants(this, modelLocation)
    ModelLoader.setCustomMeshDefinition(this) { modelLocation }
}

fun Block.setModelAlt(modelLocation: ModelResourceLocation) {
    Item.getItemFromBlock(this).takeUnless { item: Item -> item == Items.AIR }?.setModelAlt(modelLocation)
}

fun Item.setModelAlt(location: ResourceLocation) {
    this.setModelAlt(ModelResourceLocation(location, "inventory"))
}

fun Block.setModelAlt(location: ResourceLocation) {
    this.setModelAlt(ModelResourceLocation(location, "inventory"))
}

//    Ore Dictionary    //

fun registerOreDict(oredict: String, item: Item?, meta: Int = 0, share: String? = null) {
    item?.let { OreDictionary.registerOre(oredict, it.itemStack(meta = meta)) }
    share?.let { shareOredict(oredict, it) }
}

fun registerOreDict(oredict: String, block: Block?, meta: Int = 0, share: String? = null) {
    block?.let { OreDictionary.registerOre(oredict, it.itemStack(meta = meta)) }
    share?.let { shareOredict(oredict, it) }
}

fun shareOredict(oredict1: String, oredict2: String) {
    OreDictionary.getOres(oredict1).forEach { OreDictionary.registerOre(oredict2, it) }
    OreDictionary.getOres(oredict2).forEach { OreDictionary.registerOre(oredict1, it) }
}

//    Reflection    //

fun Field.enableAccess(): Field = also { it.isAccessible = true }

//    Registry    //

inline fun <reified T : IForgeRegistryEntry<T>> getEntry(registryName: String): T? =
    getEntry(GameRegistry.findRegistry(T::class.java), ResourceLocation(registryName))

inline fun <reified T : IForgeRegistryEntry<T>> getEntry(registryName: ResourceLocation): T? =
    getEntry(GameRegistry.findRegistry(T::class.java), registryName)

fun <T : IForgeRegistryEntry<T>> getEntry(registry: IForgeRegistry<T>?, registryName: String): T? =
    getEntry(registry, ResourceLocation(registryName))

fun <T : IForgeRegistryEntry<T>> getEntry(registry: IForgeRegistry<T>?, registryName: ResourceLocation): T? =
    registry?.getValue(registryName)

fun <T : IForgeRegistryEntry<T>> removeEntry(registry: IForgeRegistry<T>, registryName: String) {
    removeEntry(registry, ResourceLocation(registryName))
}

fun <T : IForgeRegistryEntry<T>> removeEntry(registry: IForgeRegistry<T>, registryName: ResourceLocation) {
    (registry as? IForgeRegistryModifiable<T>)?.remove(registryName)
}

//    Render    //

fun drawSquareTexture(minecraft: Minecraft, x: Int, y: Int, textureLocation: ResourceLocation) {
    drawSquareTexture(minecraft, x.toDouble(), y.toDouble(), textureLocation)
}

fun drawSquareTexture(minecraft: Minecraft, x: Double, y: Double, textureLocation: ResourceLocation) {
    //テクスチャを取得する
    val textureMap: TextureMap = minecraft.textureMapBlocks
    val sprite: TextureAtlasSprite = textureMap.getTextureExtry(textureLocation.toString()) ?: textureMap.missingSprite
    minecraft.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE)
    //TextureAtlasSpriteのx座標の左端と右端，y座標の下端と上端をDoubleに変換する
    val uMin: Double = sprite.minU.toDouble()
    val uMax: Double = sprite.maxU.toDouble()
    val vMin: Double = sprite.minV.toDouble()
    val vMax: Double = sprite.maxV.toDouble()
    //GUiは2次元なのでz座標は適当?
    val z = 100.0
    //Tessellatorに設定を書き込んでいく
    val tessellator: Tessellator = Tessellator.getInstance()
    val vertexBuffer: BufferBuilder = tessellator.buffer
    vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX)
    vertexBuffer.pos(x, y + 16, z).tex(uMin, vMax).endVertex() //左下
    vertexBuffer.pos(x + 16, y + 16, z).tex(uMax, vMax).endVertex() //右下
    vertexBuffer.pos(x + 16, y, z).tex(uMax, vMin).endVertex() //左上
    vertexBuffer.pos(x, y, z).tex(uMin, vMin).endVertex() //右上
    //いざ描画!!
    tessellator.draw()
}

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

fun ResourceLocation.append(path: String) = ResourceLocation(this.namespace, this.path + path)

fun ResourceLocation.appendBefore(path: String) = ResourceLocation(this.namespace, path + this.path)

fun ResourceLocation.toModelLocation(variant: String = "inventory") = ModelResourceLocation(this, variant)

//    Result    //

private val WORLD_CLIENT: WorldClient by lazy { Minecraft.getMinecraft().world }
private val PLAYER_CLIENT by lazy { Minecraft.getMinecraft().player }

fun printResult(block: Block, player: ICommandSender = PLAYER_CLIENT, predicate: (Block, ICommandSender) -> Boolean) {
    if (predicate(block, player)) succeeded(block, player) else failed(block, player)
}

fun printResult(
    world: IBlockAccess = WORLD_CLIENT,
    pos: BlockPos,
    player: ICommandSender = PLAYER_CLIENT,
    predicate: (IBlockAccess, BlockPos, ICommandSender) -> Boolean
) {
    if (predicate(world, pos, player)) succeeded(world, pos, player) else failed(world, pos, player)
}

fun printResult(
    tile: TileEntity,
    player: ICommandSender = PLAYER_CLIENT,
    predicate: (TileEntity, ICommandSender) -> Boolean
) {
    if (predicate(tile, player)) succeeded(tile, player) else failed(tile, player)
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
    getEntry<SoundEvent>("minecraft:entity.player.levelup")?.let { soundEvent: SoundEvent ->
        world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0f, 0.5f)
    }
}

fun playSoundHypixel(tile: TileEntity) {
    playSoundHypixel(tile.world, tile.pos)
}

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

fun isShiftPressed(): Boolean = Keyboard.isCreated() && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)