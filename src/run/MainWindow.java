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
import java.awt.Font;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.Effect;

import core.Player;
import core.TextBlock;

public class MainWindow extends Window {

    private final int LINE_HEIGHT = 100; // Distance between baselines of successive lines of text.
    private final double TICK_LENGTH = 15; // Milliseconds for text to move one pixel.
    private final int MARGIN = 30;
    private final int SCREEN_WIDTH = 250 - 2 * MARGIN; // FIXME: calculate.
    private BufferedReader reader;
    private ArrayList<TextBlock> lines;
    private int lineOffset;
    private UnicodeFont font;

    public MainWindow(Player player) {
        super(player);
        font = new UnicodeFont(java.awt.Font.decode("Arial"));
        lines = new ArrayList<TextBlock>(200);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g, Player player) throws SlickException {
        for (int i = 0; i < lines.size(); i++) {
            TextBlock line = lines.get(i);
            line.render(container, game, g, player);
        }

        player.render(container, game, g, playerPos[0], playerPos[1]);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game, Player player) throws SlickException {
        playerPos[0] = player.windowPos[0] + 50;
        playerPos[1] = player.windowPos[1] + 50;
        try {
            this.reader = new BufferedReader(new FileReader("resources/text.txt"));
        } catch (FileNotFoundException e) {
            throw new SlickException(e.getMessage());
        }
        StringBuilder currentString = new StringBuilder(120); // Capacity of buffer.

        String fontPath = "resources/cantarell.ttf";
        UnicodeFont uFont = new UnicodeFont(fontPath , 20, false, false);
        uFont.addAsciiGlyphs();
        uFont.addGlyphs(400, 600);
        uFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
        uFont.loadGlyphs();

        try {
            int counter = 0;
            float x = player.windowPos[0];
            float y = player.windowPos[1];
            for (String words = reader.readLine(); words != null; words = reader.readLine()) {
                for (String word : words.split("\\s")) {
                    if (word.length() == 0)
                        continue;

                    if (currentString.length() > 0) {
                        long gap = Math.round(6 * Math.random());
                        for (int i = 0; i <= gap; i++) {
                            currentString.append(' ');
                        }
                    }
                    currentString.append(word);

                    if (font.getWidth(currentString.toString()) > SCREEN_WIDTH) {
                        // FIXME: this will cause problems with one-word lines.
                        int prevLength = currentString.length() - word.length() - 1;
                        lines.add(new TextBlock(currentString.substring(0, prevLength), uFont, x + MARGIN, y + MARGIN + LINE_HEIGHT * counter - lineOffset));
                        counter++;
                        currentString.delete(0, prevLength + 1);
                    }
                }
            }
            lines.add(new TextBlock(currentString.toString(), uFont, x + MARGIN, y + MARGIN + LINE_HEIGHT * counter - lineOffset));
            counter++;
        } catch (IOException e) {
            throw new SlickException(e.getMessage());
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta, Player player) throws SlickException {

        Input input = container.getInput();
        if (input.isKeyDown(player.getButton("left"))) {
            playerPos[0] -= delta * .2f;
        }
        if (input.isKeyDown(player.getButton("right"))) {
            playerPos[0] += delta * .2f;
        }

        lineOffset += delta / TICK_LENGTH;
        float movement = -(float)delta/(float)TICK_LENGTH;
        for (int i = 0; i < lines.size(); i++) {
            TextBlock line = lines.get(i);
            boolean result = line.update(movement, player);
            if (result) {
                line.color = Color.red;
            }
        }
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game, Player player) {

    }
}
