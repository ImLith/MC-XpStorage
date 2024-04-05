package com.lith.xpstorage;

import com.lith.lithcore.abstractClasses.MainPlugin;
import com.lith.lithcore.abstractClasses.PluginConfigManager;
import com.lith.xpstorage.events.ShiftClickEvent;

public class Plugin extends MainPlugin<PluginConfigManager> {
    public static Plugin plugin;

    public void onEnable() {
        Plugin.plugin = this;

        registerEvents();

        Static.log.info("Plugin enabled");
    }

    public void onDisable() {
        Static.log.info("Plugin disabled");
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new ShiftClickEvent(), this);
    }
}
