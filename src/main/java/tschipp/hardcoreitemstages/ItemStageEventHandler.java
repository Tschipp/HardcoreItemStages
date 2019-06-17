package tschipp.hardcoreitemstages;

import net.darkhax.gamestages.GameStageHelper;
import net.darkhax.itemstages.ItemStages;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.IRegistry;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tschipp.hardcoreitemstages.client.HardcoreItemStageModel;

@EventBusSubscriber(modid = HardcoreItemStages.MODID)
public class ItemStageEventHandler
{

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onModelBakeEvent(ModelBakeEvent event)
	{
		if (Config.hideUnknownItems)
		{
			IRegistry<ModelResourceLocation, IBakedModel> registry = event.getModelRegistry();
			for (ModelResourceLocation key : registry.getKeys())
			{
				IBakedModel original = registry.getObject(key);
				HardcoreItemStageModel model = new HardcoreItemStageModel(original);
				registry.putObject(key, model);
			}
		}

	}

	@SubscribeEvent
	public static void onUpdate(LivingUpdateEvent event)
	{
		if (Config.dropUnknownItems)
		{
			EntityLivingBase entity = event.getEntityLiving();
			if (entity != null && entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) entity;
				if (!player.isCreative())
				{
					for (int slot = 0; slot <= 40; slot++)
					{
						ItemStack stack = player.inventory.getStackInSlot(slot).copy();
						if (!stack.isEmpty())
						{
							String stage = ItemStages.getStage(stack);
							if (stage != null)
							{
								if (!GameStageHelper.hasStage(player, stage))
								{
									player.inventory.setInventorySlotContents(slot, ItemStack.EMPTY);
									player.dropItem(stack, false);
								}
							}
						}
					}
				}
			}
		}
	}

}
