package util;

public class Point2D {
	public int x;
	public int y;
	
	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "[" + Integer.toString(x) + "," + Integer.toString(y) + "]";
	}
}
