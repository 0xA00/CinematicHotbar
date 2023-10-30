package length.oxao.cinematichotbar.keybind;


import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class VHKeyBinding {

    public static KeyBinding optionKeyBinding;

    public static void registerKeyBinding(){
        optionKeyBinding =KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "option menu",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_V,
                        "Vanished hotbar"
                )
        );
    }
}
