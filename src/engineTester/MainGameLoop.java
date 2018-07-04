package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import objConverter.*;

//test commit from another computer
public class MainGameLoop {

	public static void main(String[] args) {
		

		DisplayManager.createDisplay();
        Loader loader = new Loader();	         
	    
        //RawModel model = OBJLoader.loadObjModel("tree", loader);
        ModelData data = OBJFileLoader.loadOBJ("tree");
        RawModel treeModel = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
         
        /* TEXTURE PACK START */
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
        
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
        /* TEXTURE PACK ENDS  */
        
        TexturedModel staticModel = new TexturedModel(treeModel,new ModelTexture(loader.loadTexture("tree")));
        TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader),new ModelTexture(loader.loadTexture("grassTexture")));
        TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern", loader),new ModelTexture(loader.loadTexture("fern")));
        TexturedModel bunny = new TexturedModel(OBJLoader.loadObjModel("bunny", loader),new ModelTexture(loader.loadTexture("tree")));
        TexturedModel stall = new TexturedModel(OBJLoader.loadObjModel("stall", loader),new ModelTexture(loader.loadTexture("stallTexture")));

        TexturedModel playerModel = new TexturedModel(OBJLoader.loadObjModel("person", loader),new ModelTexture(loader.loadTexture("playerTexture")));

        grass.getTexture().setHasTransparency(true);
        fern.getTexture().setHasTransparency(true);
        grass.getTexture().setUseFakeLightning(true);
        fern.getTexture().setUseFakeLightning(true);
        
        List<Entity> entities = new ArrayList<Entity>();
        Random random = new Random();
        
        for(int i=0;i<200;i++){
            entities.add(new Entity(staticModel, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,3));
            entities.add(new Entity(grass, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,1));
            entities.add(new Entity(fern, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,0.6f));
            entities.add(new Entity(bunny, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,0.6f));
            entities.add(new Entity(stall, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,0.6f));
        }
         
        Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));
         
        Terrain terrain = new Terrain(-1,-1,loader,texturePack,blendMap);
        Terrain terrain2 = new Terrain(0,-1,loader,texturePack,blendMap);
        Terrain terrain3 = new Terrain(-1,0,loader,texturePack,blendMap);
        Terrain terrain4 = new Terrain(0,0,loader,texturePack,blendMap);
         
        MasterRenderer renderer = new MasterRenderer();
         
        Player player = new Player ( playerModel, new Vector3f(),0,0,0,1);
        Camera camera = new Camera(player);   
        
        while(!Display.isCloseRequested()){
            camera.move();
             
            player.move();
            renderer.processEntity(player);
            
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            renderer.processTerrain(terrain3);
            renderer.processTerrain(terrain4);
            for(Entity entity:entities){
                //renderer.processEntity(entity);
            }
            renderer.render(light, camera);
            DisplayManager.updateDisplay();
        }
 
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

	}

}
