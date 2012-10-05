package core;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.geom.Rectangle;

public class Player {

    // public float[] pos;
    public float[] windowPos;
    public float[] windowSize;
    private HashMap<String, Integer> buttons;
    private Image playerSprite;
    public Rectangle boundingBox;

    public Player(float[] startWindowPos, float[] startWindowSize, HashMap<String, Integer> playerButtons) {
        windowPos = startWindowPos;
        windowSize = startWindowSize;
        buttons = playerButtons;
        boundingBox = new Rectangle(0, 0, 40, 40);
        try {
            this.playerSprite = new Image("resources/ninja.png");
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g, float x, float y) {

        boundingBox.setX(x);
        boundingBox.setY(y);
        g.drawImage(playerSprite, x, y);
    }

    public Integer getButton(String command) {
        return this.buttons.get(command);
    }
}
