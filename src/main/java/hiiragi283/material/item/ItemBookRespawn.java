package hiiragi283.material.item;

import hiiragi283.material.api.item.HiiragiItem;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

public class ItemBookRespawn extends HiiragiItem {

    public ItemBookRespawn() {
        super("book_respawn", 0);
    }

    //    General    //

    @Override
    public @NotNull IRarity getForgeRarity(@NotNull ItemStack stack) {
        return EnumRarity.EPIC;
    }

    //    Event    //

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        //落下死防止やコマンド権限のためクリエモードに切り替え
        if (!world.isRemote) HiiragiUtil.executeCommand(player, "gamemode 1");
        if (world.isRemote) {
            var spawnPoint = world.getSpawnPoint();
            player.motionX = 0.0;
            player.motionY = 0.0;
            player.motionZ = 0.0; //運動ベクトルをリセット
            //プレイヤーを指定した座標にテレポート
            player.setLocationAndAngles(spawnPoint.getX() + 0.5, 128.0, spawnPoint.getZ() + 0.5, 0.0f, 0.0f);
        }
        return super.onItemRightClick(world, player, hand);
    }

}