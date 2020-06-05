package tschipp.hardcoreitemstages.client;

import java.util.Collections;
import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableList;

import net.darkhax.gamestages.GameStageHelper;
import net.darkhax.itemstages.ItemStages;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tschipp.hardcoreitemstages.HardcoreItemStages;

@SideOnly(Side.CLIENT)
public class HardcoreItemStageModel implements IBakedModel
{

	private IBakedModel originalModel;
	private ItemOverrideList override;
	private static IBakedModel newmodel;

	public HardcoreItemStageModel(IBakedModel original)
	{
		originalModel = original;
		
		override = new OverrideList(originalModel.getOverrides().getOverrides(), originalModel.getOverrides(), original);
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand)
	{
		return originalModel.getQuads(state, side, rand);
	}

	@Override
	public boolean isAmbientOcclusion()
	{
		return originalModel.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d()
	{
		return originalModel.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer()
	{
		return originalModel.isBuiltInRenderer();
	}

	@Override
	public TextureAtlasSprite getParticleTexture()
	{
		return originalModel.getParticleTexture();
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms()
	{
		return originalModel.getItemCameraTransforms();
	}

	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType)
	{
		return originalModel.handlePerspective(cameraTransformType);
	}

	@Override
	public boolean isAmbientOcclusion(IBlockState state)
	{
		return originalModel.isAmbientOcclusion(state);
	}

	@Override
	public ItemOverrideList getOverrides()
	{
		return override;
	}
	
	@SideOnly(Side.CLIENT)
	public static class OverrideList extends ItemOverrideList
	{

		private ItemOverrideList original;
		private IBakedModel originalModel;
		
		public OverrideList(List<ItemOverride> overridesIn, ItemOverrideList original, IBakedModel originalModel)
		{
			super(overridesIn);
			this.original = original;
			this.originalModel = originalModel;
		}

		@Override
		public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity)
		{
			if (newmodel == null)
				newmodel = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(new ModelResourceLocation(HardcoreItemStages.MODID + ":unknown", "inventory"));

			if (!stack.isEmpty())
			{
				String stage = ItemStages.getStage(stack);
				if (stage != null)
				{
					EntityPlayer player = Minecraft.getMinecraft().player;
					if (player != null)
					{
						if (!GameStageHelper.hasStage(player, stage))
						{
							return newmodel;
						}
					}
				}

			}

			return original.handleItemState(originalModel, stack, world, entity);
		}
		
		@Override
		public ResourceLocation applyOverride(ItemStack stack, World worldIn, EntityLivingBase entityIn)
		{
			return original.applyOverride(stack, worldIn, entityIn);
		}
		
		@Override
		public ImmutableList<ItemOverride> getOverrides()
		{
			return original.getOverrides();
		}
	}

}
