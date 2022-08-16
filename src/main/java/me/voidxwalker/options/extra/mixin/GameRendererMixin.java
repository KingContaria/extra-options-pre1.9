package me.voidxwalker.options.extra.mixin;

import me.voidxwalker.options.extra.ExtraOptions;
import net.minecraft.client.render.GameRenderer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Shadow private float movementFovMultiplier;

    @Inject(method = "updateMovementFovMultiplier", at = @At("TAIL"))
    private void applyFovEffectScale(CallbackInfo ci){
        this.movementFovMultiplier = 1 - ExtraOptions.fovEffectScale.getValue() + this.movementFovMultiplier * ExtraOptions.fovEffectScale.getValue();
    }

    @Redirect(method = "setupCamera", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 0))
    private void applyDistortionEffectScale1(float angle, float x, float y, float z) {
        GL11.glRotatef(angle * ExtraOptions.distortionEffectScale.getValue(), x, y, z);
    }

    @Redirect(method = "setupCamera", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 1))
    private void applyDistortionEffectScale2(float angle, float x, float y, float z) {
        GL11.glRotatef(angle * ExtraOptions.distortionEffectScale.getValue(), x, y, z);
    }

    @Redirect(method = "setupCamera", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glScalef(FFF)V", ordinal = 1))
    private void applyDistortionEffectScale3(float x, float y, float z) {
        GL11.glScalef(1 - ExtraOptions.distortionEffectScale.getValue() + x * ExtraOptions.distortionEffectScale.getValue(), y, z);
    }

}
