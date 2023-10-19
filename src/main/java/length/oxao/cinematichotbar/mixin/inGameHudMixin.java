package length.oxao.cinematichotbar.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.JumpingMount;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import static length.oxao.cinematichotbar.CinematicHotbar.fadeOut;

@Mixin(InGameHud.class)
public class inGameHudMixin extends DrawableHelper {


    //inject and cancel render
    @Inject(method = "renderHotbar",
            at = @At(value="HEAD"),
            cancellable = true)
    private void renderHotbar(float tickDelta, MatrixStack matrices, CallbackInfo ci) {
        if(fadeOut)
            ci.cancel();

    }

    //inject and cancel render
    @Inject(method = "renderMountJumpBar",
            at = @At(value="HEAD"),
            cancellable = true)
    private void renderMountJumpBar(JumpingMount mount, MatrixStack matrices, int x, CallbackInfo ci) {
        if(fadeOut)
            ci.cancel();

    }


    //inject and cancel render
    @Inject(method = "renderCrosshair",
            at = @At(value="HEAD"),
            cancellable = true)
    private void renderCrosshair(MatrixStack matrices, CallbackInfo ci) {
        if(fadeOut)
            ci.cancel();
    }


    @Inject(method = "renderStatusBars",
            at = @At(value="HEAD"),
            cancellable = true)
    private void renderStatusBars(MatrixStack matrices, CallbackInfo ci) {
        if(fadeOut)
            ci.cancel();
    }

    //inject and cancel render
    @Inject(method = "renderMountHealth",
            at = @At(value="HEAD"),
            cancellable = true)
    private void renderMountHealth(MatrixStack matrices, CallbackInfo ci) {
        if(fadeOut)
            ci.cancel();
    }



    //inject and cancel render
    @Inject(method = "renderExperienceBar",
            at = @At(value="HEAD"),
            cancellable = true)
    private void renderExperienceBar(MatrixStack matrices, int x, CallbackInfo ci) {
        if(fadeOut)
            ci.cancel();
    }

    //inject and cancel render
    @Inject(method = "renderHeldItemTooltip",
            at = @At(value="HEAD"),
            cancellable = true)
    private void renderHeldItemTooltip(MatrixStack matrices, CallbackInfo ci) {
        if(fadeOut)
            ci.cancel();
    }













}
