package run;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import core.Player;

public class DodgeWindow extends Window {
    private float[][] objPos = new float[10][2];
    private Image objSprite;
    private boolean[] objVis = new boolean[10];
    private float[][] objSpd = new float[10][2];
    private Double timer;

    public DodgeWindow(Player player) throws SlickException {
        super(player);
        objSprite = new Image("resources/trophy.png");
        // TODO Change objSprite
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g, Player player) throws SlickException {
        this.displayMinigameBackground(g, player);
        for (int i = 0; i < objPos.length; i++) {
            if (objVis[i])
                g.drawImage(objSprite, objPos[i][0], objPos[i][1]);
        }
        player.render(container, game, g, playerPos[0], playerPos[1]);
        g.drawString(Double.toString(Math.ceil(timer)), player.windowPos[0] + 350, player.windowPos[1]);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game, Player player) throws SlickException {
        super.init(container, game, player);
        playerPos[0] = player.windowPos[0] + player.windowSize[0] - player.pWidth;
        playerPos[1] = player.windowPos[1] + (int) player.windowSize[1] / 2;
        for (int i = 0; i < objPos.length; i++) {
            objPos[i][0] = player.windowPos[0] + (int) ((player.windowSize[0] - 100) * Math.random());
            objPos[i][1] = player.windowPos[1] + (int) (player.windowSize[1] * Math.random());
            objVis[i] = true;
            double x = Math.random();
            if (Math.random() < .5)
                x *= -1;
            double y = Math.pow(1 - Math.pow(x, 2), .5);
            if (Math.random() < .5)
                y *= -1;
            objSpd[i] = new float[] { (float) x, (float) y };
        }
        timer = 10.0;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta, Player player) throws SlickException {
        Input input = container.getInput();

        float moveValue = delta * .2f;
        if (input.isKeyDown(player.getButton("left"))) {
            if (playerPos[0] - moveValue > player.windowPos[0]) {
                playerPos[0] -= moveValue;
            }
        }
        if (input.isKeyDown(player.getButton("right"))) {
            if (playerPos[0] + player.pWidth + moveValue < player.windowPos[0] + player.windowSize[0]) {
                playerPos[0] += moveValue;
            }
        }
        if (input.isKeyDown(player.getButton("up"))) {
            if (playerPos[1] - moveValue > player.windowPos[1]) {
                playerPos[1] -= moveValue;
            }
        }
        if (input.isKeyDown(player.getButton("down"))) {
            if (playerPos[1] + player.pHeight + moveValue < player.windowPos[1] + player.windowSize[1]) {
                playerPos[1] += moveValue;
            }
        }
        for (int i = 0; i < objPos.length; i++) {
            if (objVis[i]) {
                if (player.windowPos[0] + player.windowSize[0] < objPos[i][0] + 40 + moveValue * objSpd[i][0]
                        || objPos[i][0] + moveValue * objSpd[i][0] < player.windowPos[0]) {
                    objSpd[i][0] *= -1;
                }
                objPos[i][0] += moveValue * objSpd[i][0];
                if (player.windowPos[1] + player.windowSize[1] < objPos[i][1] + 40 + moveValue * objSpd[i][1]
                        || objPos[i][1] + moveValue * objSpd[i][1] < player.windowPos[1]) {
                    objSpd[i][1] *= -1;
                }
                objPos[i][1] += moveValue * objSpd[i][1];
            }
        }

        Rectangle playerShape = new Rectangle(playerPos[0], playerPos[1], 20, 20);
        for (int i = 0; i < objPos.length; i++) {
            if (objVis[i] && (new Rectangle(objPos[i][0], objPos[i][1], 40, 40)).intersects(playerShape)) {
                timer += 2;
                objVis[i] = false;
            }
        }
        timer -= 1.0 / 60;
        if (timer <= 0) {
            this.over = true;
        }
    }
}
