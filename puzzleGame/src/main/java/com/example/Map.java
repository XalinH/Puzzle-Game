package com.example;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private static int maxX;
    private static int maxY;
    private static Tile[][] currentMap = new Tile[50][50];
    private static List<Entity> entityList = new ArrayList<Entity>();

    public static void changeToMap1() {
        currentMap = new Tile[50][50];
        entityList.clear();
        entityList.add(new Entity(1, 1, "Player", "dark red"));
        maxX = 6;
        maxY = 2;
        for(int i = 0; i < 7; i++) {
            currentMap[0][i] = new Tile("black", "", false);
        }
        currentMap[1][0] = new Tile("black", "", false);
        for(int i = 1; i < 5; i++) {
            currentMap[1][i] = new Tile("white", "", false);
        }
        currentMap[1][4] = new Tile("white", "", true);
        currentMap[1][5] = new Tile("white", "f", false);
        currentMap[1][6] = new Tile("black", "", false);
        for(int i = 0; i < 7; i++) {
            currentMap[2][i] = new Tile("black", "", false);
        }
    }

    public static void changeToMap2() {
        currentMap = new Tile[50][50];
        entityList.clear();
        entityList.add(new Entity(1, 1, "Player", "dark red"));
        maxX = 6;
        maxY = 2;
        for(int i = 0; i < 7; i++) {
            currentMap[0][i] = new Tile("black", "", false);
        }
        currentMap[1][0] = new Tile("black", "", false);
        for(int i = 1; i < 5; i++) {
            currentMap[1][i] = new Tile("white", "", false);
        }
        currentMap[1][2] = new Tile("white", "white", "b", new int[] {1, 4}, false);
        currentMap[1][3] = new Tile("green", "", false);
        currentMap[1][4] = new Tile("white", "red", "", true);
        currentMap[1][5] = new Tile("white", "f", false);
        currentMap[1][6] = new Tile("black", "", false);
        for(int i = 0; i < 7; i++) {
            currentMap[2][i] = new Tile("black", "", false);
        }
    }

    public static void changeColor(int input) throws Exception {
        if(input == 50) {
            setMapColor("red");
        }
        else if(input == 51) {
            setMapColor("green");
        }
        else if(input == 52) {
            setMapColor("blue");
        }
        else {
            setMapColor("white");
        }
    }

    public static void setMapColor(String color) throws Exception {
        for(int i = 0; i < currentMap.length; i++) {
            for(int j = 0; j < currentMap[i].length; j++) {
                if(currentMap[i][j] == null) break;
                currentMap[i][j].setColor(color);
            }
            if(currentMap[i][0] == null) break;
        }
    }

    public static boolean checkFinish() {
        if(currentMap[entityList.get(0).getY()][entityList.get(0).getX()].getType() == "F") return true;
        return false;
    }

    public static int getMaxX() {
        return maxX;
    }

    public static int getMaxY() {
        return maxY;
    }

    public static void movePlayerLeft() {
        entityList.get(0).moveLeft();
    }

    public static void movePlayerUp() {
        entityList.get(0).moveUp();
    }

    public static void movePlayerRight() {
        if(entityList.get(0).moveRight()) {
            int x = entityList.get(0).getX();
            int y = entityList.get(0).getY();
            if(currentMap[y][x].getType() == "B") {
                currentMap[y][x].setPressed();
                int[] linked = currentMap[y][x].getLinked();
                currentMap[linked[0]][linked[1]].setLocked();
            }
        };
    }

    public static void movePlayerDown() {
        entityList.get(0).moveDown();
    }

    public static Tile getTile(int x, int y) {
        return currentMap[y][x];
    }

    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < currentMap.length; i++) {
            for(int j = 0; j < currentMap[i].length; j++) {
                if(currentMap[i][j] == null) {
                    break;
                }
                boolean addedEntity = false;
                for(Entity entity : entityList) {
                    if(entity.getX() == j && entity.getY() == i) {
                        result += entity;
                        addedEntity = true;
                        break;
                    }
                }
                if(!addedEntity) {
                    result += currentMap[i][j];
                }
            }
            if(currentMap[i][0] == null) {
                break;
            }
            result += "\n";
        }
        Main.clearTerminal();
        return result + "\f\033[H\f\u001B[?25l";
    }
}
