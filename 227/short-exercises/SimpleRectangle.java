package rectangles;

//defining our own rectangle class. Written during lecture. 

public class SimpleRectangle { 

	//Instance variables 
	private int width; 
	private int height; 

	//Contstuctors 
	public SimpleRectangle(int givenWidth, int givenHeight) { 
		width = givenWidth; 
		height = givenHeight; 
	}

	//Methods 

	//Accessors
	public int getWidth() { 
		return width; 
	}

	public int getHeight() { 
		return height; 
	}

	public int getArea() { 
		int area = width * height; 
		return(area); 
	}

	//Mutator 
	public void setSize(int newWidth, int newHeight) { 
		width = newWidth; 
		height = newHeight; 
	}

	public void grow(int factor) { 
		int newHeight = factor * height; 
		int newWidth = factor * height; 
		
		width = newWidth; 
		height = newHeight; 
	}
}
