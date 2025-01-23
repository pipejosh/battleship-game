# Battleship Game

A graphical implementation of the classic Battleship game written in Java. This game allows two players to compete against each other by placing ships on a grid and trying to hit the opponent’s ships.

## Features

- Fully functional GUI for playing the game
- Two-player gameplay (local)
- Customizable grid size
- Multiple ship types with different sizes
- Gameplay mechanics (guessing, hitting, and sinking ships)
- Intuitive interface for placing ships and making guesses

## Prerequisites

Ensure you have the following installed on your machine:

- Java Development Kit (JDK) 8 or higher
- An IDE like IntelliJ IDEA, Eclipse, or NetBeans (optional)

### Verify Java Installation

To check if Java is installed, run the following command in your terminal or command prompt:

```bash
java -version
```

If Java is installed, this will display the installed version of Java.

## Installation

1. Clone this repository:

```bash
git clone https://github.com/pipejosh/battleship-game.git
```

2. Navigate to the repository directory:

```bash
cd battleship-game
```

3. Open the project in your preferred Java IDE (like IntelliJ IDEA, Eclipse, or NetBeans).

4. Build and run the project from the IDE or from the command line.

   To run the game from the command line, use:

```bash
javac Main.java
java Main
```

## How to Play

1. The game board is displayed with a grid where each player places their ships.
2. Players take turns making guesses to locate and hit the opponent’s ships.
3. A player wins once all the opponent’s ships have been sunk.

## Code Structure

- `Main.java`: The entry point of the game, initializing the GUI and handling the main gameplay logic.
- `Board.java`: Manages the game board, including placing ships and handling hits.
- `Ship.java`: Defines ship properties, such as size and coordinates.
- `Player.java`: Handles player actions like placing ships and making guesses.
- `GameGUI.java`: Contains the graphical user interface components and event listeners.

## Contributing

If you’d like to contribute to this project, feel free to fork the repository and submit a pull request. Please follow the coding style and ensure all new features are well-documented.

### Issues

If you encounter any bugs or issues, please feel free to open an issue in the GitHub Issues section.

### AUTHORS
- Yarik228666
- Lizziee44
- pipejosh
