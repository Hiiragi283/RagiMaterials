package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.init.RagiBlock
import hiiragi283.ragi_materials.init.RagiItem
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.RagiMaterial
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemOreCrushed: ItemBase(Reference.MOD_ID, "ore_crushed", RagiBlock.BlockOre1.list.size - 1) {

    val list = listOf(
            listOf(MaterialRegistry.SALT, MaterialRegistry.BORAX, MaterialRegistry.SPODUMENE),
            listOf(MaterialRegistry.EMERALD, MaterialRegistry.AQUAMARINE, RagiMaterial.EMPTY),
            listOf(MaterialRegistry.FLUORITE, MaterialRegistry.CRYOLITE, RagiMaterial.EMPTY),
            listOf(MaterialRegistry.HEMATITE, MaterialRegistry.BAUXITE, MaterialRegistry.RUTILE),
            listOf(MaterialRegistry.RUBY, MaterialRegistry.SAPPHIRE, RagiMaterial.EMPTY),
            listOf(MaterialRegistry.SULFUR, MaterialRegistry.PHOSPHORUS, RagiMaterial.EMPTY),
            listOf(MaterialRegistry.NITER, MaterialRegistry.MAGNESITE, RagiMaterial.EMPTY),
            listOf(MaterialRegistry.LIME, MaterialRegistry.GYPSUM, RagiMaterial.EMPTY),
            listOf(MaterialRegistry.IRON, MaterialRegistry.MANGANESE, MaterialRegistry.COBALT),
            listOf(MaterialRegistry.MAGNETITE, MaterialRegistry.CHROMIUM, RagiMaterial.EMPTY),
            listOf(MaterialRegistry.COPPER, MaterialRegistry.NICKEL, MaterialRegistry.COBALT),
            listOf(MaterialRegistry.ZINC, RagiMaterial.EMPTY, RagiMaterial.EMPTY)
    )

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String = I18n.format("item.ore_crushed.name", I18n.format("${RagiItem.ItemBlockOre1.getUnlocalizedName(stack)}.name"))

}