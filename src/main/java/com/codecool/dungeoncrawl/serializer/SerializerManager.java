package com.codecool.dungeoncrawl.serializer;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import com.codecool.dungeoncrawl.model.GameStateModel;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;

import java.io.*;
import java.sql.Date;
import java.util.Arrays;

public class SerializerManager {

    public static void serializeGameStateToFile(GameMap map, File file) throws IOException {
        InventoryModel inventoryModel = new InventoryModel(map.getPlayer().getInventory());
        PlayerModel playerModel = new PlayerModel(map.getPlayer());
        playerModel.setInventory(inventoryModel);
        setWeaponInUse(map, playerModel);
        playerModel.getPlayer().setPlayerCell(map.getPlayer().getCell());

        Map currentMap = getCurrentMap(map);

        GameStateModel gameStateModel = new GameStateModel(currentMap, new Date(System.currentTimeMillis()), playerModel, file.getName());
        if (!file.getName().toLowerCase().endsWith(".json")) {
            file = new File(file.getAbsolutePath() + ".json");
        }
        FileWriter writer = new FileWriter(file);
        new Gson().toJson(gameStateModel, writer);
        writer.flush();
        writer.close();
    }

    private static Map getCurrentMap(GameMap map) {
        return Arrays.stream(Map.values()).
                filter(mapEntry -> mapEntry.getId() == map.getLevelId()).
                findFirst().
                orElseThrow(() -> new RuntimeException("Error while retrieving map with id: " + map.getLevelId()));
    }

    private static void setWeaponInUse(GameMap map, PlayerModel playerModel) {
        for (Item item: map.getPlayer().getInventory().getItems()) {
            if (item.getName().equals(map.getPlayer().getWeapon().getName())){
                playerModel.setWeapon(new ItemModel(item));
            }
        }
    }

    public static GameStateModel deserializeGameStateGson(File file) throws FileNotFoundException {
        FileReader reader = new FileReader(file.getAbsolutePath());
        GameStateModel gameState =  new Gson().fromJson(reader, GameStateModel.class);
        return gameState;
    }

}
