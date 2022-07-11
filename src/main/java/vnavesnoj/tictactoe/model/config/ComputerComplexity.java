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

package vnavesnoj.tictactoe.model.config;

import vnavesnoj.tictactoe.component.ComputerMoveStrategy;
import vnavesnoj.tictactoe.component.strategy.*;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public enum ComputerComplexity {

    LEVEL1(new ComputerMoveStrategy[]{
            new FirstMoveToTheCenterComputerMoveStrategy(),
            new RandomComputerMoveStrategy()
    }),

    LEVEL2(new ComputerMoveStrategy[]{
            new FirstMoveToTheCenterComputerMoveStrategy(),
            new WinNowComputerMoveStrategy(),
            new PreventUserWinComputerMoveStrategy(),
            new RandomComputerMoveStrategy()
    }),

    LEVEL3(new ComputerMoveStrategy[]{
            new FirstMoveToTheCenterComputerMoveStrategy(),
            new WinNowComputerMoveStrategy(),
            new PreventUserWinComputerMoveStrategy(),
            new WinOnTheNextStepComputerMoveStrategy(),
            new RandomComputerMoveStrategy()
    });

    private final ComputerMoveStrategy[] strategies;

    ComputerComplexity(final ComputerMoveStrategy[] strategies) {
        this.strategies = strategies;
    }

    public ComputerMoveStrategy[] getStrategies() {
        return strategies.clone();
    }
}
