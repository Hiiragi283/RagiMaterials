package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.event.RightClickBlock
import hiiragi283.ragi_materials.items.ItemBookDebug
import hiiragi283.ragi_materials.items.ItemMaterialDust
import hiiragi283.ragi_materials.items.ItemMaterialMetal
import hiiragi283.ragi_materials.materials.EnumMaterials
import hiiragi283.ragi_materials.materials.MaterialHelper
import hiiragi283.ragi_materials.materials.MaterialType
import hiiragi283.ragi_materials.util.RagiUtils
import hiiragi283.ragi_materials.util.RegexStatics.snakeToUpperCamelCase
import net.minecraft.block.material.Material
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.BlockFluidClassic
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.registry.ForgeRegistries


object RagiInit {

    //Blockの定義

    //Itemの定義
    val ItemBookDebug = ItemBookDebug()
    val ItemDust = ItemMaterialDust()
    val ItemIngot = ItemMaterialMetal("ingot")
    val ItemNugget = ItemMaterialMetal("nugget")
    val ItemPlate = ItemMaterialMetal("plate")

    //Blockを登録するメソッド
    fun registerBlocks() {}

    //Eventを登録するメソッド
    fun registerEvents() {
        MinecraftForge.EVENT_BUS.register(RightClickBlock())
    }

    //Fluidを登録するメソッド
    fun registerFluids() {
        //EnumMaterialsの各enumに対して実行
        for (material in EnumMaterials.values()) {
            //materialがWILDCARDでない場合
            if(material != EnumMaterials.WILDCARD) {
                //materialのtypeのhasFluidがtrueの場合
                if(material.type.hasFluid) {
                    //Fluidの登録
                    val fluid = FluidBase(material.registryName)
                    fluid.setColor(material.color)
                    //MaterialTypesがGASの場合
                    if(material.type == MaterialType.GAS) {
                        fluid.isGaseous = true
                        fluid.density = fluid.density * -1
                    }
                    FluidRegistry.registerFluid(fluid)
                    //fluid入りバケツを登録
                    FluidRegistry.addBucketForFluid(fluid)

                    /*
                      Thanks to defeatedcrow!
                      Source: https://github.com/defeatedcrow/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/main/MainMaterialRegister.java
                    */

                    //materialのtypeのhasFluidBlockがtrueの場合
                    if(material.type.hasFluidBlock) {
                        //液体ブロックを生成・登録・割り当て
                        val fluidBlock = BlockFluidClassic(fluid, Material.WATER)
                        fluidBlock.setRegistryName(fluid.name)
                        ForgeRegistries.BLOCKS.register(fluidBlock)
                        fluid.block = fluidBlock
                    }
                }
            }
        }
    }

    //Itemを登録するメソッド
    fun registerItems() {
        ForgeRegistries.ITEMS.register(ItemBookDebug)
        ForgeRegistries.ITEMS.register(ItemDust)
        ForgeRegistries.ITEMS.register(ItemIngot)
        ForgeRegistries.ITEMS.register(ItemNugget)
        ForgeRegistries.ITEMS.register(ItemPlate)
    }

    //鉱石辞書を登録するメソッド
    fun registerOreDict() {
        //EnumMaterialsの各enumに対して実行
        for (material in EnumMaterials.values()) {
            //list内の各prefixに対して実行
            for (prefix in MaterialHelper.listPrefix) {
                //鉱石辞書名 -> prefix + registryNameをUpperCamelCaseに変換した文字列
                //ItemStack -> pathをprefix, metadataをmaterialのindexとしてRagiUtils.getStackで取得
                RagiUtils.setOreDict(prefix + material.getOreDict(), RagiUtils.getStack(
                    Reference.MOD_ID, prefix, 1, material.index))
            }
        }
    }
}