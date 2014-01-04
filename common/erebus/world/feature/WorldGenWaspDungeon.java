package erebus.world.feature;
import erebus.ModBlocks;

public class WorldGenWaspDungeon extends WorldGeneratorExt{
	@Override
	protected boolean generate(int x, int y, int z){
		y-=12+rand.nextInt(14);
		int testY=y;
		
		for(; testY>60; testY--){
			if (world.isAirBlock(x,testY,z))break;
		}
		System.out.println("y "+y+", testY "+testY+" ... "+(y-4-testY));
		if (y-4-testY>4||true)return false; // Not finished
		
		System.out.println("gen at "+x+","+y+","+z);
		
		// Layer 0 (starting from top)
		
		rect(ModBlocks.waspNestBlock,x-1,z-1,x+1,z+1,y);
		--y;
		
		// Layer 1
		
		for(int a=0; a<2; a++){
			linex(ModBlocks.waspNestBlock,x-1-a,x+1+a,z-3+a,y);
			linex(ModBlocks.waspNestBlock,x-1-a,x+1+a,z+3-a,y);
		}
		rect(ModBlocks.waspNestBlock,x-3,z-1,x-1,z+1,y);
		rect(ModBlocks.waspNestBlock,x+1,z-1,x+3,z+1,y);
		for(int a=0; a<2; a++){
			linex(ModBlocks.waspNestStairs,4+(a==0?2:3),x-1,x+1,z-1+2*a,y);
			block(ModBlocks.waspNestStairs,4+(a==0?1:0),x-1+2*a,z,y);
		}
		--y;
		
		// Layer 2
		
		for(int a=0; a<2; a++){
			linex(ModBlocks.waspNestBlock,x-1,x+1,z-5+10*a,y);
			linex(ModBlocks.waspNestBlock,x-3,x+3,z-4+8*a,y);
			linex(ModBlocks.waspNestBlock,x-4,x-2,z-3+6*a,y);
			linex(ModBlocks.waspNestBlock,x+2,x+4,z-3+6*a,y);
			linex(ModBlocks.waspNestBlock,x-4,x-3,z-2+4*a,y);
			linex(ModBlocks.waspNestBlock,x+3,x+4,z-2+4*a,y);
		}
		rect(ModBlocks.waspNestBlock,x-5,z-1,x-4,z+1,y);
		rect(ModBlocks.waspNestBlock,x+4,z-1,x+5,z+1,y);
		--y;
		
		// Layer 3
		
		for(int a=0; a<2; a++){
			linex(ModBlocks.waspNestBlock,x-3,x+3,z-5+10*a,y);
			linez(ModBlocks.waspNestBlock,z-3,z+3,x-5+10*a,y);
			for(int b=0; b<2; b++)block(ModBlocks.waspNestBlock,x-4+8*a,z-4+8*b,y);
		}
		--y;
		
		// Layer 4
		
		for(int a=0; a<2; a++){
			linex(ModBlocks.waspNestBlock,x-1,x+1,z-6+12*a,y);
			linex(ModBlocks.waspNestBlock,x-4,x-2,z-5+10*a,y);
			linex(ModBlocks.waspNestBlock,x+2,x+4,z-5+10*a,y);
			linex(ModBlocks.waspNestBlock,x-5,x-4,z-4+8*a,y);
			linex(ModBlocks.waspNestBlock,x+4,x+5,z-4+8*a,y);
			linez(ModBlocks.waspNestBlock,z-3,z-1,x-5+10*a,y);
			linez(ModBlocks.waspNestBlock,z+1,z+3,x-5+10*a,y);
			linez(ModBlocks.waspNestBlock,z-1,z+1,x-6+12*a,y);
		}
		--y;
		
		// Layer 5
		
		for(int a=0; a<2; a++){
			linex(ModBlocks.waspNestBlock,x-2,x+2,z-6+12*a,y);
			linex(ModBlocks.waspNestBlock,x-4,x-3,z-5+10*a,y);
			linex(ModBlocks.waspNestBlock,x+3,x+4,z-5+10*a,y);
			linez(ModBlocks.waspNestBlock,z-4,z-3,x-5+10*a,y);
			linez(ModBlocks.waspNestBlock,z+3,z+4,x-5+10*a,y);
			linez(ModBlocks.waspNestBlock,z-2,z+2,x-6+12*a,y);
		}
		--y;
		
		// Layer 6,7,8
		
		block(ModBlocks.waspSpawner,x,z,y);
		
		for(int layer=0; layer<3; layer++){
			for(int a=0; a<2; a++){
				linex(ModBlocks.waspNestBlock,x-1,x+1,z-7+14*a,y);
				linex(ModBlocks.waspNestBlock,x-3,x-2,z-6+12*a,y);
				linex(ModBlocks.redGem,x-1,x+1,z-6+12*a,y);
				linex(ModBlocks.waspNestBlock,x+2,x+3,z-6+12*a,y);
				linex(ModBlocks.waspNestBlock,x-5,x-4,z-5+10*a,y);
				linex(ModBlocks.waspNestBlock,x+4,x+5,z-5+10*a,y);
				block(ModBlocks.waspNestBlock,x-5,z-4+8*a,y);
				linez(ModBlocks.waspNestBlock,z-3,z-2,x-6+12*a,y);
				linez(ModBlocks.redGem,z-1,z+1,x-6+12*a,y);
				linez(ModBlocks.waspNestBlock,z+2,z+3,x-6+12*a,y);
				linez(ModBlocks.waspNestBlock,z-1,z+1,x-7+14*a,y);
			}
			--y;
		}
		
		// Layer 9 (copied 5)
		
		for(int a=0; a<2; a++){
			linex(ModBlocks.waspNestBlock,x-2,x+2,z-6+12*a,y);
			linex(ModBlocks.waspNestBlock,x-4,x-3,z-5+10*a,y);
			linex(ModBlocks.waspNestBlock,x+3,x+4,z-5+10*a,y);
			linez(ModBlocks.waspNestBlock,z-4,z-3,x-5+10*a,y);
			linez(ModBlocks.waspNestBlock,z+3,z+4,x-5+10*a,y);
			linez(ModBlocks.waspNestBlock,z-2,z+2,x-6+12*a,y);
		}
		--y;
		
		// Layer 10 (copied 4)
		
		for(int a=0; a<2; a++){
			linex(ModBlocks.waspNestBlock,x-1,x+1,z-6+12*a,y);
			linex(ModBlocks.waspNestBlock,x-4,x-2,z-5+10*a,y);
			linex(ModBlocks.waspNestBlock,x+2,x+4,z-5+10*a,y);
			linex(ModBlocks.waspNestBlock,x-5,x-4,z-4+8*a,y);
			linex(ModBlocks.waspNestBlock,x+4,x+5,z-4+8*a,y);
			linez(ModBlocks.waspNestBlock,z-3,z-1,x-5+10*a,y);
			linez(ModBlocks.waspNestBlock,z+1,z+3,x-5+10*a,y);
			linez(ModBlocks.waspNestBlock,z-1,z+1,x-6+12*a,y);
		}
		--y;
		
		// Layer 11 (copied 3)
		
		for(int a=0; a<2; a++){
			linex(ModBlocks.waspNestBlock,x-3,x+3,z-5+10*a,y);
			linez(ModBlocks.waspNestBlock,z-3,z+3,x-5+10*a,y);
			for(int b=0; b<2; b++)block(ModBlocks.waspNestBlock,x-4+8*a,z-4+8*b,y);
		}
		--y;
		
		// Layer 12
		
		for(int a=0; a<2; a++){
			linex(ModBlocks.waspNestBlock,x-2,x+2,z-5+10*a,y);
			linex(ModBlocks.waspNestBlock,x-3,x+3,z-4+8*a,y);
			linez(ModBlocks.waspNestBlock,z-2,z+2,x-5+10*a,y);
			linez(ModBlocks.waspNestBlock,z-3,z+3,x-4+8*a,y);
		}
		--y;
		
		// Layer 13
		
		for(int a=0; a<2; a++){
			linex(ModBlocks.waspNestStairs,4+(a==0?2:3),x-4,x+4,z-4+8*a,y);
			linez(ModBlocks.waspNestStairs,4+(a==0?1:0),z-4,z+4,x-4+8*a,y);
			linex(ModBlocks.waspNestBlock,x-3,x+3,z-3+6*a,y);
			linez(ModBlocks.waspNestBlock,z-2,z+2,x-3+6*a,y);
		}
		
		// Layer 14
		
		for(int a=0; a<2; a++){
			linex(ModBlocks.waspNestStairs,4+(a==0?0:1),x-3,x+3,z-3+6*a,y);
			linez(ModBlocks.waspNestStairs,4+(a==0?3:2),z-2,z+2,x-3+6*a,y);
		}
		
		return true;
	}
}
