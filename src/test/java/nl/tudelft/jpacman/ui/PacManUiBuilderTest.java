package nl.tudelft.jpacman.ui;

import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.ui.Action;
import nl.tudelft.jpacman.ui.PacManUI;
import nl.tudelft.jpacman.ui.PacManUiBuilder;
import nl.tudelft.jpacman.ui.ScorePanel.ScoreFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class PacManUiBuilderTest {

    private PacManUiBuilder pacManUiBuilder;
    private Game game;

    @BeforeEach
    void setUp() {
        pacManUiBuilder = new PacManUiBuilder();
        game = Mockito.mock(Game.class);
    }

    @Test
    void testBuild() {
        PacManUI pacManUI = pacManUiBuilder.build(game);

        Assertions.assertNotNull(pacManUI);
        // Verify the expected behavior after building the PacManUI
    }

    @Test
    void testAddKey() {
        Action action = Mockito.mock(Action.class);

        pacManUiBuilder.addKey(KeyEvent.VK_UP, action);

        PacManUI pacManUI = pacManUiBuilder.build(game);
        // Verify the expected behavior after adding a key
    }

    @Test
    void testAddButton() {
        Action action = Mockito.mock(Action.class);

        pacManUiBuilder.addButton("CustomButton", action);

        PacManUI pacManUI = pacManUiBuilder.build(game);
        // Verify the expected behavior after adding a button
    }

    @Test
    void testWithDefaultButtons() {
        pacManUiBuilder.withDefaultButtons();

        PacManUI pacManUI = pacManUiBuilder.build(game);
        // Verify the expected behavior after adding default buttons
    }

    @Test
    void testWithScoreFormatter() {
        ScoreFormatter scoreFormatter = Mockito.mock(ScoreFormatter.class);
        pacManUiBuilder.withScoreFormatter(scoreFormatter);

        PacManUI pacManUI = pacManUiBuilder.build(game);
        // Verify the expected behavior after setting the score formatter
    }

    // Add more tests based on other functionalities in PacManUiBuilder
}
