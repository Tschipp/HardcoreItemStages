package tschipp.hardcoreitemstages;

import net.minecraft.client.renderer.block.model.IBakedModel;
import team.chisel.ctm.client.model.AbstractCTMBakedModel;

public class CTMCompat
{
	public static boolean isCTM(IBakedModel model)
	{
		return model instanceof AbstractCTMBakedModel;
	}
}
