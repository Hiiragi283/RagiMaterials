package hiiragi283.ragi_materials.client.gui

import hiiragi283.ragi_materials.tile.TileIndustrialLabo
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.inventory.IContainerListener
import net.minecraft.inventory.Slot

/*
  Thanks to defeatedcrow!
  Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/gui/ContainerIBCv3.java
*/

class ContainerIndustrialLabo(val tile: TileIndustrialLabo, player: EntityPlayer) : Container() {

    init {
        tile.openInventory(player)
        //タイルエンティティのインベントリのスロットを登録
        for (i in 0..4) {
            this.addSlotToContainer(Slot(tile.inventory, i, i * 18, 0)) //入力スロット
            this.addSlotToContainer(Slot(tile.inventory, i + 5, i * 18, 18 * 2)) //出力スロット
        }
        //プレイヤーのインベントリのスロットを登録
        for (i in 0..2) {
            for (j in 0..8) {
                addSlotToContainer(Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18))
            }
        }
        //プレイヤーのホットバーのスロットを登録
        for (k in 0..8) {
            addSlotToContainer(Slot(player.inventory, k, 8 + k * 18, 142))
        }
    }

    override fun addListener(listener: IContainerListener) {
        super.addListener(listener)
        listener.sendAllWindowProperties(this, this.tile)
    }

    //プレイヤーがこのContainerに干渉できるか判定するメソッド
    override fun canInteractWith(player: EntityPlayer): Boolean = tile.isUsableByPlayer(player)

    //Containerを閉じた時に呼ばれるメソッド
    /*override fun onContainerClosed(player: EntityPlayer) {
        super.onContainerClosed(player)
        tile.closeInventory(player) //タイルエンティティのインベントリを閉じる
    }*/
}