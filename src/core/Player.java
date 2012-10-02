package core;

import java.util.HashMap;

public class Player {

    // public float[] pos;
    public float[] windowPos;
    public float[] windowSize;
    private HashMap<String, Integer> buttons;

    public Player(float[] startWindowPos, float[] startWindowSize, HashMap<String, Integer> playerButtons) {
        this.windowPos = startWindowPos;
        this.windowSize = startWindowSize;
        this.buttons = playerButtons;
    }

    public Integer getButton(String command) {
        return this.buttons.get(command);
    }
}
