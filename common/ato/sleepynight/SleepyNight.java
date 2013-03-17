package ato.sleepynight;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(
        modid = "SleepyNight",
        name = "Sleepy Night",
        version = "1.1.1"
)
@NetworkMod(
        clientSideRequired = true,
        serverSideRequired = false
)
public class SleepyNight {

    @Instance("SleepyNight")
    public static SleepyNight instance;
    @SidedProxy(
            serverSide = "ato.sleepynight.CommonProxy",
            clientSide = "ato.sleepynight.ClientProxy"
    )
    public static CommonProxy proxy;

    @Init
    public void load(FMLInitializationEvent event) {
        proxy.registerTickHandler();
    }
}
