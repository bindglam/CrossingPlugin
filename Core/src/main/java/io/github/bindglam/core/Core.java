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

    @Override
    public void onEnable() {
        INSTANCE = this;
        multiverseCore = (MultiverseCore) getServer().getPluginManager().getPlugin("Multiverse-Core");
        assert multiverseCore != null;
        worldManager = multiverseCore.getMVWorldManager();
        advancementManager = new AdvancementManager(this);

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

        getServer().getPluginManager().registerEvents(new CheckCommand(), this);

        Objects.requireNonNull(getCommand("menu")).setExecutor(new MenuCommand());
        Objects.requireNonNull(getCommand("backto")).setExecutor(new BackToCommand());
        Objects.requireNonNull(getCommand("money")).setExecutor(new MoneyCommand());
        Objects.requireNonNull(getCommand("deposit")).setExecutor(new DepositWithdrawCommand());
        Objects.requireNonNull(getCommand("withdraw")).setExecutor(new DepositWithdrawCommand());
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

        Objects.requireNonNull(getCommand("crossemote")).setTabCompleter(new EmoteTabCompletion());

        getConfig().options().copyDefaults(true);
        saveConfig();

        BankManager.init();
        BasicItemsCommand.init();
        PluginItemManager.init();
        ServerTickListener.init();
        HomeManager.load();
        StockManager.init();
        StatsManager.init();
        //ShopMenu.load();
        UserShopManager.load();
        EventCoinManager.init();

        Bukkit.getScheduler().runTaskLater(this, () -> {
            getLogger().warning(" ! 서버를 재부팅하신거라면, 이플러그인을 한번 리로드해야합니다! ! ");
        }, 80L);

        for(Player player : Bukkit.getOnlinePlayers()){
            PrivateSettingManager.loadPlayer(player.getUniqueId());
        }
    }

    @Override
    public void onDisable() {
        BankManager.save();
        BasicItemsCommand.save();
        HomeManager.save();
        StatsManager.save();
        //ShopMenu.save();
        MaxHealthManager.save();
        PrivateSettingManager.save();
        StockManager.save();
        UserShopManager.save();
        EventCoinManager.save();
        saveConfig();
    }
}
