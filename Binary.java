import java.util.Scanner;
public class Binary {

	// Returns decimal representation of given binary number.
	public int toDecimal(String b) {
		int[] count=new int[b.length()];
		int counter=0;
		for(int i=0;i<b.length();i++) {
			count[i]=(int)Math.pow(2,i );
		}
		for(int u=0;u<count.length;u++) {
			counter=counter+count[u]*Character.getNumericValue(b.charAt(b.length()-u-1));
		}
		return counter;
	}
	// Returns whether or not given string is a binary number.
	public boolean isBinary(String b) {
		for(int i=0;i<b.length();i++) {
	
			//if(Character.getNumericValue(b.charAt(i))!=0||Character.getNumericValue(b.charAt(i))!=1) {
			if(b.charAt(i)!='1'&&b.charAt(i)!='0') {
				// not 1 and not 0, false
				//1 or 0, true
				return false;
				
			}
		}
		return true;
		
	}

	public static void main(String[] args) {
		Binary converter=new Binary();
		String a = null;
		System.out.print("Enter binary: ");
		Scanner input=new Scanner(System.in);
		a=input.nextLine();
		System.out.println("");
		if(!converter.isBinary(a)) {
			System.out.println("Not binary!");
		}else {
			System.out.println(converter.toDecimal(a)+" in decimal");
		}
		
	}
}