package length.oxao.cinematichotbar.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OutdatedScreen extends Screen {
    public OutdatedScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        ButtonWidget btngoback = new ButtonWidget.Builder(Text.of("Okay, I'll update later"), (buttonWidget) -> {
            assert this.client != null;
            this.client.setScreen(null);
        }).size(200, 20).position(this.width / 2 - 100, this.height / 2 - 10).build();
        this.addDrawableChild(btngoback);

        ButtonWidget btngoUpdate = new ButtonWidget.Builder(Text.of("Update now"), (buttonWidget) -> {
            assert this.client != null;
            //open players browser to download page
            String os = System.getProperty("os.name").toLowerCase();
            String url = "https://modrinth.com/mod/cinematic-hotbar/versions";
            if(os.contains("win")){
                try {
                    Runtime.getRuntime().exec(new String[] { "rundll32", "url.dll,FileProtocolHandler", url });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(os.contains("mac")){
                try {
                    Runtime.getRuntime().exec(new String[] { "open", url });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(os.contains("nix") || os.contains("nux")){
                try {
                    Runtime.getRuntime().exec(new String[] { "xdg-open", url });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }

            //close the game to force player to update
            this.client.stop();

        }).size(200, 20).position(this.width / 2 - 100, this.height / 2 + 20).build();
        this.addDrawableChild(btngoUpdate);
        }


//render text
@Override
public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        String title = super.title.getString();
        this.renderBackground(matrices);
        this.textRenderer.draw(matrices, "Cinematic Hotbar is outdated!", (float) this.width / 2 - 100, (float) this.height / 2 - 50, Color.RED.getRGB());
        this.textRenderer.draw(matrices, "Please update to the latest version! the "+ title, (float) this.width / 2 - 100, (float) this.height / 2 - 30, Color.RED.getRGB());
        super.render(matrices, mouseX, mouseY, delta);
        }


@Override
public void close() {
        assert this.client != null;
        this.client.setScreen(null);
        }
}
        
        
        
        
        


