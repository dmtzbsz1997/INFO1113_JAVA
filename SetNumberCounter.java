import java.util.Scanner;
import java.util.ArrayList;
public class SetNumberCounter {
	public static void main(String[] args) {
		Scanner keyboard= new Scanner(System.in);
		try {
			for(int i=0;keyboard.hasNextLine();i++) {
				String s=keyboard.nextLine();
				String[] str=s.split(" ");
				for(int j=0; j<str.length; j++) {
					System.out.println("set "+j);
					for(int l=0;l<10;l++) {
						int counter=0;
						String number=""+l;
						for(int k=0;k<str[j].length();k++) {
							if(number.equals(""+str[j].charAt(k))) {
								counter++;
							}
						}
						System.out.println("Number of time "+ l+" Occurs: "+ counter);
					}	
				}
				
			}
		}catch(Exception e) {
			System.out.println("Opps, Wrong input");
		}		
	}
}

