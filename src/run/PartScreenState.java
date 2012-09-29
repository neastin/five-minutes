package run;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PartScreenState extends BasicGameState {

    protected int id;
    protected int xMin;
    protected int xMax;
    protected int yMin;
    protected int yMax;

    /*
     * Constructor that allows for providing of a stateID
     */
    public PartScreenState(int stateID, int xMin, int xMax, int yMin, int yMax) {
        super();
        this.id = stateID;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) {
    }
}

