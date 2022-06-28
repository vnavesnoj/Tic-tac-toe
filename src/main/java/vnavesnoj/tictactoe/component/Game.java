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

import java.util.Random;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class Game {

    private final DataPrinter dataPrinter;

    private final ComputerTurn computerTurn;

    private final UserTurn userTurn;

    private final WinnerVerifier winnerVerifier;

    private final CellVerifier cellVerifier;

    public Game(final DataPrinter dataPrinter,
                final ComputerTurn computerTurn,
                final UserTurn userTurn,
                final WinnerVerifier winnerVerifier,
                final CellVerifier cellVerifier) {
        this.dataPrinter = dataPrinter;
        this.computerTurn = computerTurn;
        this.userTurn = userTurn;
        this.winnerVerifier = winnerVerifier;
        this.cellVerifier = cellVerifier;
    }

    public void play() {
        System.out.println("Use the following mapping table to specify a cell using numbers from 1 to 9");
        dataPrinter.printMappingTable();
        final GameTable gameTable = new GameTable();
        if (new Random().nextBoolean()) {
            computerTurn.makeMove(gameTable);
            dataPrinter.printGameTable(gameTable);
        }
        final Turn[] turns = {userTurn, computerTurn};
        boolean isGameOn = true;
        while (isGameOn) {
            for (Turn turn : turns) {
                turn.makeMove(gameTable);
                dataPrinter.printGameTable(gameTable);
                if (turn instanceof UserTurn) {
                    if (winnerVerifier.isUserWinner(gameTable)) {
                        System.out.println("You win");
                        isGameOn = false;
                        break;
                    }
                }
                if (turn instanceof ComputerTurn) {
                    if (winnerVerifier.isComputerWinner(gameTable)) {
                        System.out.println("Computer win");
                        isGameOn = false;
                        break;
                    }
                }
                if (cellVerifier.allCellsFilled(gameTable)) {
                    System.out.println("DRAW");
                    isGameOn = false;
                    break;
                }
            }
        }
        System.out.println("Game over");
    }
}
