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


	}

}

