package ato.sleepynight;

import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerTickHandler() {
        MinecraftForge.EVENT_BUS.register(new Ticking());
    }

}
