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

package vnavesnoj.tictactoe.component.config;

import vnavesnoj.tictactoe.model.config.ComputerComplexity;
import vnavesnoj.tictactoe.model.config.PlayerType;
import vnavesnoj.tictactoe.model.config.UserInterface;

import static vnavesnoj.tictactoe.model.config.PlayerType.COMPUTER;
import static vnavesnoj.tictactoe.model.config.PlayerType.USER;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class CommandLineArgumentParser {

    private final String[] args;

    public CommandLineArgumentParser(final String[] args) {
        this.args = args;

    }

    public GameFeatures parse() {
        PlayerType player1Type = null;
        PlayerType player2Type = null;
        UserInterface userInterface = null;
        ComputerComplexity computerComplexity = null;
        for (final String arg : args) {
            if (USER.name().equalsIgnoreCase(arg) || COMPUTER.name().equalsIgnoreCase(arg)) {
                if (player1Type == null) {
                    player1Type = PlayerType.valueOf(arg.toUpperCase());
                } else if (player2Type == null) {
                    player2Type = PlayerType.valueOf(arg.toUpperCase());
                } else {
                    System.err.printf(
                            "Invalid command line argument: '%s', because player types already set: " +
                                    "player1Type = '%s', player2Type = '%s'%n",
                            arg, player1Type, player2Type
                    );
                }
            } else if (UserInterface.CONSOLE.name().equalsIgnoreCase(arg) || UserInterface.GUI.name().equalsIgnoreCase(arg)) {
                if (userInterface == null) {
                    userInterface = UserInterface.valueOf(arg.toUpperCase());
                } else {
                    System.err.printf(
                            "Invalid command line argument: '%s', because user interface already set: '%s'%n",
                            arg, userInterface
                    );
                }
            } else if ((ComputerComplexity.LEVEL1.name().equalsIgnoreCase(arg) ||
                    ComputerComplexity.LEVEL2.name().equalsIgnoreCase(arg) ||
                    ComputerComplexity.LEVEL3.name().equalsIgnoreCase(arg))) {
                if (computerComplexity == null) {
                    computerComplexity = ComputerComplexity.valueOf(arg.toUpperCase());
                } else {
                    System.err.printf(
                            "Invalid command line argument: '%s', because computer complexity already set: '%s'%n",
                            arg, computerComplexity
                    );
                }
            } else {
                System.err.printf("Unsupported command line argument: '%s'%n", arg);
            }
        }
        if (userInterface == null) {
            userInterface = UserInterface.CONSOLE;
        }
        if (computerComplexity == null) {
            computerComplexity = ComputerComplexity.LEVEL3;
        }
        if (player1Type == null) {
            return new GameFeatures(USER, COMPUTER, userInterface, computerComplexity);
        } else if (player2Type == null) {
            return new GameFeatures(USER, player1Type, userInterface, computerComplexity);
        } else {
            return new GameFeatures(player1Type, player2Type, userInterface, computerComplexity);
        }
    }

    public static class GameFeatures {

        private final PlayerType player1Type;

        private final PlayerType player2Type;

        private final UserInterface userInterface;

        private final ComputerComplexity computerComplexity;

        private GameFeatures(final PlayerType player1Type,
                             final PlayerType player2Type,
                             final UserInterface userInterface, final ComputerComplexity computerComplexity) {
            this.player1Type = player1Type;
            this.player2Type = player2Type;
            this.userInterface = userInterface;
            this.computerComplexity = computerComplexity;
        }

        public PlayerType getPlayer1Type() {
            return player1Type;
        }

        public PlayerType getPlayer2Type() {
            return player2Type;
        }

        public UserInterface getUserInterface() {
            return userInterface;
        }

        public ComputerComplexity getComputerComplexity() {
            return computerComplexity;
        }
    }
}
