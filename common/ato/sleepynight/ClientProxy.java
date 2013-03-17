package ato.sleepynight;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerTickHandler() {
        TickRegistry.registerScheduledTickHandler(new Ticking(), Side.CLIENT);
    }

}
