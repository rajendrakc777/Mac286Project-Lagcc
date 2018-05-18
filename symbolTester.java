import java.io.BufferedReader; 
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
	
	private boolean patternLong(int index) {
	
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
	
	private boolean patternShort(int index) {
		
		double currentHigh = mData.elementAt(index).High();
		double currentLow = mData.elementAt(index).Low();
	
		for (int j = 60; j > 0; j--) {
			if(currentLow > mData.elementAt(index - j).Low()) {
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
			if (patternLong(i)) {
					
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
			
			else if(patternShort(i)) {
				
				Bar sixtyDayBar = mData.elementAt(i);
				
				for (int j = i + 3; j <= i + 6; j++) {
					
					if (mData.elementAt(j).Low() <= sixtyDayBar.Low() - 0.01) {
						Trade trade = new Trade();
						double sLoss = (1 + mLoss) * (sixtyDayBar.Low() - 0.01);
						double tPrice = (1 - mTarget) * (sixtyDayBar.Low() - 0.01);
						trade.open(mSymbol, mData.elementAt(j).getDate(), sixtyDayBar.Low() - 0.01, sLoss, tPrice,
									Direction.SHORT);
						findCloseShort(trade, j);
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
		if(mLoss == 0)
		{
			trade.close(mData.elementAt(index).getDate(), mData.elementAt(index).Close());
			return;
		}
		// Search for a bar that reaches/matches our target or loss
		// if we're at the end of the file (mData.size()), close immediately.
		for (int i = index; i < size; i++) {
			Bar currentBar = mData.elementAt(i);
			//we consider the gap 
			if (trade.getTarget() < currentBar.High()) {
				//if open is larger than target close at open of day
				if(currentBar.Open() > trade.getTarget())
				{
					trade.close(currentBar.getDate(), currentBar.Open());
				}
				else {
					trade.setHoldingPeriod(hold);
					trade.close(currentBar.getDate(), trade.getTarget());
				}
				return;
			} else if (currentBar.Low() < trade.getStopLoss()) {
				// Reached our Stop loss
				//if open is smaller than stop loss close at open
				if(currentBar.Open() < trade.getStopLoss())
				{
					trade.close(currentBar.getDate(), currentBar.Open());
				}
				else{
					trade.setHoldingPeriod(hold);
					trade.close(currentBar.getDate(), trade.getStopLoss());
				}
				return;
			}
			hold++;
		}
		trade.setHoldingPeriod(hold);
		trade.close(mData.elementAt(size - 1).getDate(), mData.elementAt(size - 1).Close());
		return ;
	}
	
	private void findCloseShort(Trade trade, int index) {
		
		int size = mData.size();
		int holdL = 0;
		
		if(mLoss == 0)
		{
			trade.close(mData.elementAt(index).getDate(), mData.elementAt(index).Close());
			return;
		}
	
		for (int i = index; i < size; i++) {
			Bar currentBarL = mData.elementAt(i);
			//< or <= ?
			if (trade.getStopLoss() <= currentBarL.High()) {
				if (trade.getStopLoss() <= currentBarL.Open()) {
					trade.close(currentBarL.getDate(), currentBarL.Open());
				}
				else{
					trade.close(currentBarL.getDate(), trade.getStopLoss());
				}
				trade.setHoldingPeriod(holdL);

				return;
			}else if (currentBarL.Low() < trade.getTarget()) {
				if(currentBarL.Open() < trade.getTarget()) {
					trade.close(currentBarL.getDate(), currentBarL.Open());
				}else {
					trade.close(currentBarL.getDate(), trade.getTarget());
				}
				trade.setHoldingPeriod(holdL);
				return;
			}
			holdL++;
		}
		trade.setHoldingPeriod(holdL);
		trade.close(mData.elementAt(size - 1).getDate(), mData.elementAt(size - 1).Close());
		
		return;
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