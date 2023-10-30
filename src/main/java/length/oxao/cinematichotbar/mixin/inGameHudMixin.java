package length.oxao.cinematichotbar.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import length.oxao.cinematichotbar.FadeMode;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.JumpingMount;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static length.oxao.cinematichotbar.CinematicHotbar.*;

@Mixin(InGameHud.class)
public class inGameHudMixin extends DrawableHelper {


    @Shadow @Final private ItemRenderer itemRenderer;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbar(FLnet/minecraft/client/util/math/MatrixStack;)V"))
    private void startHotbarTranslate(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        //blend color
        DotheThingy();
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbar(FLnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
    private void endHotbarTranslate(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        //blend color
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/util/math/MatrixStack;)V"))
    private void startStatusBarsTranslate(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        DotheThingy();
    }

    private void DotheThingy() {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        if(fadeMode == FadeMode.FADE_OUT) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, fadeOpacity);
        }
        else if (fadeMode == FadeMode.FADE_IN){
            if (fadeOpacity < 1.0F) {
                fadeOpacity += 0.01F;
            }
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, fadeOpacity);
        }
        else {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
    private void endStatusBarsTranslate(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }


    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/util/math/MatrixStack;)V"))
    private void startMountHealthTranslate(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        DotheThingy();
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
    private void endMountHealthTranslate(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountJumpBar(Lnet/minecraft/entity/JumpingMount;Lnet/minecraft/client/util/math/MatrixStack;I)V"))
    private void startMountJumpBarTranslate(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        DotheThingy();
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountJumpBar(Lnet/minecraft/entity/JumpingMount;Lnet/minecraft/client/util/math/MatrixStack;I)V", shift = At.Shift.AFTER))
    private void endMountJumpBarTranslate(MatrixStack matrices, float tickDelta, CallbackInfo ci) {

    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    // EXPERIENCE BAR
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/util/math/MatrixStack;I)V"))
    private void startExperienceBarTranslate(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        DotheThingy();
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/util/math/MatrixStack;I)V", shift = At.Shift.AFTER))
    private void endExperienceBarTranslate(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Inject(method = "renderHotbarItem", at = @At(value = "HEAD"), cancellable = true)
    private void renderHotbarItem(MatrixStack matrixStack, int i, int j, float f, PlayerEntity playerEntity, ItemStack itemStack, int k, CallbackInfo ci) {
        if (fadeOut && fadeOpacity< 0.35F) {
            ci.cancel();
        }
    }










}
