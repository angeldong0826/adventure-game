package student.adventure;

import java.io.FileNotFoundException;
import java.util.Map;
import org.junit.Test;
import student.server.AdventureException;
import student.server.GameStatus;
import student.server.HendrickService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for game api.
 */
public class HendrickTest {
    HendrickService service = new HendrickService();

    @Test
    public void test_reset() throws FileNotFoundException, AdventureException {
        service.newGame();
        service.reset();
        assertEquals(0, service.getId());
    }

    @Test
    public void test_newGame() throws AdventureException, FileNotFoundException {
        assertEquals(0, service.newGame());
        assertEquals(1, service.getMap().size());
        assertEquals(1, service.newGame());
        assertEquals(2, service.getMap().size());
    }

    @Test
    public void test_getExistingGame() throws FileNotFoundException, AdventureException {
        service.newGame();
        GameStatus status = service.getGame(0);
        assertFalse(status.isError());
        service.newGame();
        GameStatus newStatus = service.getGame(1);
        assertFalse(status.isError());
    }

    @Test
    public void test_getNonexistentGame() throws FileNotFoundException, AdventureException {
        service.newGame();
        GameStatus status = service.getGame(1);
        assertTrue(status.isError());
    }

    @Test
    public void test_destroyGame() throws FileNotFoundException, AdventureException {
        service.newGame();
        assertTrue(service.destroyGame(0));
        assertEquals(0, service.getMap().size());
        service.newGame();
        service.newGame();
        assertTrue(service.destroyGame(1));
        assertEquals(1, service.getMap().size());
    }
}
