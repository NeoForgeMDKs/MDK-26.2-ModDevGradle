package com.JSUSHDX.WorldTriggerMod.client.renderer.entity;

import com.JSUSHDX.WorldTriggerMod.WorldTriggerMod;
import com.JSUSHDX.WorldTriggerMod.entity.ModTrionBullet;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.joml.Matrix4f;

public class TrionBulletRenderer extends EntityRenderer<ModTrionBullet, TrionBulletRenderer.TrionBulletRenderState> {
    public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(WorldTriggerMod.MODID, "textures/entity/projectiles/trion_bullet.png");

    public TrionBulletRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public TrionBulletRenderState createRenderState() {
        return new TrionBulletRenderState();
    }

    @Override
    public void extractRenderState(ModTrionBullet entity, TrionBulletRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.age = entity.tickCount + partialTick;
    }

    @Override
    public void submit(TrionBulletRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraState) {
        poseStack.pushPose();

        poseStack.mulPose(Axis.YP.rotationDegrees(state.age * 10.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(state.age * 10.0F));

        collector.submitCustomGeometry(poseStack, net.minecraft.client.renderer.rendertype.RenderTypes.entityTranslucent(TEXTURE), (pose, vertexconsumer) -> {
            Matrix4f matrix4f = pose.pose();
            float s = 0.15f;

            // Front (North): uv [0, 0, 4, 4] -> u: 0~0.25, v: 0~0.25
            vertex(vertexconsumer, matrix4f, -s, -s,  s, 0.0f, 0.25f);
            vertex(vertexconsumer, matrix4f,  s, -s,  s, 0.25f, 0.25f);
            vertex(vertexconsumer, matrix4f,  s,  s,  s, 0.25f, 0.0f);
            vertex(vertexconsumer, matrix4f, -s,  s,  s, 0.0f, 0.0f);

            // Back (South): uv [4, 0, 8, 4] -> u: 0.25~0.5, v: 0~0.25
            vertex(vertexconsumer, matrix4f,  s, -s, -s, 0.25f, 0.25f);
            vertex(vertexconsumer, matrix4f, -s, -s, -s, 0.5f, 0.25f);
            vertex(vertexconsumer, matrix4f, -s,  s, -s, 0.5f, 0.0f);
            vertex(vertexconsumer, matrix4f,  s,  s, -s, 0.25f, 0.0f);

            // Top (Up): uv [4, 12, 0, 8] -> u: 0.25~0.0, v: 0.75~0.5
            vertex(vertexconsumer, matrix4f, -s,  s,  s, 0.25f, 0.75f);
            vertex(vertexconsumer, matrix4f,  s,  s,  s, 0.0f, 0.75f);
            vertex(vertexconsumer, matrix4f,  s,  s, -s, 0.0f, 0.5f);
            vertex(vertexconsumer, matrix4f, -s,  s, -s, 0.25f, 0.5f);

            // Bottom (Down): uv [12, 0, 8, 4] -> u: 0.75~0.5, v: 0.0~0.25
            vertex(vertexconsumer, matrix4f, -s, -s, -s, 0.75f, 0.25f);
            vertex(vertexconsumer, matrix4f,  s, -s, -s, 0.5f, 0.25f);
            vertex(vertexconsumer, matrix4f,  s, -s,  s, 0.5f, 0.0f);
            vertex(vertexconsumer, matrix4f, -s, -s,  s, 0.75f, 0.0f);

            // Left (West): uv [4, 4, 8, 8] -> u: 0.25~0.5, v: 0.25~0.5
            vertex(vertexconsumer, matrix4f, -s, -s, -s, 0.25f, 0.5f);
            vertex(vertexconsumer, matrix4f, -s, -s,  s, 0.5f, 0.5f);
            vertex(vertexconsumer, matrix4f, -s,  s,  s, 0.5f, 0.25f);
            vertex(vertexconsumer, matrix4f, -s,  s, -s, 0.25f, 0.25f);

            // Right (East): uv [0, 4, 4, 8] -> u: 0~0.25, v: 0.25~0.5
            vertex(vertexconsumer, matrix4f,  s, -s,  s, 0.0f, 0.5f);
            vertex(vertexconsumer, matrix4f,  s, -s, -s, 0.25f, 0.5f);
            vertex(vertexconsumer, matrix4f,  s,  s, -s, 0.25f, 0.25f);
            vertex(vertexconsumer, matrix4f,  s,  s,  s, 0.0f, 0.25f);
        });

        poseStack.popPose();
        super.submit(state, poseStack, collector, cameraState);
    }

    private static void vertex(VertexConsumer consumer, Matrix4f pose, float x, float y, float z, float u, float v) {
        consumer.addVertex(pose, x, y, z).setColor(255, 255, 255, 255).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(0, 1, 0);
    }

    public static class TrionBulletRenderState extends EntityRenderState {
        public float age;
    }
}
