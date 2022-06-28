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

import static vnavesnoj.tictactoe.model.Sign.O;
import static vnavesnoj.tictactoe.model.Sign.X;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class GameFactory {

    private final PlayerType player1Type = PlayerType.USER;

    private final PlayerType player2Type = PlayerType.COMPUTER;

    public GameFactory(final String[] args) {
    }

    public Game create() {
        final CellNumberConverter cellNumberConverter = new DesktopNumericKeypadCellNumberConverter();
        return new Game(
                new DataPrinter(cellNumberConverter),
                new Player(X, new UserTurn(cellNumberConverter)),
                new Player(O, new ComputerTurn()),
                new WinnerVerifier(),
                new CellVerifier(),
                new WinnerAnnouncement(),
                true
        );
    }
}
