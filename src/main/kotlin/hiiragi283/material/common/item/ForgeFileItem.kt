package hiiragi283.material.common.item

import hiiragi283.material.api.HiiragiItem
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.RagiResourcePack
import hiiragi283.material.common.util.addItem
import hiiragi283.material.common.util.addTag
import hiiragi283.material.common.util.hiiragiId
import hiiragi283.material.common.util.itemModelLayered
import net.devtech.arrp.json.recipe.JKeys
import net.devtech.arrp.json.recipe.JPattern
import net.devtech.arrp.json.recipe.JRecipe
import net.devtech.arrp.json.recipe.JResult
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.Identifier

object ForgeFileItem : HiiragiItem(
    FabricItemSettings()
        .group(ItemGroup.TOOLS)
        .maxCount(1)
        .maxDamage(63)
) {

    override fun getRecipeRemainder(stack: ItemStack): ItemStack {
        val damage = stack.damage
        return if (damage >= 63) ItemStack.EMPTY else stack.copy().also { it.damage += 1 }
    }

    //    HiiragiEntry    //

    override fun getIdentifier(): Identifier = hiiragiId("forge_file")

    override fun register(): Item {

        val item = super.register()

        RagiResourcePack.addItemModel(getIdentifier(), itemModelLayered {
            layer0("minecraft:item/stick")
            layer1("ragi_materials:item/forge_hammer")
        })

        RagiResourcePack.addRecipe(
            getIdentifier(), JRecipe.shaped(
                JPattern.pattern("A", "A", "B"),
                JKeys.keys()
                    .addTag("A", ShapeRegistry.PLATE.getCommonTag(MaterialElements.IRON).toString())
                    .addItem("B", Items.STICK),
                JResult.item(this)
            )
        )

        return item
    }

}