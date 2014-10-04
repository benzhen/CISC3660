package edu.zhen.lab3.intro;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IntroToLibGDX extends ApplicationAdapter{

	private SpriteBatch spriteBatch;
	private Sprite bug;
	private Sprite chest;
	private float rotDeg;
	private float x=30, y =0;
	private final float speed = 10.0f;
	private float deltaTime;
	private boolean reachTop = false;
	
	@Override
	public void create() {
		// Game Initialization  
		spriteBatch = new SpriteBatch(); 
		bug = new Sprite(new Texture("EnemyBug.png"));
		bug.setSize(50, 85);
		bug.setOrigin(bug.getWidth() / 2, bug.getHeight() / 2);
		bug.setPosition(30, 0);
		chest = new Sprite(new Texture("ChestClosed.png"));
		chest.setSize(50, 85);
		chest.setOrigin(chest.getWidth()/2, chest.getHeight()/2);
		chest.setPosition(270, 240);
		bug.rotate(20);
		rotDeg = 180;
	}

	@Override
	public void render() {
		// Game Loop
		Gdx.gl.glClearColor(0.7f, 0.7f, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		
		deltaTime = Gdx.graphics.getDeltaTime();
		
		if(!reachTop){
			x += (speed * deltaTime) + 0.12f;
			y += (speed * deltaTime);
		}
		
		if(reachTop){
			x -= (speed * deltaTime) + 0.12f;
			y -= (speed * deltaTime);
		}
		
		if(x >= 750 && y >= 420 || x <= 0 && y <= 0){
			if(reachTop){
				reachTop = false;
				bug.rotate(rotDeg);
			}
			else{
				reachTop = true;
				bug.rotate(rotDeg);
			}
		}
		
		/*
		if(x >= 750 && y >= 420){
			bug.rotate(rotDeg);
		}
		
		if(x <= 0 && y <= 0){
			bug.rotate(rotDeg);
		}
		
		
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		*/
		
		bug.setPosition(x, y);
		
		bug.draw(spriteBatch);
		chest.draw(spriteBatch);
		spriteBatch.end();
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		super.dispose();
	}
}
