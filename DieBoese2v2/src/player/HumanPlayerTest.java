package player;

import java.awt.Point;
import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Data;

class HumanPlayerTest {

	private static Data data = new Data(4);
	private static HumanPlayer player = new HumanPlayer('X', HumanPlayerTest.data);
	private static Point point = new Point(3, 1);

	@DisplayName("order: leading number, small letter")
	@Test
	void testConvertCoordinate1() {
		var in = new ByteArrayInputStream("3d".getBytes());
		System.setIn(in);
		HumanPlayerTest.player.move();
		Assertions.assertEquals(HumanPlayerTest.player.getMyMove(), HumanPlayerTest.point);
	}

	@DisplayName("order: leading small letter, number")
	@Test
	void testConvertCoordinate2() {
		var in = new ByteArrayInputStream("d3".getBytes());
		System.setIn(in);
		HumanPlayerTest.player.move();
		Assertions.assertEquals(HumanPlayerTest.player.getMyMove(), HumanPlayerTest.point);
	}

	@DisplayName("order: leading number, capital letter")
	@Test
	void testConvertCoordinate3() {
		var in = new ByteArrayInputStream("3D".getBytes());
		System.setIn(in);
		HumanPlayerTest.player.move();
		Assertions.assertEquals(HumanPlayerTest.player.getMyMove(), HumanPlayerTest.point);
	}

	@DisplayName("order: leading capital letter, number")
	@Test
	void testConvertCoordinate4() {
		var in = new ByteArrayInputStream("D3".getBytes());
		System.setIn(in);
		HumanPlayerTest.player.move();
		Assertions.assertEquals(HumanPlayerTest.player.getMyMove(), HumanPlayerTest.point);
	}

	@DisplayName("fail: string length < 2")
	@Test
	void testIsValidString01() {
		var in = new ByteArrayInputStream("1".getBytes());
		System.setIn(in);
		Assertions.assertThrows(NullPointerException.class, () -> {
			HumanPlayerTest.player.move();
		});
	}

	@DisplayName("fail: string length > 3")
	@Test
	void testIsValidString02() {
		var in = new ByteArrayInputStream("1111".getBytes());
		System.setIn(in);
		Assertions.assertThrows(NullPointerException.class, () -> {
			HumanPlayerTest.player.move();
		});
	}

	@DisplayName("fail: string length == 3, 2nd character is a letter")
	@Test
	void testIsValidString03() {
		var in = new ByteArrayInputStream("1a1".getBytes());
		System.setIn(in);
		Assertions.assertThrows(NullPointerException.class, () -> {
			HumanPlayerTest.player.move();
		});
	}
//
//	@DisplayName("ivs: character is neither a letter nor a number")
//	@Test
//	void testIsValidString04() {
//		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "/1"));
//		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "1/"));
//		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, ":1"));
//		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "1:"));
//		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "`1"));
//		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "1`"));
//		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "{1"));
//		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "1{"));
//	}
//
//	@DisplayName("ivs: letter count > 1")
//	@Test
//	void testIsValidString05() {
//		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "aa1"));
//		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "1aa"));
//	}
//
//	@DisplayName("ivs: letter count == 0")
//	@Test
//	void testIsValidString06() {
//		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "111"));
//	}
//
//	@DisplayName("ivs: valid letter")
//	@Test
//	void testIsValidString07() {
//		Assert.assertTrue(HumanPlayerTest.player.isValidString(15, "o1"));
//	}
//
//	@DisplayName("ivs: invalid letter")
//	@Test
//	void testIsValidString08() {
//		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "p1"));
//	}
//
//	@DisplayName("ivs: valid number")
//	@Test
//	void testIsValidString09() {
//		Assert.assertTrue(HumanPlayerTest.player.isValidString(15, "a1"));
//		Assert.assertTrue(HumanPlayerTest.player.isValidString(15, "a15"));
//	}
//
//	@DisplayName("ivs: invalid number")
//	@Test
//	void testIsValidString10() {
//		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "a0"));
//		Assert.assertFalse(HumanPlayerTest.player.isValidString(15, "a16"));
//	}
//
//	@DisplayName("mv: positive tests")
//	@Test
//	void testMove() {
//		HumanPlayerTest.player.move(4, "a4");
//		Assertions.assertEquals(new Point(0, 0), HumanPlayerTest.player.getMyMove());
//
//		HumanPlayerTest.player.move(4, "4a");
//		Assertions.assertEquals(new Point(0, 0), HumanPlayerTest.player.getMyMove());
//
//		HumanPlayerTest.player.move(4, "a1");
//		Assertions.assertEquals(new Point(0, 3), HumanPlayerTest.player.getMyMove());
//
//		HumanPlayerTest.player.move(4, "1a");
//		Assertions.assertEquals(new Point(0, 3), HumanPlayerTest.player.getMyMove());
//
//		HumanPlayerTest.player.move(4, "d4");
//		Assertions.assertEquals(new Point(3, 0), HumanPlayerTest.player.getMyMove());
//
//		HumanPlayerTest.player.move(4, "4d");
//		Assertions.assertEquals(new Point(3, 0), HumanPlayerTest.player.getMyMove());
//
//		HumanPlayerTest.player.move(4, "d1");
//		Assertions.assertEquals(new Point(3, 3), HumanPlayerTest.player.getMyMove());
//
//		HumanPlayerTest.player.move(4, "1d");
//		Assertions.assertEquals(new Point(3, 3), HumanPlayerTest.player.getMyMove());
//	}
}
