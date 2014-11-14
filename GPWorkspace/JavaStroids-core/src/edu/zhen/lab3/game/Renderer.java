package edu.zhen.lab3.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.zhen.lab3.gameobjects.GameObject;

public class Renderer {
	
	private SpriteBatch spriteBatch;
	private Controller control;
	BitmapFont font;
	Texture bg1, bg2;
	float bg1XPos, bg2XPos;
	
	Animation explosionAnim;
	Texture explosionSheet;
	TextureRegion [] explosionFrames;
	TextureRegion currentFrameExplosion;
	float shipExplosionStateTime;
	
	public Renderer(Controller c){
		control = c;
		spriteBatch = new SpriteBatch(); 
		font = new BitmapFont();
		bg1 = new Texture("PIA18847.jpg");
		bg2 = bg1;
		bg1XPos = 0;
		bg2XPos = bg1.getWidth();
		explosionSheet = new Texture(Gdx.files.internal("explosion17.PNG"));
		TextureRegion [] [] tmp = TextureRegion.split(explosionSheet, explosionSheet.getWidth()/5, explosionSheet.getHeight()/5);
		explosionFrames = new TextureRegion[25];
		int index = 0;
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				explosionFrames[index++] = tmp[i][j];
			}
		}
		explosionAnim = new Animation(0.040f, explosionFrames);
		shipExplosionStateTime = 0f;
	}
	
	public void render(){
		spriteBatch.begin();
		renderBackground();
		for(GameObject gObj : control.getDrawableObjects()){
			gObj.sprite.draw(spriteBatch);
		}
		
		if(control.isShipCrashed() && !explosionAnim.isAnimationFinished(shipExplosionStateTime)){
			shipExplosionStateTime += Gdx.graphics.getDeltaTime();
			currentFrameExplosion = explosionAnim.getKeyFrame(shipExplosionStateTime, false);
			spriteBatch.draw(currentFrameExplosion, control.getExplosionX()-Constants.SHIP_WIDTH, control.getExplosionY()-Constants.SHIP_HEIGHT);
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