package io.github.bindglam.economy;

import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EconomyManager {
    @lombok.Getter
    @Deprecated
    private static final HashMap<String, Double> money = new HashMap<>();
    @lombok.Getter
    @Deprecated
    private static final HashMap<String, Double> bank = new HashMap<>();

    private static final HashMap<UUID, Double> moneyData = new HashMap<>();
    private static final HashMap<UUID, Double> bankData = new HashMap<>();

    public static void init() throws IOException, InvalidConfigurationException {
        for(String data : Economy.INSTANCE.getConfig().getStringList("Money")){
            String name = data.split(":")[0];
            Double amount = Double.parseDouble(data.split(":")[1]);
            money.put(name, amount);
        }

        for(String data : Economy.INSTANCE.getConfig().getStringList("Bank")){
            String name = data.split(":")[0];
            Double amount = Double.parseDouble(data.split(":")[1]);
            bank.put(name, amount);
        }

        for(String data : Economy.INSTANCE.getConfig().getStringList("MoneyData")){
            String name = data.split(":")[0];
            Double amount = Double.parseDouble(data.split(":")[1]);
            moneyData.put(UUID.fromString(name), amount);
        }

        for(String data : Economy.INSTANCE.getConfig().getStringList("BankData")){
            String name = data.split(":")[0];
            Double amount = Double.parseDouble(data.split(":")[1]);
            bankData.put(UUID.fromString(name), amount);
        }
    }

    public static void save() throws IOException {
        List<String> data = new ArrayList<>();
        for(String name : money.keySet()){
            data.add(name + ":" + money.get(name));
        }
        Economy.INSTANCE.getConfig().set("Money", data);

        List<String> data2 = new ArrayList<>();
        for(String name : bank.keySet()){
            data2.add(name + ":" + bank.get(name));
        }
        Economy.INSTANCE.getConfig().set("Bank", data2);

        List<String> data3 = new ArrayList<>();
        for(UUID name : moneyData.keySet()){
            data3.add(name.toString() + ":" + moneyData.get(name));
        }
        Economy.INSTANCE.getConfig().set("MoneyData", data3);

        List<String> data4 = new ArrayList<>();
        for(UUID name : bankData.keySet()){
            data4.add(name.toString() + ":" + bankData.get(name));
        }
        Economy.INSTANCE.getConfig().set("BankData", data4);
        Economy.INSTANCE.saveConfig();
    }

    @Deprecated
    public static Integer getAmount(String name){
        checkExist(name);
        return money.get(name);
    }

    @Deprecated
    public static Integer getBankAmount(String name){
        checkExistBank(name);
        return bank.get(name);
    }

    @Deprecated
    public static void deposit(String name, int amount){
        checkExist(name);
        money.put(name, money.get(name)+amount);
    }

    @Deprecated
    public static void withdraw(String name, int amount){
        checkExist(name);
        money.put(name, money.get(name)-amount);
    }

    @Deprecated
    public static void depositBank(String name, int amount){
        checkExistBank(name);
        bank.put(name, bank.get(name)+amount);
    }

    @Deprecated
    public static void withdrawBank(String name, int amount){
        checkExistBank(name);
        bank.put(name, bank.get(name)-amount);
    }

    @Deprecated
    private static void checkExist(String name){
        if(!money.containsKey(name))
            money.put(name, 0);
    }

    @Deprecated
    private static void checkExistBank(String name){
        if(!bank.containsKey(name))
            bank.put(name, 0);
    }

    public static Integer getAmount(UUID name){
        checkExist(name);
        return moneyData.get(name);
    }

    public static Integer getBankAmount(UUID name){
        checkExistBank(name);
        return bankData.get(name);
    }

    public static void deposit(UUID name, int amount){
        checkExist(name);
        moneyData.put(name, moneyData.get(name)+amount);
    }

    public static void withdraw(UUID name, int amount){
        checkExist(name);
        moneyData.put(name, moneyData.get(name)-amount);
    }

    public static void depositBank(UUID name, int amount){
        checkExistBank(name);
        bankData.put(name, bankData.get(name)+amount);
    }

    public static void withdrawBank(UUID name, int amount){
        checkExistBank(name);
        bankData.put(name, bankData.get(name)-amount);
    }

    private static void checkExist(UUID name){
        if(!moneyData.containsKey(name))
            moneyData.put(name, 0);
    }

    private static void checkExistBank(UUID name){
        if(!bankData.containsKey(name))
            bankData.put(name, 0);
    }
}
