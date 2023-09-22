package hiiragi283.material.container

import hiiragi283.material.api.container.HiiragiContainer
import hiiragi283.material.entity.EntityMinecartTank
import net.minecraft.entity.player.EntityPlayer

class ContainerMinecartTank(val entity: EntityMinecartTank, player: EntityPlayer) : HiiragiContainer(player) {

    init {
        initSlotsPlayer(51)
    }

}