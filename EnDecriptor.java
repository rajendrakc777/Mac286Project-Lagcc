import java.io.*;



//Encryption Bitshifter 
public class EnDecriptor {

//create chain for complexity
private static final int[] chain ={
//generate random #s and each char will be associated with these random #s
142, 52
	
};
	
public static String Encriptor(String key) {
	String res = " ";
	int length = key.length();
	char ch;

	int ck = 0;//counter for char key
	
	//Iterate thru length of a string and save each char
	for(int i=0; i < length; i++){
		//preventing outofBounce error in array
		if(ck >= chain.length - 1)
			ck = 0;
		
		ch = key.charAt(i);
		
		//ch += 10; //simple method:shift char 10 unicode places. Note:Generate a more complex formula for better security
		
		ch += chain[ck];//chaining char
		ck++; //iterate current key char in the string
		
		res += ch;
	}
	
	return res;
}

//Decripting method, reverse the formula; generated in Encription
public static String Decriptor(String key) {
	String res = " ";
	int length = key.length();
	char ch;
	int ck = 0;
	
	//Iterate thru length of a string and save each char
	for(int i=0; i < length; i++){
		if(ck >= chain.length - 1)
			ck = 0;
		ch = key.charAt(i);
		
		//ch -= 10; //reverse formula by subtraction
		
		ch -= chain[ck];
		res += ch;
		ck++;
	}
	
	return res;
}

}
