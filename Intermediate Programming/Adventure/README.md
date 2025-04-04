# Adventure Game - README

Welcome to **Adventure**, a **text-based** exploration game inspired by classic adventures like *Colossal Cave Adventure*. In this game, you will navigate through a series of interconnected rooms, collect and use items, and complete tasks to achieve victory. Your objective is to earn points by solving puzzles and ultimately reach the goal room to win the game.

## How to Play
### Commands
The game supports the following commands (case-insensitive):

- **GO direction / MOVE direction / direction** (e.g., `GO NORTH`, `EAST`): Move in the specified direction if possible.
- **TAKE item / GET item**: Pick up an item from the current room.
- **DROP item**: Drop an item in the current room.
- **LOOK**: Display a detailed description of the current room, including exits and items.
- **LOOK item**: Show a description of the specified item if it is in your inventory or in the room.
- **INVENTORY**: List all items you are currently carrying.
- **SCORE**: Display your current score.
- **QUIT**: End the game.

### Scoring & Winning
- Points are awarded for completing specific objectives, such as finding rooms and interacting with items.
- To win, you must complete at least three required tasks and then reach the exit room.
- Repeating a task (such as revisiting a room or dropping and picking up the same item) does not grant additional points.

## Game World
The game consists of at least 10 interconnected rooms with distinct names and descriptions. There are at least 5 items scattered throughout the world, each with unique uses. The layout and objectives will be revealed through exploration.

## File Configuration
The game loads its world dynamically from external files:
- **rooms.txt**: Defines room names, descriptions, and connections.
- **items.txt**: Lists item names, descriptions, and initial locations.
- **scoring.txt**: Specifies point-earning conditions.

## Implementation Notes
- The game is designed as a flexible engine, allowing new adventures to be created by changing the data files without modifying the code.
- Input parsing ensures robust error handling to prevent crashes from invalid commands.

## Extra Features
- The game includes a dynamic description system that changes room details based on the player's progress.
- A hidden easter egg provides a bonus challenge for observant players.

## Acknowledgments
This project was developed as part of a programming course assignment. It draws inspiration from classic text-based adventures and emphasizes modular design for easy customization.

Enjoy the adventure!

