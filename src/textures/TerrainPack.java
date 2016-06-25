package textures;

public class TerrainPack {

	private TerrainTexture back;
	private TerrainTexture r;
	private TerrainTexture g;
	private TerrainTexture b;

	public TerrainPack(TerrainTexture back, TerrainTexture r, TerrainTexture g, TerrainTexture b) {
		this.back = back;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public TerrainTexture getBack() {
		return back;
	}

	public TerrainTexture getR() {
		return r;
	}

	public TerrainTexture getG() {
		return g;
	}

	public TerrainTexture getB() {
		return b;
	}

}
