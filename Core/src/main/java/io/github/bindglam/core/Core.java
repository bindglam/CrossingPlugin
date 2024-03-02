package io.github.bindglam.core;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import io.github.bindglam.core.advancements.*;
import io.github.bindglam.core.commands.*;
import io.github.bindglam.core.commands.tabcompletors.EmoteTabCompletion;
import io.github.bindglam.core.items.PluginItemManager;
import io.github.bindglam.core.listeners.*;
import io.github.bindglam.core.managers.*;
import io.github.bindglam.core.menu.npc.BankerMenu;
import io.github.bindglam.core.menu.core.GroundMenu;
import io.github.bindglam.core.menu.shops.ShopMenu;
import io.github.bindglam.core.npc.InteractNPCManager;
import io.github.bindglam.ground.GroundManager;
import io.github.bindglam.ground.GroundPlugin;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.roxeez.advancement.AdvancementManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Core extends JavaPlugin {
    public static Core INSTANCE;

    public MultiverseCore multiverseCore;
    public MVWorldManager worldManager;
    public AdvancementManager advancementManager;
    public LuckPerms luckPerms;

    @Override
    public void onEnable() {
        INSTANCE = this;
        multiverseCore = (MultiverseCore) getServer().getPluginManager().getPlugin("Multiverse-Core");
        assert multiverseCore != null;
        worldManager = multiverseCore.getMVWorldManager();
        advancementManager = new AdvancementManager(this);
        luckPerms = LuckPermsProvider.get();

        this.advancementManager.register(new BossAdvancement());
        this.advancementManager.register(new BossAdvancement.AncientFighter());
        this.advancementManager.register(new BossAdvancement.Hydra());
        this.advancementManager.register(new MinecraftCrossAdvancement());
        this.advancementManager.register(new EnhanceAdvancement());
        this.advancementManager.register(new TenEnhanceAdvancement());
        this.advancementManager.register(new GetRubyAdvancement());
        this.advancementManager.register(new SpawnBossAdvancement());
        this.advancementManager.register(new KillPlayerAdvancement());
        this.advancementManager.register(new UserShopAdvancement());
        this.advancementManager.register(new SellUserShopAdvancement());
        this.advancementManager.register(new StockAdvancement());
        this.advancementManager.register(new SellStockAdvancement());
        this.advancementManager.register(new SendMoneyAdvancement());
        this.advancementManager.register(new SendMoneyKingAdvancement());
        this.advancementManager.register(new CheckAdvancement());
        this.advancementManager.register(new BigCheckAdvancement());
        this.advancementManager.register(new LostHealthAdvancement());
        this.advancementManager.register(new RecoveryHealthAdvancement());
        this.advancementManager.register(new KillPlayerTwentyFiveTimesAdvancement());

        getServer().getPluginManager().registerEvents(new PlayerJoinQuitLoginListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerBlockListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerActionListener(), this);
        getServer().getPluginManager().registerEvents(new EntityListener(), this);
        getServer().getPluginManager().registerEvents(new WorldListener(), this);
        getServer().getPluginManager().registerEvents(new ServerTickListener(), this);

        getServer().getPluginManager().registerEvents(new InteractNPCManager(), this);
        getServer().getPluginManager().registerEvents(new PluginItemManager(), this);

        getServer().getPluginManager().registerEvents(new BankerMenu(null), this);
        getServer().getPluginManager().registerEvents(new GroundMenu(), this);

        getServer().getPluginManager().registerEvents(new SitCommand(), this);

        Objects.requireNonNull(getCommand("menu")).setExecutor(new MenuCommand());
        Objects.requireNonNull(getCommand("backto")).setExecutor(new BackToCommand());
        Objects.requireNonNull(getCommand("money")).setExecutor(new MoneyCommand());
        Objects.requireNonNull(getCommand("clearbank")).setExecutor(new ClearBankCommand());
        Objects.requireNonNull(getCommand("basicitems")).setExecutor(new BasicItemsCommand());
        Objects.requireNonNull(getCommand("resetmaxhealth")).setExecutor(new ResetMaxHealthCommand());
        Objects.requireNonNull(getCommand("floorcleaner")).setExecutor(new FloorCleanerCommand());
        Objects.requireNonNull(getCommand("yescmd")).setExecutor(new YesNoCommand());
        Objects.requireNonNull(getCommand("nocmd")).setExecutor(new YesNoCommand());
        Objects.requireNonNull(getCommand("check")).setExecutor(new CheckCommand());
        Objects.requireNonNull(getCommand("tpa")).setExecutor(new TpACommand());
        Objects.requireNonNull(getCommand("tpaccept")).setExecutor(new TpACommand());
        Objects.requireNonNull(getCommand("home")).setExecutor(new HomeCommand());
        Objects.requireNonNull(getCommand("sethome")).setExecutor(new HomeCommand());
        Objects.requireNonNull(getCommand("trashbin")).setExecutor(new TrashbinCommand());
        Objects.requireNonNull(getCommand("shop")).setExecutor(new ShopCommand());
        Objects.requireNonNull(getCommand("stock")).setExecutor(new StockCommand());
        Objects.requireNonNull(getCommand("stats")).setExecutor(new StatsCommand());
        Objects.requireNonNull(getCommand("tutorial")).setExecutor(new TutorialCommand());
        Objects.requireNonNull(getCommand("crossemote")).setExecutor(new EmoteCommand());
        Objects.requireNonNull(getCommand("usershop")).setExecutor(new UserShopCommand());
        Objects.requireNonNull(getCommand("eventcoin")).setExecutor(new EventCoinCommand());
        Objects.requireNonNull(getCommand("sendmoney")).setExecutor(new SendMoneyCommand());
        Objects.requireNonNull(getCommand("hat")).setExecutor(new HatCommand());
        Objects.requireNonNull(getCommand("sit")).setExecutor(new SitCommand());
        Objects.requireNonNull(getCommand("donate")).setExecutor(new DonateCommand());
        Objects.requireNonNull(getCommand("donatepoint")).setExecutor(new DonatePointCommand());
        Objects.requireNonNull(getCommand("core-data")).setExecutor(new DataCommand());
        Objects.requireNonNull(getCommand("core-data")).setExecutor(new DataCommand());
        Objects.requireNonNull(getCommand("ban-player")).setExecutor(new WarnBanCommand());
        Objects.requireNonNull(getCommand("warn")).setExecutor(new WarnBanCommand());

        Objects.requireNonNull(getCommand("crossemote")).setTabCompleter(new EmoteTabCompletion());

        getConfig().options().copyDefaults(true);
        saveConfig();

        loadData();
        DonateManager.init();

        Bukkit.getScheduler().runTaskLater(this, () -> {
            getLogger().warning(" ! 서버를 재부팅하신거라면, 이플러그인을 한번 리로드해야합니다! ! ");
        }, 80L);

        for(Player player : Bukkit.getOnlinePlayers()){
            PrivateSettingManager.loadPlayer(player.getUniqueId());
        }
    }

    @Override
    public void onDisable() {
        saveData(true);
        DonateManager.toonation.close();
        //DonateManager.twip.close();

        MaxHealthManager.save(); // 마지막!!!
        saveConfig();
    }

    public static void loadData(){
        GroundManager.load();
        BankManager.init();
        BasicItemsCommand.init();
        PluginItemManager.init();
        ServerTickListener.init();
        HomeManager.load();
        StockManager.init();
        StatsManager.init();
        //ShopMenu.load();
        UserShopManager.load();
        DivingPointManager.init();
        EventCoinManager.init();
        DonatePointManager.init();
        BanManager.init();
    }

    public static void saveData(boolean disabling){
        GroundManager.save();
        BankManager.save();
        BasicItemsCommand.save();
        HomeManager.save();
        StatsManager.save();
        //ShopMenu.save();
        PrivateSettingManager.save();
        StockManager.save();
        UserShopManager.save();
        EventCoinManager.save();
        DivingPointManager.save();
        DonatePointManager.save();
        BanManager.save();
        if(!disabling) {
            GroundPlugin.INSTANCE.saveConfig();
            Core.INSTANCE.saveConfig();
        }
    }
}
