package erebus.client.render.tileentity;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Calendar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import erebus.ErebusMod;
import erebus.ModBlocks;
import erebus.block.BlockBambooCrate;
import erebus.client.model.block.ModelBamboo;
import erebus.client.model.block.ModelBambooCrate;
import erebus.client.model.block.ModelColossalCrate;
import erebus.core.helper.LogHelper;
import erebus.tileentity.TileEntityBamboo;

@SideOnly(Side.CLIENT)
public class TileEntityRenderBamboo extends TileEntitySpecialRenderer
{
    private ModelBamboo bambooModel = new ModelBamboo();
    public static ResourceLocation bambooResource = new ResourceLocation("erebus:textures/item/bamboo.png");
    
    private ModelBambooCrate bambooCrateModel = new ModelBambooCrate();
    public static ResourceLocation bambooCrateResource = new ResourceLocation("erebus:textures/item/bambooCrate.png");
    
    private ModelColossalCrate colossalCrateModel = new ModelColossalCrate();
    public static ResourceLocation colossalCrateResource = new ResourceLocation("erebus:textures/item/colossalcrate.png");
    
    public void renderTileEntityChestAt(TileEntityBamboo bambooCrate, double par2, double par4, double par6, float par8){ 	
    	int x = bambooCrate.xCoord;
    	int y = bambooCrate.yCoord;
    	int z = bambooCrate.zCoord;
    	
    	if(bambooCrate.worldObj.getBlockMetadata(x, y, z) == 0) {
    		this.bindTexture(bambooResource);
            
            GL11.glPushMatrix(); //start
            GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.5F, (float)par6 + 0.5F); //size
            
            GL11.glRotatef(0, 0.0F, 1.0F, 0.0F); //change the first 0 in like 90 to make it rotate 90 degrees.
            GL11.glScalef(1.0F, -1F, -1F); // to make your block have a normal positioning. comment out to see what happens
            bambooModel.renderModel(0.0625F); //renders and 0.0625F is exactly 1/16. every block has 16 entitys/pixels. if you make the number 1, every pixel will be as big as a block. make it 0.03125 and your block will be half as big as a normal one.
            GL11.glPopMatrix(); //end
            return;
    	}
    	
    	BlockBambooCrate crate = (BlockBambooCrate)ModBlocks.bambooCrate;
		if(bambooCrate.worldObj.getBlockId(x, y - 1, z) == crate.blockID && bambooCrate.worldObj.getBlockMetadata(x, y - 1, z) == 1) y--;
		if(bambooCrate.worldObj.getBlockId(x - 1, y, z) == crate.blockID && bambooCrate.worldObj.getBlockMetadata(x - 1, y, z) == 1) x--;
		if(bambooCrate.worldObj.getBlockId(x, y, z - 1) == crate.blockID && bambooCrate.worldObj.getBlockMetadata(x, y, z - 1) == 1) z--;
    	if(crate.squareCrate(bambooCrate.worldObj, x, y, z)) {
	    	if(bambooCrate.xCoord != x || bambooCrate.yCoord != y || bambooCrate.zCoord != z) return;
    			
    		this.bindTexture(colossalCrateResource);
	    	        
	    	GL11.glPushMatrix(); //start
	    	GL11.glTranslatef((float)par2 + 1.5F, (float)par4 + 1.5F, (float)par6 + 1.5F); //size
	    	        
	    	GL11.glRotatef(0, 0.0F, 1.0F, 0.0F); //change the first 0 in like 90 to make it rotate 90 degrees.
	    	GL11.glScalef(1.0F, -1F, -1F); // to make your block have a normal positioning. comment out to see what happens
	    	colossalCrateModel.renderModel(0.0625F); //renders and 0.0625F is exactly 1/16. every block has 16 entitys/pixels. if you make the number 1, every pixel will be as big as a block. make it 0.03125 and your block will be half as big as a normal one.
	    	GL11.glPopMatrix(); //end
	    	return;
	    	    
    	}
    	
    	this.bindTexture(bambooCrateResource);
        
        GL11.glPushMatrix(); //start
        GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.5F, (float)par6 + 0.5F); //size
        
        GL11.glRotatef(0, 0.0F, 1.0F, 0.0F); //change the first 0 in like 90 to make it rotate 90 degrees.
        GL11.glScalef(1.0F, -1F, -1F); // to make your block have a normal positioning. comment out to see what happens
        bambooCrateModel.renderModel(0.0625F); //renders and 0.0625F is exactly 1/16. every block has 16 entitys/pixels. if you make the number 1, every pixel will be as big as a block. make it 0.03125 and your block will be half as big as a normal one.
        GL11.glPopMatrix(); //end
    }

    @Override
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityChestAt((TileEntityBamboo)par1TileEntity, par2, par4, par6, par8);
    }
}
