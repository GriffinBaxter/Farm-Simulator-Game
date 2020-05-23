package crops;

import main.Crop;

/**
 * Corn class extending the Crop class.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class Corn extends Crop
{

    /**
     * Corn constructor.
     */
    public Corn()
    {
        super("Corn", 10.0, 25.0, 3);//Name, buy price, sell price, days to grow(harvestDays)
    }

}