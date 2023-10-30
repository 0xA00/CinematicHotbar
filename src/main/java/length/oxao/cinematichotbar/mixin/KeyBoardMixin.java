package length.oxao.cinematichotbar.mixin;


import length.oxao.cinematichotbar.Timer;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static length.oxao.cinematichotbar.CinematicHotbar.*;

@Mixin(Keyboard.class)
public class KeyBoardMixin {


    @Shadow
    @Final
    private MinecraftClient client;


    @Inject(method = "onKey", at= @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;hudHidden:Z"), cancellable = true)
    public void F1key(CallbackInfo ic){

        if (fadeOut){
            //create a message
            assert client.player != null;
            client.player.sendMessage(Text.of("§c§lhud is already hidden !"),true);
        }else {
            isHudHidden = !isHudHidden;
            if (isHudHidden){
                Timer.SetTimer(Timer.getTiming());
            }

            client.options.hudHidden = isHudHidden;
        }

        ic.cancel();

    }
}
