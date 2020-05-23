package crops;

import main.Crop;

/**
 * Strawberry class extending the Crop class.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class Strawberry extends Crop
{

    /**
     * Strawberry constructor.
     */
    public Strawberry()
    {
        super("Strawberry", 40.0, 90.0, 3);//Name, buy price, sell price, days to grow(harvestDays)
    }

}