package tschipp.hardcoreitemstages;

public class Config
{
	public static boolean dropUnknownItems;
	public static boolean hideUnknownItems;

	public static void sync()
	{
		try
		{
			dropUnknownItems = HardcoreItemStages.config.getBoolean("dropUnknownItems", "general", true, "If unknown items should be dropped from every slot in the inventory");
			hideUnknownItems = HardcoreItemStages.config.getBoolean("hideUnknownItems", "general", true, "If unknown items should be hidden with a question mark instead of showing their model");
		} catch (Exception e)
		{
		} finally
		{

			if (HardcoreItemStages.config.hasChanged())
				HardcoreItemStages.config.save();
		}
	}
}
