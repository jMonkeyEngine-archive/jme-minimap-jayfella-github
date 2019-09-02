package com.jayfella.minimap;

import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingSphere;
import com.jme3.light.LightProbe;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

public class Main extends SimpleApplication {

    public static void main(String[] args) {

        Main app = new Main();

        AppSettings settings = new AppSettings(true);
        settings.setTitle("MiniMap Example");
        app.setSettings(settings);

        app.start();

    }

    @Override
    public void simpleInitApp() {

        flyCam.setMoveSpeed(10);
        viewPort.setBackgroundColor(new ColorRGBA(0.5f, 0.6f, 0.7f, 1.0f));

        // Create a scene to explore. We will also pass this scene to the minimap, though you can pass any node to it.
        // In some games you may want to pass a simpler version to the minimap. For this example, we won't bother.

        Spatial probeModel = assetManager.loadModel("Light/defaultProbe.j3o");
        LightProbe lightProbe = (LightProbe) probeModel.getLocalLightList().get(0);
        lightProbe.setBounds(new BoundingSphere(500, new Vector3f(0, 0, 0)));

        rootNode.addLight(lightProbe);

        for (int i = 0; i < 100; i++) {
            Geometry geom = createGeometry();
            geom.setLocalTranslation(createRandomCoord(500), 0, createRandomCoord(50));
            rootNode.attachChild(geom);
        }

        // create the minimap

        // The height of the minimap camera. Usually slightly higher than your world height.
        // the higher up, the more "zoomed out" it will be (and thus display more).
        float height = 64;
        int size = 200; // the size of the minimap in pixels.

        MiniMapState miniMapState = new MiniMapState(rootNode, height, size);
        stateManager.attach(miniMapState);
    }

    private Geometry createGeometry() {

        Material mat = new Material(assetManager, "Common/MatDefs/Light/PBRLighting.j3md");
        mat.setColor("BaseColor", createRandomColor());
        mat.setFloat("Roughness", FastMath.nextRandomFloat());
        mat.setFloat("Metallic", 0.01f);

        Box b = new Box(
                0.3f + FastMath.nextRandomFloat() * 3,
                0.3f + FastMath.nextRandomFloat() * 2,
                0.3f + FastMath.nextRandomFloat() * 3
        );

        Geometry geom = new Geometry("Box", b);
        geom.setMaterial(mat);

        return geom;
    }

    private float createRandomCoord(float dist) {
        return -dist + (FastMath.nextRandomFloat() * (dist * 2));
    }

    private ColorRGBA createRandomColor() {
        return new ColorRGBA(FastMath.nextRandomFloat(), FastMath.nextRandomFloat(), FastMath.nextRandomFloat(), 1.0F);
    }

}