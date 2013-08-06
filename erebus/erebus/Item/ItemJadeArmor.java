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
public class ItemJadeArmor extends ItemArmor {

    /**
     * Custom made class for Jade Armor
     * @param id The Item Id
     * @param armorMaterial The EnumArmorMaterial that relates to the armor 
     * @param renderIndex Render index that is used in the render code
     * @param armorType The armor type: 0 is helmet, 1 is plate, 2 is legs and 3 is boots
     */
    public ItemJadeArmor(int id, EnumArmorMaterial armorMaterial, int renderIndex, int armorType) {
        super(id, armorMaterial, renderIndex, armorType);
        this.setCreativeTab(mod_Erebus.tabErebus);
    }
    
    /**
     * Determines which texture path the armor on the player should be 
     */
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
        if(stack.itemID == mod_Erebus.jadeHelmet.itemID || stack.itemID == mod_Erebus.jadeBody.itemID || stack.itemID == mod_Erebus.jadeBoots.itemID) {
            return "erebus:textures/armor/jade_1.png";
        }
        else { //if(itemstack.itemID == mod_Erebus.exoskeletonLegs.itemID) {
            return "erebus:textures/armor/jade_2.png";
        }
    }
}