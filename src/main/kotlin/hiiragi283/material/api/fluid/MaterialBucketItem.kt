package hiiragi283.material.api.fluid

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.RMItemColorProvider
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.common.RMItemGroup
import hiiragi283.material.common.RMResourcePack
import hiiragi283.material.common.util.commonId
import hiiragi283.material.common.util.hiiragiId
import hiiragi283.material.common.util.itemModelLayered
import net.devtech.arrp.json.tags.JTag
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.fluid.Fluid
import net.minecraft.item.BucketItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

abstract class MaterialBucketItem(fluid: Fluid) :
    BucketItem(fluid, FabricItemSettings().group(RMItemGroup.MATERIAL_ITEM)), HiiragiEntry.ITEM, RMItemColorProvider {

    companion object {

        @JvmStatic
        fun of(fluid: Fluid, material: HiiragiMaterial): MaterialBucketItem = object : MaterialBucketItem(fluid) {

            override fun getName(stack: ItemStack): Text =
                TranslatableText("item.ragi_materials.material_bucket", material.getTranslatedName())

            //    HiiragiEntry    //

            override fun getIdentifier(): Identifier = hiiragiId("${material.name}_buckets")

            override fun register(): Item {

                RMResourcePack.addItemModel(getIdentifier(), itemModelLayered {
                    layer0("minecraft:item/bucket")
                    layer1("ragi_materials:item/bucket_layer")
                })
                RMResourcePack.addItemTag(commonId(getIdentifier().path), JTag().add(getIdentifier()))

                return Registry.register(Registry.ITEM, getIdentifier(), this)
            }

            //    IHiiragiPart    //

            override fun getColor(stack: ItemStack, tintIndex: Int): Int = if (tintIndex == 1) material.color else -1

        }

    }

}