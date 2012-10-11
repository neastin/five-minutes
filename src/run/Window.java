/*
 * Object: Window
 * Displays a single game state, but does so by displaying it to only a section
 * of the screen specified by parameters to the render call. This allows for the
 * rendering of this view multiple times in different locations on the screen,
 * or for the movement of this view around the screen.
 */
package run;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;

import core.Player;

public class Window {

    protected boolean over = false;
    protected Player player;
    protected float[] playerPos = new float[2];
    private Image bgImageOne;
    private Image bgImageTwo;

    /*
     * Constructor that allows for providing of a stateID
     */
    public Window(Player player) {
        this.player = player;
    }

    public void displayMinigameBackground(Graphics g, Player player) {
        if (player.windowPos[0] < 400) { // hacky way to figure out which image to display
            g.drawImage(bgImageOne, player.windowPos[0] + 21, player.windowPos[1] + 21);
        }
        else {
            g.drawImage(bgImageTwo, player.windowPos[0] + 21, player.windowPos[1] + 21);
        }
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g, Player player) throws SlickException {

    }

    public void init(GameContainer container, StateBasedGame game, Player player) throws SlickException {
        try {
            bgImageOne = new Image("resources/mini-bg-1.png");
            bgImageTwo = new Image("resources/mini-bg-2.png");
        }
        catch (SlickException e){
            System.out.println("Error loading mini game resources.");
        }
    }

    public void update(GameContainer container, StateBasedGame game, int delta, Player player) throws SlickException {
    }

    public void enter(GameContainer container, StateBasedGame game, Player player) {
    }

    public boolean over() {
        return over;
    }
}
