package hiiragi283.material.tile;

import hiiragi283.material.api.capability.item.HiiragiItemHandler;
import hiiragi283.material.api.capability.item.HiiragiItemHandlerWrapper;
import hiiragi283.material.api.tile.HiiragiTileEntity;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class TileEntityModuleInstaller extends HiiragiTileEntity {

    public final HiiragiItemHandler inputInv0 = new HiiragiItemHandler(1) {
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
    };
    public final HiiragiItemHandler inputInv1 = new HiiragiItemHandler(1) {
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
    };
    public final HiiragiItemHandler inputInv2 = new HiiragiItemHandler(1) {
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
    };
    public final HiiragiItemHandler inputInv3 = new HiiragiItemHandler(1) {
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
    };
    public final HiiragiItemHandler outputInv = new HiiragiItemHandler(1) {
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
    };

    //    General    //

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString("Module Installer");
    }

    //    Event    //

    @Override
    public boolean onTileActivated(World world, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing facing) {
        if (!world.isRemote)
            openGui(player, world, pos);
        return true;
    }

    @Override
    public void onTileRemoved(World world, BlockPos pos, IBlockState state) {
        HiiragiUtil.dropInventoryItems(world, pos, new HiiragiItemHandlerWrapper(inputInv0, inputInv1, inputInv2, inputInv3, outputInv), Vec3d.ZERO);
    }

}