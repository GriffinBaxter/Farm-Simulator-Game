package crops;

import main.Crop;

/**
 * Wheat class extending the Crop class.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class Wheat extends Crop
{

    /**
     * Wheat constructor.
     */
    public Wheat()
    {
        super("Wheat", 10.0, 20.0, 1);//Name, buy price, sell price, days to grow(harvestDays)
    }

}