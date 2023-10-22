package length.oxao.cinematichotbar;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import length.oxao.cinematichotbar.screen.OutdatedScreen;
import net.minecraft.client.MinecraftClient;
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

public class CinematicHotbar implements ModInitializer {


    public static final Logger LOGGER = LoggerFactory.getLogger("cinematichotbar");
    public boolean isCinematicHotbaractivated;
    public static boolean fadeOut = false;

    //get modid from fabric.mod.json
    public static final String MOD_ID = "cinematichotbar";
    //get mod version from fab(ric.mod.json
    public static final String MOD_VERSION = "1.2.1";
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

        @Override
    public void onInitialize() {
        if (INSTANCE == null) INSTANCE = this;
        this.isCinematicHotbaractivated = new setPropertiess().getProperty("CinematicHotbar");
        isHudHidden = false;

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


        }
}
