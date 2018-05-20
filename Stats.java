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
int    AverageHoldingPeriod = (numberDays/numberOfTrades);
double PercentWinners = (double)numberWinners/numberOfTrades*100.0;
double PercentLongwinners = (double)longWinners/numberLong * 100.0;
double PercentShortwin = (double)shortWinners/numberShort*100.0;


String s = "NumofTrades:"+numberOfTrades +"\tNumLong:" + numberLong + "\t NumShort:"+ numberShort+"\tWinners:" + df.format(PercentWinners)+"%" 
			+"\tAvrgPL:" +df.format(AveragePLpercent)+"%" + "\tHoldingPeriod:"+AverageHoldingPeriod + "days" +"\tLongwinners:" +df.format(PercentLongwinners)+"%"+
			"\tAvrPLlong:"+df.format(AverageProfitLong) + "\tShortwinners:" + df.format(PercentShortwin)+"%" +"\tAvrPLShort:" + df.format(AverageProfitShort);

			return s;

/*Original before change
String s ="NumOfTrades: "+numberOfTrades + "\tWinners: " + df.format(PercentWinners)+"%"+"\tNumLong:"+ numberLong + "\tAvrgPLpercent: " +df.format(AveragePLpercent)+"%"+ 
		"\tLongwinners: " +df.format(PercentLongwinners)+"%"+"\tNumShort:" + numberShort + "\tShortwinners: " + df.format(PercentShortwin)+"%"+ 
		"\tAvrgProfitShort:"+df.format(AverageProfitShort) +"\tAvrgProfitLong:"+df.format(AverageProfitLong) +"\tHoldingPeriod:"+AverageHoldingPeriod + "days";

		return s; */
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

DecimalFormat df2 = new DecimalFormat("#0.00");



	double AveragePLpercent = ((totalWinnings + totalLoss) / numberOfTrades);
	double AverageProfitShort =(totalShortWinnings - totalShortLoss / numberShortDays);
	double AverageProfitLong = ((totalLongWinnings + totalLongLoss)/numberLong);
	int AverageHoldingPeriod = (numberDays/numberOfTrades); //original was declared as double, but holding period is days
	double PercentWinners = (double)numberWinners/numberOfTrades*100.0;
	double PercentLongwinners = (double)longWinners/numberLong * 100.0;
	double PercentShortwin = (double)shortWinners/numberShort*100.0;

	


String s =numberOfTrades + "," + numberLong+ "," + numberShort + "," + df2.format(PercentWinners)+"%" + "," +df2.format(AveragePLpercent)+"%"+ "," + AverageHoldingPeriod +"," 
		+df2.format(PercentLongwinners)+"%" + "," + df2.format(AverageProfitLong) +","+df2.format(PercentShortwin)+"%"+ "," + df2.format(AverageProfitShort);
	

			return s; 
		}
	

}
