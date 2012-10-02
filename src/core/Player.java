package core;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Player {

    // public float[] pos;
    public float[] windowPos;
    public float[] windowSize;
    private HashMap<String, Integer> buttons;
    private Image playerSprite;

    public Player(float[] startWindowPos, float[] startWindowSize, HashMap<String, Integer> playerButtons) {
        this.windowPos = startWindowPos;
        this.windowSize = startWindowSize;
        this.buttons = playerButtons;
        try {
            this.playerSprite = new Image("resources/face.png");
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g, float x, float y) {
        g.drawImage(playerSprite, x, y);
    }

    public Integer getButton(String command) {
        return this.buttons.get(command);
    }
}
