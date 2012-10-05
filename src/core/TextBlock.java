package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

public class TextBlock {
    public float[] pos; // x, y
    public String text;
    protected float[] size; // width, height
    public Color color;
    public Font font;
    protected Rectangle boundingBox;

    public TextBlock(String text, Font font, float x, float y) {
        this.text = text;
        this.font = font;
        this.color = Color.black;
        this.pos = new float[2];
        this.pos[0] = x;
        this.pos[1] = y;
        boundingBox = new Rectangle(x, y, font.getWidth(text), font.getHeight(text));
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g, Player player) throws SlickException {
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, pos[0], pos[1]);
    }

    // this should be called after the player has been moved
    public boolean update(float movement, Player player) {
        // move our shape
        pos[1] = pos[1] + movement;
        boundingBox.setX(pos[0]);
        boundingBox.setY(pos[1]);
        if (boundingBox.intersects(player.boundingBox)) {
            return true;
        }
        return false;
    }
}
