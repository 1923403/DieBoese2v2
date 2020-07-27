package player;

import static org.junit.Assert.assertEquals;

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
	}

}
