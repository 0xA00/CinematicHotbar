package length.oxao.cinematichotbar;

import length.oxao.cinematichotbar.client.CineHotbar;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ModInitializer;

public class CinematicHotbar implements ModInitializer {


    public static final Logger LOGGER = LoggerFactory.getLogger("cinematichotbar");
    public boolean isCinematicHotbaractivated;
    public static boolean fadeOut = false;

    public CineHotbar cineHotbar = new CineHotbar();
    static CinematicHotbar INSTANCE;


    public CinematicHotbar() {
        INSTANCE = this;
    }

    public CinematicHotbar getInstance() {
        return INSTANCE;
    }

        @Override
    public void onInitialize() {
        if (INSTANCE == null) INSTANCE = this;
        this.isCinematicHotbaractivated = new setPropertiess().getProperty("CinematicHotbar");
    }
}