import java.util.*;
import java.io.*;

//
public class symbolTester {
	//member variables
	private String mPath, mSymbol;
	private double mLoss, mTarget;
	private tradeArray mTrades;
	private barArray mData;

	public symbolTester(String symbol, String path, double loss, double target) {
		mPath = path; //first 
		mSymbol = symbol;//first BAC
		mLoss = loss; //first 0.01
		mTarget = target;//first = 0.01
		mTrades = new tradeArray(200, 100);
		mData = new barArray(4000);
	}

	public tradeArray getTrades() {
		return mTrades;
	}
//This method will scan the previous 60th days of High's and Low's
	private boolean pattern(int index) {
		double prevHigh = mData.elementAt(index).High();
		double prevLow = mData.elementAt(index).Low();
		 	for (int j = 60; j > 0 ;j--) {
		 		boolean pH = (prevHigh >= mData.elementAt(j-1).High());
		 		boolean pL = (prevLow <= mData.elementAt(j-1).Low());
				if(!pH && !pL ) {
					return false;
				}
				index--;
			}
		
		return true; //or false
	}

	public boolean test() {
		//load the data into mData
		if (!load()) {
			return false;
		}
		//Now you have your data in mData mData.elementAt(0) is the oldest bar
		mData.elementAt(0).Close();

		//go through mData bar by bar
		int size = mData.size();
		for (int i = 60; i < size; i++) {
			mData.elementAt(i);
		}
		//if pattern found (make a private method pattern(i) where i is the index of the 
		//current bar

		//create a trade with appropriate values
		Trade t1 = new Trade();
		t1.open("BAC", t1.getEntryDate(), 1.0, 1.0, 0.01, Direction.LONG);
		//check the outcome of the trade, and close it 
		//outcome(Trade T, int index); 
		t1.close(t1.getExitDate(), 0.1);

		//insert the trade into mTrades.
		return true;
	}

	private boolean load() { //HW for the break
		//build a file with path name using mPath and mSymbol

		String fileName = mPath + mSymbol + "_Daily.csv";
		// first String fileName = "C:/ProfOmar286/Data/" + "BAC" +"_Daily.csv";
		//check if the file exists (use FILE object)
		File myfile = new File(fileName);
		if (!myfile.exists()) {
			System.out.println("file does not exist");
			return false;
		}
		try {
			FileReader fr = new FileReader(fileName);
			//open the file FileReader->BufferedReader
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();//discard this line
			while ((line = br.readLine()) != null) {
				Bar b = new Bar(line);
				mData.insert(b);
			}
			br.close();
			return true;
		} catch (IOException e) {
			System.out.println(e.toString());
			return false;
		}
	}

}