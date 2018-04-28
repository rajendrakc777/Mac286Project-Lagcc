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
//if (numberOfTrades == 0)
	//numberOfTrades = 1; 
//Decimal format, formats(does not round) floating point values
//into specified # of decimal spaces, req lib java.text.DecimalFormat
DecimalFormat df = new DecimalFormat("#.00");

//TODO: need to fix percentWinners in general and print PercentLosers and holding period
//Note: To calculate average Holding period, divide inventory by cost of sales
//and multiply the answer by 365 for the holding period in days or by 12 for the
//holding period in months
double Averageprofit = (totalWinnings - totalLoss) / (numberOfTrades + numberLong);
double AverageProfitWinners =(totalLongWinnings - totalLongLoss / numberLongDays);
double AverageProfitShort =(totalShortWinnings - totalShortLoss / numberShortDays);


String s = "\tNumOfTrades:"+numberOfTrades + "," + "\tWinners:" + (numberWinners / numberOfTrades * 100)+"%" + ","
		+ "\tAvrgProfit: " +df.format(Averageprofit)+ ","+ "\tLongwinners: " + (numberLong / numberLongDays * 100)+"%" + 
		" ," + "\tAvrgProfitWinner: "+ df.format(AverageProfitWinners) + ", "+"\tNumOfShort:" + numberShort + ","
		+"\tShortwinners: " + (numberShort / numberShortDays * 100)+"%" + "," + "\tAvrgProfitShort: "
		+df.format(AverageProfitShort) + "," + "\tAvrgHoldingPeriod: "+ (numberDays / numberOfTrades);

		return s;
	}
}
