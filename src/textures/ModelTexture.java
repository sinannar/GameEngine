package textures;

import org.lwjgl.input.Keyboard;

public class ModelTexture {

	private int textureID;
	
	
	private float shineDamper = 1;
	private float reflectivity = 1;
	
	public ModelTexture(int id) {
		this.textureID = id;
	}
	
	public int GetID()
	{
		return this.textureID;
	}

	public float getShineDamper() {
		return shineDamper;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	public float getReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}	
	
	public void setLightResource()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9))
		{
			shineDamper += 0.1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6))
		{
			shineDamper -= 0.1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7))
		{
			reflectivity += 0.1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4))
		{
			reflectivity -= 0.1f;
		}
	}
}
