import java.io.File; 
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.*;
public class bigMain {

public static void main(String[] args) {
double[] target = new double[4];
target[0] = 0.01;
target[1] = 0.02;
target[2] = 0.05;
target[3] = 0.1;
double[] loss = new double[4];
loss[0] = 0.01;
loss[1] = 0.02;
loss[2] = 0.05;
loss[3] = 0.1;

String path = "/Users/jorgebarrameda/Desktop/Data/";

for (int i = 0; i < 4; i++) {
for (int j = 0; j < 4; j++) {

Simulator sim = new Simulator(path, "Stocks.txt", loss[i], target[j]);
sim.run();
Simulator simETF = new Simulator(path, "ETFs.txt", loss[i], target[j]);
simETF.run();

tradeArray Tr1 = sim.getTrades();
tradeArray Tr2 = simETF.getTrades();

System.out.println("|STOCKS|" + "\tLoss:"+loss[i] + "\tTarget:"+target[j] +"\t"+Tr1.getStats().toString());
System.out.println();


System.out.println("|ETF|" +"\t\tLoss:"+loss[i] + "\tTarget:"+target[j] +"\t"+ " "+Tr2.getStats().toString());
System.out.println();

Tr1.addArray(Tr2);
System.out.println("|STOCKS+ETF|"+ "\tLoss:" +loss[i]+"\tTarget:"+target[j] + "\t"+Tr1.getStats().toString());
System.out.println();


	}
}  



//Write Stocks to CSV
PrintWriter pw = null;
StringBuilder builder = new StringBuilder();
try {
	pw = new PrintWriter(new File("Stock.csv"));
}catch(FileNotFoundException e) {
	e.printStackTrace();
}


String Collist = "Loss," + " "+ "Target,"+"NumTrades,"+ "Win%,"+ "AvrgPL%," +"LongWin%,"+"NumShort,"+"Shortwin%,"+"AvrgProfitShort,"+"AvrgProfitLong,"+"HoldPeriod,";
builder.append(Collist + "\n");

for(int k =0; k <  4; k++) {
for(int z=0;  z  <  4; z++) {

Simulator sim = new Simulator(path, "Stocks.txt", loss[k], target[z]);
sim.run();
//Simulator simETF = new Simulator(path, "ETFs.txt", loss[k], target[z]);
//simETF.run();


tradeArray Tr = sim.getTrades();



builder.append(loss[k] + ","+ target[z]+","+Tr.getStats().toString2());
builder.append('\n');
	}
}
pw.write(builder.toString());
pw.flush();
pw.close();


//Write ETF to CSV
PrintWriter pw2 = null;
StringBuilder builder2 = new StringBuilder();
try {
	pw2 = new PrintWriter(new File("ETF.csv"));
}catch(FileNotFoundException e) {
	e.printStackTrace();
}

String Collist2 = "Loss," + "Target,"+"NumTrades,"+ "Win%,"+ "AvrgPL%," +"LongWin%,"+"NumShort,"+"Shortwin%,"+"AvrgProfitShort,"+"AvrgProfitLong,"+"HoldPeriod,";
builder2.append(Collist2 + "\n");

for(int k =0; k <  4; k++) {
for(int z=0;  z  <  4; z++) {


Simulator simETF = new Simulator(path, "ETFs.txt", loss[k], target[z]);
simETF.run();

tradeArray Tr1 = simETF.getTrades();


builder2.append(loss[k] + ","+ target[z]+","+Tr1.getStats().toString2());
builder2.append('\n');
	}
}
pw2.write(builder2.toString());
pw2.flush();
pw2.close();



//Write Stocks + ETF both
PrintWriter pw3 = null;
StringBuilder builder3 = new StringBuilder();
try {
	pw3 = new PrintWriter(new File("Stocks+ETF.csv"));
}catch(FileNotFoundException e) {
	e.printStackTrace();
}

String Collist3 = "Loss," + "Target,"+"NumTrades,"+ "Win%,"+ "AvrgPL%," +"LongWin%,"+"NumShort,"+"Shortwin%,"+"AvrgProfitShort,"+"AvrgProfitLong,"+"HoldPeriod,";
builder3.append(Collist3 + "\n");

for(int k =0; k <  4; k++) {
for(int z=0;  z  <  4; z++) {

Simulator sim = new Simulator(path, "Stocks.txt", loss[k], target[z]);
sim.run();
Simulator simETF = new Simulator(path, "ETFs.txt", loss[k], target[z]);
simETF.run();

tradeArray Tr1 = sim.getTrades();
tradeArray Tr2 = simETF.getTrades();

Tr1.addArray(Tr2);

builder3.append(loss[k] + ","+ target[z]+","+Tr1.getStats().toString2());
builder3.append('\n');
	}
}
pw3.write(builder3.toString());
pw3.flush();
pw3.close();




/*IGNORE: THIS IS NOT PART OF PROJECT
//Encription Test 
String passcode = EnDecriptor.Encriptor("Mucho dinero en la Computer Science"+ " " +"Chcę jeść dobre polskie jedzenie");
//System.out.println(passcode);

System.out.println(EnDecriptor.Decriptor(passcode)); 

*/






	}

}

