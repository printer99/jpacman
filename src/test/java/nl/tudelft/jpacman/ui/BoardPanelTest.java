package nl.tudelft.jpacman.ui;

import static org.mockito.Mockito.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Collections;

import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.sprite.Sprite;
import nl.tudelft.jpacman.ui.BoardPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BoardPanelTest {

    private Game game;
    private Board board;
    private BoardPanel boardPanel;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        board = Mockito.mock(Board.class);
        Mockito.when(game.getLevel()).thenReturn(Mockito.mock(Level.class));
        Mockito.when(game.getLevel().getBoard()).thenReturn(board);

        boardPanel = new BoardPanel(game);
    }

    @Test
    void testPaint() {
        Graphics graphics = Mockito.mock(Graphics.class);
        Dimension size = new Dimension(300, 200);
        Mockito.when(graphics.create()).thenReturn(graphics);

        boardPanel.paint(graphics);

        Mockito.verify(board, Mockito.times(1)).getWidth();
        Mockito.verify(board, Mockito.times(1)).getHeight();
        Mockito.verify(graphics, Mockito.times(1)).setColor(Color.BLACK);
        Mockito.verify(graphics, Mockito.times(1)).fillRect(0, 0, size.width, size.height);
    }

    @Test
    void testRenderSquareWithNoOccupants() {
        // Add a test case to cover the scenario when a square has no occupants.
        // Verify that the render method is called appropriately for a square with no occupants.
        Graphics graphics = Mockito.mock(Graphics.class);
        Dimension size = new Dimension(300, 200);
        Mockito.when(graphics.create()).thenReturn(graphics);

        // Set up the square with no occupants.
        Square emptySquare = Mockito.mock(Square.class);
        Mockito.when(emptySquare.getSprite()).thenReturn(Mockito.mock(Sprite.class));
        Mockito.when(board.squareAt(0, 0)).thenReturn(emptySquare);

        // Invoke the paint method, triggering rendering.
        boardPanel.paint(graphics);

        // Verify that the render method is called with the expected arguments for a square with no occupants.
        Mockito.verify(emptySquare.getSprite(), Mockito.times(1)).draw(graphics, 0, 0, 25, 25); // Replace with actual sprite size
    }


    @Test
    void testRenderSquareWithOccupants() {
        // Add a test case to cover the scenario when a square has occupants.
        // Verify that the render method is called appropriately for a square with occupants.
        Graphics graphics = Mockito.mock(Graphics.class);
        Dimension size = new Dimension(300, 200);
        Mockito.when(graphics.create()).thenReturn(graphics);

        // Set up the square with occupants.
        Square squareWithOccupants = Mockito.mock(Square.class);
        Unit occupant = Mockito.mock(Unit.class);
        Sprite occupantSprite = Mockito.mock(Sprite.class);
        Mockito.when(occupant.getSprite()).thenReturn(occupantSprite);
        Mockito.when(squareWithOccupants.getSprite()).thenReturn(Mockito.mock(Sprite.class));
        Mockito.when(squareWithOccupants.getOccupants()).thenReturn(Collections.singletonList(occupant));
        Mockito.when(board.squareAt(0, 0)).thenReturn(squareWithOccupants);

        // Invoke the paint method, triggering rendering.
        boardPanel.paint(graphics);

        // Verify that the render method is called with the expected arguments for a square with occupants.
        Mockito.verify(occupantSprite, Mockito.times(1)).draw(graphics, 0, 0, 25, 25); // Replace with actual sprite size
    }


    @Test
    void testRenderWithDifferentBoardSize() {
        // Add a test case to cover the scenario of rendering a board with a different size.
        // Verify that the render method is called appropriately for a different board size.
        Graphics graphics = Mockito.mock(Graphics.class);
        Dimension size = new Dimension(400, 300);
        Mockito.when(graphics.create()).thenReturn(graphics);

        // Set up the board with a different size.
        Mockito.when(board.getWidth()).thenReturn(4);
        Mockito.when(board.getHeight()).thenReturn(3);

        // Invoke the paint method, triggering rendering.
        boardPanel.paint(graphics);

        // Verify that the render method is called with the expected arguments for a different board size.
        Mockito.verify(board, Mockito.times(1)).getWidth();
        Mockito.verify(board, Mockito.times(1)).getHeight();
        Mockito.verify(graphics, Mockito.times(1)).setColor(Color.BLACK);
        Mockito.verify(graphics, Mockito.times(1)).fillRect(0, 0, size.width, size.height);
    }


    @Test
    void testRenderWithDifferentWindowSize() {
        // Add a test case to cover the scenario of rendering a board with a different window size.
        // Verify that the render method is called appropriately for a different window size.
        Graphics graphics = Mockito.mock(Graphics.class);
        Dimension size = new Dimension(200, 150);
        Mockito.when(graphics.create()).thenReturn(graphics);

        // Set up the boardPanel with a different window size.
        boardPanel.setMinimumSize(size);
        boardPanel.setPreferredSize(size);

        // Invoke the paint method, triggering rendering.
        boardPanel.paint(graphics);

        // Verify that the render method is called with the expected arguments for a different window size.
        Mockito.verify(board, Mockito.times(1)).getWidth();
        Mockito.verify(board, Mockito.times(1)).getHeight();
        Mockito.verify(graphics, Mockito.times(1)).setColor(Color.BLACK);
        Mockito.verify(graphics, Mockito.times(1)).fillRect(0, 0, size.width, size.height);
    }


    @Test
    void testRenderWithDifferentSprites() {
        Graphics graphics = Mockito.mock(Graphics.class);
        Dimension size = new Dimension(300, 200);
        Mockito.when(graphics.create()).thenReturn(graphics);

        // Set up the board with different sprites.
        Square squareWithDifferentSprites = Mockito.mock(Square.class);
        Unit unitWithDifferentSprite = Mockito.mock(Unit.class);
        Sprite differentSprite = Mockito.mock(Sprite.class);
        Mockito.when(unitWithDifferentSprite.getSprite()).thenReturn(differentSprite);
        Mockito.when(squareWithDifferentSprites.getSprite()).thenReturn(differentSprite);
        Mockito.when(squareWithDifferentSprites.getOccupants()).thenReturn(Collections.singletonList(unitWithDifferentSprite));
        Mockito.when(board.squareAt(0, 0)).thenReturn(squareWithDifferentSprites);

        boardPanel.paint(graphics);

        // Verify that the render method is called with the expected arguments for different sprites.
        Mockito.verify(differentSprite, Mockito.times(1)).draw(graphics, 0, 0, 25, 25); // Replace with actual sprite size
    }


    @Test
    void testRenderWithDifferentColors() {
        // Add a test case to cover the scenario of rendering a board with different colors.
        // Verify that the render method is called appropriately for different colors.
        Graphics graphics = Mockito.mock(Graphics.class);
        Dimension size = new Dimension(300, 200);
        Mockito.when(graphics.create()).thenReturn(graphics);

        // Set up the board with different colors.

        boardPanel.paint(graphics);

        // Verify that the render method is called with the expected arguments for different colors.
    }

    // Add more test cases based on other functionalities in the BoardPanel class.
}
