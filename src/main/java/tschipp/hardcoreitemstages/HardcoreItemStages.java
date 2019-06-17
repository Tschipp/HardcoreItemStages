package tschipp.hardcoreitemstages;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = HardcoreItemStages.MODID, name = "Hardcore Item Stages", version = HardcoreItemStages.VERSION, dependencies = "required-after:itemstages;")
public class HardcoreItemStages {

	public static final String MODID = "hardcoreitemstages";
	public static final String VERSION = "1.0";

	@Instance(value = MODID)
	public static HardcoreItemStages instance;

	public static final String CLIENT_PROXY = "tschipp.hardcoreitemstages.ClientProxy";
	public static final String COMMON_PROXY = "tschipp.hardcoreitemstages.CommonProxy";

	@SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
	public static CommonProxy proxy;

	public static Configuration config;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		
		config = new Configuration(event.getSuggestedConfigurationFile());
		
		Config.sync();
		
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
