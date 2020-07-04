package uk.co.fakas.minecraft.forge.buildspace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;


public abstract class CommonProxy {
    public void preInit() {
        StartupCommon.preInitCommon();
    }

    public void init() {
        MinecraftForge.EVENT_BUS.register(BlockPlaceHandler.class);
    }

    public void postInit() {
    }

    abstract public boolean playerIsInCreativeMode(EntityPlayer player);

    abstract public boolean isDedicatedServer();
}