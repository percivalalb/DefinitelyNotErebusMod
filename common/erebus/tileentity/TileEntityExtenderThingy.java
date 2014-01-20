package erebus.tileentity;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import erebus.ModBlocks;

public class TileEntityExtenderThingy extends TileEntity {

	private boolean extending;
	private int index = 0;
	private ForgeDirection dir = null;

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;

		if (dir == null)
			dir = getDirectionFromMetadata(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));

		boolean stop;
		int increment;
		int blockID;
		Block extension = getExtension(dir);

		if (extending) {
			stop = index > 16;
			increment = 1;
			blockID = extension.blockID;
		} else {
			stop = index <= 0;
			increment = -1;
			blockID = 0;
		}

		if (!stop) {
			int x = xCoord + index * dir.offsetX;
			int y = yCoord + index * dir.offsetY;
			int z = zCoord + index * dir.offsetZ;

			if (!extending || worldObj.isAirBlock(x, y, z)) {
				worldObj.setBlock(x, y, z, blockID, getMetaFromDirection(dir), 3);
				if (extending)
					worldObj.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, extension.stepSound.getPlaceSound(), (extension.stepSound.getVolume() + 1.0F) / 2.0F, extension.stepSound.getPitch() * 0.8F);
				else
					worldObj.playAuxSFXAtEntity(null, 2001, x, y, z, extension.blockID + (worldObj.getBlockMetadata(x, y, z) << 12));
			}
			index += increment;
		}
	}

	private Block getExtension(ForgeDirection dir) {
		return dir == ForgeDirection.UP || dir == ForgeDirection.DOWN ? ModBlocks.bambooPole : ModBlocks.bambooBridge;
	}

	private int getMetaFromDirection(ForgeDirection dir) {
		switch(dir) {
			case UP:
				return 1;
			case DOWN:
				return 1;
			case EAST:
			case WEST:
				return 4;
			case NORTH:
			case SOUTH:
				return 3;
			default:
				return 0;
		}
	}

	private ForgeDirection getDirectionFromMetadata(int meta) {
		switch (meta) {
			case 0:
				return ForgeDirection.DOWN;
			case 1:
				return ForgeDirection.UP;
			case 2:
				return ForgeDirection.NORTH;
			case 3:
				return ForgeDirection.SOUTH;
			case 4:
				return ForgeDirection.WEST;
			case 5:
				return ForgeDirection.EAST;
		}
		return null;
	}

	public void setExtending(boolean extending) {
		this.extending = extending;
	}
}