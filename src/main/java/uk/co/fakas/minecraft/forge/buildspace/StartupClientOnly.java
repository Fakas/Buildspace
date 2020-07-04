package uk.co.fakas.minecraft.forge.buildspace;

public class StartupClientOnly {
    public static void preInitClientOnly() {
        BuildspaceConfiguration.clientPreInit();
    }

    public static void initClientOnly() {
    }

    public static void postInitClientOnly() {
    }
}
