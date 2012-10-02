/*
 * Object: Window
 * Displays a single game state, but does so by displaying it to only a section
 * of the screen specified by parameters to the render call. This allows for the
 * rendering of this view multiple times in different locations on the screen,
 * or for the movement of this view around the screen.
 */
package run;

import core.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Window {

    protected boolean over = false;

    /*
     * Constructor that allows for providing of a stateID
     */
    public Window() {
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g, Player player) throws SlickException {

    }

    public void init(GameContainer container, StateBasedGame game, Player player) throws SlickException {
    }

    public void update(GameContainer container, StateBasedGame game, int delta, Player player) throws SlickException {
    }

    public void enter(GameContainer container, StateBasedGame game, Player player) {
    }

    public boolean over() {
        return over;
    }
}
