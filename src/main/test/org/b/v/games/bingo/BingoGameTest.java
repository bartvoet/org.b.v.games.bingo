package org.b.v.games.bingo;

import static org.junit.Assert.*;

import org.junit.Test;

public class BingoGameTest {

	@Test
	public void a_bingo_game_with_any_positive_int_is_ok() {
		new BingoGame(1);
		new BingoGame(10);
	}
	
	@Test
	public void a_bingo_game_should_contain_at_least_1_value() {
		try {
			new BingoGame(0);
		} catch(IllegalArgumentException e) {
			assertEquals("size should be at least 1, is now 0",e.getMessage());
		}
	}
	
	@Test
	public void a_bingo_game_should_not_be_negative() {
		try {
			new BingoGame(-10);
		} catch(IllegalArgumentException e) {
			assertEquals("size should be at least 1, is now -10",e.getMessage());
		}
	}
}
