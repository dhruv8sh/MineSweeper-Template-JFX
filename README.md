# MineSweeper-Template-JFX
This is a basic JavaFX implementation of [MineSweeper, the Game](https://en.wikipedia.org/wiki/Minesweeper_(video_game)) that can easily be added to a JFX application.

## How to use?
First create an object of `Board` class
```
Board ob=new Board(X_SIZE,Y_SIZE,MINE_AMOUNT);
```
Then get a JavaFX GridPane using the object.
```
ob.getGridPane();
```
Add this GridPane to your required JavaFX Node/Container to show in a scene.

## Example implementation
![image](https://user-images.githubusercontent.com/67322047/173282433-d2b65103-531e-4e42-bbfc-169c7a876012.png)
![image](https://user-images.githubusercontent.com/67322047/173282618-4bde1527-2d26-4ac6-b5b6-e39248dab69e.png)
![image](https://user-images.githubusercontent.com/67322047/173282703-843c1f0b-d21a-45b0-81bd-57be1da384bc.png)
