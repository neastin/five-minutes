package run;

import core.Player;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
import java.lang.Integer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayGameState extends BasicGameState {

    // states holds two stacks of Windows, one for each of the player views
    // the top state of each stack will be rendered each time render is called on this object
    public ArrayList<Stack<Window>> states;
    public Player[] players;

    public Image background;

    public PlayGameState() {
        super();
        float[] p1Pos = {0, 50};
        float[] p2Pos = {0, 350};
        float[] p1WinSize = {399, 600};
        float[] p2WinSize = {399, 600};
        float[] p1WinPos = {0, 0};
        float[] p2WinPos = {401, 0};

        HashMap<String, Integer> p1Buttons = new HashMap<String, Integer>();
        p1Buttons.put("up", Input.KEY_UP);
        p1Buttons.put("left", Input.KEY_LEFT);
        p1Buttons.put("down", Input.KEY_DOWN);
        p1Buttons.put("right", Input.KEY_RIGHT);
        p1Buttons.put("action", Input.KEY_PERIOD);
        HashMap<String, Integer> p2Buttons = new HashMap<String, Integer>();
        p2Buttons.put("up", Input.KEY_W);
        p2Buttons.put("left", Input.KEY_A);
        p2Buttons.put("down", Input.KEY_S);
        p2Buttons.put("right", Input.KEY_D);
        p2Buttons.put("action", Input.KEY_T);

        players = new Player[2];
        players[0] = new Player(p1Pos, p1WinPos, p1WinSize, p1Buttons);
        players[1] = new Player(p2Pos, p2WinPos, p2WinSize, p2Buttons);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        // show a background
        Image im = this.background;
        g.drawImage(im, 0, 0);

        //g.fillRect(390, 0, 20, 599);
        // maintain two internal states. Render one on each side of the screen
        for (int i = 0; i < this.states.size(); i++) {
            Stack<Window> stack = this.states.get(i);
            Window windowedState = stack.peek();
            windowedState.render(container, game, g, players[i]);
        }
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.background = new Image("resources/big-background.png");

        this.states = new ArrayList<Stack<Window>>();
        Stack<Window> states1 = new Stack<Window>();
        Stack<Window> states2 = new Stack<Window>();
        MainWindow startState = new MainWindow();

        states1.push(startState);
        states2.push(startState);

        states.add(states1);
        states.add(states2);

        for (int i = 0; i < this.states.size(); i++) {
            Stack<Window> stack = this.states.get(i);
            Window windowedState = stack.peek();
            windowedState.init(container, game, players[i]);
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
            windowedState.update(container, game, delta, players[i]);
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
            windowedState.enter(container, game, players[i]);
        }
    }
}
