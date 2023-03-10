package hiiragi283.ragi_materials.base

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.IMaterialItem
import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.MaterialUtil
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.*
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemToolBase(val ID: String, val material: MaterialBuilder, materialTool: ToolMaterial): ItemTool(materialTool.attackDamage, -3.0f, materialTool, setOf()), IMaterialItem, IMaterialTool {

    init {
        val name = "${ID}_${material.name}"
        setRegistryName(Reference.MOD_ID, name)
        unlocalizedName = name
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        MaterialUtil.materialInfo(getMaterial(stack), tooltip)
        super.addInformation(stack, world, tooltip, flag)
    }

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String = I18n.format("item.ragi_${ID}.name", I18n.format("material.${material.name}"))

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): MaterialBuilder = material

    override fun setMaterial(stack: ItemStack, material: MaterialBuilder): ItemStack = stack

    //    IMaterialTool    //

    override fun getToolID(): String = ID

    override fun getToolMaterial(): MaterialBuilder = material

    class AXE(val material: MaterialBuilder, materialTool: ToolMaterial, val ID: String = "axe"): ItemAxe(materialTool, 6.0f + materialTool.attackDamage, 3.0f), IMaterialItem, IMaterialTool {

        init {
            val name = "${ID}_${material.name}"
            setRegistryName(Reference.MOD_ID, name)
            unlocalizedName = name
        }

        //    Client    //

        @SideOnly(Side.CLIENT)
        override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
            MaterialUtil.materialInfo(getMaterial(stack), tooltip)
            super.addInformation(stack, world, tooltip, flag)
        }

        @SideOnly(Side.CLIENT)
        override fun getItemStackDisplayName(stack: ItemStack): String = I18n.format("item.ragi_axe.name", I18n.format("material.${material.name}"))

        //    IMaterialItem    //

        override fun getMaterial(stack: ItemStack): MaterialBuilder = material

        override fun setMaterial(stack: ItemStack, material: MaterialBuilder): ItemStack = stack

        //    IMaterialTool    //

        override fun getToolID(): String = ID

        override fun getToolMaterial(): MaterialBuilder = material

    }

    class PICKAXE(val material: MaterialBuilder, materialTool: ToolMaterial, val ID: String = "pickaxe"): ItemPickaxe(materialTool), IMaterialItem, IMaterialTool {

        init {
            val name = "${ID}_${material.name}"
            setRegistryName(Reference.MOD_ID, name)
            unlocalizedName = name
        }

        //    Client    //

        @SideOnly(Side.CLIENT)
        override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
            MaterialUtil.materialInfo(getMaterial(stack), tooltip)
            super.addInformation(stack, world, tooltip, flag)
        }

        @SideOnly(Side.CLIENT)
        override fun getItemStackDisplayName(stack: ItemStack): String = I18n.format("item.ragi_pickaxe.name", I18n.format("material.${material.name}"))

        //    IMaterialItem    //

        override fun getMaterial(stack: ItemStack): MaterialBuilder = material

        override fun setMaterial(stack: ItemStack, material: MaterialBuilder): ItemStack = stack

        //    IMaterialTool    //

        override fun getToolID(): String = ID

        override fun getToolMaterial(): MaterialBuilder = material

    }

    class SPADE(val material: MaterialBuilder, materialTool: ToolMaterial, val ID: String = "spade"): ItemSpade(materialTool), IMaterialItem, IMaterialTool {

        init {
            val name = "${ID}_${material.name}"
            setRegistryName(Reference.MOD_ID, name)
            unlocalizedName = name
        }

        //    Client    //

        @SideOnly(Side.CLIENT)
        override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
            MaterialUtil.materialInfo(getMaterial(stack), tooltip)
            super.addInformation(stack, world, tooltip, flag)
        }

        @SideOnly(Side.CLIENT)
        override fun getItemStackDisplayName(stack: ItemStack): String = I18n.format("item.ragi_spade.name", I18n.format("material.${material.name}"))

        //    IMaterialItem    //

        override fun getMaterial(stack: ItemStack): MaterialBuilder = material

        override fun setMaterial(stack: ItemStack, material: MaterialBuilder): ItemStack = stack

        //    IMaterialTool    //

        override fun getToolID(): String = ID

        override fun getToolMaterial(): MaterialBuilder = material

    }

    class SWORD(val material: MaterialBuilder, materialTool: ToolMaterial, val ID: String = "sword"): ItemSword(materialTool), IMaterialItem, IMaterialTool {

        init {
            val name = "${ID}_${material.name}"
            setRegistryName(Reference.MOD_ID, name)
            unlocalizedName = name
        }

        //    Client    //

        @SideOnly(Side.CLIENT)
        override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
            MaterialUtil.materialInfo(getMaterial(stack), tooltip)
            super.addInformation(stack, world, tooltip, flag)
        }

        @SideOnly(Side.CLIENT)
        override fun getItemStackDisplayName(stack: ItemStack): String = I18n.format("item.ragi_sword.name", I18n.format("material.${material.name}"))

        //    IMaterialItem    //

        override fun getMaterial(stack: ItemStack): MaterialBuilder = material

        override fun setMaterial(stack: ItemStack, material: MaterialBuilder): ItemStack = stack

        //    IMaterialTool    //

        override fun getToolID(): String = ID

        override fun getToolMaterial(): MaterialBuilder = material

    }
}