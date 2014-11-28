package ato.sleepynight;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;

@SideOnly(Side.CLIENT)
public class Ticking {

    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            World world = player.worldObj;
            long time = world.getWorldTime();
            if (world.isRemote && time % 100 == 0 && isSleepy(time)) {
                int[] coord = getNearBedCoord(world, player);
                if (coord != null)
                    clickBed(player, world, coord[0], coord[1], coord[2]);
            }
        }
    }

    /**
     * 眠い時間かどうか
     */
    private boolean isSleepy(long time) {
        return 12500 < time && time < 23500;
    }

    /**
     * 近くのベッドの座標を取得
     */
    private int[] getNearBedCoord(World world, EntityPlayer player) {

        for (int dx = -2; dx <= 2; ++dx) {
            for (int dy = -2; dy <= 2; ++dy) {
                for (int dz = -2; dz <= 2; ++dz) {
                    int x = (int) player.posX + dx;
                    int y = (int) player.posY + dy;
                    int z = (int) player.posZ + dz;
                    if (world.getBlock(x, y, z) == Blocks.bed) {
                        return new int[]{x, y, z};
                    }
                }
            }
        }
        return null;
    }

    /**
     * ベッドを右クリックする
     */
    private void clickBed(EntityPlayer player, World world, int x, int y, int z) {
        Minecraft.getMinecraft().playerController
                .onPlayerRightClick(player, world, null, x, y, z, 1, Vec3.createVectorHelper(0, 0, 0));
    }
}
