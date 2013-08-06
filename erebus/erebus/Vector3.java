package erebus;

public class Vector3 
{
	public int x;
	public int y;
	public int z;
	
	public Vector3(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String toString()
	{
		return x + ", " + y + ", " + z;
	}
}