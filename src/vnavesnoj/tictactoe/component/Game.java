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

    private final DrawVerifier drawVerifier;

    public Game(final DataPrinter dataPrinter,
                final ComputerTurn computerTurn,
                final UserTurn userTurn,
                final WinnerVerifier winnerVerifier,
                final DrawVerifier drawVerifier) {
        this.dataPrinter = dataPrinter;
        this.computerTurn = computerTurn;
        this.userTurn = userTurn;
        this.winnerVerifier = winnerVerifier;
        this.drawVerifier = drawVerifier;
    }

    public void play() {
        System.out.println("Use the following mapping table to specify a cell using numbers from 1 to 9");
        dataPrinter.printMappingTable();
        final GameTable gameTable = new GameTable();
        if (new Random().nextBoolean()) {
            computerTurn.makeMove(gameTable);
            dataPrinter.printGameTable(gameTable);
        }
        while (true) {
            userTurn.makeMove(gameTable);
            dataPrinter.printGameTable(gameTable);
            if (winnerVerifier.isUserWinner(gameTable)) {
                System.out.println("You win");
                break;
            }
            if (drawVerifier.isDraw(gameTable)) {
                System.out.println("DRAW");
                break;
            }
            computerTurn.makeMove(gameTable);
            dataPrinter.printGameTable(gameTable);
            if (winnerVerifier.isComputerWinner(gameTable)) {
                System.out.println("Computer win");
                break;
            }
            if (drawVerifier.isDraw(gameTable)) {
                System.out.println("DRAW");
                break;
            }
        }
        System.out.println("Game over");
    }
}
