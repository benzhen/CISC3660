package edu.zhen.lab3.gameobjects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import edu.zhen.lab3.game.Constants;
import edu.zhen.lab3.gameobjects.Asteroid;
import edu.zhen.lab3.gameobjects.GameObject;
import edu.zhen.lab3.gameobjects.Missile;
import edu.zhen.lab3.gameobjects.Ship;


public class Missile extends GameObject implements Updatable {
	private Vector2 dirAndVel;
	private final float VELOCITY = 100;
	
	public boolean remove;
	int screenWidth;
	int screenHeight;
	
	public Missile(Texture pmap, Vector2 direction, Vector2 position){
		sprite = new Sprite(pmap);
		sprite.setOrigin(pmap.getWidth()/2, pmap.getHeight()/2);
		sprite.setPosition(position.x, position.y);
		dirAndVel = new Vector2(direction.x, -direction.y);
		dirAndVel.scl(VELOCITY);
		sprite.rotate(dirAndVel.angle()-90);
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		setIsDrawable(true);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		sprite.translate(dirAndVel.x*Gdx.graphics.getDeltaTime(), dirAndVel.y*Gdx.graphics.getDeltaTime());
		if(sprite.getX() > (screenWidth - Constants.SHIP_WIDTH) ||
				sprite.getX() < -Constants.SHIP_WIDTH ||
				sprite.getY() < -Constants.SHIP_HEIGHT ||
				sprite.getY() > (screenHeight + Constants.SHIP_HEIGHT)){
			remove = true;
		}
			
	}
	
	public boolean getRemove(){
		return remove;
	}
}
