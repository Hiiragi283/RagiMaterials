package hiiragi283.material.util

import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.ItemMeshDefinition
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.block.statemap.StateMapperBase
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

class SimpleStateMapper(private val modelLocation: ModelResourceLocation) : StateMapperBase(), ItemMeshDefinition {

    constructor(location: ResourceLocation, variant: String) : this(location.toModelLocation(variant))

    override fun getModelLocation(stack: ItemStack): ModelResourceLocation = modelLocation

    override fun getModelResourceLocation(state: IBlockState): ModelResourceLocation = modelLocation

}