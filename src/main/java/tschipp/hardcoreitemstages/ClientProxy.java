package tschipp.hardcoreitemstages;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
	
	public void preInit(FMLPreInitializationEvent event) {

		super.preInit(event);

		ModelBakery.registerItemVariants(Items.AIR, new ModelResourceLocation(HardcoreItemStages.MODID + ":unknown" , "inventory"));
	}

	public void init(FMLInitializationEvent event) {

		super.init(event);

	}

	public void postInit(FMLPostInitializationEvent event) {

		super.postInit(event);

	}

}
