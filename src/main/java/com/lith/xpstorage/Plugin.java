package com.lith.xpstorage;

import com.lith.lithcore.abstractClasses.AbstractPlugin;
import com.lith.xpstorage.events.ShiftClickEvent;

@SuppressWarnings("rawtypes")
public class Plugin extends AbstractPlugin {
    @Override
    protected void registerEvents() {
        registerEvent(new ShiftClickEvent());
    }
}
