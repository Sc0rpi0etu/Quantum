package fr.sc0rpi0.quantum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItems {
	private static Main main;
	public CustomItems(Main main) {
		CustomItems.main = main;
	}
	
	public static List<String> loreCleric = new ArrayList<String>(Arrays.asList("This stick can create aura of :", "strength, resistance or regeneration"));
	public static List<String> loreWizard = new ArrayList<String>(Arrays.asList("This stick can create :", "lightning, fireball or iceball"));
	public static List<String> loreUlti = new ArrayList<String>(Arrays.asList("This stick is", "ulti for physical class"));
	
	public ItemStack wizardStick() {
		ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Wizard : Lightning");
		itemMeta.setLore(loreWizard);
		itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 0, true);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	public ItemStack clericStick() {
		ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Cleric : Strength");
		itemMeta.setLore(loreCleric);
		itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 0, true);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	public ItemStack ultiStick() {
		ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Ulti : 0");
		itemMeta.setLore(loreUlti);
		itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 0, true);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	@SuppressWarnings("deprecation")
	public void AddRecipes() {
		ShapedRecipe recipeWizardStick = new ShapedRecipe(wizardStick());
		
		recipeWizardStick.shape("b  ", "s  ", "   ");
		recipeWizardStick.setIngredient('b', Material.BLAZE_POWDER);
		recipeWizardStick.setIngredient('s', Material.STICK);
		
		main.getServer().addRecipe(recipeWizardStick);
		
		recipeWizardStick = new ShapedRecipe(wizardStick());
		
		recipeWizardStick.shape(" b ", " s ", "   ");
		recipeWizardStick.setIngredient('b', Material.BLAZE_POWDER);
		recipeWizardStick.setIngredient('s', Material.STICK);
		
		main.getServer().addRecipe(recipeWizardStick);
		
		recipeWizardStick = new ShapedRecipe(wizardStick());
		
		recipeWizardStick.shape("  b", "  s", "   ");
		recipeWizardStick.setIngredient('b', Material.BLAZE_POWDER);
		recipeWizardStick.setIngredient('s', Material.STICK);
		
		main.getServer().addRecipe(recipeWizardStick);
		
		recipeWizardStick = new ShapedRecipe(wizardStick());
		
		recipeWizardStick.shape("   ", "b  ", "s  ");
		recipeWizardStick.setIngredient('b', Material.BLAZE_POWDER);
		recipeWizardStick.setIngredient('s', Material.STICK);
		
		main.getServer().addRecipe(recipeWizardStick);
		
		recipeWizardStick = new ShapedRecipe(wizardStick());
		
		recipeWizardStick.shape("   ", " b ", " s ");
		recipeWizardStick.setIngredient('b', Material.BLAZE_POWDER);
		recipeWizardStick.setIngredient('s', Material.STICK);
		
		main.getServer().addRecipe(recipeWizardStick);
		
		recipeWizardStick = new ShapedRecipe(wizardStick());
		
		recipeWizardStick.shape("   ", "  b", "  s");
		recipeWizardStick.setIngredient('b', Material.BLAZE_POWDER);
		recipeWizardStick.setIngredient('s', Material.STICK);
		
		main.getServer().addRecipe(recipeWizardStick);
		
		ShapedRecipe recipeClericStick = new ShapedRecipe(clericStick());
		
		recipeClericStick.shape("fg ", " s ", " s ");
		recipeClericStick.setIngredient('f', Material.IRON_INGOT);
		recipeClericStick.setIngredient('g', Material.GOLD_INGOT);
		recipeClericStick.setIngredient('s', Material.STICK);
		
		main.getServer().addRecipe(recipeClericStick);
		
		recipeClericStick = new ShapedRecipe(clericStick());
		
		recipeClericStick.shape(" fg", "  s", "  s");
		recipeClericStick.setIngredient('f', Material.IRON_INGOT);
		recipeClericStick.setIngredient('g', Material.GOLD_INGOT);
		recipeClericStick.setIngredient('s', Material.STICK);
		
		main.getServer().addRecipe(recipeClericStick);
		
		ShapedRecipe recipeUltiStick = new ShapedRecipe(ultiStick());
		
		recipeUltiStick.shape("s s", "s s", "sss");
		recipeUltiStick.setIngredient('s', Material.STICK);
		
		main.getServer().addRecipe(recipeUltiStick);
	}
}
