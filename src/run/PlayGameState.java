package run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.Player;

public class PlayGameState extends BasicGameState {

    // states holds two stacks of Windows, one for each of the player views
    // the top state of each stack will be rendered each time render is called on this object
    public ArrayList<Stack<Window>> states;
    public Player[] players;

    public Image background;

    public PlayGameState() {
        super();
        float[] p1WinSize = { 399, 600 };
        float[] p2WinSize = { 399, 600 };
        float[] p1WinPos = { 0, 0 };
        float[] p2WinPos = { 400, 0 };

        HashMap<String, Integer> p1Buttons = new HashMap<String, Integer>();
        p1Buttons.put("up", Input.KEY_W);
        p1Buttons.put("left", Input.KEY_A);
        p1Buttons.put("down", Input.KEY_S);
        p1Buttons.put("right", Input.KEY_D);
        p1Buttons.put("action", Input.KEY_T);
        HashMap<String, Integer> p2Buttons = new HashMap<String, Integer>();
        p2Buttons.put("up", Input.KEY_UP);
        p2Buttons.put("left", Input.KEY_LEFT);
        p2Buttons.put("down", Input.KEY_DOWN);
        p2Buttons.put("right", Input.KEY_RIGHT);
        p2Buttons.put("action", Input.KEY_PERIOD);

        players = new Player[2];
        players[0] = new Player(p1WinPos, p1WinSize, p1Buttons);
        players[1] = new Player(p2WinPos, p2WinSize, p2Buttons);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        // show a background
        Image im = this.background;
        g.drawImage(im, 0, 0);

        // g.fillRect(390, 0, 20, 599);
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

        states1.push(new MainWindow(players[0]));
        states2.push(new MainWindow(players[1]));

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

        // for testing mash
        if (input.isKeyPressed(Input.KEY_1)) {
            this.states.get(0).push(new MashWindow(players[0]));
            this.states.get(0).peek().init(container, game, players[0]);
        }
        if (input.isKeyPressed(Input.KEY_0)) {
            this.states.get(1).push(new MashWindow(players[1]));
            this.states.get(1).peek().init(container, game, players[1]);
        }

        // for testing maze
        if (input.isKeyPressed(Input.KEY_2)) {
            this.states.get(0).push(new MazeWindow(players[0]));
            this.states.get(0).peek().init(container, game, players[0]);
        }
        if (input.isKeyPressed(Input.KEY_9)) {
            this.states.get(1).push(new MazeWindow(players[1]));
            this.states.get(1).peek().init(container, game, players[1]);
        }

        for (int i = 0; i < this.states.size(); i++) {
            Stack<Window> stack = this.states.get(i);
            Window windowedState = stack.peek();

            // note: update before or after?
            if (windowedState.over() == true) {
                stack.pop();
            }
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
