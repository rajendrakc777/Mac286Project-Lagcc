import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Simulator {
private String mPath;
private String mFileName;
private double loss, target;
private tradeArray mTrades;

	
public Simulator(String p, String name, double l, double t) {
mPath = p;
mFileName = name;
loss = l;
target = t; 
mTrades = new tradeArray(4000); 
}

public tradeArray getTrades() {
	return mTrades;
}

public void run() {

String fileName = mPath + mFileName;

File myfile = new File(fileName);
if (!myfile.exists()) {
	System.out.println("file does not exist " + fileName);
	System.exit(1);
}
try {
FileReader fr = new FileReader(fileName);

BufferedReader br = new BufferedReader(fr);
String symbol;

	while ((symbol = br.readLine()) != null) {
		symbolTester tester = new symbolTester(symbol, mPath, loss, target);
	
		if (tester.test()) {
			mTrades.addArray(tester.getTrades());
		} else {
			System.out.println("Error testing  " + symbol);
			}
		}
		br.close();
	} catch (IOException e) {
		e.printStackTrace();
		System.exit(1);
	}
}
}