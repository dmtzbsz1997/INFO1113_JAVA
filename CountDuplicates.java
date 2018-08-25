import java.util.ArrayList;
import java.util.Scanner;
public class CountDuplicates {
	// turn array into arrayList 
	// once duplicates found, turn it into -1
	// counting the number of non-minus-one value in the list
	public static int countDuplicates(int[] a) {
		int count=0;
		ArrayList<Integer> arr= new ArrayList();
		if(a==null) {
			return 0;
		}
		for(int i=0;i<a.length;i++){
			arr.add(a[i]);
		}
		for(int i=0;i<arr.size();i++){
			boolean checker=false;
			for(int u=i+1;u<arr.size();u++){
				if(arr.get(i)==arr.get(u)){
					arr.set(u,-1);
					checker=true;
				}
			}
			if(checker==false) {
				arr.set(i, -1);
			}
		}
		for(int i=0;i<a.length;i++){
			if(arr.get(i)!=-1) {
				count++;
			}
		}
		System.out.println(arr);
		return count;
	}
	
	public static void main(String[] args) {
		
		// testing integer array!
		Scanner input=new Scanner(System.in);
		System.out.println("input integer array, seperate with whitespace: ");
		String a=input.nextLine();
		String[] str= a.split(" ");
		int[] arr=new int[str.length];
		for(int i=0; i<str.length;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		System.out.println( countDuplicates(arr) );	
	}
	
}
			
	
	

