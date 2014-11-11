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
		//Assignment 1
		double cosTheta = (direction.dot(targetDirection))/targetDirection.len();
		
		if(cosTheta > 1){
			cosTheta = 1;
		}
		
		double deg = Math.acos(cosTheta); //gets converted to radians
		
		//create smooth rotation
		// libGDX works in degrees need to convert to degree
		deg = Math.toDegrees(deg) * deltaTime;
		
		if(direction.crs(targetDirection) > 0){
			deg = -deg;
		}
		
		sprite.rotate((float) deg);
		direction.rotate(-(float) deg);
		
	}

	public void moveForward(float deltaTime) {
//		Student, your code goes here		
		
	}

}
