package player;

import java.awt.Point;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HumanPlayerTest {

	private static HumanPlayer player = new HumanPlayer('X');
	private static Point point = new Point(3, 1);

	@DisplayName("cc: leading number, small letter")
	@Test
	void testConvertCoordinate1() {
		Assert.assertEquals(HumanPlayerTest.player.convertCoordinates(4, "3d"), HumanPlayerTest.point);
	}

	@DisplayName("cc: leading small letter, number")
	@Test
	void testConvertCoordinate2() {
		Assert.assertEquals(HumanPlayerTest.player.convertCoordinates(4, "d3"), HumanPlayerTest.point);
	}

	@DisplayName("cc: leading number, capital letter")
	@Test
	void testConvertCoordinate3() {
		Assert.assertEquals(HumanPlayerTest.player.convertCoordinates(4, "3D"), HumanPlayerTest.point);
	}

	@DisplayName("cc: leading capital letter, number")
	@Test
	void testConvertCoordinate4() {
		Assert.assertEquals(HumanPlayerTest.player.convertCoordinates(4, "D3"), HumanPlayerTest.point);
	}

	@DisplayName("ivs: string length < 2")
	@Test
	void testIsValidString01() {
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "1"));
	}

	@DisplayName("ivs: string length > 3")
	@Test
	void testIsValidString02() {
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "1111"));
	}

	@DisplayName("ivs: string length == 3, 2nd character is a letter")
	@Test
	void testIsValidString03() {
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "1a1"));
	}

	@DisplayName("ivs: character is neither a letter nor a number")
	@Test
	void testIsValidString04() {
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "/1"));
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "1/"));
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, ":1"));
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "1:"));
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "`1"));
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "1`"));
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "{1"));
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "1{"));
	}

	@DisplayName("ivs: letter count > 1")
	@Test
	void testIsValidString05() {
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "aa1"));
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "1aa"));
	}

	@DisplayName("ivs: letter count == 0")
	@Test
	void testIsValidString06() {
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "111"));
	}

	@DisplayName("ivs: valid letter")
	@Test
	void testIsValidString07() {
		Assert.assertTrue(HumanPlayerTest.player.isValidString(15, "o1"));
	}

	@DisplayName("ivs: invalid letter")
	@Test
	void testIsValidString08() {
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "p1"));
	}

	@DisplayName("ivs: valid number")
	@Test
	void testIsValidString09() {
		Assert.assertTrue(HumanPlayerTest.player.isValidString(15, "a1"));
		Assert.assertTrue(HumanPlayerTest.player.isValidString(15, "a15"));
	}

	@DisplayName("ivs: invalid number")
	@Test
	void testIsValidString10() {
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "a0"));
		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "a16"));
	}
}
