package edu.zhen.lab3.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Ship extends GameObject implements Updatable{
	private Vector2 direction;
	private Vector2 targetDirection;
	
	public Ship(Texture texture, int x, int y) {
		sprite = new Sprite(texture);
		sprite.setOrigin(texture.getWidth()/2, texture.getHeight()/2);
		sprite.setPosition(x, y);
		direction = new Vector2(0,-1);
		targetDirection = new Vector2(0,-1);
		setIsDrawable(true);
	}
	
	//Assignment 1
	public void face(Vector2 targetPos){
		targetDirection = targetPos;
	}
	
	@Override
	public void update(float deltaTime) {
//		Student, your code goes here		
	}

	public void moveForward(float deltaTime) {
//		Student, your code goes here		
		
	}

}
