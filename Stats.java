public class Stats {
	public int numberOfTrades, numberLong, numberShort, numberWinners, numberLosers;
	public int longWinners, longLosers, shortWinners, shortLosers;
	public int numberDays, numberLongDays, numberShortDays;
	double totalWinnings, totalLoss, totalLongWinnings, totalLongLoss, totalShortWinnings;
	double totalShortLoss;
	
public Stats() {
		numberOfTrades= numberLong= numberShort= numberWinners= numberLosers = 0;
		longWinners= longLosers= shortWinners= shortLosers = 0;
		numberDays= numberLongDays= numberShortDays=0;
		totalWinnings= totalLoss= totalLongWinnings= totalLongLoss= totalShortWinnings = 0.0;
		totalShortLoss = 0.0;
		
	}

	
//TODO for HW4
public String toString() {

	//Assuming that if numberOfTrades (denominator) is 0, then numberOfWinners should also be 0,
	// Therefore, initializing numberOfTrades and any other denominator to 1 to avoid division
	// by 0, the result will still be 0 %. Ex: 0 / 1 * 100 = 0%
	if (numberOfTrades == 0 ) numberOfTrades = 1;
	if (numberLongDays == 0 ) numberLongDays = 1;
	if (numberShortDays == 0 ) numberShortDays = 1;
	if (numberOfTrades == 0 ) numberOfTrades = 1;
String s = numberOfTrades + "," + "PercentWinners: "+(numberWinners/numberOfTrades*100)+ "," + "AverageProfit: " + (totalWinnings+ totalLoss)/numberOfTrades
+ numberLong + "," + "Percent Longwinners: " +(numberLong / numberLongDays * 100)  +" ," + "averageProfitWinner: " + totalLongWinnings + totalLongLoss / numberLongDays 
+ ", " + numberShort + "," + "Percent Shortwinners: " +(numberShort / numberShortDays * 100)  + "," + "averageProfitShort: " + totalShortWinnings + totalShortLoss /numberShortDays
+ "," + "average Holding Period: " + numberDays / numberOfTrades;
	
	return s;
	}
}
