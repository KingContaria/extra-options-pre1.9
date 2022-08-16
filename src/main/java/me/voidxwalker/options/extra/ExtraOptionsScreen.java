/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package me.voidxwalker.options.extra;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;

@Environment(value=EnvType.CLIENT)
public class ExtraOptionsScreen extends Screen {
    private final Screen parent;
    protected final String title = "Extra Options";

    public ExtraOptionsScreen(Screen parent) {
        this.parent = parent;
    }

    @Override
    public void init() {
        this.buttons.clear();
        this.buttons.add(new ButtonWidget(200, this.width / 2 - 100, this.height / 6 + 168, I18n.translate("gui.done")));
        this.buttons.add(new SliderWidget(28743857, this.width / 2 - 155, this.height / 6 + 24 - 6, ExtraOptions.fovEffectScale));
        this.buttons.add(new SliderWidget(28743857, this.width / 2 + 5, this.height / 6 + 24 - 6, ExtraOptions.distortionEffectScale));
    }

    @Override
    protected void buttonClicked(ButtonWidget button) {
        if (!button.active) {
            return;
        }
        if (button.id == 200) {
            ExtraOptions.save();
            this.client.openScreen(this.parent);
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        this.renderBackground();
        this.drawCenteredString(this.textRenderer, this.title, this.width / 2, 15, 0xFFFFFF);
        super.render(mouseX, mouseY, tickDelta);
    }
}

