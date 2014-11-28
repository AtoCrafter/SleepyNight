package ato.sleepynight;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.EnumSet;

public class Ticking implements IScheduledTickHandler {

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        if (type.equals(EnumSet.of(TickType.PLAYER))) {
            Minecraft mc = FMLClientHandler.instance().getClient();
            EntityPlayer player = mc.thePlayer;
            World world = mc.theWorld;

            if (player != null && world != null) {
                int playerX = (int) Math.floor(player.posX);
                int playerY = (int) Math.floor(player.posY - 1.62);
                int playerZ = (int) Math.floor(player.posZ);

                long time = world.getWorldTime() % 24000;
                if (12500 < time && time < 23500) {
                    for (int dx = -2; dx <= 2; ++dx) {
                        for (int dy = -2; dy <= 2; ++dy) {
                            for (int dz = -2; dz <= 2; ++dz) {
                                int x = playerX + dx;
                                int y = playerY + dy;
                                int z = playerZ + dz;
                                if (world.getBlockId(x, y, z) == Block.bed.blockID) {
                                    mc.playerController.onPlayerRightClick(player, world, null, x, y, z, 1, Vec3.createVectorHelper(0, 0, 0));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.PLAYER);
    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public int nextTickSpacing() {
        return 100;
    }
}
