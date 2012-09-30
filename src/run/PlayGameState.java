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

    public Image background;

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        // show a background
        Image im = this.background;
        g.drawImage(im, 0, 0);

		g.setColor(Color.white);
        g.fillRect(390, 0, 20, 599);
        // maintain two internal states. Render one on each side of the screen
        for (int i = 0; i < this.states.size(); i++) {
            Stack<PartScreenState> stack = this.states.get(i);
            PartScreenState partState = stack.peek();
            partState.render(container, game, g);
        }
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.background = new Image("resources/big-background.png");

        this.states = new ArrayList<Stack<PartScreenState>>();
        Stack<PartScreenState> states1 = new Stack<PartScreenState>();
        Stack<PartScreenState> states2 = new Stack<PartScreenState>();

        states1.push(new PartScreenState(1, 0, 390, 0, 600));
        states2.push(new PartScreenState(1, 410, 800, 0, 600));

        states.add(states1);
        states.add(states2);

        for (int i = 0; i < this.states.size(); i++) {
            Stack<PartScreenState> stack = this.states.get(i);
            PartScreenState partState = stack.peek();
            partState.init(container, game);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            container.exit();
        }

        for (int i = 0; i < this.states.size(); i++) {
            Stack<PartScreenState> stack = this.states.get(i);
            PartScreenState partState = stack.peek();
            partState.update(container, game, delta);
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
