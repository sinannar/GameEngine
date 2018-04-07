package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import textures.ModelTexture;


//test commit from another computer
public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		RawModel model2 = OBJLoader.loadObjModel("stall", loader);
		
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("white")));
		TexturedModel staticModel2 = new TexturedModel(model2,new ModelTexture(loader.loadTexture("stallTexture")));
		Light light = new Light(new Vector3f(0,0,-20),new Vector3f(1,1,1));
		Camera camera = new Camera();
		
		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random();
		for (int i = 0; i < 15; i++) {
			float x = random.nextFloat() * 100 - 50;
			float y = random.nextFloat() * 100 - 50;
			float z = random.nextFloat() * -300;
			float rotx = random.nextFloat() * 180f;
			float roty = random.nextFloat() * 180f;
			
			entities.add(new Entity(staticModel, new Vector3f(x,y,z), rotx,roty,0f,1f));
		}
		
		for (int i = 0; i < 15; i++) {
			float x = random.nextFloat() * 100 - 50;
			float y = random.nextFloat() * 100 - 50;
			float z = random.nextFloat() * -300;
			float rotx = random.nextFloat() * 180f;
			float roty = random.nextFloat() * 180f;
			
			entities.add(new Entity(staticModel2, new Vector3f(x,y,z), rotx,roty,0f,1f));
		}
		
		MasterRenderer renderer = new MasterRenderer();
		
		while(!Display.isCloseRequested()){
			camera.move();
			
			for(Entity entity:entities)
				renderer.processEntity(entity);
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();			
		}
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
