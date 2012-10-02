/*
 * Object: MashWindow
 * The view for a minigame in which the player must complete a maze. 
 */
package run;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import core.Player;

public class MazeWindow extends Window {

    private float[] mazePlayerPos = new float[2];
    private float[] goalPos = new float[2];
    private Image goalSprite;

    // to be implemented when we have different maze minigames
    // private final String MINIGAME_TYPE = "";

    public MazeWindow(Player player) throws SlickException {
        super(player);
        goalSprite = new Image("resources/trophy.png");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g, Player player) throws SlickException {
        g.drawImage(goalSprite, goalPos[0], goalPos[1]);
        player.render(container, game, g, mazePlayerPos[0], mazePlayerPos[1]);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game, Player player) throws SlickException {
        mazePlayerPos[0] = player.windowPos[0] + player.windowSize[0] / 2;
        mazePlayerPos[1] = player.windowPos[1] + player.windowSize[1] / 2;

        goalPos[0] = player.windowPos[0] + 50;
        goalPos[1] = player.windowPos[1] + 50;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta, Player player) throws SlickException {
        Input input = container.getInput();

        if (input.isKeyDown(player.getButton("left"))) {
            mazePlayerPos[0] -= delta * .2f;
        }
        if (input.isKeyDown(player.getButton("right"))) {
            mazePlayerPos[0] += delta * .2f;
        }
        if (input.isKeyDown(player.getButton("up"))) {
            mazePlayerPos[1] -= delta * .2f;
        }
        if (input.isKeyDown(player.getButton("down"))) {
            mazePlayerPos[1] += delta * .2f;
        }

        if (Math.abs(mazePlayerPos[0] - goalPos[0]) < 20 && (Math.abs(mazePlayerPos[1] - goalPos[1]) < 20)) {
            this.over = true;
        }
    }
}
