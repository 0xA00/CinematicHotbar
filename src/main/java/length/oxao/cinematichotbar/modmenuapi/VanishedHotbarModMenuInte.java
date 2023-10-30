package length.oxao.cinematichotbar.modmenuapi;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import length.oxao.cinematichotbar.Timer;
import length.oxao.cinematichotbar.setPropertiess;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import static length.oxao.cinematichotbar.CinematicHotbar.isCinematicHotbaractivated;

public class VanishedHotbarModMenuInte implements ModMenuApi {


    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent)
                    .setTitle(Text.of("Vanished Hotbar Config"));
            ConfigCategory general = builder.getOrCreateCategory(Text.of("General"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            general.addEntry(entryBuilder.startBooleanToggle(Text.of("Vanished Hotbar"),isCinematicHotbaractivated)
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> {
                        isCinematicHotbaractivated = newValue;
                        new setPropertiess().setProperty("CinematicHotbar",isCinematicHotbaractivated);
                    })
                    .build());

            general.addEntry(entryBuilder.startIntSlider(Text.of("Fade Timing (seconds)"), Timer.getTiming(), 1, 100)
                    .setDefaultValue(5)
                    .setSaveConsumer(newValue -> {
                        new setPropertiess().setProperty("timing",newValue);
                    })
                    .build());

            return builder.build();
        };
    }
}
