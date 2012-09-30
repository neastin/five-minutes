/*
 * Object: MainWindow
 * The view of the scrolling text portion of the game.
 */
package run;

import core.TextBlock;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

public class MainWindow extends Window {

    public ArrayList<TextBlock> textBlocks;

    /*
     * Constructor that allows for providing of a stateID
     */
    public MainWindow() {
        super();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g, int x, int y, int height, int width, int playerIndex) throws SlickException {
        // render the text blocks
        // render each player
    }

    @Override
    public void init(GameContainer container, StateBasedGame game, int playerIndex) throws SlickException {
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta, int playerIndex) throws SlickException {
        // react to key presses for that player
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game, int playerIndex) {
    }
}

