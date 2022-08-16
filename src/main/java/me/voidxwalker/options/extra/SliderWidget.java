/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package me.voidxwalker.options.extra;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.lwjgl.opengl.GL11;

@Environment(value=EnvType.CLIENT)
public class SliderWidget extends ButtonWidget {
    private float value;
    public boolean dragging;
    private final ExtraOption extraOption;

    public SliderWidget(int id, int x, int y, ExtraOption extraOption) {
        super(id, x, y, 150, 20, "");
        this.extraOption = extraOption;
        this.value = extraOption.getValue();
        this.message = extraOption.getDisplayName();
    }

    @Override
    public int getYImage(boolean isHovered) {
        return 0;
    }

    @Override
    protected void renderBg(MinecraftClient client, int mouseX, int mouseY) {
        if (!this.visible) {
            return;
        }
        if (this.dragging) {
            this.value = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
            if (this.value < 0.0f) {
                this.value = 0.0f;
            }
            if (this.value > 1.0f) {
                this.value = 1.0f;
            }
            this.extraOption.setValue(this.value);
            this.message = extraOption.getDisplayName();
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.drawTexture(this.x + (int)(this.value * (float)(this.width - 8)), this.y, 0, 66, 4, 20);
        this.drawTexture(this.x + (int)(this.value * (float)(this.width - 8)) + 4, this.y, 196, 66, 4, 20);
    }

    @Override
    public boolean isMouseOver(MinecraftClient client, int mouseX, int mouseY) {
        if (super.isMouseOver(client, mouseX, mouseY)) {
            this.value = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
            if (this.value < 0.0f) {
                this.value = 0.0f;
            }
            if (this.value > 1.0f) {
                this.value = 1.0f;
            }
            extraOption.setValue(this.value);
            this.message = extraOption.getDisplayName();
            this.dragging = true;
            return true;
        }
        return false;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        this.dragging = false;
    }
}