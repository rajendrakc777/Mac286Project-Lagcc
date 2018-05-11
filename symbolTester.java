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

private boolean pattern(int index) {

		double currentHigh = mData.elementAt(index).High();
		double currentLow = mData.elementAt(index).Low();

		for (int j = 60; j > 0; j--) {
			if(currentHigh < mData.elementAt(index - j).High()) {
				return false;
			}
		}

		for (int i = index + 1; i < index + 3; i++) {
			if (currentHigh <= mData.elementAt(i).High() || currentLow >= mData.elementAt(i).Low()) {
				return false;
			}
		}
		
		return true;
	}

public boolean test() {

if (!load()) {
return false;
}

int size = mData.size();
for (int i = 60; i < size - 6; i++) {

// check if pattern exists
if (pattern(i)) {

	Bar sixtyDayBar = mData.elementAt(i);

for (int j = i + 3; j <= i + 6; j++) {
	
	if (mData.elementAt(j).High() >= sixtyDayBar.High() + 0.01) {
		Trade trade = new Trade();
		
		double sLoss = (1 - mLoss) * (sixtyDayBar.High() + 0.01);
		double tPrice = (1 + mTarget) * (sixtyDayBar.High() + 0.01);
		trade.open(mSymbol, mData.elementAt(j).getDate(), sixtyDayBar.High() + 0.01, sLoss, tPrice,
				Direction.LONG);
		findCloseLong(trade, j);
		mTrades.insert(trade);
		break;
	} else if (mData.elementAt(j).Low() >= sixtyDayBar.Low()
			&& mData.elementAt(j).High() <= sixtyDayBar.High()) {
		continue;
	} else {
		break;
	}
	
	
}
			}
		}

		return true;

	}

//Method to find the closing price/loss
private void findCloseLong(Trade trade, int index) {
		int size = mData.size();
		int hold = 0;

// Search for a bar that reaches/matches our target or loss
// if we're at the end of the file (mData.size()), close immediately.
for (int i = index; i < size; i++) {
	Bar currentBar = mData.elementAt(i);

	if (trade.getTarget() < currentBar.High()) {
		trade.setHoldingPeriod(hold);
		trade.close(currentBar.getDate(), trade.getTarget());
		return;
	} else if (currentBar.Low() < trade.getStopLoss()) {
		// Reached our Stop loss
		trade.setHoldingPeriod(hold);
		trade.close(currentBar.getDate(), trade.getStopLoss());
		return;
	}
			hold++;
}
trade.setHoldingPeriod(hold);
trade.close(mData.elementAt(size - 1).getDate(), mData.elementAt(size - 1).Close());

		return ;
	}
//finding close
private Trade findCloseShort(Trade trade, int index) {
	int size = mData.size();
	int holdL = 0;

for (int i = index; i < size; i++) {
	Bar currentBarL = mData.elementAt(i);

	if (trade.getStopLoss() <= currentBarL.High()) {
		trade.setHoldingPeriod(holdL);
		trade.close(currentBarL.getDate(), trade.getStopLoss());
		return trade;
	}else if (currentBarL.Low() <= trade.getTarget()) {
		
			trade.setHoldingPeriod(holdL);
			trade.close(currentBarL.getDate(), trade.getTarget());
			return trade;
		}
		holdL++;
	}
	trade.setHoldingPeriod(holdL);
	trade.close(mData.elementAt(size - 1).getDate(), mData.elementAt(size - 1).Close());

	return trade;
}

	

private boolean load() {

String fileName = mPath + mSymbol + "_Daily.csv";
File myfile = new File(fileName);
if (!myfile.exists()) {
	System.out.println("file does not exist");
	return false;
}
try {
	FileReader fr = new FileReader(fileName);
	// open the file FileReader->BufferedReader
BufferedReader br = new BufferedReader(fr);
String line = br.readLine();// discard this line
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