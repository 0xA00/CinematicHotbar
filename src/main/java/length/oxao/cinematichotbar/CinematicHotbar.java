package length.oxao.cinematichotbar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ModInitializer;

public class CinematicHotbar implements ModInitializer {


    public static final Logger LOGGER = LoggerFactory.getLogger("cinematichotbar");
    public boolean isCinematicHotbaractivated;
    public static boolean fadeOut = false;

    static CinematicHotbar INSTANCE;


    public CinematicHotbar() {
        INSTANCE = this;
    }

    public CinematicHotbar getInstance() {
        return INSTANCE;
    }
    public static boolean  isHudHidden;

        @Override
    public void onInitialize() {
        if (INSTANCE == null) INSTANCE = this;
        this.isCinematicHotbaractivated = new setPropertiess().getProperty("CinematicHotbar");
        isHudHidden = false;

    }
}
