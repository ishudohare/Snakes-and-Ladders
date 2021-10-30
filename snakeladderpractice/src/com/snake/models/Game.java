/**
 * 
 */
package com.snake.models;

import java.util.*;

import lombok.*;


/**
 * @author ishudohare
 *
 */

@Getter
@Setter
@AllArgsConstructor
public class Game {	
	private Board board;
	private List<Snake> snakes;
	private List<Ladder> ladders;
	private List<Player> players;
	private Dice dice;
	
	
	
	

}
