package hiiragi283.material.api.block

import hiiragi283.material.api.HiiragiBlock
import hiiragi283.material.api.RMItemColorProvider
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.client.color.block.BlockColorProvider

abstract class MaterialBlock(settings: FabricBlockSettings) : HiiragiBlock(settings),
    BlockColorProvider, RMItemColorProvider