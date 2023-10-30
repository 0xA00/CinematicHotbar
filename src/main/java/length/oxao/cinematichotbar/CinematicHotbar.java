package length.oxao.cinematichotbar;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import length.oxao.cinematichotbar.screen.OutdatedScreen;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ModInitializer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

import static length.oxao.cinematichotbar.keybind.VHKeyBinding.optionKeyBinding;
import static length.oxao.cinematichotbar.keybind.VHKeyBinding.registerKeyBinding;

public class CinematicHotbar implements ClientModInitializer {


    public static final Logger LOGGER = LoggerFactory.getLogger("cinematichotbar");
    public static boolean isCinematicHotbaractivated;
    public static boolean fadeOut = false;

    //get modid from fabric.mod.json
    public static final String MOD_ID = "cinematichotbar";
    //get mod version from fab(ric.mod.json
    public static final String MOD_VERSION = "1.2.2";
    public static final String MC_VERSION = "1.19.4";



    static CinematicHotbar INSTANCE;


    public CinematicHotbar() {
        INSTANCE = this;
    }

    public CinematicHotbar getInstance() {
        return INSTANCE;
    }
    public static boolean  isHudHidden;

    public static boolean isOutdated;

    public static FadeMode fadeMode;
    public static float fadeOpacity;



    @Environment(EnvType.CLIENT)
    public static void registerClientTickEvents(){
        ClientTickEvents.END_CLIENT_TICK.register(client->{
            while(optionKeyBinding.wasPressed()){
                ConfigBuilder builder = ConfigBuilder.create().setParentScreen(client.currentScreen)
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

                Screen scr = builder.build();
                client.setScreen(scr);
            }
        });
    }

        @Override
    public void onInitializeClient() {
        if (INSTANCE == null) INSTANCE = this;
        new setPropertiess();
        isCinematicHotbaractivated = setPropertiess.getProperty("CinematicHotbar");
        isHudHidden = false;
        fadeOpacity = 1.0F;
        Timer.SetTimer(60);

            try {
                URL url = new URL("https://api.modrinth.com/v2/project/cinematic-hotbar/version");

                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "cinematichotbar");
                con.setRequestProperty("Accept", "application/json");
                con.setConnectTimeout(5000);
                con.connect();

            // read the response
            int status = con.getResponseCode();
            if (status == 200){
                //read the response
                StringBuilder inline = new StringBuilder();
                Scanner scn = new Scanner(url.openStream());
                while (scn.hasNext()) {
                    inline.append(scn.nextLine());
                }
                scn.close();

               // System.out.println(inline);
                //get the version
                JsonParser parse = new JsonParser();
                JsonArray data_array = (JsonArray) parse.parse(String.valueOf(inline));
                JsonObject data_object = (JsonObject) data_array.get(0);
                String version = String.valueOf(data_object.get("version_number")).replace("\"", "");
                 if (!version.equals(MOD_VERSION)){
                    isOutdated = true;
                    LOGGER.info("CinematicHotbar is outdated, please update it to the latest version");
                     //open outdated screen on the render thread
                        MinecraftClient.getInstance().execute(() -> {
                            MinecraftClient.getInstance().setScreen(new OutdatedScreen(Text.literal(version)));
                        });

                }
                else {
                    isOutdated = false;
                }
            }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            registerClientTickEvents();
            registerKeyBinding();

        }
}
