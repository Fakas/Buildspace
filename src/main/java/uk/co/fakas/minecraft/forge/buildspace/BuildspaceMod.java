package uk.co.fakas.minecraft.forge.buildspace;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = BuildspaceMod.MODID, name = BuildspaceMod.NAME, version = BuildspaceMod.VERSION)
public class BuildspaceMod {
    public static final String MODID = "mod_buildspace";
    public static final String NAME = "Buildspace";
    public static final String VERSION = "0.0.0-dev";

    private static Logger logger;

    @SidedProxy(clientSide = "uk.co.fakas.minecraft.forge.buildspace.ClientOnlyProxy", serverSide = "uk.co.fakas.minecraft.forge.buildspace.DedicatedServerproxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }
}
