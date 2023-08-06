package hiiragi283.material

import hiiragi283.api.HiiragiEntry
import hiiragi283.api.item.HiiragiItem
import hiiragi283.api.item.ItemMaterial
import hiiragi283.api.material.MaterialCommon
import hiiragi283.api.material.MaterialRegistry
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.core.RMCreativeTabs
import hiiragi283.core.config.RMConfig
import hiiragi283.core.util.executeCommand
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

    @JvmField
    val MATERIAL_BLOCK = ItemMaterial(HiiragiShapes.BLOCK)

    @JvmField
    val MATERIAL_BOTTLE = ItemMaterial(HiiragiShapes.BOTTLE)

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
    val MATERIAL_ORE = object : ItemMaterial(HiiragiShapes.ORE) {

        //    HiiragiEntry    //

        override fun registerColorItem(itemColors: ItemColors) {
            itemColors.registerItemColorHandler({ stack, tintIndex ->
                val material = MaterialRegistry.getMaterial(stack.metadata)
                if (tintIndex == 1) material.color else MaterialCommon.STONE.color
            }, this)
        }

    }

    @JvmField
    val MATERIAL_PLATE = ItemMaterial(HiiragiShapes.PLATE)

    @JvmField
    val MATERIAL_STICK = ItemMaterial(HiiragiShapes.STICK)

    override fun register(registry: IForgeRegistry<Item>) {

        MATERIAL_BOTTLE.setCreativeTab(RMCreativeTabs.BOTTLE)

        BOOK_RESPAWN.register(registry)

        if (RMConfig.EXPERIMENTAL.enableMetaTileBlock) {
            RMBlocks.MATERIAL_BLOCK.itemBlock.register(registry)
        } else {
            MATERIAL_BLOCK.register(registry)
        }

        MATERIAL_BOTTLE.register(registry)
        MATERIAL_DUST.register(registry)
        MATERIAL_DUST_TINY.register(registry)
        MATERIAL_GEAR.register(registry)
        MATERIAL_GEM.register(registry)
        MATERIAL_INGOT.register(registry)
        MATERIAL_NUGGET.register(registry)
        MATERIAL_ORE.register(registry)
        MATERIAL_PLATE.register(registry)
        MATERIAL_STICK.register(registry)
    }

    override fun registerOreDict() {
        MATERIAL_BOTTLE.registerOreDict()
        MATERIAL_DUST.registerOreDict()
        MATERIAL_DUST_TINY.registerOreDict()
        MATERIAL_GEAR.registerOreDict()
        MATERIAL_GEM.registerOreDict()
        MATERIAL_INGOT.registerOreDict()
        MATERIAL_NUGGET.registerOreDict()
        MATERIAL_ORE.registerOreDict()
        MATERIAL_PLATE.registerOreDict()
        MATERIAL_STICK.registerOreDict()
    }

    override fun registerRecipe() {
        //MATERIAL_CELL.registerRecipe()
        MATERIAL_DUST.registerRecipe()
        MATERIAL_DUST_TINY.registerRecipe()
        MATERIAL_GEAR.registerRecipe()
        MATERIAL_GEM.registerRecipe()
        MATERIAL_INGOT.registerRecipe()
        MATERIAL_NUGGET.registerRecipe()
        MATERIAL_ORE.registerRecipe()
        MATERIAL_PLATE.registerRecipe()
        MATERIAL_STICK.registerRecipe()
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        MATERIAL_BOTTLE.registerColorItem(itemColors)
        MATERIAL_DUST.registerColorItem(itemColors)
        MATERIAL_DUST_TINY.registerColorItem(itemColors)
        MATERIAL_GEAR.registerColorItem(itemColors)
        MATERIAL_GEM.registerColorItem(itemColors)
        MATERIAL_INGOT.registerColorItem(itemColors)
        MATERIAL_NUGGET.registerColorItem(itemColors)
        MATERIAL_ORE.registerColorItem(itemColors)
        MATERIAL_PLATE.registerColorItem(itemColors)
        MATERIAL_STICK.registerColorItem(itemColors)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        BOOK_RESPAWN.registerModel()

        MATERIAL_BOTTLE.registerModel()
        MATERIAL_DUST.registerModel()
        MATERIAL_DUST_TINY.registerModel()
        MATERIAL_GEAR.registerModel()
        MATERIAL_GEM.registerModel()
        MATERIAL_INGOT.registerModel()
        MATERIAL_NUGGET.registerModel()
        MATERIAL_ORE.registerModel()
        MATERIAL_PLATE.registerModel()
        MATERIAL_STICK.registerModel()
    }

}