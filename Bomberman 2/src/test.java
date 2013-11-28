
public class test {

	public static void main(String[] args) {
		Point start,dest;
		start = new Point(2,2);
		dest = new Point(1,2);
		
		double elapsedTime = (double) 1000l;
		double stepTime = 2000;
		double compTime, p;
		int offsetX;
		
		double weg = (dest.getX() - start.getX());
		System.out.println("weg: "+weg);
		
		compTime = weg*stepTime;
		if (compTime<0.0) compTime *= -1;
		System.out.println("ganze zeit: "+compTime);
		
		if (compTime!=0) { 
			p = (elapsedTime/compTime);
			System.out.println("p: "+p);
			offsetX = (int) (-1*(weg*32-p * weg*32));
		} else {
			System.out.println("division durch 0 verhindert!");
			offsetX = 0;
		}
		System.out.println("offsetX: "+offsetX);
	}
}
