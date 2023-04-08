package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.base.TileBase
import hiiragi283.ragi_materials.capability.RagiEnergyStorage
import hiiragi283.ragi_materials.item.IMaterialItem
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.type.EnumCrystalType
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.CapabilityEnergy

class TileQuartzAntenna : TileBase(106), ITickable {

    private val energyStorage = RagiEnergyStorage(16000)
    private var count = 0
    var posTo: BlockPos? = null
    var material = RagiMaterial.EMPTY

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        tag.setTag(keyEnergy, energyStorage.serializeNBT()) //バッテリーをNBTに書き込む
        posTo?.let {
            tag.setInteger("toX", it.x)
            tag.setInteger("toY", it.y)
            tag.setInteger("toZ", it.z)
        }
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        energyStorage.deserializeNBT(tag.getCompoundTag(keyEnergy)) //バッテリーをNBTから読み込む
        if (tag.hasKey("toX") && tag.hasKey("toY") && tag.hasKey("toZ")) {
            posTo = BlockPos(tag.getInteger("toX"), tag.getInteger("toY"), tag.getInteger("toZ"))
        }
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (hasCapability(capability, null)) energyStorage as T else null
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return capability == CapabilityEnergy.ENERGY
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        var result = false
        val stack = player.getHeldItem(hand)
        val item = stack.item
        if (item is IMaterialItem) {
            val material = item.getMaterial(stack)
            if (material.crystalType == EnumCrystalType.QUARTZ) {
                this.material = material
                result = true
            }
        }
        return result
    }

    //    ITickable    //

    override fun update() {
        //countが20以上の場合
        if (count >= 20) {
            //真下の座標からcapabilityを取得する
            val tileFrom = this.world.getTileEntity(this.pos.offset(EnumFacing.DOWN))
            //真下のタイルエンティティからFEを搬入する
            if (tileFrom !== null) energyStorage.receiveEnergyFrom(tileFrom, EnumFacing.UP, false)
            //転送先の座標からcapabilityを得る
            if (posTo !== null) {
                val tileTo = this.world.getTileEntity(posTo!!)
                //転送先のタイルエンティティにFEを搬出する
                if (tileTo !== null) energyStorage.extractEnergyTo(tileTo, null, false)
            }
            count = 0 //countをリセット
        } else count++ //countを追加
    }
}