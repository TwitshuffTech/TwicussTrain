package net.ddns.twicusstumble.twicusstrain.model;

import net.ddns.twicusstumble.twicusstrain.TwicussTrain;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTrain extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape5;
    public ModelRenderer shape6;
    public ModelRenderer shape7;

    public ModelTrain() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape3 = new ModelRenderer(this, 0, 10);
        this.shape3.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.shape3.addBox(-10.0F, -8.0F, -1.0F, 20, 16, 2, 0.0F);
        this.setRotateAngle(shape3, 1.5707963705062866F, 0.0F, 0.0F);
        this.shape4 = new ModelRenderer(this, 44, 10);
        this.shape4.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.shape4.addBox(-9.0F, -7.0F, -1.0F, 18, 14, 1, 0.0F);
        this.setRotateAngle(shape4, -1.5707963705062866F, 0.0F, 0.0F);
        this.shape6 = new ModelRenderer(this, 0, 0);
        this.shape6.setRotationPoint(0.0F, 4.0F, 7.0F);
        this.shape6.addBox(-8.0F, -9.0F, -1.0F, 16, 8, 2, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 0);
        this.shape2.setRotationPoint(9.0F, 4.0F, 0.0F);
        this.shape2.addBox(-8.0F, -9.0F, -1.0F, 16, 8, 2, 0.0F);
        this.setRotateAngle(shape2, 0.0F, 1.5707963705062866F, 0.0F);
        this.shape5 = new ModelRenderer(this, 0, 0);
        this.shape5.setRotationPoint(0.0F, 4.0F, -7.0F);
        this.shape5.addBox(-8.0F, -9.0F, -1.0F, 16, 8, 2, 0.0F);
        this.setRotateAngle(shape5, 0.0F, 3.1415927410125732F, 0.0F);
        this.shape7 = new ModelRenderer(this, 32, -7);
        this.shape7.setRotationPoint(0.0F, -5.0F, 0.0F);
        this.shape7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 3.0F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(-9.0F, 4.0F, 0.0F);
        this.shape1.addBox(-8.0F, -9.0F, -1.0F, 16, 8, 2, 0.0F);
        this.setRotateAngle(shape1, 0.0F, 4.71238899230957F, 0.0F);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.shape3.render(scale);
        this.shape4.render(scale);
        this.shape6.render(scale);
        this.shape2.render(scale);
        this.shape5.render(scale);
        this.shape7.render(scale);
        this.shape1.render(scale);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
