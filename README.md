# **Reversi**
This was the last assessment for my Further OOP university module. I was awarded full marks for this assessment.
 
## Description
Developing a two-player version of the “Reversi” game in Java using Swing.
Reversi is a two-player game in which the players place identical game pieces called disks on a rectangular board. Disks are light on one side and dark on the other. The objective of the game is to have the majority of disks turned to display the player's colour when the last playable empty square is filled.

### Board
There are many variations on the board size, the most commonly used being 8x8. Boards must be squared and the numbers of rows and columns must be even. At the start of the game four pieces of alternating colours are disposed in a 2x2 square at the centre of the board. At the start of the game players choose their colour and the black starts placing disks on the board with their colour facing up.

### Captures 
When a player places a disk on the board, e.g a black disk, he/she also flips to black any white disks that are in a straight line (horizontal, vertical or diagonal) between the black disk just placed and other black disks. When the black player places a disk on the board, it flips four white disks to the black side.

### Legal moves and end of game
Each player can only place a disk in such a position that at least one of the opponent's disks is captured. All dotted circles indicate where the black player can place his/her starting piece. If, during the course of the game, a player cannot place a disk, the play passes back to the other player. When neither player can move, the game ends.

## Implementation Requirments

The two players can play many games in one session. The screen is divided into three areas. On the right panel it shows players’ names and their respective scores in the session as well as the number of disks of each colour on the board. Players enter their names in appropriate boxes on this panel and then click the “Play” button. A new session for different players can be initiated through the menu.

The main area shows a grid of buttons to place “discs”. At the 
start the default size is 8x8 but this can be changed at any time by accessing the menu. If a game is in progress the application asks for confirmation before resetting the table. At the bottom a status bar displays game status messages, e.g. which player is currently playing.

Labels on the buttons initially are empty, but they will change to white (or black) discs to signify that a disc of the first (second) player occupies that specific position. If an already “occupied” button is clicked nothing happens. If the button pressed is “unoccupied” then the programme checks whether the move is legal, i.e. if it would result in the capture of at least one disk. If that is not the case the game board does not change but the status bar is updated to show a message indicating an illegal move. When the current player selects a valid move a disk of the player's colour is placed on the board and the application flips all captured disks i.e., disks of the opponent’s colour in a continuous straight line (horizontal, vertical or diagonal) between the placed disk to a disk of the current player's colour by changing "W" labels into "B" (or vice-versa). Then the total number of disks of each player is counted and updated on the right pane.

When a player cannot make any legal moves a pop-up dialog alerts the current player and after confirmation, the turn moves to the other player. At the start of each player’s turn the system computes which of the players can make any legal move. If neither player can make a legal move or the board is full the game is over. The system checks the number of disks for each colour, updates the scores and declares the winner with the highest score. A button is then made visible on the right panel for players to initiate a new game.

Save files: The application state corresponding to the current game session can be saved in and restored from a save file (you can decide the proper UI elements for invoking and managing this function). The application state includes the information about the session and the current game. After loading a save file the application should resume execution from the same state it had when the save file was saved. You need to handle exceptions while reading/writing the save file and you should use exceptions for handling malformed/corrupted save files.

UI: You will need to use a number of layout managers to develop the game interface. You should not use absolute positioning for placing components and should test that resizing the main window results in a sensible layout behaviour. You should respect the above interface specification but you can make the interface nicer (e.g. adding images to buttons instead of letters, etc.)
Board: To generate buttons you need to use a loop according to the board size. You are forbidden from using a UI designer – UI code must be hand coded.

Miscellaneous: As always, marks will be awarded for concise code that is properly formatted and commented. Your code should also be developed in a manner such that it could easily be extended.