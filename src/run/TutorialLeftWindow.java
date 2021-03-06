package run;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

import core.Player;

public class TutorialLeftWindow extends Window {
    private int lapCounter = 0;
    private int lapGoal;

    public TutorialLeftWindow(Player player) {
        super(player);
        lapGoal = 1;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g, Player player) throws SlickException {
        this.displayMinigameBackground(g, player);
        player.render(container, game, g, playerPos[0], playerPos[1]);

        g.setColor(Color.white);
        PlayGameState state = (PlayGameState) (game.getCurrentState());
        UnicodeFont uFont = state.uFont;
        g.setFont(state.uFont);
        g.drawString("Use left arrow key", 100 + player.windowPos[0], 80);
        g.drawString("or A to move to the", 100 + player.windowPos[0], 80 + 30);
        g.drawString("left of the screen", 100 + player.windowPos[0], 80 + 60);
        g.setColor(Color.black);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game, Player player) throws SlickException {
        super.init(container, game, player);
        playerPos[0] = player.windowPos[0] + player.windowSize[0] - player.pWidth;
        playerPos[1] = player.windowPos[1] + 170;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta, Player player) throws SlickException {
        Input input = container.getInput();

        float moveValue = delta * .2f;
        if (input.isKeyDown(player.getButton("left"))) {
            playerPos[0] -= moveValue;
        }
        if (playerPos[0] < player.windowPos[0] + player.pWidth) {
            lapCounter += 1;
            playerPos[0] = player.windowPos[0] + player.windowSize[0] - player.pWidth;
        }
        if (lapCounter >= lapGoal) {
            this.over = true;
        }
    }
}
