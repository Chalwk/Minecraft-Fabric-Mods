package com.chalwk.longneck.mixin;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityModel.class)
public class PlayerEntityModelMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void adjustNeckLength(ModelPart root, boolean thinArms, CallbackInfo ci) {
        PlayerEntityModel model = (PlayerEntityModel) (Object) this;
        // Use the getHead() method which should be available
        ModelPart head = model.getHead();
        head.setPivot(0.0F, -12.0F, 0.0F);
    }

    @Inject(method = "setAngles*", at = @At("TAIL"))
    private void adjustLongNeckAngles(LivingEntity livingEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, CallbackInfo ci) {
        PlayerEntityModel model = (PlayerEntityModel) (Object) this;
        ModelPart head = model.getHead();

        float smoothFactor = 0.6F;
        head.pitch = headPitch * 0.017453292F * smoothFactor;
        head.yaw = headYaw * 0.017453292F * smoothFactor;

        if (limbDistance > 0.25F) {
            head.pitch += (float)Math.sin(animationProgress * 0.6F) * 0.05F;
            head.roll = (float)Math.sin(animationProgress * 0.3F) * 0.03F;
        }
    }
}