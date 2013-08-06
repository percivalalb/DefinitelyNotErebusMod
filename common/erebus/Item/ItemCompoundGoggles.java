package erebus.Item; 
  
import net.minecraft.entity.Entity; 
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial; 
import net.minecraft.item.ItemArmor; 
import net.minecraft.item.ItemStack; 
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import erebus.mod_Erebus; 
 
/**
 * @author ProPercivalalb
 */
public class ItemCompoundGoggles extends ItemArmor  { 
  
	public ItemCompoundGoggles(int i, EnumArmorMaterial enumarmormaterial, int j, int k)   { 
        super(i, enumarmormaterial, j, k); 
        this.setCreativeTab(mod_Erebus.tabErebus); 
    }  
  
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) { 
        if(stack.itemID == mod_Erebus.compoundGoggles.itemID) { 
            return "erebus:textures/armor/goggles_1.png"; 
        } 
        else return null; 
    } 
    
    @Override
    public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
    	long worldTime = world.getWorldTime();
    	
    	if(worldTime % 20 == 0) {
    		player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 13 * 20, 0));
    	}
    }
}