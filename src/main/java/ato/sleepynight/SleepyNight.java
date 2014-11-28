package ato.sleepynight;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "SleepyNight")
public class SleepyNight {
    @SidedProxy(
            serverSide = "ato.sleepynight.CommonProxy",
            clientSide = "ato.sleepynight.ClientProxy"
    )
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        proxy.registerTickHandler();
    }
}
