import java.util.Scanner;
import java.util.ArrayList;
public class SetNumberCounter {
	public static void main(String[] args) {
		Scanner keyboard= new Scanner(System.in);
		for(int i=0;keyboard.hasNextLine();i++) {
			String s=keyboard.nextLine();
			String[] str=s.split(" ");
			//assign input values into String array str
			for(int j=0; j<str.length; j++) {
				//go through string array str
				System.out.println("set "+j);
				for(int l=0;l<10;l++) {
					int counter=0;
					String number=Integer.toString(l);
					for(int k=0;k<str[j].length();k++) {
						if(number.equals(Character.toString(str[j].charAt(k)))) {
							counter++;
						}
					}
					System.out.println("Number of time "+ l+" Occurs: "+ counter);
				}			
			}
		}		
	}
}

