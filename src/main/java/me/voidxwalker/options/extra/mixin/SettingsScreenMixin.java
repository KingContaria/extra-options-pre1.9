package me.voidxwalker.options.extra.mixin;

import me.voidxwalker.options.extra.ExtraOptionsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SettingsScreen.class)
public class SettingsScreenMixin extends Screen {

    @Shadow @Final private Screen parent;

    @Redirect(method = "buttonClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;superSecretSettings()V"))
    private void test2(GameRenderer gameRenderer) {
        if (this.parent instanceof TitleScreen) {
            this.client.openScreen(new ExtraOptionsScreen(this));
        } else {
            gameRenderer.superSecretSettings();
        }
    }

    @ModifyConstant(method = "init", constant = @Constant(stringValue = "Super Secret Settings..."))
    private String injected(String string) {
        if (this.parent instanceof TitleScreen) {
            return "Extra Options";
        }
        return string;
    }

}
