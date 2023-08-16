package hiiragi283.material

import hiiragi283.api.HiiragiEntry
import hiiragi283.api.item.HiiragiItem
import hiiragi283.api.item.ItemMaterial
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.material.config.RMConfig
import hiiragi283.material.item.ItemCast
import hiiragi283.material.item.ItemCrushingHammer
import hiiragi283.material.item.ItemUnfiredCast
import hiiragi283.material.util.executeCommand
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumRarity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumHand
import net.minecraft.world.World
import net.minecraftforge.common.IRarity
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

object RMItems : HiiragiEntry.ITEM {

    private val entries: MutableList<HiiragiEntry.ITEM> = mutableListOf()

    @JvmField
    val BOOK_RESPAWN = object : HiiragiItem("book_respawn", 0) {

        //    General    //

        override fun getForgeRarity(stack: ItemStack): IRarity = EnumRarity.EPIC

        //    Event    //

        override fun onItemRightClick(world: World, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {
            //落下死防止やコマンド権限のためクリエモードに切り替え
            if (!world.isRemote) executeCommand(player, "gamemode 1")
            if (world.isRemote) {
                val spawnPoint = world.spawnPoint
                player.motionX = 0.0
                player.motionY = 0.0
                player.motionZ = 0.0 //運動ベクトルをリセット
                //プレイヤーを指定した座標にテレポート
                player.setLocationAndAngles(spawnPoint.x + 0.5, 128.0, spawnPoint.z + 0.5, 0.0f, 0.0f)
            }
            return super.onItemRightClick(world, player, hand)
        }

    }

    //    Material    //

    @JvmField
    val MATERIAL_BLOCK = ItemMaterial(HiiragiShapes.BLOCK)

    @JvmField
    val MATERIAL_BOTTLE = ItemMaterial(HiiragiShapes.BOTTLE).also { it.setCreativeTab(RMCreativeTabs.BOTTLE) }

    @JvmField
    val MATERIAL_DUST = ItemMaterial(HiiragiShapes.DUST)

    @JvmField
    val MATERIAL_DUST_TINY = ItemMaterial(HiiragiShapes.DUST_TINY)

    @JvmField
    val MATERIAL_GEAR = ItemMaterial(HiiragiShapes.GEAR)

    @JvmField
    val MATERIAL_GEM = ItemMaterial(HiiragiShapes.GEM)

    @JvmField
    val MATERIAL_INGOT = ItemMaterial(HiiragiShapes.INGOT)

    @JvmField
    val MATERIAL_NUGGET = ItemMaterial(HiiragiShapes.NUGGET)

    @JvmField
    val MATERIAL_ORE = ItemMaterial(HiiragiShapes.ORE)

    @JvmField
    val MATERIAL_PLATE = ItemMaterial(HiiragiShapes.PLATE)

    @JvmField
    val MATERIAL_STICK = ItemMaterial(HiiragiShapes.STICK)

    //    Common    //

    @JvmField
    val CAST_GEAR = ItemCast(MATERIAL_GEAR)

    @JvmField
    val CAST_INGOT = ItemCast(MATERIAL_INGOT)

    @JvmField
    val CAST_NUGGET = ItemCast(MATERIAL_NUGGET)

    @JvmField
    val CAST_PLATE = ItemCast(MATERIAL_PLATE)

    @JvmField
    val CAST_STICK = ItemCast(MATERIAL_STICK)

    @JvmField
    val CRUSHING_HAMMER = ItemCrushingHammer

    @JvmField
    val UNFIRED_CAST = ItemUnfiredCast

    fun init() {
        RagiMaterials.LOGGER.info("RMItems has been initialized!")
        entries.addAll(RMBlocks.getItemBlockEntries())
        entries.add(BOOK_RESPAWN)
        if (!RMConfig.EXPERIMENTAL.enableMetaTileBlock) entries.add(MATERIAL_BLOCK)
        entries.add(MATERIAL_BOTTLE)
        entries.add(MATERIAL_DUST)
        entries.add(MATERIAL_DUST_TINY)
        entries.add(MATERIAL_GEAR)
        entries.add(MATERIAL_GEM)
        entries.add(MATERIAL_INGOT)
        entries.add(MATERIAL_NUGGET)
        entries.add(MATERIAL_ORE)
        entries.add(MATERIAL_PLATE)
        entries.add(MATERIAL_STICK)
        entries.add(CAST_GEAR)
        entries.add(CAST_INGOT)
        entries.add(CAST_NUGGET)
        entries.add(CAST_PLATE)
        entries.add(CAST_STICK)
        entries.add(CRUSHING_HAMMER)
        entries.add(UNFIRED_CAST)
    }

    override fun register(registry: IForgeRegistry<Item>) {
        entries.forEach { registry.register(it.getObject()) }
    }

    override fun registerOreDict() {
        entries.forEach { it.registerOreDict() }
    }

    override fun registerRecipe() {
        entries.forEach { it.registerRecipe() }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        entries.forEach { it.registerColorItem(itemColors) }
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        entries.forEach { it.registerModel() }
    }

}