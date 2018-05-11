 //TODO:Output should have first line stat for stocks, 2nd line for ETF, 3rd line stock+ETF

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

for (int i = 0; i < 4; i++) {//test all combinations of loss and target
for (int j = 0; j < 4; j++) {

Simulator sim = new Simulator(path, "Stocks.txt", loss[i], target[j]);
sim.run();

tradeArray Tr1 = sim.getTrades();

//print the stats; put the company symbol too
System.out.println("|STOCKS|" + "Loss:"+loss[i] + ", " +"Target:"+target[j] +","+ Tr1.getStats().toString());



Simulator simETF = new Simulator(path, "ETFs.txt", loss[i], target[j]);
simETF.run();

tradeArray Tr2 = simETF.getTrades();
//prints stats for ETF
System.out.println("| ETF |" +"Loss:"+loss[i] + ", " + "Target:"+target[j] + "," + Tr2.getStats().toString());

//display the stats for these parameters loss[i], target [j]
Tr1.addArray(Tr2);
System.out.println("| STOCKS+ETF |"+ "Loss:" +loss[i]+ ", " + "Target:"+target[j] + Tr1.getStats().toString());

	}
}  


	}

}

