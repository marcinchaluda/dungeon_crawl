package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Item {

    public Key(Cell cell) {
        super(cell);
        this.name = "Key";
        this.obtainMessage = "You have obtained a key!";
    }

    public String getTileName() {
        return "key";
    }
}
