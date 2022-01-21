import java.util.*;
class Main 
{ 
  public static Scanner input = new Scanner(System.in);
  public static boolean isWinner = false;
  
  public static void main(String[] args) {
    // declare an emtpy gamestate
    char[][] gameState = new char[][] { {' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};

    //  true = x's go, false = 0's go
    boolean player = true;
    int turn = 0;

    //run primary loop whole there isnt a winner and there had been less than 9 turns
    while(!isWinner&& turn < 9)
    {
      turn(gameState,player);

      //swap players turn and incrememt the turn counter
      player = !player;
      turn++;
    }
    //checks if 9 turns or more have passed without a winner : a stalemate
    if(turn >= 9 && !isWinner)
    {
      System.out.println( "stalemate" );
    }
    else
    {
      player = !player;
      outputGamestate(gameState);
      System.out.println((player ? "X" : "O" )+ " wins" );
    }
  }
  
  public static void turn(char[][] gameState,boolean player)
  {
    //output numbers 1-9 mapped to there position on the grid
    outputGamestate(new char[][]{{'7','8','9'},{'4','5','6'},{'1','2','3'}});
    System.out.println("\n");

    // outputs current gameState
    outputGamestate(gameState);
    
    //recives input 
    int inputNumber = input.nextInt()-1;
    boolean validInput = false;

    while(!validInput)
    {
      //checks if in range 1-9 if not take new input
      while (inputNumber < 1 && inputNumber > 9) 
      {
        System.out.println("invalid");
        inputNumber = input.nextInt()-1;
      }

      //converts from number from 0-8 to a (0-2 , 0-2)
       int inputY =2 -  inputNumber /3;
      int inputX = inputNumber % 3;

     //checks if position is empty
      if(gameState[inputY][inputX]==' ')
      {
        //sets the gamestate at pos (inputY , inputX) to x or 0
       gameState[inputY][inputX] = player ? 'X': 'O';
      
        //checks if there is a winner
        isWinner= checkWinCondition(gameState,player,inputX,inputY); 
        validInput = true;
      }
      else
      {
        System.out.println("invalid");
      }
    }
  }

  public static boolean checkWinCondition(char[][] gameState,boolean player, int x, int y)
  {
    // checks the row and collum of position for a win condition
    char p = player ? 'X': 'O';
    if (gameState[y][0]==p && gameState[y][1]==p && gameState[y][2]==p)
    {
      return true;
    }
    else if  (gameState[0][x]==p && gameState[1][x]==p && gameState[2][x]==p)
    {
      return true;
    }

    //checks diagonals
    if(x==y)
    {
      if(gameState[0][0]==p && gameState[1][1]==p && gameState[2][2]==p)
      {
        return true;
      }
    }
    if(2-x == y)
    {
      if(gameState[2][0]==p && gameState[1][1]==p && gameState[0][2]==p)
      {
        return true;
      }
    }
    return false;
  }


  public static void outputGamestate(char[][] state)
  {
    //outputs a grid filled with s gamestate
    System.out.println(String.format("_%c_|_%c_|_%c_",state[0][0],state[0][1],state[0][2]));
    System.out.println(String.format("_%c_|_%c_|_%c_",state[1][0],state[1][1],state[1][2]));
    System.out.println(String.format(" %c | %c | %c ",state[2][0],state[2][1],state[2][2]));
  }
}