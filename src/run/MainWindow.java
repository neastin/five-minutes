/*
 * Object: MainWindow
 * The view of the scrolling text portion of the game.
 */
package run;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

import core.Player;

public class MainWindow extends Window {

    private final int LINE_HEIGHT = 100; // Distance between baselines of successive lines of text.
    private final double TICK_LENGTH = 10; // Milliseconds for text to move one pixel.
    private final int MARGIN = 30;
    private final int SCREEN_WIDTH = 250 - 2 * MARGIN; // FIXME: calculate.
    private BufferedReader reader;
    private ArrayList<String> lines;
    private int lineOffset;
    private UnicodeFont font;

    private float[] mainPlayerPos = new float[2];

    public MainWindow(Player player) {
        super(player);
        font = new UnicodeFont(java.awt.Font.decode("Arial"));
        lines = new ArrayList<String>(200);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g, Player player) throws SlickException {
        float x = player.windowPos[0];
        float y = player.windowPos[1];
        g.setColor(Color.black);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            g.drawString(line, x + MARGIN, y + MARGIN + LINE_HEIGHT * i - lineOffset);
        }

        g.drawImage(new Image("resources/face.png"), mainPlayerPos[0], mainPlayerPos[1]);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game, Player player) throws SlickException {
        mainPlayerPos[0] = player.windowPos[0] + 50;
        mainPlayerPos[1] = player.windowPos[1] + 50;
        try {
            this.reader = new BufferedReader(new FileReader("resources/text.txt"));
        } catch (FileNotFoundException e) {
            throw new SlickException(e.getMessage());
        }
        StringBuilder currentString = new StringBuilder(120); // Capacity of buffer.
        try {
            for (String words = reader.readLine(); words != null; words = reader.readLine()) {
                for (String word : words.split("\\s")) {
                    if (word.length() == 0)
                        continue;

                    if (currentString.length() > 0) {
                        currentString.append(' ');
                    }
                    currentString.append(word);

                    if (font.getWidth(currentString.toString()) > SCREEN_WIDTH) {
                        // FIXME: this will cause problems with one-word lines.
                        int prevLength = currentString.length() - word.length() - 1;
                        lines.add(currentString.substring(0, prevLength));
                        currentString.delete(0, prevLength + 1);
                    }
                }
            }
            lines.add(currentString.toString());
        } catch (IOException e) {
            throw new SlickException(e.getMessage());
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta, Player player) throws SlickException {
        lineOffset += delta / TICK_LENGTH;

        Input input = container.getInput();
        if (input.isKeyDown(player.getButton("left"))) {
            mainPlayerPos[0] -= delta * .2f;
        }
        if (input.isKeyDown(player.getButton("right"))) {
            mainPlayerPos[0] += delta * .2f;
        }
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game, Player player) {

    }
}
