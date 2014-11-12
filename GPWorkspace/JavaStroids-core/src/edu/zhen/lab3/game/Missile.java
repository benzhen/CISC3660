package edu.zhen.lab3.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import edu.zhen.lab3.gameobjects.GameObject;
import edu.zhen.lab3.gameobjects.Updatable;

public class Missile extends GameObject implements Updatable {
	private Vector2 dirAndVel;
	private final float VELOCITY = 100;
	
	public Missile(Texture pmap, Vector2 direction, Vector2 position){
		dirAndVel = direction;
		dirAndVel.scl(VELOCITY);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}
	
	
}
