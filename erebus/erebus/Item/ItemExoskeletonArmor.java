package erebus.Item;

import erebus.CommonErebusProxy;
import erebus.mod_Erebus;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

/**
 * @author ProPercivalalb
 */
public class ItemExoskeletonArmor extends ItemArmor {

    /**
     * Custom made class for ExoSkeleton Armor
     * @param id The Item Id
     * @param armorMaterial The EnumArmorMaterial that relates to the armor 
     * @param renderIndex Render index that is used in the render code
     * @param armorType The armor type: 0 is helmet, 1 is plate, 2 is legs and 3 is boots
     */
    public ItemExoskeletonArmor(int id, EnumArmorMaterial armorMaterial, int renderIndex, int armorType) {
        super(id, armorMaterial, renderIndex, armorType);
        this.setCreativeTab(mod_Erebus.tabErebus);
    }
    
    /**
     * Determines which texture path the armor on the player should be 
     */
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
        if(stack.itemID == mod_Erebus.exoskeletonHelmet.itemID || stack.itemID == mod_Erebus.exoskeletonBody.itemID || stack.itemID == mod_Erebus.exoskeletonBoots.itemID) {
            return "erebus:textures/armor/exoskeleton_1.png";
        }
        else { //if(itemstack.itemID == mod_Erebus.exoskeletonLegs.itemID) {
            return "erebus:textures/armor/exoskeleton_2.png";
        }
    }
}