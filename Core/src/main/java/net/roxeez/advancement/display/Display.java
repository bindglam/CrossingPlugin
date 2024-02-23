/*     */ package net.roxeez.advancement.display;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.function.Consumer;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Display
/*     */ {
/*     */   @Expose
/*     */   @SerializedName("title")
/*  19 */   private String title = "UNDEFINED";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Expose
/*     */   @SerializedName("description")
/*  26 */   private String description = "UNDEFINED";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Expose
/*     */   @SerializedName("icon")
/*     */   private Icon icon;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Expose
/*     */   @SerializedName("background")
/*     */   private NamespacedKey background;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Expose
/*     */   @SerializedName("frame")
/*     */   private FrameType frame;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Expose
/*     */   @SerializedName("show_toast")
/*     */   private boolean toast = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Expose
/*     */   @SerializedName("announce_to_chat")
/*     */   private boolean announce = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Expose
/*     */   @SerializedName("hidden")
/*     */   private boolean hidden = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitle(@NotNull String title) {
/*  78 */     Preconditions.checkNotNull(title);
/*  79 */     this.title = title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(@NotNull String description) {
/*  88 */     Preconditions.checkNotNull(description);
/*  89 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFrame(@NotNull FrameType frame) {
/*  98 */     Preconditions.checkNotNull(frame);
/*  99 */     this.frame = frame;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIcon(@NotNull Icon icon) {
/* 108 */     Preconditions.checkNotNull(icon);
/* 109 */     this.icon = icon;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIcon(@NotNull Consumer<Icon> consumer) {
/* 119 */     Preconditions.checkNotNull(consumer);
/* 120 */     Icon icon = new Icon();
/* 121 */     consumer.accept(icon);
/*     */     
/* 123 */     this.icon = icon;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIcon(@NotNull Material material) {
/* 132 */     this.icon = new Icon(material, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIcon(@NotNull Material material, String nbt) {
/* 142 */     this.icon = new Icon(material, nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBackground(@NotNull BackgroundType background) {
/* 152 */     Preconditions.checkNotNull(background);
/* 153 */     this.background = background.getTexture();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBackground(@NotNull NamespacedKey key) {
/* 163 */     Preconditions.checkNotNull(key);
/* 164 */     this.background = key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setToast(boolean toast) {
/* 173 */     this.toast = toast;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAnnounce(boolean announce) {
/* 182 */     this.announce = announce;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHidden(boolean hidden) {
/* 191 */     this.hidden = hidden;
/*     */   }
/*     */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\display\Display.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */