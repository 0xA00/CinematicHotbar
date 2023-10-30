package length.oxao.cinematichotbar.mixin;

import length.oxao.cinematichotbar.CinematicHotbar;
import length.oxao.cinematichotbar.FadeMode;
import length.oxao.cinematichotbar.Timer;
import length.oxao.cinematichotbar.setPropertiess;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static length.oxao.cinematichotbar.CinematicHotbar.*;


@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Unique
    private final MinecraftClient client = (MinecraftClient) (Object) this;

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci) throws InterruptedException {
        boolean isCinematicHotbaractivated = new setPropertiess().getProperty("CinematicHotbar");
        boolean worldLoaded = client.world != null;
        //wait for 5 seconds before doing anything
        if(isCinematicHotbaractivated && worldLoaded) {
            if (!isHudHidden) {
                //  System.out.println(Timer.timer);
                if (Timer.timer > 0) {
                    Timer.timer--;
                    if (fadeOpacity < 1.0F && fadeMode == FadeMode.valueOf("FADE_IN")) {
                        fadeOpacity += 0.05F;
                    }
                }
                if (Timer.timer == 0) {
                    fadeOut = true;
                    Timer.timer = -1;
                }
                if (Timer.timer== -1){
                    fadeMode = FadeMode.valueOf("FADE_OUT");
                    if (fadeOpacity > 0.0F) {
                        fadeOpacity -= 0.05F;
                    }
                }
            }
        }
        else {
            fadeOut = false;
        }
    }


    @Inject(at = @At("HEAD"), method = "handleInputEvents")
        private void handleInputEvents(CallbackInfo ci) {
            assert client.player != null;
            if(client.player.isSpectator()) {
                fadeOut = false;
            }
            else if (this.client.options.useKey.isPressed()||this.client.options.attackKey.isPressed()||this.client.player.hurtTime>1){
                Timer.SetTimer(Timer.getTiming());
                fadeMode = FadeMode.valueOf("FADE_IN");
                fadeOut= false;
            }

    }
}
