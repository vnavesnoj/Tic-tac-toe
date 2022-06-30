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

package vnavesnoj.tictactoe.component.console;

import vnavesnoj.tictactoe.component.DataPrinter;
import vnavesnoj.tictactoe.component.UserInputReader;
import vnavesnoj.tictactoe.model.game.Cell;

import java.util.Scanner;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class ConsoleUserInputReader implements UserInputReader {

    private final CellNumberConverter cellNumberConverter;

    private final DataPrinter dataPrinter;

    public ConsoleUserInputReader(final CellNumberConverter cellNumberConverter, final DataPrinter dataPrinter) {
        this.cellNumberConverter = cellNumberConverter;
        this.dataPrinter = dataPrinter;
    }

    @Override
    public Cell getUserInput() {
        dataPrinter.printInfoMessage("Please type number between 1 and 9: ");
        final String enter = new Scanner(System.in).nextLine();
        if (enter.length() == 1) {
            if (enter.charAt(0) >= '1' && enter.charAt(0) <= '9') {
                final int enteredNumber = Integer.parseInt(enter);
                return cellNumberConverter.toCell(enteredNumber);
            }
        }
        return null;
    }
}
