package run;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Stack;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayGameState extends BasicGameState {

    // states holds two stacks of PartScreenStates, one for each of the player views
    // the top state of each stack will be rendered each time render is called on this object
    public ArrayList<Stack<PartScreenState>> states;

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
        g.fillRect(390, 0, 20, 599);
        // maintain two internal states. Render one on each side of the screen
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        states = new ArrayList<Stack<PartScreenState>>();
        Stack<PartScreenState> states1 = new Stack<PartScreenState>();
        Stack<PartScreenState> states2 = new Stack<PartScreenState>();

        states1.push(new PartScreenState(1, 0, 390, 0, 600));
        states2.push(new PartScreenState(1, 0, 390, 0, 600));

        states.add(states1);
        states.add(states2);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            container.exit();
        }
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) {
    }

}
