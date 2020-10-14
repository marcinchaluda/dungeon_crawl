package com.codecool.dungeoncrawl.serializer;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SerializerManager {
    private GameState state;
    private InventoryModel inventory;
    private PlayerModel player;

    public void serializeGameStateToFile(GameMap map, String filePath) throws IOException {
        // TODO add gameState to serialization
        InventoryModel inventoryModel = new InventoryModel();
        List<ItemModel> items = new ArrayList<>();
        for (Item item: map.getPlayer().getInventory().getItems()) {
            items.add(new ItemModel(item));
        }
        inventoryModel.setItems(items);
        PlayerModel playerModel = new PlayerModel(map.getPlayer(), inventoryModel, new ItemModel(map.getPlayer().getWeapon()));

        new Gson().toJson(playerModel, new FileWriter(filePath));
    }

    public void deserializeGameStateGson(String filePath) throws FileNotFoundException {
        this.state = new Gson().fromJson(new FileReader(filePath), GameState.class);
        this.player = state.getPlayer();
        this.inventory = player.getInventory();
    }

    public GameState getStateModel() {
        return state;
    }

    public PlayerModel getPlayerModel() {
        return player;
    }

    public InventoryModel getInventoryModel() {
        return inventory;
    }
}
