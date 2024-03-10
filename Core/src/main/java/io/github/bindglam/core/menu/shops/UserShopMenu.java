/*     */ package io.github.bindglam.core.menu.shops;
/*     */ 
/*     */ import fr.dwightstudio.dsmapi.Menu;
/*     */ import fr.dwightstudio.dsmapi.MenuView;
/*     */ import fr.dwightstudio.dsmapi.pages.Page;
/*     */ import fr.dwightstudio.dsmapi.pages.PageType;
/*     */ import io.github.bindglam.core.Core;
/*     */ import io.github.bindglam.core.managers.UserShopManager;
/*     */ import io.github.bindglam.core.utils.AdvItemCreator;
/*     */ import io.github.bindglam.core.utils.AdvancementUtil;
/*     */ import io.github.bindglam.economy.EconomyManager;
/*     */ import io.papermc.paper.event.player.AsyncChatEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.UUID;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import net.kyori.adventure.text.TextComponent;
/*     */ import net.kyori.adventure.text.format.NamedTextColor;
/*     */ import net.kyori.adventure.text.format.TextColor;
/*     */ import net.kyori.adventure.text.format.TextDecoration;
/*     */ import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.ClickType;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.persistence.PersistentDataType;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class UserShopMenu extends Menu implements Listener {
/*     */   public String getName() {
/*  45 */     return "유저 상점";
/*     */   }
/*     */   
/*     */   public Page[] getPages() {
/*  50 */     Page[] pages = new Page[getPageCount()];
/*  52 */     for (int i = 0; i < getPageCount(); i++) {
/*  53 */       final int finalI = i;
/*  54 */       pages[i] = new Page() {
/*     */           public String getName() {
/*  57 */             return "유저 상점 ( 페이지 " + finalI + 1 + " )";
/*     */           }
/*     */           
/*     */           public ItemStack[] getContent() {
/*  62 */             ItemStack[] contents = ((PageType)Objects.<PageType>requireNonNull(getPageType())).getBlankArray();
/*  64 */             int i = 0;
/*  65 */             for (UUID uuid : UserShopManager.data.keySet()) {
/*  66 */               /*  67 */
        /*  68 */
        contents[i] = (new AdvItemCreator((ItemStack) UserShopManager.data.get(uuid).get(0))).setLore(Component.empty(), Component.text("가격 : " + UserShopManager.data.get(uuid).get(1) + "원").color(TextColor.color(255, 200, 0)), Component.text("소유자 : " + Bukkit.getOfflinePlayer((UUID) UserShopManager.data.get(uuid).get(2)).getName()).color(NamedTextColor.BLUE)).addPersistentData("cost", (Integer) UserShopManager.data.get(uuid).get(1))
/*  70 */                 .addPersistentData("uuid", uuid.toString())
/*  71 */                 .addPersistentData("owner", UserShopManager.data.get(uuid).get(2).toString())
/*  72 */                 .getItemStack();
/*  73 */               i++;
/*     */             } 
/*  76 */             contents[45] = (new AdvItemCreator(Material.ARROW)).setDisplayName("§c뒤로가기").getItemStack();
/*  77 */             contents[46] = (new AdvItemCreator(Material.GREEN_STAINED_GLASS_PANE)).setDisplayName("§a§l등록").getItemStack();
/*  78 */             contents[47] = (new AdvItemCreator(Material.RED_STAINED_GLASS_PANE)).setDisplayName("§c§l삭제").getItemStack();
/*  79 */             contents[48] = (new AdvItemCreator(Material.YELLOW_STAINED_GLASS_PANE)).setDisplayName("§e§l등록한 아이템 ID 목록").getItemStack();
/*  80 */             contents[53] = (new AdvItemCreator(Material.ARROW)).setDisplayName("§a다음으로").getItemStack();
/*  81 */             return contents;
/*     */           }
/*     */           
/*     */           public PageType getPageType() {
/*  86 */             return PageType.DOUBLE_CHEST;
/*     */           }
/*     */           
/*     */           public void onClick(MenuView view, ClickType clickType, int slot, ItemStack itemStack) {
/*     */             NamespacedKey costKey, ownerKey, uuidKey;
/*     */             int cost;
/*     */             String ownerUUID;
/*     */             UUID itemUUID;
/*     */             OfflinePlayer owner;
/*     */             Player ownerOnline;
/*  91 */             if (itemStack == null) {
/*  92 */               (new UserShopMenu()).open(view.getPlayer(), view.getCurrentPageIndex());
/*     */               return;
/*     */             } 
/*  95 */             ItemMeta meta = itemStack.getItemMeta();
/*  96 */             if (meta == null)
/*     */               return; 
/*  98 */             switch (slot) {
/*     */               case 45:
/* 100 */                 view.previousPage();
/*     */                 break;
/*     */               case 46:
/* 104 */                 view.getPlayer().closeInventory();
/* 105 */                 view.getPlayer().sendMessage(((TextComponent)Component.text("/유저상점 등록 <가격> <- 손에 든 아이템을 유저상점에 등록합니다.").color((TextColor)NamedTextColor.YELLOW)));
/*     */                 break;
/*     */               case 47:
/* 110 */                 view.getPlayer().closeInventory();
/* 111 */                 view.getPlayer().sendMessage(((TextComponent)Component.text("/유저상점 삭제 <ID> <- 입력한 ID의 아이템을 유저상점에서 삭제합니다.").color((TextColor)NamedTextColor.YELLOW)));
/*     */                 break;
/*     */               case 48:
/* 116 */                 view.getPlayer().closeInventory();
/* 117 */                 Bukkit.dispatchCommand((CommandSender)view.getPlayer(), "유저상점 목록");
/*     */                 break;
/*     */               case 53:
/* 121 */                 view.nextPage();
/*     */                 break;
/*     */               default:
/* 125 */                 costKey = new NamespacedKey((Plugin)Core.INSTANCE, "cost");
/* 126 */                 ownerKey = new NamespacedKey((Plugin)Core.INSTANCE, "owner");
/* 127 */                 uuidKey = new NamespacedKey((Plugin)Core.INSTANCE, "uuid");
/* 128 */                 cost = ((Integer)meta.getPersistentDataContainer().get(costKey, PersistentDataType.INTEGER)).intValue();
/* 129 */                 ownerUUID = (String)meta.getPersistentDataContainer().get(ownerKey, PersistentDataType.STRING);
/* 130 */                 itemUUID = UUID.fromString((String)meta.getPersistentDataContainer().get(uuidKey, PersistentDataType.STRING));
/* 132 */                 if (EconomyManager.getAmount(view.getPlayer().getUniqueId()) < cost) {
/* 133 */                   view.getPlayer().sendMessage(((TextComponent)Component.text("소지금이 부족합니다!").color(TextColor.color(255, 0, 0))));
/*     */                   return;
/*     */                 } 
/* 137 */                 owner = Bukkit.getOfflinePlayer(UUID.fromString(Objects.<String>requireNonNull(ownerUUID)));
/* 138 */                 EconomyManager.deposit(owner.getUniqueId(), cost);
/* 139 */                 EconomyManager.withdraw(view.getPlayer().getUniqueId(), cost);
/* 140 */                 ownerOnline = Bukkit.getPlayer(owner.getUniqueId());
/* 141 */                 if (ownerOnline != null) {
/* 142 */                   ownerOnline.sendMessage((Component)Component.text(view.getPlayer().getName() + "님이 아이템을 구매하셨습니다!"));
/* 143 */                   AdvancementUtil.awardAdvancement(ownerOnline, new NamespacedKey((Plugin)Core.INSTANCE, "sell_usershop"), "complete");
/*     */                 } 
/* 145 */                 view.getPlayer().sendMessage(Component.text("성공적으로 아이템을 구매했습니다!").color(TextColor.color(0, 255, 0)));
/* 146 */                 view.getPlayer().getInventory().addItem((ItemStack) UserShopManager.data.get(itemUUID).get(0));
/* 147 */                 UserShopManager.data.remove(itemUUID);
/*     */                 break;
/*     */             } 
/* 151 */             if (slot < 45)
/* 152 */               (new UserShopMenu()).open(view.getPlayer(), view.getCurrentPageIndex()); 
/*     */           }
/*     */         };
/*     */     } 
/* 156 */     return pages;
/*     */   }
/*     */   
/*     */   public int getPageCount() {
/* 161 */     return UserShopManager.data.size() / (PageType.DOUBLE_CHEST.getRow() - 1) * 9 + 1;
/*     */   }
}


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\menu\shops\UserShopMenu.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */