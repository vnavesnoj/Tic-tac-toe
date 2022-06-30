/*
 * Copyright (c) 2022. vnavesnoj <vnavesnoj@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vnavesnoj.tictactoe.component;

import vnavesnoj.tictactoe.model.GameTable;
import vnavesnoj.tictactoe.model.Player;

import java.util.Random;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class Game {

    private final DataPrinter dataPrinter;

    private final Player player1;

    private final Player player2;

    private final WinnerVerifier winnerVerifier;

    private final CellVerifier cellVerifier;

    private final WinnerAnnouncement winnerAnnouncement;

    private final GameOverHandler gameOverHandler;

    private final boolean canSecondPlayerMakeFirstMove;

    public Game(final DataPrinter dataPrinter,
                final Player player1,
                final Player player2,
                final WinnerVerifier winnerVerifier,
                final CellVerifier cellVerifier,
                final WinnerAnnouncement winnerAnnouncement,
                final GameOverHandler gameOverHandler,
                final boolean canSecondPlayerMakeFirstMove) {
        this.dataPrinter = dataPrinter;
        this.player1 = player1;
        this.player2 = player2;
        this.winnerVerifier = winnerVerifier;
        this.cellVerifier = cellVerifier;
        this.winnerAnnouncement = winnerAnnouncement;
        this.gameOverHandler = gameOverHandler;
        this.canSecondPlayerMakeFirstMove = canSecondPlayerMakeFirstMove;
    }

    public void play() {
        dataPrinter.printInstruction();
        final GameTable gameTable = new GameTable();
        if (canSecondPlayerMakeFirstMove && new Random().nextBoolean()) {
            player2.makeMove(gameTable);
            dataPrinter.printGameTable(gameTable);
        }
        final Player[] players = {player1, player2};
        boolean isGameOn = true;
        while (isGameOn) {
            for (Player player : players) {
                player.makeMove(gameTable);
                dataPrinter.printGameTable(gameTable);
                if (winnerVerifier.isWinner(gameTable, player)) {
                    winnerAnnouncement.announceWinner(player);
                    isGameOn = false;
                    break;
                }
                if (cellVerifier.allCellsFilled(gameTable)) {
                    winnerAnnouncement.announceDraw();
                    isGameOn = false;
                    break;
                }
            }
        }
        gameOverHandler.gameOver();
    }
}
