package Spill;
import java.io.*;
import java.util.Scanner;

/**
 * Test program for the Spill algorithms.
 * @author Sophie Quigley
 * @author Noel Kocheril
 * 
 */
public class Test {

	/**
	 * 
	 * @param args the command line arguments
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		TestSpill3();
		//        Scanner in = new Scanner(System.in);
		//        TestSpill1();
		//  TestSpill2(in);
	}

	/**
	 * Tests the Spill function on randomly generated surfaces.
	 */
	private static void TestSpill1 () {
		int increment = 5;
		for (int percent=0; percent<=200; percent +=increment, increment*=2) {
			Grid surface = new Grid(10,20,percent);
			System.out.println("10X20 grid with " + percent + " percent obstacles:");
			System.out.println(surface);
			System.out.println("Spill of strength 5 at (5,5)");
			surface.Spill(5, 5, 5);
			System.out.println(surface);
		}
	}
	/**
	 * Tests the Spill function as specified in the input.
	 * Input first reads a Grid
	 * Then the following line specifying the spill:
	 * strength row col 
	 * @param in  Scanner (standard input)
	 */
	private static void TestSpill2(Scanner in) {
		Grid surface = new Grid(in);
		System.out.println("Input Grid:");
		System.out.println(surface);
		int strength = in.nextInt();
		int row = in.nextInt();
		int col = in.nextInt();
		System.out.println("Spill of strength " + strength + " at (" + row + "," + col +")");
		surface.Spill(row,col,strength);
		System.out.println(surface);
	}

	private static void TestSpill3() throws UnsupportedEncodingException, FileNotFoundException, IOException {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("out1.csv", true));
			bw.write("Spill Test 1 - Slow");
			bw.newLine();
			bw.write("Grid Size, i, Nano Seconds");
			bw.newLine();
			bw.flush();
		} catch (IOException ioe){
			ioe.printStackTrace();
		} finally {
			if(bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				ioe2.printStackTrace();
			}
		}
		int i = 2;
		while(true) {
			Grid surface = new Grid(2*(i)-1,2*(i) - 1,0);
			int size = 2*i-1;
			long t1 =  System.nanoTime();
			//			System.out.printf("%dX%d grid with No Obstacles:\n", 2*i-1, 2*i-1);
			//			System.out.println(surface);
			//			System.out.printf("Spill of strength %d at (%d,%d)", i,i,i);
			surface.Spill(i, i, i);
			//			System.out.println(surface);
			long t2 = System.nanoTime();
			try {
				bw = new BufferedWriter(new FileWriter("out1.csv", true));
				bw.write(Integer.toString(2*i-1)+"X"+Integer.toString(2*i-1) + ", "+Integer.toString(i)+", "+ Long.toString(t2-t1));
				bw.newLine();
				bw.flush();
				System.out.println(i);
//				System.out.println(surface);
			} catch (IOException ioe){
				ioe.printStackTrace();
			} finally {
				if(bw != null) try {
					bw.close();
				} catch (IOException ioe2) {
					ioe2.printStackTrace();
				}
			}
			i++;
		}

	}

}