package erebus.client.render.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import erebus.client.model.item.ModelWaspSword;

public class WaspSwordItemRenderer implements IItemRenderer 
{
	private ModelWaspSword ModelWaspSword;
	public static ResourceLocation texture = new ResourceLocation("erebus:textures/item/ModelWaspSword.png");
	public WaspSwordItemRenderer() 
	{
		ModelWaspSword = new ModelWaspSword();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		return type != ItemRenderType.FIRST_PERSON_MAP;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return helper != ItemRendererHelper.BLOCK_3D;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
        switch (type) {
        case ENTITY: 
            renderSword(0.0F, 1.0F, 0.0F, 1.D);
            break;
        case EQUIPPED: 
        	renderEquipped(0.3F, 0.8F, 0.4F, 1.2D);
            break;
        case EQUIPPED_FIRST_PERSON: 
        	renderSwordFirstPerson(0.5F, 1.0F, 0.5F, 1.2D);
            break;
        case INVENTORY: 
        	renderSwordInventory(-0.35F, -0.4F, 0.0F, 0.5D);
            break;
        default:
            break;
        }
	}
	
	private void renderEquipped(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
        GL11.glPushMatrix(); //Start Rendering
        GL11.glTranslatef(x, y + 0.6F, z + 0.5F);// Position
        GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScaled(-size, -size, size); //Changes the size (Only really used when reading in the inventory)
        ModelWaspSword.render(0.0625F); //Render
        GL11.glPopMatrix(); //End Rendering
	}
	
    private void renderSword(float x, float y, float z, double size) {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
        GL11.glPushMatrix(); //Start Rendering
        GL11.glTranslatef(x, y, z); //Position
        GL11.glRotatef(180F, 1F, 0, 0);
        GL11.glRotatef(-135F, 0, 1F, 0);
        GL11.glRotatef(70F, 0, 0, 1F);
        GL11.glScaled(size, size, size); //Changes the size (Only really used when reading in the inventory)
        ModelWaspSword.render(0.0625F); //Render
        GL11.glPopMatrix(); //End Rendering
    }
    
    private void renderSwordFirstPerson(float x, float y, float z, double size) {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
        GL11.glPushMatrix(); //Start Rendering
        GL11.glTranslatef(x, y, z); //Position
        GL11.glRotatef(180F, 1F, 0, 0);
        GL11.glRotatef(-45F, 0, 1F, 0);
        GL11.glScaled(size, size, size); //Changes the size (Only really used when reading in the inventory)
        ModelWaspSword.render(0.0625F); //Render
        GL11.glPopMatrix(); //End Rendering
    }
    
    private void renderSwordInventory(float x, float y, float z, double size) {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
        GL11.glPushMatrix(); //Start Rendering
        GL11.glTranslatef(x, y, z); //Position
        GL11.glRotatef(135F, 1F, 0, 0);
        GL11.glRotatef(135F, 0, 1F, 0);
        GL11.glRotatef(-20F, 0, 0, 1F);
        GL11.glScaled(size, size, size); //Changes the size (Only really used when reading in the inventory)
        ModelWaspSword.render(0.0625F); //Render
        GL11.glPopMatrix(); //End Rendering
    }
}
