package student.adventure;

import java.io.FileNotFoundException;
import org.junit.Test;
import student.server.AdventureException;
import student.server.HendrickService;

import static org.junit.Assert.assertEquals;

public class HendrickTest {
    HendrickService service = new HendrickService();

    @Test
    public void test_reset() {
        service.reset();
        assertEquals(0, service.getId());
    }

    @Test
    public void test_newGame() throws AdventureException, FileNotFoundException {
        assertEquals(0, service.newGame());
    }
}
