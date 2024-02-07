package nl.tudelft.jpacman.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class PacKeyListenerTest {

    private PacKeyListener pacKeyListener;
    private Map<Integer, Action> keyMappings;

    @BeforeEach
    void setUp() {
        keyMappings = new HashMap<>();
        pacKeyListener = new PacKeyListener(keyMappings);
    }

    @Test
    void testKeyPressedWithValidAction() {
        Action mockAction = Mockito.mock(Action.class);
        keyMappings.put(KeyEvent.VK_UP, mockAction);

        KeyEvent upKeyEvent = new KeyEvent(new JComponent() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'U');
        pacKeyListener.keyPressed(upKeyEvent);

        Mockito.verify(mockAction, Mockito.times(1)).doAction();
    }

    @Test
    void testKeyPressedWithNoAction() {
        // No action mapped for this key code.
        KeyEvent unknownKeyEvent = new KeyEvent(new JComponent() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        pacKeyListener.keyPressed(unknownKeyEvent);

    }

    @Test
    void testKeyTyped() {
        // Test the keyTyped method for different key events.
        KeyEvent keyTypedEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(keyTypedEvent.getID()).thenReturn(KeyEvent.KEY_TYPED);
        Mockito.when(keyTypedEvent.getWhen()).thenReturn(System.currentTimeMillis());
        Mockito.when(keyTypedEvent.getKeyCode()).thenReturn(KeyEvent.VK_UNDEFINED);
        Mockito.when(keyTypedEvent.getKeyChar()).thenReturn('A');
        pacKeyListener.keyTyped(keyTypedEvent);

    }

    @Test
    void testKeyReleased() {
        // Test the keyReleased method for different key events.
        Action mockAction = Mockito.mock(Action.class);
        keyMappings.put(KeyEvent.VK_DOWN, mockAction);

        KeyEvent downKeyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(downKeyEvent.getID()).thenReturn(KeyEvent.KEY_RELEASED);
        Mockito.when(downKeyEvent.getWhen()).thenReturn(System.currentTimeMillis());
        Mockito.when(downKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);
        Mockito.when(downKeyEvent.getKeyChar()).thenReturn('D');

        pacKeyListener.keyReleased(downKeyEvent);

        Mockito.verify(mockAction, Mockito.times(1)).doAction();
    }
}
