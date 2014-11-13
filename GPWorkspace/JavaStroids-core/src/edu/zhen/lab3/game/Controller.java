package edu.zhen.lab3.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.math.Vector2;

import edu.zhen.lab3.gameobjects.Asteroid;
import edu.zhen.lab3.gameobjects.GameObject;
import edu.zhen.lab3.gameobjects.Missile;
import edu.zhen.lab3.gameobjects.Ship;

public class Controller {
	
	ArrayList<GameObject> drawableObjects; 
	Ship ship;
	private float screenHeight;

	
	public Controller(){
		drawableObjects = new ArrayList<GameObject>(); 
		initShip();
		initAsteroids(10);
		screenHeight = Gdx.graphics.getHeight();
	}
	
	private void initShip(){
		int w = Constants.SHIP_WIDTH; 
		int h = Constants.SHIP_HEIGHT; 
		Pixmap pmap = new Pixmap(w, h, Format.RGBA8888); // TODO: Check Image Format
		pmap.setColor(1, 1, 1, 1);
		pmap.drawLine(0, h, w/2, 0);
		pmap.drawLine(w, h, w/2, 0);
		pmap.drawLine(1, h-1, w, h-1);
		ship = new Ship(new Texture(pmap), 100, 100);
		drawableObjects.add(ship);
	}
	
	private void initAsteroids(int num){
		Random rand = new Random();
		for(int i = 0; i<num; i++){
			Asteroid asteroid = new Asteroid(new Texture("Asteroid_tex.png"));
			asteroid.sprite.setPosition(rand.nextInt(Gdx.graphics.getWidth()), rand.nextInt(Gdx.graphics.getHeight()));
			asteroid.sprite.setOrigin(asteroid.sprite.getWidth() / 2, asteroid.sprite.getHeight() / 2);
			asteroid.setRotVel(rand.nextFloat()*8-4);
			drawableObjects.add(asteroid);
		}
	}
	
	public void update(){
		processKeyboardInput();
		processMouseInput();
		//System.out.println(drawableObjects.size());
		
		for(int i=0; i<drawableObjects.size();i++){
			GameObject gObj = drawableObjects.get(i);
			if(gObj instanceof Missile){
				if(((Missile) gObj).getRemove() == true){
					drawableObjects.remove(i);
				}
			}
		}
		
		for(int i=0; i<drawableObjects.size(); i++){
			GameObject gObj = drawableObjects.get(i);
			//update Asteroid
			if(gObj instanceof Asteroid){
				((Asteroid) gObj).update(Gdx.graphics.getDeltaTime());
			}
			if(gObj instanceof Missile){
				((Missile) gObj).update(Gdx.graphics.getDeltaTime());
			}
		}
		/*
		// Update Asteroids
		for(GameObject gObg : drawableObjects){
			if(gObg instanceof Asteroid){
				((Asteroid) gObg).update(Gdx.graphics.getDeltaTime()); 
			}
		}
		*/
		// Update ship
		ship.update(Gdx.graphics.getDeltaTime());
		
	}
	
	private void processKeyboardInput(){
		if (Gdx.app.getType() != ApplicationType.Desktop) return; // Just in case :)
		if (Gdx.input.isKeyPressed(Keys.UP)) 
			ship.moveForward(Gdx.graphics.getDeltaTime());
		// Student, your code goes here
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			initMissile();
		}
	}
	
	public ArrayList<GameObject> getDrawableObjects(){
		return drawableObjects;
	}
	
	private void processMouseInput(){
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			ship.face(new Vector2(Gdx.input.getX()-ship.sprite.getX(),
					- (screenHeight - Gdx.input.getY()-ship.sprite.getY())));
		}
	}
	
	private void initMissile(){
		int w = Constants.SHIP_WIDTH/3;
		int h = Constants.SHIP_HEIGHT/3;
		Pixmap pmap = new Pixmap(w, h, Format.RGB565);
		pmap.setColor(1,1,1,1);
		pmap.drawLine(w/2, 0, w/2, h);
		drawableObjects.add(new Missile(new Texture(pmap), ship.getDirection(), ship.getPosition()));
	}
	
	
}
