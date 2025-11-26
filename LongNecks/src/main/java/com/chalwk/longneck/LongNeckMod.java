package com.chalwk.longneck;

import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LongNeckMod implements ClientModInitializer {

    public static final String MOD_ID = "longneck";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Long Neck mod initialized! Get ready for a long neck!");
    }
}