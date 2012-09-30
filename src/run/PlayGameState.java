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
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayGameState extends BasicGameState {

    // states holds two stacks of Windows, one for each of the player views
    // the top state of each stack will be rendered each time render is called on this object
    public ArrayList<Stack<Window>> states;

    public Image background;

    public static int[] XS = new int[2];
    public static int[] YS = new int[2];
    public static int[] HEIGHTS = new int[2];
    public static int[] WIDTHS = new int[2];

    public PlayGameState() {
        super();
        XS[0] = 0;
        XS[1] = 410;
        YS[0] = 0;
        YS[1]= 0;
        HEIGHTS[0] = 600;
        HEIGHTS[1] = 600;
        WIDTHS[0] = 390;
        WIDTHS[1] = 390;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        // show a background
        Image im = this.background;
        g.drawImage(im, 0, 0);

		g.setColor(Color.white);
        //g.fillRect(390, 0, 20, 599);
        // maintain two internal states. Render one on each side of the screen
        for (int i = 0; i < this.states.size(); i++) {
            Stack<Window> stack = this.states.get(i);
            Window windowedState = stack.peek();
            windowedState.render(container, game, g, XS[i], YS[i], WIDTHS[i], HEIGHTS[i], i);
        }
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.background = new Image("resources/big-background.png");

        this.states = new ArrayList<Stack<Window>>();
        Stack<Window> states1 = new Stack<Window>();
        Stack<Window> states2 = new Stack<Window>();
        Window startState = new Window();

        states1.push(startState);
        states2.push(startState);

        states.add(states1);
        states.add(states2);

        for (int i = 0; i < this.states.size(); i++) {
            Stack<Window> stack = this.states.get(i);
            Window windowedState = stack.peek();
            windowedState.init(container, game, i);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            container.exit();
        }

        for (int i = 0; i < this.states.size(); i++) {
            Stack<Window> stack = this.states.get(i);
            Window windowedState = stack.peek();
            windowedState.update(container, game, delta, i);
        }
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) {
        for (int i = 0; i < this.states.size(); i++) {
            Stack<Window> stack = this.states.get(i);
            Window windowedState = stack.peek();
            windowedState.enter(container, game, i);
        }
    }
}
