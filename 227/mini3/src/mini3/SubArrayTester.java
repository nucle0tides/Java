package mini3;

public class SubArrayTester {
	public static void main(String[] args)
	{
		int width = 7;
		int height = 6;
		
		int[][] test3 = new int[height][width];
		
		for(int i = 0; i < height; i++)
		{
			for(int j =0; j < width; j++)
			{
				test3[i][j] = i*width+1+j;
				//System.out.print(i*width+1+j+",");
			}
			//System.out.println();
		}
		
		int[][] output = GridUtil.getSubArray(test3, 0, 2, 1, false);
		ITransform transexual = new SmoothingTransform(1);
		
		System.out.println(transexual.apply(output));
		
		for(int i = 0; i < output.length; i++)
		{
			for(int j =0; j < output[0].length; j++)
			{
				System.out.print(output[i][j]+",");
			}
			System.out.println();
		}
		
			
	}
}
