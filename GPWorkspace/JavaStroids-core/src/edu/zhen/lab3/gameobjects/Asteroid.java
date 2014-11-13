package edu.zhen.lab3.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import edu.zhen.lab3.game.Constants;

public class Asteroid extends GameObject implements Updatable{
	private Vector2 dirAndVel;
	private float rotationalVel;
	private float ranX = 0, ranY = 0;
	private final float VELOCITY = 100;
	private int state = 1;
	
	int screenWidth;
	int screenHeight;
	float deltaT;
	
	public Asteroid(Texture tex){
		sprite = new Sprite(tex);
		sprite.setSize(Constants.ASTEROIDS_SIZE, Constants.ASTEROIDS_SIZE); 
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		//ranX = rand.nextFloat() * screenWidth;
		//ranY = rand.nextFloat() * screenHeight;
		ranX = MathUtils.random(screenWidth);
		ranY = MathUtils.random(screenHeight);
		dirAndVel = new Vector2(ranX, ranY);
		dirAndVel.nor();
		dirAndVel.scl(VELOCITY);
		setIsDrawable(true);
	}
	
	@Override
	public void update(float deltaTime) {
		deltaT = Gdx.graphics.getDeltaTime();
		
		sprite.rotate(getRotVel()); // TODO: Student, use delta time here
		
		if(sprite.getX() >= screenWidth-10 && state == 1 
				|| sprite.getY() >= screenHeight && state == 1
				|| sprite.getX() <= 10 && state == 2
				|| sprite.getY() <= 10 && state == 2){
			dirAndVel.scl(-1);
			if(state == 1){
				state = 2;
			}
			else{
				state = 1;
			}
		}
		
		
		/*
		System.out.println("screenWidth= " + screenWidth + ", screenHeight= " + screenHeight);
		System.out.println("ranX= " + ranX + ", ranY= " + ranY);
		System.out.println("x= " + dirAndVel.x + ", y= " + dirAndVel.y);
		System.out.println("spriteX= " + sprite.getX() + ", spriteY= " + sprite.getY());
		*/
		// Student, create Asteroid behavior here.
		sprite.translate(dirAndVel.x*deltaT, dirAndVel.y*deltaT);
	}
		
	public void setRotVel(float vel){
		rotationalVel = vel;
	}
	public float getRotVel(){
		return rotationalVel;
	}

}
