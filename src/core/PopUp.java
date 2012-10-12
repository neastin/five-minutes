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
        image = new Image("resources/face.png");

        int side = 0;
        if (Math.random() > .5)
            side = 1;

        startLoc[0] = 100 + side * 400;
        startLoc[1] = 600;
        endLoc[0] = 300 + side * 400;
        endLoc[1] = 480;

        loc[0] = startLoc[0];
        loc[1] = startLoc[1];

        over = false;
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(image, loc[0], loc[1]);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if ((endLoc[0] - startLoc[0]) != 0) {
            loc[0] += delta / (endLoc[0] - startLoc[0]) * 30f;
        }
        if ((endLoc[1] - startLoc[1]) != 0) {
            loc[1] += delta / (endLoc[1] - startLoc[1]) * 30f;
        }

        if ((loc[1] < endLoc[1]) || (loc[0] > endLoc[0])) {
            over = true;
        }
    }

    public boolean over() {
        return over;
    }
}
