package com.example;

public class Entity {
    private int x;
    private int y;
    private String type;
    private String color;

    public Entity(int x, int y, String type, String color) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.color = color;
    }

    private boolean checkTile(int x, int y) {
        if(Map.getTile(x, y).getColor() == Map.getTile(this.x, this.y).getColor()) return true;
        return false;
    }

    public boolean moveLeft() {
        if(checkTile(this.x - 1, this.y)) {
            x--;
            return true;
        }
        return false;
    }

    public boolean moveUp() {
        if(checkTile(this.x, this.y - 1)) {
            y--;
            return true;
        }
        return false;
    }

    public boolean moveRight() {
        if(checkTile(this.x + 1, this.y)) {
            x++;
            return true;
        }
        return false;
    }

    public boolean moveDown() {
        if(checkTile(this.x, this.y + 1)) {
            y++;
            return true;
        }
        return false;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return Main.addColor(this.color) + "  " + "\033[0m";
    }
}
