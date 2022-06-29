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

import vnavesnoj.tictactoe.component.keypad.DesktopNumericKeypadCellNumberConverter;
import vnavesnoj.tictactoe.model.Player;
import vnavesnoj.tictactoe.model.PlayerType;

import static vnavesnoj.tictactoe.model.PlayerType.USER;
import static vnavesnoj.tictactoe.model.Sign.O;
import static vnavesnoj.tictactoe.model.Sign.X;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class GameFactory {

    private final PlayerType player1Type;

    private final PlayerType player2Type;

    public GameFactory(final String[] args) {
        final CommandLineArgumentParser.PlayerTypes playerTypes = new CommandLineArgumentParser(args).parse();
        this.player1Type = playerTypes.getPlayer1Type();
        this.player2Type = playerTypes.getPlayer2Type();
    }

    public Game create() {
        final CellNumberConverter cellNumberConverter = new DesktopNumericKeypadCellNumberConverter();
        final Player player1;
        if (player1Type == USER) {
            player1 = new Player(X, new UserTurn(cellNumberConverter));
        } else {
            player1 = new Player(X, new ComputerTurn());
        }
        final Player player2;
        if (player2Type == USER) {
            player2 = new Player(O, new UserTurn(cellNumberConverter));
        } else {
            player2 = new Player(O, new ComputerTurn());
        }
        boolean canSecondPlayerMakeFirstMove = player1Type != player2Type;
        return new Game(
                new DataPrinter(cellNumberConverter),
                player1,
                player2,
                new WinnerVerifier(),
                new CellVerifier(),
                new WinnerAnnouncement(),
                canSecondPlayerMakeFirstMove
        );
    }
}
