package length.oxao.cinematichotbar.mixin;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static length.oxao.cinematichotbar.CinematicHotbar.fadeOut;
import static length.oxao.cinematichotbar.CinematicHotbar.isHudHidden;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Shadow
    @Final
    MinecraftClient client;


    @Inject(method = "renderWorld", at = @At("HEAD"))
    private void RenderHand1(CallbackInfo ci){
        if (isHudHidden) {
            client.options.hudHidden = false;
        }


    }

    @Inject(method = "renderWorld", at = @At("TAIL"))
    private void RenderHand2(CallbackInfo ci){
        if (isHudHidden) {
            client.options.hudHidden = true;
        }
    }
}
