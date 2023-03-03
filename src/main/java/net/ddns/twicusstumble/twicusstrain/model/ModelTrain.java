package net.ddns.twicusstumble.twicusstrain.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTrain extends ModelBase {
    private final ModelRenderer front;
    private final ModelRenderer front2;
    private final ModelRenderer wheel;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer wheel2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer wheel3;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer wheel4;
    private final ModelRenderer cube_r7;
    private final ModelRenderer cube_r8;
    private final ModelRenderer cover;
    private final ModelRenderer cover2;
    private final ModelRenderer bb_main;
    private final ModelRenderer cube_r9;
    private final ModelRenderer cube_r10;

    public ModelTrain() {
        textureWidth = 96;
        textureHeight = 96;

        front = new ModelRenderer(this);
        front.setRotationPoint(1.0F, 24.0F, 0.0F);
        front.cubeList.add(new ModelBox(front, 59, 20, -10.0F, -18.51F, -6.5F, 1, 1, 13, 0.0F, false));
        front.cubeList.add(new ModelBox(front, 44, 47, -10.0F, -19.51F, 2.5F, 1, 1, 4, 0.0F, false));
        front.cubeList.add(new ModelBox(front, 28, 44, -10.0F, -19.51F, -6.5F, 1, 1, 4, 0.0F, false));
        front.cubeList.add(new ModelBox(front, 60, 68, -9.0F, -21.51F, 5.5F, 1, 4, 1, 0.0F, false));
        front.cubeList.add(new ModelBox(front, 56, 68, -9.0F, -21.51F, -6.5F, 1, 4, 1, 0.0F, false));
        front.cubeList.add(new ModelBox(front, 56, 63, -11.75F, -21.26F, -1.5F, 7, 2, 3, 0.0F, false));
        front.cubeList.add(new ModelBox(front, 50, 11, -12.0F, -20.75F, -1.0F, 2, 1, 2, 0.0F, false));
        front.cubeList.add(new ModelBox(front, 44, 47, -8.0F, -21.5F, -5.0F, 1, 2, 10, 0.0F, false));

        front2 = new ModelRenderer(this);
        front2.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(front2, 0.0F, 3.1416F, 0.0F);
        front2.cubeList.add(new ModelBox(front2, 58, 6, -9.0F, -18.51F, -6.5F, 1, 1, 13, 0.0F, false));
        front2.cubeList.add(new ModelBox(front2, 44, 22, -9.0F, -19.51F, 2.5F, 1, 1, 4, 0.0F, false));
        front2.cubeList.add(new ModelBox(front2, 16, 44, -9.0F, -19.51F, -6.5F, 1, 1, 4, 0.0F, false));
        front2.cubeList.add(new ModelBox(front2, 24, 68, -8.0F, -21.51F, 5.5F, 1, 4, 1, 0.0F, false));
        front2.cubeList.add(new ModelBox(front2, 66, 11, -8.0F, -21.51F, -6.5F, 1, 4, 1, 0.0F, false));
        front2.cubeList.add(new ModelBox(front2, 50, 6, -10.75F, -21.26F, -1.5F, 7, 2, 3, 0.0F, false));
        front2.cubeList.add(new ModelBox(front2, 44, 27, -11.0F, -20.75F, -1.0F, 2, 1, 2, 0.0F, false));
        front2.cubeList.add(new ModelBox(front2, 16, 44, -7.0F, -21.5F, -5.0F, 1, 2, 10, 0.0F, false));

        wheel = new ModelRenderer(this);
        wheel.setRotationPoint(-0.5F, 24.0F, -0.5F);
        wheel.cubeList.add(new ModelBox(wheel, 58, 20, -6.0F, -21.74F, -5.501F, 5, 4, 2, 0.0F, false));
        wheel.cubeList.add(new ModelBox(wheel, 44, 52, -5.0F, -18.0F, -5.5F, 3, 2, 2, 0.0F, false));

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-5.0F, -16.0F, -5.0F);
        wheel.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.0F, -0.5236F);
        cube_r1.cubeList.add(new ModelBox(cube_r1, 18, 68, 0.0F, -2.0F, -0.5F, 1, 2, 2, 0.0F, false));

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-2.0F, -16.0F, -5.0F);
        wheel.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, 0.5236F);
        cube_r2.cubeList.add(new ModelBox(cube_r2, 64, 57, -1.0F, -2.0F, -0.5F, 1, 2, 2, 0.0F, false));

        wheel2 = new ModelRenderer(this);
        wheel2.setRotationPoint(7.5F, 24.0F, -0.5F);
        wheel2.cubeList.add(new ModelBox(wheel2, 56, 47, -6.0F, -21.74F, -5.501F, 5, 4, 2, 0.0F, false));
        wheel2.cubeList.add(new ModelBox(wheel2, 0, 50, -5.0F, -18.0F, -5.5F, 3, 2, 2, 0.0F, false));

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(-5.0F, -16.0F, -5.0F);
        wheel2.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, -0.5236F);
        cube_r3.cubeList.add(new ModelBox(cube_r3, 62, 53, 0.0F, -2.0F, -0.5F, 1, 2, 2, 0.0F, false));

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(-2.0F, -16.0F, -5.0F);
        wheel2.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, 0.5236F);
        cube_r4.cubeList.add(new ModelBox(cube_r4, 64, 26, -1.0F, -2.0F, -0.5F, 1, 2, 2, 0.0F, false));

        wheel3 = new ModelRenderer(this);
        wheel3.setRotationPoint(7.5F, 24.0F, 9.5F);
        wheel3.cubeList.add(new ModelBox(wheel3, 0, 6, -6.0F, -21.74F, -5.501F, 5, 4, 2, 0.0F, false));
        wheel3.cubeList.add(new ModelBox(wheel3, 28, 49, -5.0F, -18.0F, -5.5F, 3, 2, 2, 0.0F, false));

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(-5.0F, -16.0F, -5.0F);
        wheel3.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, 0.0F, -0.5236F);
        cube_r5.cubeList.add(new ModelBox(cube_r5, 58, 26, 0.0F, -2.0F, -0.5F, 1, 2, 2, 0.0F, false));

        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(-2.0F, -16.0F, -5.0F);
        wheel3.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0F, 0.0F, 0.5236F);
        cube_r6.cubeList.add(new ModelBox(cube_r6, 62, 14, -1.0F, -2.0F, -0.5F, 1, 2, 2, 0.0F, false));

        wheel4 = new ModelRenderer(this);
        wheel4.setRotationPoint(-0.5F, 24.0F, 9.5F);
        wheel4.cubeList.add(new ModelBox(wheel4, 0, 0, -6.0F, -21.74F, -5.501F, 5, 4, 2, 0.0F, false));
        wheel4.cubeList.add(new ModelBox(wheel4, 16, 49, -5.0F, -18.0F, -5.5F, 3, 2, 2, 0.0F, false));

        cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(-5.0F, -16.0F, -5.0F);
        wheel4.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.0F, 0.0F, -0.5236F);
        cube_r7.cubeList.add(new ModelBox(cube_r7, 50, 22, 0.0F, -2.0F, -0.5F, 1, 2, 2, 0.0F, false));

        cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(-2.0F, -16.0F, -5.0F);
        wheel4.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0F, 0.0F, 0.5236F);
        cube_r8.cubeList.add(new ModelBox(cube_r8, 56, 53, -1.0F, -2.0F, -0.5F, 1, 2, 2, 0.0F, false));

        cover = new ModelRenderer(this);
        cover.setRotationPoint(0.0F, 24.0F, 0.0F);
        cover.cubeList.add(new ModelBox(cover, 44, 16, 2.0F, -22.0F, -7.0F, 4, 4, 2, 0.0F, false));
        cover.cubeList.add(new ModelBox(cover, 68, 34, -6.0F, -22.0F, -7.0F, 7, 4, 2, 0.0F, false));

        cover2 = new ModelRenderer(this);
        cover2.setRotationPoint(0.0F, 24.0F, 12.0F);
        cover2.cubeList.add(new ModelBox(cover2, 0, 44, 2.0F, -22.0F, -7.0F, 4, 4, 2, 0.0F, false));
        cover2.cubeList.add(new ModelBox(cover2, 0, 68, -6.0F, -22.0F, -7.0F, 7, 4, 2, 0.0F, false));

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
        bb_main.cubeList.add(new ModelBox(bb_main, 28, 47, 8.0F, -35.0F, -6.0F, 2, 12, 12, 0.0F, false));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 44, -10.0F, -35.0F, -6.0F, 2, 12, 12, 0.0F, false));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 30, -10.0F, -35.0F, -8.0F, 20, 12, 2, 0.0F, false));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 16, -10.0F, -35.0F, 6.0F, 20, 12, 2, 0.0F, false));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -9.0F, -23.26F, -7.0F, 18, 2, 14, 0.0F, false));
        bb_main.cubeList.add(new ModelBox(bb_main, 56, 47, 8.999F, -23.26F, -7.0F, 1, 2, 14, 0.0F, false));
        bb_main.cubeList.add(new ModelBox(bb_main, 42, 16, -9.999F, -23.26F, -7.0F, 1, 2, 14, 0.0F, false));
        bb_main.cubeList.add(new ModelBox(bb_main, 34, 34, -6.0F, -21.5F, -5.0F, 12, 3, 10, 0.0F, false));

        cube_r9 = new ModelRenderer(this);
        cube_r9.setRotationPoint(0.0F, -23.0F, 8.0F);
        bb_main.addChild(cube_r9);
        setRotationAngle(cube_r9, -0.5236F, 0.0F, 0.0F);
        cube_r9.cubeList.add(new ModelBox(cube_r9, 50, 0, -10.001F, 0.0F, -1.0F, 20, 2, 1, 0.0F, false));

        cube_r10 = new ModelRenderer(this);
        cube_r10.setRotationPoint(0.0F, -23.0F, -8.0F);
        bb_main.addChild(cube_r10);
        setRotationAngle(cube_r10, 0.5236F, 0.0F, 0.0F);
        cube_r10.cubeList.add(new ModelBox(cube_r10, 50, 3, -10.001F, 0.0F, 0.0F, 20, 2, 1, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        front.render(f5);
        front2.render(f5);
        wheel.render(f5);
        wheel2.render(f5);
        wheel3.render(f5);
        wheel4.render(f5);
        cover.render(f5);
        cover2.render(f5);
        bb_main.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
