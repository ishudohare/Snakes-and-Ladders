/**
 * 
 */
package com.snake.driver;

import java.util.*;

import com.snake.models.Board;
import com.snake.models.Dice;
import com.snake.models.Game;
import com.snake.models.Ladder;
import com.snake.models.Player;
import com.snake.models.Snake;

/**
 * @author ishudohare
 *
 */
public class DriverClass {

	public static int boardSize;
	public static int snakePopulation;
	public static int ladderNumber;
	public static int playerNumber;

	public static HashSet<Integer> firstNumber = new HashSet<>();
	public static HashSet<Integer> secondNumber = new HashSet<>();

	public static List<Snake> snakes;
	public static List<Ladder> ladders;
	public static List<Player> players;
	public static Board board;
	public static Dice dice;
	public static Game game;
	public static Scanner sc;

	public static void main(String args[]) {
		sc = new Scanner(System.in);
		System.out.println("Enter snake ladder board game size, nigga");
		boardSize = sc.nextInt();
		System.out.println("Enter number of snakes to be added, nigga");
		snakePopulation = sc.nextInt();
		System.out.println("Enter number of climbable ladders, nigga");
		ladderNumber = sc.nextInt();

		System.out.println("Enter number of players");
		playerNumber = sc.nextInt();

		initBoardDetails();
		sc.close();
		initGame();
		

	}

	private static void initGame() {
		// method where actual playing of game occurs;

		Queue<Player> q = new LinkedList<>(players);

		Player currentPlayer;
		int currentPosition;
		int diceNumber;
		int nextPosition;

		while (!q.isEmpty()) {
			currentPlayer = q.poll();
			currentPosition = currentPlayer.getCurrentPosition();
			diceNumber = dice.rollDice();

			nextPosition = getNextPlayerPositin(currentPosition, diceNumber, currentPlayer.getName());

			if (nextPosition == boardSize) {
				System.out.println(currentPlayer.getName() + " won ");
			} else {
				currentPlayer.setCurrentPosition(nextPosition);
				q.add(currentPlayer);
			}
		}
		System.out.println("Game is finished, go find something else to occupy your mind to, nigga!");

	}

	private static int getNextPlayerPositin(int currentPosition, int diceNumber, String playerName) {
		if (currentPosition + diceNumber == boardSize)
			return boardSize;
		else if (currentPosition + diceNumber > boardSize)
			return currentPosition;

		for (int i = 0; i < snakePopulation; i++) {
			if ((currentPosition + diceNumber) == snakes.get(i).getHead()) {
				System.out.println(playerName + " is bit by a snake");
				return snakes.get(i).getTail();

			}
		}
		for (int i = 0; i < ladderNumber; i++) {
			if (currentPosition + diceNumber == ladders.get(i).getStartPosition()) {
				System.out.println(playerName + " climbed a ladder");
				return ladders.get(i).getEndPosition();

			}
		}

		return currentPosition + diceNumber;
	}

	private static void initBoardDetails() {

		snakes = getSnakeDetails();
		ladders = getLadderDetails();
		players = getPlayerDetails();
		board = new Board(boardSize);
		dice = new Dice(3);
		game = new Game(board, snakes, ladders, players, dice);

	}

	private static List<Player> getPlayerDetails() {

		List<Player> players = new ArrayList<>();

		for (int i = 0; i < playerNumber; i++) {
			System.out.println("Enter new player name");

			Player player = new Player(sc.next(), 1);

			players.add(player);

		}
		System.out.println("Players are initialed\n");

		return players;
	}

	private static List<Ladder> getLadderDetails() {

		int ladderStart = 2;
		int ladderEnd = 2;
		Random random = new Random();

		List<Ladder> ladders = new ArrayList<>();
		for (int i = 0; i < ladderNumber; i++) {

			while (true) {
				if (ladderEnd > ladderStart && !firstNumber.contains(ladderStart)
						&& !secondNumber.contains(ladderEnd)) {
					firstNumber.add(ladderStart);
					secondNumber.add(ladderEnd);
					break;
				}
				ladderStart = random.nextInt(boardSize - 2) + 2;
				ladderEnd = random.nextInt(boardSize - 2) + 2;

			}
			Ladder ladder = new Ladder(ladderStart, ladderEnd);
			ladders.add(ladder);

		}

		System.out.println("Ladders are initialized\n");

		return ladders;
	}

	private static List<Snake> getSnakeDetails() {

		int snakeHead = 2;
		int snakeTail = 2;
		Random random = new Random();
		List<Snake> snakes = new ArrayList<>();
		for (int i = 0; i < snakePopulation; i++) {
			while (true) {

				if (snakeHead > snakeTail && !firstNumber.contains(snakeTail) && !secondNumber.contains(snakeHead)) {
					firstNumber.add(snakeTail);
					secondNumber.add(snakeHead);
					break;
				}
				snakeTail = random.nextInt(boardSize - 2) + 2;
				snakeHead = random.nextInt(boardSize - 2) + 2;
			}
			Snake snake = new Snake(snakeHead, snakeTail);
			snakes.add(snake);
		}
		System.out.println("Snakes are initialized\n");
		return snakes;
	}

}
