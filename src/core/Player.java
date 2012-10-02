package core;

import java.util.HashMap;

import org.newdawn.slick.Graphics;

public class Player {

    private float[] pos;
    private float[] windowPos;
    private float[] windowSize;
    private HashMap<String, Integer> buttons;

    public Player(float[] startPos, float[] startWindowPos, float[] startWindowSize,
            HashMap<String, Integer> playerButtons) {
        this.windowPos = startWindowPos;
        this.windowSize = startWindowSize;
        this.pos = startPos;
        this.buttons = playerButtons;
    }

    public void render(Graphics g, int xOffset, int yOffset) {
        // render the player on g
    }

    public float[] getPos() {
        return this.pos;
    }

    public float[] getWindowPos() {
        return this.windowPos;
    }

    public float[] getWindowSize() {
        return this.windowSize;
    }

    public Integer getButton(String command) {
        return this.buttons.get(command);
    }
}
