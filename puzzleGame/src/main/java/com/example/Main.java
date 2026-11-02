package com.example;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class Main {

    public static String addColor(String color) {
        switch(color) {
            case "black":
                return "\033[48;5;16m";
            case "white":
                return "\033[48;5;255m";
            case "red":
                return "\033[48;5;196m";
            case "green":
                return "\033[48;5;46m";
            case "blue":
                return "\033[48;5;21m";
            case "dark red":
                return "\033[48;5;52m";
            case "light blue":
                return "\033[48;5;51m";
            default:
                return "\033[48;5;0m";
        }
    }

    public static void mapChange(int mapNum) {
        switch(mapNum) {
            case 1:
                Map.changeToMap1();
                break;
            case 2:
                Map.changeToMap2();
                break;
            default:
                System.out.println("a");
                while(true);
        }
    }

    public static void clearTerminal() {
        System.out.println("\033[H\033[2J\033[3J");
        System.out.flush();
    }

    public static void proccessUserInput(int input, Terminal terminal) throws Exception {
        if(input == 27) {
            terminal.reader().read();
            switch(terminal.reader().read()) {
                case 68:
                    Map.movePlayerLeft();
                    break;
                case 65:
                    Map.movePlayerUp();
                    break;
                case 67:
                    Map.movePlayerRight();
                    break;
                case 66:
                    Map.movePlayerDown();
                    break;
            }
        }
        else if(input >= 49 && input <= 52) {
            Map.changeColor(input);
        }
        
        
        // System.out.println(input);
    }

    public static void gameLoop(Terminal terminal) throws Exception{
        int mapNum = 1;
        while(true) {
            int a = terminal.reader().read();
            if(a == 92) break;
            proccessUserInput(a, terminal);
            if(Map.checkFinish() == true) {
                ++mapNum;
                mapChange(mapNum);
            }
            System.out.println(new Map());
        }
    }

    public static void main(String[] args) throws Exception {
        Terminal terminal = TerminalBuilder.terminal();
        terminal.enterRawMode();
        clearTerminal();
        Map.changeToMap1();
        System.out.println(new Map());
        gameLoop(terminal); 
        // terminal.close();
    }
}