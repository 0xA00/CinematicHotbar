package length.oxao.cinematichotbar.mixin;

import length.oxao.cinematichotbar.CinematicHotbar;
import length.oxao.cinematichotbar.Timer;
import length.oxao.cinematichotbar.setPropertiess;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static length.oxao.cinematichotbar.CinematicHotbar.fadeOut;



@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Unique
    private final MinecraftClient client = (MinecraftClient) (Object) this;

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci) {
        boolean isCinematicHotbaractivated = new setPropertiess().getProperty("CinematicHotbar");
        if(isCinematicHotbaractivated) {
          //  System.out.println(Timer.timer);
            if (Timer.timer > 0) {
                Timer.timer--;
            }
            if (Timer.timer == 0) {
                fadeOut = true;
                Timer.timer = -1;
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
                Timer.SetTimer(200);
                fadeOut= false;
            }

    }
}
