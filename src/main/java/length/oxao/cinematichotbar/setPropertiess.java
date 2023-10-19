package length.oxao.cinematichotbar;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileInputStream;


import java.io.FileOutputStream;
import java.util.Properties;


public class setPropertiess {

    File file = new File(FabricLoader.getInstance().getConfigDir()+"/cinematichotbar.properties");

    public setPropertiess() {
        if(!file.exists()){
            createFile();
        }
    }

    public void createFile(){
        try {
            file.createNewFile();
            Properties props = new Properties();
            FileInputStream in = new FileInputStream(file);
            FileOutputStream out = new FileOutputStream(file);
            props.load(in);
            props.setProperty("CinematicHotbar", "false");
            props.store(out, "cinematichotbar");
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


public boolean getProperty(String property) {
        Properties props = new Properties();
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            props.load(in);
            in.close();
            return Boolean.parseBoolean(props.getProperty(property));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public void setProperty(String property, boolean value) {
        Properties props = new Properties();
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            props.load(in);
            in.close();
            FileOutputStream out = new FileOutputStream(file);
            props.setProperty(property, String.valueOf(value));
            props.store(out, "cinematichotbar");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
