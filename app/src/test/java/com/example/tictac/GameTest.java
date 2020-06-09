package com.example.tictac;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {
Game game = new Game();

        @Test
    void zmianaWartosciPlayers2() {
        assertTrue(game.getPlayers2());
        game.setPlayers2(false);
        assertFalse(game.getPlayers2());
    }

    @Test
    void zmianaWartosciPlayers() {
        boolean players=false;
        game.setPlayers(false);
        assertEquals(players,game.getPlayers());
        game.setPlayers(true);
        assertNotEquals(players,game.getPlayers());
    }

    @Test
    void ustawName() {
        game.setName("Adam");
        assertEquals("Adam",game.getName());
        assertNotEquals(7,game.getPlayers());
    }

    @Test
    void ustawPlayer1() {
        game.setPlayer1("Adam");
        assertEquals("Adam",game.getPlayer1());
        assertNotEquals(7,game.getPlayer1());
    }

    @Test
    void ustawPlayer2() {
        game.setPlayer2("Adam");
        assertEquals("Adam",game.getPlayer2());
    }

    @Test
    void wartoscBoard() {
        assertEquals(-1, game.getBoard());
    }

    @Test
    void zmienBoard() {
        game.setBoard(4);
        assertEquals(4, game.getBoard());
    }

}