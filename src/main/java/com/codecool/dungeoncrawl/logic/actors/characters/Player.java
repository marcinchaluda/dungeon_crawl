package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.components.Inventory;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import com.codecool.dungeoncrawl.logic.actors.items.Weapon;

public class Player extends Person {

    private Inventory inventory;
    private Weapon chosenWeapon;

    public Player(Cell cell) {
        super(cell);
        this.name = "Player";
        this.strength = 5;
        this.health = 10;
        this.inventory = new Inventory();
    }

    public Player(Cell cell, Inventory inventory) {
        super(cell);
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void getItemFromTheFloor(Item obtainedItem) {
        inventory.addItemToInventory(obtainedItem);
    }

    public String getTileName() {
        return "player";
    }

    public void setWeapon(Weapon chosenWeapon) {
        this.chosenWeapon = chosenWeapon;
    }

    public Weapon getWeapon() {
        return chosenWeapon;
    }

    public int getStrengthBasedOnWeapon() {
        if (chosenWeapon != null) {
            return chosenWeapon.getStatistic() + strength;
        }
        return strength;
    }

    @Override
    public void actionAfterDefeat(Actor actorWhichDefeatedPlayer) {
        // show message after defeat
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = getNextCell(dx, dy);
        if (isNextFieldEmpty(nextCell) && isEncounterDone(nextCell)) {
            updatePosition(nextCell);
        }
    }

    public void openDoor() {

    }
}
