package io.github.bindglam.core.menu;

import fr.dwightstudio.dsmapi.Menu;
import fr.dwightstudio.dsmapi.MenuView;
import fr.dwightstudio.dsmapi.pages.Page;
import fr.dwightstudio.dsmapi.pages.PageType;
import io.github.bindglam.core.Core;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/*    */ public class StealMenu
/*    */   extends Menu
/*    */ {
/*    */   private final Player player;
/*    */   
/*    */   public StealMenu(Player player) {
/* 26 */     this.player = player;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 31 */     return "쌔비기 모드 ( " + this.player.getName() + " )";
/*    */   }
/*    */ 
/*    */   
/*    */   public Page[] getPages() {
/* 36 */     Page[] pages = new Page[getPageCount()];
/*    */     
/* 38 */     pages[0] = new Page()
/*    */       {
/*    */         public String getName() {
/* 41 */           return "쌔비기 모드 ( " + StealMenu.this.player.getName() + " )";
/*    */         }
/*    */ 
/*    */         
/*    */         public ItemStack[] getContent() {
/* 46 */           ItemStack[] content = getPageType().getBlankArray();
/*    */ 
/*    */           
/* 49 */           for (int i = 0; i < PageType.CHEST_PLUS.getSize(); i++) {
/* 50 */             content[i] = StealMenu.this.player.getInventory().getItem(i);
/*    */           }
/*    */           
/* 53 */           content[PageType.CHEST_PLUS.getRow() * 9] = StealMenu.this.player.getInventory().getItem(EquipmentSlot.HEAD);
/* 54 */           content[1 + PageType.CHEST_PLUS.getRow() * 9] = StealMenu.this.player.getInventory().getItem(EquipmentSlot.CHEST);
/* 55 */           content[2 + PageType.CHEST_PLUS.getRow() * 9] = StealMenu.this.player.getInventory().getItem(EquipmentSlot.LEGS);
/* 56 */           content[3 + PageType.CHEST_PLUS.getRow() * 9] = StealMenu.this.player.getInventory().getItem(EquipmentSlot.FEET);
/* 57 */           content[4 + PageType.CHEST_PLUS.getRow() * 9] = StealMenu.this.player.getInventory().getItemInOffHand();
/*    */           
/* 59 */           return content;
/*    */         }
/*    */ 
/*    */         
/*    */         public PageType getPageType() {
/* 64 */           return PageType.CHEST_PLUS_PLUS;
/*    */         }
/*    */ 
/*    */         
/*    */         public void onClick(MenuView view, ClickType clickType, int slot, ItemStack itemStack) {
/* 69 */           if (itemStack == null)
/*    */             return;

if(itemStack.getPersistentDataContainer().has(new NamespacedKey(Core.INSTANCE, "is-donate-item"))){
    view.getPlayer().sendMessage(Component.text("후원 아이템은 훔치기가 불가능합니다!").color(NamedTextColor.RED));
    return;
}

/* 71 */           view.close();
/* 72 */           view.getPlayer().getInventory().addItem(itemStack);
/* 73 */           if (slot >= 0 && slot < PageType.CHEST_PLUS.getRow() * 9) {
/* 74 */             StealMenu.this.player.getInventory().remove(itemStack);
/* 75 */           } else if (slot == PageType.CHEST_PLUS.getRow() * 9) {
/* 76 */             StealMenu.this.player.getInventory().setItem(EquipmentSlot.HEAD, null);
/* 77 */           } else if (slot == PageType.CHEST_PLUS.getRow() * 9 + 1) {
/* 78 */             StealMenu.this.player.getInventory().setItem(EquipmentSlot.CHEST, null);
/* 79 */           } else if (slot == PageType.CHEST_PLUS.getRow() * 9 + 2) {
/* 80 */             StealMenu.this.player.getInventory().setItem(EquipmentSlot.LEGS, null);
/* 81 */           } else if (slot == PageType.CHEST_PLUS.getRow() * 9 + 3) {
/* 82 */             StealMenu.this.player.getInventory().setItem(EquipmentSlot.FEET, null);
/* 83 */           } else if (slot == PageType.CHEST_PLUS.getRow() * 9 + 4) {
/* 84 */             StealMenu.this.player.getInventory().setItemInOffHand(null);
/*    */           } 
/* 86 */           view.getPlayer().sendMessage(((TextComponent)Component.text("훔치기 성공!").color(TextColor.color(0, 255, 0))));
/*    */         }
/*    */       };
/* 89 */     return pages;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPageCount() {
/* 94 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\menu\StealMenu.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */