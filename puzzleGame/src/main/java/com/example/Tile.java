package com.example;

public class Tile {
    private String color;
    private String currentColor;
    private Type type;
    private boolean locked;
    private int[] linked;
    private boolean pressed;

    private enum Type {
        NORMAL,
        FINISH,
        BUTTON,
        PRESSURE_PLATE
    }

    public Tile(String color, String type, boolean locked) {
        basicInit(color, type, locked);
    }

    public Tile(String color, String currentColor, String type, boolean locked) {
        basicInit(color, type, locked);
        this.currentColor = currentColor;
    }

    public Tile(String color, String currentColor, String type, int[] linked, boolean locked) {
        basicInit(color, type, locked);
        this.currentColor = currentColor;
        this.linked = linked;
    }

    private void basicInit(String color, String type, boolean locked) {
        this.color = color;
        this.currentColor = color;
        this.locked = locked;
        switch(type) {
            case "f":
                this.type = Type.FINISH;
                break;
            case "b":
                this.type = Type.BUTTON;
                break;
            case "p":
                this.type = Type.PRESSURE_PLATE;
                break;
            default:
                this.type = Type.NORMAL;
        }
    }

    public void setColor(String color) {
        if(this.color.equals("white") && !locked) {
            this.currentColor = color;
        }
    }

    public void setPressed() {
        if(pressed) {
            this.pressed = false;
        }
        else {
            this.pressed = true;
        }
    }

    public void setLocked() {
        if(locked) {
            this.locked = false;
        }
        else {
            this.locked = true;
        }
    }

    public String getColor() {
        // if(locked) return this.currentColor;
        return this.currentColor;
    }

    public String getType() {
        if(this.type == Type.FINISH) return "F";
        if(this.type == Type.BUTTON) return "B";
        if(this.type == Type.PRESSURE_PLATE) return "P";
        return " ";
    }

    public String getLocked() {
        if(this.locked) return "L";
        return " ";
    }

    public int[] getLinked() {
        return this.linked;
    }

    public boolean getPressed() {
        return this.pressed;
    }

    @Override 
    public String toString() {
        return Main.addColor(this.getColor()) + this.getType() + this.getLocked() + "\033[0m";
    }
}
