package player;

import java.awt.Point;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void testConvertCoordinate() {

		var player = new Player('X');
		var point = new Point();

		point.x = 2;
		point.y = 0;
		Assert.assertEquals(player.convertCoordinates(3, "3c"), point);

		point.x = 0;
		point.y = 9;
		Assert.assertEquals(player.convertCoordinates(10, "1a"), point);

		point.x = 9;
		point.y = 9;
		Assert.assertEquals(player.convertCoordinates(10, "1j"), point);
	}

	@Test
	void testIsValidStirng() {
		var player = new Player('X');
		Assert.assertTrue("no valid String", player.isValidString(15, "a1"));
		Assert.assertTrue(player.isValidString(15, "1a"));
		Assert.assertTrue(player.isValidString(15, "a10"));
		Assert.assertTrue(player.isValidString(15, "10a"));

		Assert.assertFalse(player.isValidString(15, " "));
		Assert.assertFalse(player.isValidString(15, "1"));
		Assert.assertFalse(player.isValidString(15, "a"));
		Assert.assertFalse(player.isValidString(15, "11"));
		Assert.assertFalse(player.isValidString(15, "ab"));
		Assert.assertFalse(player.isValidString(15, "1ab"));
		Assert.assertFalse(player.isValidString(15, "ab1"));
		Assert.assertFalse(player.isValidString(15, "16a"));
		Assert.assertFalse(player.isValidString(15, "a16"));
	}

}
