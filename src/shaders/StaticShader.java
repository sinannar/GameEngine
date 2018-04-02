package shaders;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "src/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/shaders/fragmentShader.txt";

	private int location_transformationmatrix;
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationmatrix = super.getUniformLocation("transformationMatrix");		
	}
	
	public void loadTransformationmatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_transformationmatrix, matrix);
	}

}
