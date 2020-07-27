package player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void testConvertCoordinate() {

		var player = new Player('X');
		var point = new Point();
		
		point.x = 2;
		point.y = 0;
		assertEquals(player.convertCoordinates(3, "3c"), point);
		
		point.x = 0;
		point.y = 9;
		assertEquals(player.convertCoordinates(10, "1a"), point);
		
		point.x = 9;
		point.y = 9;
		assertEquals(player.convertCoordinates(10,"1j"), point);
	}
	
	@Test
	void testIsValidStirng() {
		var player = new Player('X');
		assertTrue("no valid String",player.isValidString(15, "a1"));
		assertTrue(player.isValidString(15, "1a"));
		assertTrue(player.isValidString(15, "a10"));
		assertTrue(player.isValidString(15, "10a"));
		
		assertFalse(player.isValidString(15," "));
		assertFalse(player.isValidString(15,"1"));
		assertFalse(player.isValidString(15,"a"));
		assertFalse(player.isValidString(15,"11"));
		assertFalse(player.isValidString(15,"ab"));
		assertFalse(player.isValidString(15,"1ab"));
		assertFalse(player.isValidString(15,"ab1"));
		assertFalse(player.isValidString(15,"16a"));
		assertFalse(player.isValidString(15,"a16"));
	}

}
