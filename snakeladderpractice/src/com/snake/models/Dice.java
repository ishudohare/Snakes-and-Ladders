/**
 * 
 */
package com.snake.models;

import java.util.Random;

import lombok.*;

/**
 * @author ishudohare
 *
 */

@Getter
@Setter
@AllArgsConstructor
public class Dice {
	private int currentHead;
	
	public  int rollDice()
	{
		Random random = new Random();
		this.currentHead= random.nextInt(5)+1;
		return currentHead;
	}
	

}
