package edu.zhen.lab3.intro;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class IntroToLibGDX extends ApplicationAdapter{

	private SpriteBatch spriteBatch;
	private Sprite bug;
	private Sprite chest;
	private float rotDeg;
	private Vector2 vec2;
	private float deltaTime;
	private boolean reachTop = false;
	private int state = 1, counter = 0;
	

	
	@Override
	public void create() {
		// Game Initialization  
		spriteBatch = new SpriteBatch(); 
		bug = new Sprite(new Texture("EnemyBug.png"));
		bug.setSize(50, 85);
		bug.setOrigin(bug.getWidth() / 2, bug.getHeight() / 2);
		bug.setPosition(0, 0);
		chest = new Sprite(new Texture("ChestClosed.png"));
		chest.setSize(50, 85);
		chest.setOrigin(chest.getWidth()/2, chest.getHeight()/2);
		chest.setPosition(270, 240);
		rotDeg = 3;
		
		vec2 = new Vector2(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		vec2.nor();
		vec2.scl(10);
	}

	@Override
	public void render() {
		// Game Loop
		deltaTime = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0.7f, 0.7f, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		
		bug.translate(vec2.x * deltaTime, vec2.y * deltaTime);
		
		if(bug.getX() >= Gdx.graphics.getWidth()-50 && bug.getY() >= Gdx.graphics.getHeight()-50 && state == 1
				|| bug.getX() <= 25 && bug.getY() <= 0 && state == 2){
			vec2.scl(-1);
			//planning to set to 0 to stop motion, 
			//then scale again for movement after rotation is done
			
			//vec2.setZero();
			if(state == 1){
				reachTop = true;
			}
			else if(state == 2){
				reachTop = false;
			}
			state = 3;
		}
		/*
		if(bug.getX() <= 25 && bug.getY() <= 0 && state == 2){
			vec2.scl(-1);
			state = 3;
			reachTop = false;
			
		}
		*/
		if(state == 3){
			bug.rotate(rotDeg);
			if(counter < 60){
				counter++;
			}
			else{
				if(reachTop){
					state = 2;
					counter = 0;
					//vec2.scl(-100);
				}
				else{
					state = 1;
					counter = 0;
					//vec2.scl(100);
				}
			}
		}
		
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
