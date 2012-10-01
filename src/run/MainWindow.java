/*
 * Object: MainWindow
 * The view of the scrolling text portion of the game.
 */
package run;

import core.TextBlock;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

public class MainWindow extends Window {

    private final int LINE_HEIGHT = 100; // Distance between baselines of successive lines of text.
    private final double TICK_LENGTH = 10; // Milliseconds for text to move one pixel.
    private final int MARGIN = 30;
    private final int SCREEN_WIDTH = 200 - 2 * MARGIN; // FIXME: calculate.
    private BufferedReader reader;
    private ArrayList<String> lines;
    private int lineOffset;
    private UnicodeFont font;

    public MainWindow() {
        super();
        font = new UnicodeFont(java.awt.Font.decode("Arial"));
        lines = new ArrayList<String>(200);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g, int x, int y, int height, int width, int playerIndex) throws SlickException {
        g.setColor(Color.black);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            g.drawString(line, x + MARGIN, y + MARGIN + LINE_HEIGHT * i - lineOffset);
        }

        // render each player
    }

    @Override
    public void init(GameContainer container, StateBasedGame game, int playerIndex) throws SlickException {
        try {
            this.reader = new BufferedReader(new FileReader("resources/text.txt"));
        } catch (FileNotFoundException e) {
            throw new SlickException(e.getMessage());
        }
        StringBuilder currentString = new StringBuilder(120); // Capacity of buffer.
        try {
            for (String words = reader.readLine(); words != null; words = reader.readLine()) {
                for (String word : words.split("\\s")) {
                    if (word.length() == 0) continue;

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
    public void update(GameContainer container, StateBasedGame game, int delta, int playerIndex) throws SlickException {
        lineOffset += delta / TICK_LENGTH;
        // react to key presses for that player
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game, int playerIndex) {
    }
}

