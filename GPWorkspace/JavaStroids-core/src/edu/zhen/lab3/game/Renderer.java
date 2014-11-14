package edu.zhen.lab3.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.zhen.lab3.gameobjects.GameObject;

public class Renderer {
	
	private SpriteBatch spriteBatch;
	private Controller control;
	BitmapFont font;
	Texture bg1, bg2;
	float bg1XPos, bg2XPos;
	
	public Renderer(Controller c){
		control = c;
		spriteBatch = new SpriteBatch(); 
		font = new BitmapFont();
		bg1 = new Texture("PIA18847.jpg");
		bg2 = bg1;
		bg1XPos = 0;
		bg2XPos = bg1.getWidth();
	}
	
	public void render(){
		spriteBatch.begin();
		renderBackground();
		for(GameObject gObj : control.getDrawableObjects()){
			gObj.sprite.draw(spriteBatch);
		}
		spriteBatch.end();
	}
	
	public void renderBackground(){
		spriteBatch.draw(bg1, bg1XPos, 0);
		spriteBatch.draw(bg2, bg2XPos, 0);
		
		//Lab 5- part 2
		// Your code goes here
		if(bg1XPos == -bg1.getWidth()){
			bg1XPos = bg2.getWidth();
		}
		if(bg2XPos == -bg2.getWidth()){
			bg2XPos = bg1.getWidth();
		}
		
		bg1XPos -= 0.3;
		bg2XPos -= 0.3;
	}

}