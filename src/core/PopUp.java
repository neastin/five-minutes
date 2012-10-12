package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class PopUp {

    private Image image;
    private float[] startLoc = new float[2];
    private float[] endLoc = new float[2];
    private float[] loc = new float[2];

    private boolean over;

    public PopUp() throws SlickException {
        image = new Image("resources/levelup.png");

        int side = 0;
        if (Math.random() > .5)
            side = 1;

        startLoc[0] = 50 + side * 400;
        startLoc[1] = 500;

        loc[0] = startLoc[0];
        loc[1] = startLoc[1];

        over = false;
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(image, loc[0], loc[1]);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

        loc[0] += delta * .3f;
        loc[1] -= delta * .7f;
        if ((loc[1] < 0) || (loc[0] < 0)) {
            over = true;
        }
    }

    public boolean over() {
        return over;
    }
}
