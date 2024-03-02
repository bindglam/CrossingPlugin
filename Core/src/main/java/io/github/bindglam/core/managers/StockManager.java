package io.github.bindglam.core.managers;

import io.github.bindglam.core.Core;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockManager {
    public static final HashMap<String, ArrayList<Object>> stocks = new HashMap<>();
    public static final int stockChangeTime = 6000;
    public static int stockChangeTimer = stockChangeTime;

    public static void init(){
        Bukkit.broadcast(Component.text("주식 정보 로드중...").color(NamedTextColor.WHITE));
        StockManager.stocks.put("마크의 숲 (게임 회사)", new ArrayList<>(List.of(
                1000, //상장가
                1000, //매매가
                1000, //상한가
                1000, //하한가
                1000, //최대 변동가
                99999, //최대 갯수
                99999, //현재 갯수
                "0" //기록
        )));

        StockManager.stocks.put("피치 (핸드폰 회사)", new ArrayList<>(List.of(
                5000, //상장가
                5000, //매매가
                5000, //상한가
                5000, //하한가
                5000, //최대 변동가
                999, //최대 갯수
                999, //현재 갯수
                "0" //기록
        )));

        StockManager.stocks.put("약강강약 (제약 회사)", new ArrayList<>(List.of(
                2000, //상장가
                2000, //매매가
                2000, //상한가
                2000, //하한가
                2000, //최대 변동가
                9999, //최대 갯수
                9999, //현재 갯수
                "0" //기록
        )));

        StockManager.stocks.put("광(물이) 물(흐르듯이 나오다) (광산 회사)", new ArrayList<>(List.of(
                2500, //상장가
                2500, //매매가
                2500, //상한가
                2500, //하한가
                2500, //최대 변동가
                9999, //최대 갯수
                9999, //현재 갯수
                "0" //기록
        )));

        if(Core.INSTANCE.getConfig().get("StockNames") != null){
            for(String name : Core.INSTANCE.getConfig().getStringList("StockNames")){
                ArrayList<Object> data = new ArrayList<>();

                for(int i = 0; i < 8; i++){
                    data.add(Core.INSTANCE.getConfig().get("Stocks." + name + "." + i));
                }
                stocks.put(name, data);
            }
        }

        if(Core.INSTANCE.getConfig().get("StockChangeTimer") != null){
            stockChangeTimer = Core.INSTANCE.getConfig().getInt("StockChangeTimer");
        }
    }

    public static void save(){
        Bukkit.broadcast(Component.text("주식 정보 저장중...").color(NamedTextColor.WHITE));
        Core.INSTANCE.getConfig().set("StockNames", stocks.keySet().stream().toList());
        Core.INSTANCE.getConfig().set("StockChangeTimer", stockChangeTimer);
        for(String name : stocks.keySet()){
            List<Object> data = stocks.get(name);

            for(int i = 0; i < data.size(); i++) {
                Core.INSTANCE.getConfig().set("Stocks." + name + "." + i, data.get(i));
            }
        }
    }
}
