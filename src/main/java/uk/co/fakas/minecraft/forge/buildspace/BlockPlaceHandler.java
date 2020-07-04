package uk.co.fakas.minecraft.forge.buildspace;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber
public class BlockPlaceHandler {

    public static final TextComponentString noBuildMessage = new TextComponentString("You cannot build here!");

    @SubscribeEvent
    public static void placeBlock(BlockEvent.EntityPlaceEvent event) {
        boolean creative;
        Entity entity = event.getEntity();


        BlockPos position = event.getPos();
        int distanceX = Math.abs(position.getX() - BuildspaceConfiguration.centreX);
        int distanceZ = Math.abs(position.getZ() - BuildspaceConfiguration.centreZ);

        if (entity != null && (distanceX > BuildspaceConfiguration.radius || distanceZ > BuildspaceConfiguration.radius)) {
            List<String> whitelist = Arrays.asList(BuildspaceConfiguration.whitelist);
            ResourceLocation blockRegistry = event.getPlacedBlock().getBlock().getRegistryName();
            String blockName;

            if (blockRegistry != null) {
                blockName = blockRegistry.toString();
            } else {
                blockName = "null";
            }

            try {
                EntityPlayer player = (EntityPlayer) entity;
                creative = player.isCreative();
            } catch (ClassCastException ce) {
                creative = false;
            }

            if (!creative && !whitelist.contains(blockName)) {
                event.setCanceled(true);
                entity.sendMessage(noBuildMessage);
            }
        }
    }
}
