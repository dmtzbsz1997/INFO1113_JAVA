import java.util.Scanner;
public class GoldenRatio {
	public static void main(String[] args) {
		try{
			Scanner input=new Scanner(System.in);
			System.out.print("Enter two numbers: ");
			String b=input.nextLine();
			String a[]= b.split(" ");		
			double c= Double.parseDouble(a[0]);
			double d= Double.parseDouble(a[1]);
			double e=(c/d);
			double f=(d/c);
			e=(double)Math.round(e * 1000d) / 1000d;
			f=(double)Math.round(f * 1000d) / 1000d;
			// round to 4 decimial places
			if((e)==1.618|(f)==1.618){						
			System.out.println("\nGolden ratio!");
			}else{
				System.out.println("\nMaybe next time.");
			}				}
		catch(Exception  e){
				System.out.println("\nInvalid input.");
		}
	}
}

