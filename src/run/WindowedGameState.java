/*
 * Object: WindowedGameState
 * Displays a single game state, but does so by displaying it to only a section
 * of the screen specified by parameters to the render call. This allows for the
 * rendering of this view multiple times in different locations on the screen,
 * or for the movement of this view around the screen.
 */
package run;

import java.awt.Font;

import core.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class WindowedGameState {

    public Player[] players;

    /*
     * Constructor that allows for providing of a stateID
     */
    public WindowedGameState() {
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g, int x, int y, int height, int width, int playerIndex) throws SlickException {
    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    }

    public void enter(GameContainer container, StateBasedGame game) {
    }
}

