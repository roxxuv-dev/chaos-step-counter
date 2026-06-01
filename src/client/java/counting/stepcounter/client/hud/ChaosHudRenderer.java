if (!HudData.lastEvent.isEmpty()
        && System.currentTimeMillis()
        < HudData.eventDisplayUntil) {

    graphics.drawString(
            mc.font,
            "EVENT:",
            10,
            60,
            0xFFFF00,
            true
    );

    graphics.drawString(
            mc.font,
            HudData.lastEvent,
            10,
            75,
            0xFFFFFF,
            true
    );
}