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

import vnavesnoj.tictactoe.model.Cell;
import vnavesnoj.tictactoe.model.GameTable;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class DataPrinterImpl {

    private final CellNumberConverter cellNumberConverter;

    public DataPrinterImpl(final CellNumberConverter cellNumberConverter) {
        this.cellNumberConverter = cellNumberConverter;
    }

    public void printMappingTable() {
        print((i, k) -> String.valueOf(cellNumberConverter.toNumber(new Cell(i, k))));
    }

    public void printGameTable(final GameTable gameTable) {
        print((i, k) -> String.valueOf(gameTable.getSign(new Cell(i, k))));
    }

    private void print(final Lambda lambda) {
        for (int i = 0; i < 3; i++) {
            System.out.println("-------------");
            for (int k = 0; k < 3; k++) {
                System.out.print("| " + lambda.getValue(i, k) + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------");
    }

    @FunctionalInterface
    private interface Lambda {

        String getValue(int i, int k);
    }
}
