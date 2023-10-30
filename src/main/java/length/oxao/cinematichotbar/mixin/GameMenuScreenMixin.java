package length.oxao.cinematichotbar.mixin;

import length.oxao.cinematichotbar.CinematicHotbar;
import length.oxao.cinematichotbar.Timer;
import length.oxao.cinematichotbar.setPropertiess;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.IconButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static length.oxao.cinematichotbar.CinematicHotbar.fadeOpacity;


@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {

    @Unique
    boolean isCinematicHotbaractivated = new setPropertiess().getProperty("CinematicHotbar");

    protected GameMenuScreenMixin(Text title) {
        super(title);
    }



    @Inject(at = @At("HEAD"), method = "initWidgets")
    private void initWidgets(CallbackInfo ci) {
        //put the button in the bottom right corner
       ButtonWidget bW = new ButtonWidget.Builder(Text.of("Vanished Hotbar : "+(isCinematicHotbaractivated?"✔":"X")),(buttonWidget) -> {
           isCinematicHotbaractivated = !isCinematicHotbaractivated;
              new setPropertiess().setProperty("CinematicHotbar",isCinematicHotbaractivated);

           Timer.SetTimer(200);
           fadeOpacity = 1.0F;
           buttonWidget.setMessage(Text.literal("Vanished Hotbar : "+(isCinematicHotbaractivated?"✔":"X")));
       }).dimensions(this.width-124,this.height-45,108,20)
               .tooltip(Tooltip.of(Text.literal("This Button switch between the fadeout hotbar and the normal once")))
               .build();

       this.addDrawableChild(bW);
    }
}
