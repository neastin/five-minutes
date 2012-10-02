/*
 * Object: MashWindow
 * The view for a minigame in which the player must get to the end of a maze (for some purpose). 
 */
package run;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import core.Player;

public class MashWindow extends Window {

    // to be implemented when we have different mash minigames
    // private final String MINIGAME_TYPE = "";
    private int mashCounter;
    private int playerIndex;

    public MashWindow() {
        super();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g, Player player) throws SlickException {
        g.setColor(Color.gray);
        g.drawString("Counter: " + mashCounter, 100, 100);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game, Player player) throws SlickException {
        this.playerIndex = playerIndex;
        mashCounter = 0;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta, Player player) throws SlickException {
        Input input = container.getInput();
        if (input.isKeyPressed(Input.KEY_PERIOD)) {// player.key("action")
            mashCounter += 1;
        }

        if (mashCounter >= 20) {
            this.over = true;
        }
    }
}
