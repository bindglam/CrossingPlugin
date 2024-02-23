/*     */ package net.roxeez.advancement.trigger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TriggerType
/*     */ {
/*  13 */   public static final TriggerWrapper<BeeNestDestroyed> BEE_NEST_DESTROYED = new TriggerWrapper<>("bee_nest_destroyed", BeeNestDestroyed.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  18 */   public static final TriggerWrapper<BredAnimals> BRED_ANIMALS = new TriggerWrapper<>("bred_animals", BredAnimals.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  23 */   public static final TriggerWrapper<InventoryChanged> INVENTORY_CHANGED = new TriggerWrapper<>("inventory_changed", InventoryChanged.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  28 */   public static final TriggerWrapper<PlacedBlock> PLACED_BLOCK = new TriggerWrapper<>("placed_block", PlacedBlock.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public static final TriggerWrapper<BrewedPotion> BREWED_POTION = new TriggerWrapper<>("brewed_potion", BrewedPotion.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   public static final TriggerWrapper<ChangedDimension> CHANGED_DIMENSION = new TriggerWrapper<>("changed_dimension", ChangedDimension.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public static final TriggerWrapper<ChanneledLightning> CHANNELED_LIGHTNING = new TriggerWrapper<>("channeled_lightning", ChanneledLightning.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public static final TriggerWrapper<ConstructBeacon> CONSTRUCT_BEACON = new TriggerWrapper<>("construct_beacon", ConstructBeacon.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public static final TriggerWrapper<ConsumeItem> CONSUME_ITEM = new TriggerWrapper<>("consume_item", ConsumeItem.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public static final TriggerWrapper<CuredZombieVillager> CURED_ZOMBIE_VILLAGER = new TriggerWrapper<>("cured_zombie_villager", CuredZombieVillager.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public static final TriggerWrapper<EffectsChanged> EFFECTS_CHANGED = new TriggerWrapper<>("effects_changed", EffectsChanged.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public static final TriggerWrapper<EnchantedItem> ENCHANTED_ITEM = new TriggerWrapper<>("enchanted_item", EnchantedItem.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public static final TriggerWrapper<EnterBlock> ENTER_BLOCK = new TriggerWrapper<>("enter_block", EnterBlock.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public static final TriggerWrapper<EntityHurtPlayer> ENTITY_HURT_PLAYER = new TriggerWrapper<>("entity_hurt_player", EntityHurtPlayer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public static final TriggerWrapper<EntityKilledPlayer> ENTITY_KILLED_PLAYER = new TriggerWrapper<>("entity_killed_player", EntityKilledPlayer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public static final TriggerWrapper<FilledBucket> FILLED_BUCKET = new TriggerWrapper<>("filled_bucket", FilledBucket.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public static final TriggerWrapper<FishingRodHooked> FISHING_ROD_HOOKED = new TriggerWrapper<>("fishing_rod_hooked", FishingRodHooked.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public static final TriggerWrapper<HeroOfTheVillage> HERO_OF_THE_VILLAGE = new TriggerWrapper<>("hero_of_the_village", HeroOfTheVillage.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public static final TriggerWrapper<Impossible> IMPOSSIBLE = new TriggerWrapper<>("impossible", Impossible.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public static final TriggerWrapper<ItemDurabilityChanged> ITEM_DURABILITY_CHANGED = new TriggerWrapper<>("item_durability_changed", ItemDurabilityChanged.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public static final TriggerWrapper<ItemUsedOnBlock> ITEM_USED_ON_BLOCK = new TriggerWrapper<>("item_used_on_block", ItemUsedOnBlock.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public static final TriggerWrapper<KilledByCrossbow> KILLED_BY_CROSSBOW = new TriggerWrapper<>("killed_by_crossbow", KilledByCrossbow.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public static final TriggerWrapper<Levitation> LEVITATION = new TriggerWrapper<>("levitation", Levitation.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public static final TriggerWrapper<Location> LOCATION = new TriggerWrapper<>("location", Location.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   public static final TriggerWrapper<NetherTravel> NETHER_TRAVEL = new TriggerWrapper<>("nether_travel", NetherTravel.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public static final TriggerWrapper<PlayerGeneratesContainerLoot> PLAYER_GENERATES_CONTAINER_LOOT = new TriggerWrapper<>("player_generates_container_loot", PlayerGeneratesContainerLoot.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public static final TriggerWrapper<PlayerHurtEntity> PLAYER_HURT_ENTITY = new TriggerWrapper<>("player_hurt_entity", PlayerHurtEntity.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public static final TriggerWrapper<PlayerInteractedWithEntity> PLAYER_INTERACTED_WITH_ENTITY = new TriggerWrapper<>("player_interacted_with_entity", PlayerInteractedWithEntity.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public static final TriggerWrapper<PlayerKilledEntity> PLAYER_KILLED_ENTITY = new TriggerWrapper<>("player_killed_entity", PlayerKilledEntity.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public static final TriggerWrapper<RecipeUnlocked> RECIPE_UNLOCKED = new TriggerWrapper<>("recipe_unlocked", RecipeUnlocked.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   public static final TriggerWrapper<ShotCrossbow> SHOT_CROSSBOW = new TriggerWrapper<>("short_crossbow", ShotCrossbow.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 168 */   public static final TriggerWrapper<SleptInBed> SLEPT_IN_BED = new TriggerWrapper<>("slept_in_bed", SleptInBed.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 173 */   public static final TriggerWrapper<SlideDownBlock> SLIDE_DOWN_BLOCK = new TriggerWrapper<>("slide_down_block", SlideDownBlock.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 179 */   public static final TriggerWrapper<SummonedEntity> SUMMONED_ENTITY = new TriggerWrapper<>("summoned_entity", SummonedEntity.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   public static final TriggerWrapper<TameAnimal> TAME_ANIMAL = new TriggerWrapper<>("tame_animal", TameAnimal.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   public static final TriggerWrapper<TargetHit> TARGET_HIT = new TriggerWrapper<>("target_hit", TargetHit.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public static final TriggerWrapper<ThrownItemPickedUpByEntity> THROWN_ITEM_PICKED_UP_BY_ENTITY = new TriggerWrapper<>("throw_item_picked_up_by_entity", ThrownItemPickedUpByEntity.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   public static final TriggerWrapper<Tick> TICK = new TriggerWrapper<>("tick", Tick.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public static final TriggerWrapper<UsedEnderEye> USED_ENDER_EYE = new TriggerWrapper<>("used_ender_eye", UsedEnderEye.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 209 */   public static final TriggerWrapper<UsedTotem> USED_TOTEM = new TriggerWrapper<>("used_totem", UsedTotem.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 214 */   public static final TriggerWrapper<VillagerTrade> VILLAGER_TRADE = new TriggerWrapper<>("villager_trade", VillagerTrade.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 219 */   public static final TriggerWrapper<VoluntaryExile> VOLUNTARY_EXILE = new TriggerWrapper<>("voluntary_exile", VoluntaryExile.class);
/*     */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\TriggerType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */