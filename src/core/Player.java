package core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Player {

    public float[] pos;

    public Player(float[] startPos) {
        this.pos = new float[2];
        if (startPos.length != 2) {
            this.pos[0] = 0.0f;
            this.pos[1] = 0.0f;
        }
        else {
            this.pos = startPos;
        }
    }

    public void render(Graphics g, int xOffset, int yOffset) {
        // render the player on g
    }

}
