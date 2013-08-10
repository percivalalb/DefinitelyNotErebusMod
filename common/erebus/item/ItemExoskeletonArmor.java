package erebus.item;

import erebus.core.proxy.CommonProxy;
import erebus.ErebusMod;
import erebus.ModItems;
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
        this.setCreativeTab(ErebusMod.tabErebus);
    }
    
    /**
     * Determines which texture path the armor on the player should be 
     */
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
        if(stack.itemID == ModItems.exoskeletonHelmet.itemID || stack.itemID == ModItems.exoskeletonBody.itemID || stack.itemID == ModItems.exoskeletonBoots.itemID) {
            return "erebus:textures/armor/exoskeleton_1.png";
        }
        else { //if(itemstack.itemID == mod_Erebus.exoskeletonLegs.itemID) {
            return "erebus:textures/armor/exoskeleton_2.png";
        }
    }
}