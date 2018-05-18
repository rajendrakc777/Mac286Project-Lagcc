import java.text.DecimalFormat;

public class Stats {
public int numberOfTrades, numberLong, numberShort, numberWinners, numberLosers;
public int longWinners, longLosers, shortWinners, shortLosers;
public int numberDays, numberLongDays, numberShortDays;
double totalWinnings, totalLoss, totalLongWinnings, totalLongLoss, totalShortWinnings;
double totalShortLoss;


public Stats() {
numberOfTrades = numberLong = numberShort = numberWinners = numberLosers = 0;
longWinners = longLosers = shortWinners = shortLosers = 0;
numberDays = numberLongDays = numberShortDays = 0;
totalWinnings = totalLoss = totalLongWinnings = totalLongLoss = totalShortWinnings = 0.0;
totalShortLoss = 0.0;

}

public String toString() {

//Fixing division by 0
//Assuming that if numberOfTrades (denominator) is 0, then numberOfWinners should also be 0,
// Therefore, initializing numberOfTrades and any other denominator to 1 to avoid division
// by 0, the result will still be 0 %. Ex: 0 / 1 * 100 = 0%
if (numberOfTrades == 0)
	numberOfTrades = 1;
if (numberLongDays == 0)
	numberLongDays = 1;
if (numberShortDays == 0)
	numberShortDays = 1;
 if (numberOfTrades == 0)
	numberOfTrades = 1; 

DecimalFormat df = new DecimalFormat("#0.00");

double AveragePLpercent = ((totalWinnings + totalLoss) / numberOfTrades);
double AverageProfitShort =(totalShortWinnings - totalShortLoss / numberShortDays);
double AverageProfitLong = ((totalLongWinnings + totalLongLoss)/numberLong);
double AverageHoldingPeriod = (numberDays/numberOfTrades);
double PercentWinners = (double)numberWinners/numberOfTrades*100.0;
double PercentLongwinners = (double)longWinners/numberLong * 100.0;
double PercentShortwin = (double)shortWinners/numberShort*100.0;

//add averagePL%m averagePLLong, averagePLShort



String s ="NumOfTrades:"+numberOfTrades + "\tWinners:" + df.format(PercentWinners)+"%" /*numberLong*/
		+ "\tAvrgPLpercent: " +df.format(AveragePLpercent)+"%"+ "\tLongwinners: " +df.format(PercentLongwinners)+"%" + 
		"\tNumOfShort:" + numberShort + "\tShortwinners: " + df.format(PercentShortwin)+"%" + "\tAvrgProfitShort:"+df.format(AverageProfitShort) + 
		"\tAvrgProfitLong:"+df.format(AverageProfitLong) +"\tAvrgHoldingPeriod:"+ AverageHoldingPeriod + "days";

		return s; 
	}



public String toString2() {


	if (numberOfTrades == 0)
		numberOfTrades = 1;
	if (numberLongDays == 0)
		numberLongDays = 1;
	if (numberShortDays == 0)
		numberShortDays = 1;
	 if (numberOfTrades == 0)
		numberOfTrades = 1; 

DecimalFormat df = new DecimalFormat("#0.00");

	double AveragePLpercent = ((totalWinnings + totalLoss) / numberOfTrades);
	double AverageProfitShort =(totalShortWinnings - totalShortLoss / numberShortDays);
	double AverageProfitLong = ((totalLongWinnings + totalLongLoss)/numberLong);
	double AverageHoldingPeriod = (numberDays/numberOfTrades);
	double PercentWinners = (double)numberWinners/numberOfTrades*100.0;
	double PercentLongwinners = (double)longWinners/numberLong * 100.0;
	double PercentShortwin = (double)shortWinners/numberShort*100.0;

	


String s =numberOfTrades + "," + df.format(PercentWinners)+"%" /*numberLong*/
			+ "," +df.format(AveragePLpercent)+"%"+ "," +df.format(PercentLongwinners)+"%" + 
			"," + numberShort + "\t" + PercentShortwin+"%" + ","+df.format(AverageProfitShort) + 
			","+df.format(AverageProfitLong) +","+ AverageHoldingPeriod + "days";

			return s; 
		}

}
