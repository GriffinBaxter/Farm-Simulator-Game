package main;

public class Crop implements StoreItem
{

	/**
	 * The name of the crop.
	 */
	private String cropName;
	
	/**
	 * The purchase price of the crop.
	 */
	private double purchasePrice;
	
	/**
	 * The sell price of the crop.
	 */
	private double sellPrice;
	
	/**
	 * The days for the crop to grow.
	 */
	private int daysToGrow;
	
	/**
	 * The days the crop has grown.
	 */
	private int daysGrown = 0;
	
	/**
	 * Constructor function for Crop Class, this constructor initialises variables <code>cropName</code>, <code>purchasePrice</code>, <code>sellPrice</code> and <code>daysToGrow</code>.
	 * @param name The name of the crop.
	 * @param initPurchasePrice The initial purchase price of the crop.
	 * @param initSellPrice The initial sell price of the crop.
	 * @param initDaysToGrow The initial number of days the crop has to grow.
	 */
	public Crop(String name, double initPurchasePrice,double initSellPrice, int initDaysToGrow)
	{
		cropName = name;
		purchasePrice = initPurchasePrice;
		sellPrice = initSellPrice;
		daysToGrow = initDaysToGrow;
	}
	
	/**
	 * for copying a crop class (this is used when you buy an crop).
	 * @param crop Crop class.
	 */
	public Crop(Crop crop)
	{
		cropName = crop.getName();
		purchasePrice = crop.getPurchasePrice();
		sellPrice = crop.getSellPrice();
		daysToGrow = crop.getDaysToGrow();
		daysGrown = 0;
	}
	
	/**
	 * Returns true of false depending on whether the crop can be harvested.
	 * This is done by checking if the <code>daysGrown</code> is greater than or equal to <code>daysToGrow</code>.
	 * @return true if the crop can be harvested, false otherwise.
	 */
	public Boolean canHarvest() 
	{
		if (daysGrown >= daysToGrow)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the purchase price of the crop.
	 * @return the purchase price.
	 */
	public double getPurchasePrice()
	{
		return purchasePrice;
	}	
	
	/**
	 * Returns the sell price of the crop.
	 * @return the sell price.
	 */
	public double getSellPrice()
	{
		return sellPrice;
	}
	
	/**
	 * Returns the name of the crop.
	 * @return the name of the crop.
	 */
	public String getName() 
	{
		return cropName;
	}
	
	/**
	 * Returns the number of days the crop has grown.
	 * @return The days the crop has grown.
	 */
	public int getDaysGrown() 
	{
		return daysGrown;
	}
	
	/**
	 * Returns the number of days the crop needs to grow.
	 * @return The days the crop needs to grow.
	 */
	public int getDaysToGrow() 
	{
		return daysToGrow;
	}
	
	/**
	 * Returns the days the crop has left to grow by subtracting the <code>daysGrown</code> from <code>daysToGrow</code>.
	 * @return
	 */
	public int getDaysLeftToGrow() 
	{
		return daysToGrow - daysGrown;
	}
	
	/**
	 * Grows the crop by increasing its <code>daysGrown</code> only if the days left to grow is greater than 0.
	 */
	public void grow()
	{
		if (getDaysLeftToGrow() > 0)
		{
			daysGrown++;
		}
		
	}
	
	/**
	 * Tends to the crop by the specified double <code>daysToIncrease</code>, only if the days left to grow is greater than 0.
	 * @param daysToIncrease Number of days to increase growth by.
	 */
	public void tend(double daysToIncrease)
	{
		daysGrown += daysToIncrease;
		if (getDaysLeftToGrow() < 0)
		{
			daysGrown = daysToGrow;
		}
	}
	
	
}
