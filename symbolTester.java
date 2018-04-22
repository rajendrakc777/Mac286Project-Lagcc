import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class symbolTester {
	private String mPath, mSymbol;
	private double mLoss, mTarget;
	private tradeArray mTrades;
	private barArray mData;

	public symbolTester(String symbol, String path, double loss, double target) {
		mPath = path;
		mSymbol = symbol;
		mLoss = loss;
		mTarget = target;
		mTrades = new tradeArray(200, 100);
		mData = new barArray(4000);
	}

	public tradeArray getTrades() {
		return mTrades;
	}

	//This method will scan the previous 60th days of High's and Low's
	//This is what "60 day high" means in pattern pic
	private boolean pattern(int index) {
		double currentHigh = mData.elementAt(index).High();//current index
		double currentLow = mData.elementAt(index).Low();

		for (int j = 60; j > 0; j--) {
			boolean pH = (currentHigh >= mData.elementAt(index - j).High());
			//	boolean pL = (currentLow <= mData.elementAt(index - j).Low());
			if (!pH) {
				return false;
			}
		}

		// By using Math.min(index + 2, mData.size()) we make sure
		// we never go beyond the amount of data we have
		// Ex: If mData has 100 elements, and index = 99,
		// Attempting to access element 100 and 101 will crash the program
		// Math.min(99 + 1, 100) will return 100, making sure that
		// i, which now equals (99 + 1), fails the comparison
		// 100 < 100 (false)
		// so the code doesn't execute
		for (int i = index + 1; i < Math.min(index + 2, mData.size()); i++) {
			if (currentHigh <= mData.elementAt(i).High() || currentLow >= mData.elementAt(i).Low()) {
				return false;
			}
		}
		return true;
	}

	// trade is the open trade, and index is the index of the day (bar) that the trade started
	private Trade findClose(Trade trade, int index) {
		int size = mData.size();
		double entryPrice = trade.getEntryPrice();
		boolean isLong = trade.getDir() == Direction.LONG;

		// Search for a bar that reaches/matches our target or loss
		// if we're at the end of the file (mData.size()), close immediately.
		// Start searching the day after the trade started (index + 1)
		for (int i = index + 1; i < size; i++) {
			Bar currentBar = mData.elementAt(i);

			if (isLong) {
				// Using total amount
				//We reached our Long target
				if (entryPrice * (1 + trade.getTarget()) > currentBar.High()) {
					trade.close(currentBar.getDate(), currentBar.High());
					return trade;
				} else if (currentBar.Low() < entryPrice - entryPrice * trade.getStopLoss()) {
					// Reached our Stop loss
					trade.close(currentBar.getDate(), currentBar.Low());
					return trade;
				}
			} else {

				// TODO: Make conditions for SHORT trade
			}

			// Close the trade at the end of the file
			if (i == size - 1) {
				trade.close(currentBar.getDate(), currentBar.Close());
				return trade;
			}
		}
		return null;
	}

	public boolean test() {

		if (!load()) {
			return false;
		}

		int size = mData.size();
		for (int i = 60; i < size; i++) {
			if (pattern(i)) {
				Bar sixtyDayBar = mData.elementAt(i);
				// We found a pattern, now we do a buy stop
				// meaning that we are going to set money aside
				// until we find a trade that goes one cent beyond
				// i's (day 60's) high.
				// Extra note: we ignore the first two days after
				for (int j = i + 2; j < size; j++) {
					// TODO: Consider SHORT pattern
					if (mData.elementAt(j).High() > sixtyDayBar.High() + 0.01) {
						Trade trade = new Trade();
						trade.setDir(Direction.LONG);

						// TODO: Ask professor if we should start counting
						// since day 2 after sixtyDayBar, or we start
						// counting the day we find a new bar higher than
						// the high of sixtyDayBar (j).

						// Case 1: we count only when we find a bar 1 cent higher than
						// sixtyDayBar's high
						//	mTrade.setEntryDate(mData.elementAt(j).getDate());

						// Case 2: we count starting from day 2
						//trade.setEntryDate(mData.elementAt(i + 2).getDate());
						//trade.setEntryPrice(sixtyDayBar.High() + 0.01);
						//trade.setStopLoss(mLoss);
						//trade.setTarget(mTarget);
						//trade.setSymbol(mSymbol);
						trade.open(mSymbol, mData.elementAt(i + 2).getDate(), sixtyDayBar.High() + 0.01, mLoss, mTarget,
								Direction.LONG);
						trade = findClose(trade, j);

						if (trade != null) {
							mTrades.insert(trade);
						}
						break;
					} else if (mData.elementAt(j).Low() < sixtyDayBar.Low()) {

						// TODO: Possibly write code for SHORT direction here 
						break;
					}
				}
			}
		}

		//TODO:Need to finish outcome method to illustrate and forecast the pattern
		//We also need to write back the results in a file; csv or notepad.Need confirmation from prof.
		//outcome(Trade T, int index); 
		return true;
	}

	//Note: Started protoype method for writing the results of trades into csv files
	//need to check if we need try-catch. It has some syntax error; needs checking.
	//also need to ask prof if we need it and which class to put it in. but
	//for now placing it here.
	/*public void tradeWrite(){
	String content = "Trade content to be written";
	File file = new File("Trades.csv");
	FileWriter outcomeP = new FileWriter(file.getAbsoluteFile());
	BufferedWriter Bw = new BufferedWriter(outcomeP);
	Bw.Write(content);
	Bw.close(); }*/

	private boolean load() {

		String fileName = mPath + mSymbol + "_Daily.csv";
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
