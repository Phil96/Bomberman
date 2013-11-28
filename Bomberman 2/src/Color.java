import static org.lwjgl.opengl.GL11.glColor3f;


public class Color {
	
	private float r,g,b;
	
	public Color(float r, float g, float b) {
		setR(r);
		setG(g);
		setB(b);
	}
	
	public void activate() {
		glColor3f(r,g,b);
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		if (r >= 0.0f && r <= 1.0f) this.r = r;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		if (g >= 0.0f && g <= 1.0f) this.g = g;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		if (b >= 0.0f && b <= 1.0f) this.b = b;
	}
	
}
