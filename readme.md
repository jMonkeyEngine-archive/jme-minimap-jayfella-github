JME-Minimap
===

A minimap state for jMonkeyEngine

See the [main class](https://github.com/jayfella/jme-minimap/blob/master/src/main/java/com/jayfella/minimap/Main.java) for a fully working prototype.


```java

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

        // create the minimap

        // The height of the minimap camera. Usually slightly higher than your world height.
        // the higher up, the more "zoomed out" it will be (and thus display more).
        float height = 64;
        int size = 200; // the size of the minimap in pixels.

        MiniMapState miniMapState = new MiniMapState(rootNode, height, size);
        stateManager.attach(miniMapState);
    }

  
}

```