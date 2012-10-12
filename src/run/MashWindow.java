/*
 * Object: MashWindow
 * The view for a minigame in which the player must hit action X times (for some purpose). 
 */
package run;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

import core.Player;

public class MashWindow extends Window {

    // to be implemented when we have different mash minigames
    // private final String MINIGAME_TYPE = "";
    private int mashCounter = 0;
    private int mashGoal;

    public MashWindow(Player player) {
        super(player);
        mashGoal = 20;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g, Player player) throws SlickException {
        this.displayMinigameBackground(g, player);

        g.setColor(Color.white);

        PlayGameState state = (PlayGameState) (game.getCurrentState());
        UnicodeFont uFont = state.uFont;
        g.setFont(state.uFont);

        g.drawString("Counter: " + mashCounter, 100 + player.windowPos[0], 100);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game, Player player) throws SlickException {
        super.init(container, game, player);
        mashCounter = 0;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta, Player player) throws SlickException {
        Input input = container.getInput();
        if (input.isKeyPressed(player.getButton("left")) || input.isKeyPressed(player.getButton("right"))
                || input.isKeyPressed(player.getButton("up")) || input.isKeyPressed(player.getButton("down"))) {
            mashCounter += 1;
        }

        if (mashCounter >= mashGoal) {
            this.over = true;
        }
    }
}
