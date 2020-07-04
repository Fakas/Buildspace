package uk.co.fakas.minecraft.forge.buildspace;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class BuildspaceConfiguration {
    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_SPACE = "space";

    private static final String[] WHITELIST_DEFAULT = new String[]{
            "minecraft:ladder",
            "minecraft:torch",
            "minecraft:boat",
            "minecraft:spruce_boat",
            "minecraft:birch_boat",
            "minecraft:jungle_boat",
            "minecraft:acacia_boat",
            "minecraft:dark_oak_boat",
            "minecraft:minecart",
            "minecraft:cake",
    };

    public static int radius;
    public static int centreX;
    public static int centreZ;
    public static String[] whitelist;

    private static Configuration config = null;

    public static class ConfigEventHandler {
        @SubscribeEvent
        public void onEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (BuildspaceMod.MODID.equals(event.getModID()) && !event.isWorldRunning()) {
                syncFromGUI();
            }
        }
    }

    public static void preInit() {
        File configFile = new File(Loader.instance().getConfigDir(), "Buildspace.cfg");
        config = new Configuration(configFile);
        syncFromFile();
    }

    public static void clientPreInit() {
        MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
    }

    public static Configuration getConfig() {
        return config;
    }

    public static void syncFromFile() {
        syncConfig(true, true);
    }

    public static void syncFromGUI() {
        syncConfig(false, true);
    }

    public static void syncFromFields() {
        syncConfig(false, false);
    }

    private static void syncConfig(boolean loadConfigFromFile, boolean readFieldsFromConfig) {
        if (loadConfigFromFile) {
            config.load();
        }

        Property propWhitelist = config.get(CATEGORY_GENERAL, "whitelist", WHITELIST_DEFAULT, "Block IDs allowed to be placed outside the build space");

        Property propRadius = config.get(CATEGORY_SPACE, "radius", 12, "Buildable space radius", 1, 9999);

        Property propCentreX = config.get(CATEGORY_SPACE, "centre_x", 0, "Buildable space X coordinate");
        Property propCentreZ = config.get(CATEGORY_SPACE, "centre_z", 0, "Buildable space Z coordinate");

        if (readFieldsFromConfig) {
            whitelist = propWhitelist.getStringList();
            radius = propRadius.getInt();
            centreX = propCentreX.getInt();
            centreZ = propCentreZ.getInt();
        }

        propWhitelist.set(whitelist);
        propRadius.set(radius);
        propCentreX.set(centreX);
        propCentreZ.set(centreZ);

        if (config.hasChanged()) {
            config.save();
        }

    }
}
