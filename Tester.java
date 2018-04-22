import java.io.*;
import java.util.*;
public class Tester {
	public static void main(String[] args) {
		//Create a bar by passing a string in the form d, o, h, l, c, ac, v

		String l = "2006-01-03,211.471466,218.053055,209.319321,217.832840,217.832840,26216100";
		Bar b = new Bar(l);
		//display it. 
		System.out.println(b.toString());
		b = new Bar("01/03/2006,211.471466,218.053055,209.319321,217.832840,217.832840,26216100");
		//display it. 
		System.out.println(b.toString());
		b = new Bar("2006-01-03,218.053055,209.319321,217.832840,217.832840,26216100");
		//display it. 
		
		b = new Bar("2006:01:03,211.471466,218.053055,209.319321,217.832840,217.832840,26216100");
		//display it. 
		//Using values
		Date d = new Date();
		b = new Bar(d, 211.471466,218.053055,209.319321,217.832840,217.832840,26216100);
		//display it. 
		System.out.println(b.toString());
		
		//how to use files USING SCANNER
		

		// Stats newStat = new  Stats(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13.3, 14.4, 15.5, 16.6, 17.7, 18.8); 
		//System.out.println("WINNERS: ");

		//System.out.println(newStat.toString());
	
	}
}


	