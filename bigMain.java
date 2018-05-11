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

tradeArray Tr = sim.getTrades();

//print the stats; put the company symbol too
System.out.println("|STOCKS|" + "Loss:"+loss[i] + ", " +"Target:"+target[j] +","+ Tr.getStats().toString());

//display trades data
//System.out.println(Tr.toString());

Simulator simETF = new Simulator(path, "ETFs.txt", loss[i], target[j]);
simETF.run();

Tr = simETF.getTrades();
//prints stats for ETF
System.out.println("| ETF |" +"Loss:"+loss[i] + ", " + "Target:"+target[j] + "," + Tr.getStats().toString());
				
	}
}

//TODO: Run Simulator for Stocks, ETFs separately and check output if stats are correct.
//then run Stocks + ETF together and check output for stats.
//Simulator sim = new Simulator(path, "Stocks.txt", 0.0, 0.0);
//sim.run();

//display the stats for these parameters loss[i], target[j]
//tradeArray Tr = sim.getTrades();

//print the stats
//System.out.println("Loss:"+0.1 + ", " +"Target:" +0.01 + Tr.getStats().toString());

//Simulator simETF = new Simulator(path, "ETFs.txt", 0.0, 0.0);
//simETF.run();

//display the stats for these parameters loss[i], target[j]
//tradeArray Tr1 = simETF.getTrades();

//print the stats
//System.out.println(0.1 + ", " + 0.01 + Tr1.getStats().toString());

//Print stats for the combination add the trades together
//Tr.addArray(Tr1);
//System.out.println(0.1 + ", " + 0.01 + Tr.getStats().toString());
	}

}

