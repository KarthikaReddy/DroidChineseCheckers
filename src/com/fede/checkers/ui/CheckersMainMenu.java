package com.fede.checkers.ui;

import android.content.Intent;

import com.fede.checkers.Constants;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import javax.microedition.khronos.opengles.GL10;


public class CheckersMainMenu extends BaseGameActivity implements IOnMenuItemClickListener {
	// ===========================================================
	// Constants
	// ===========================================================

	

	protected static final int MENU_OK = 0;
	protected static final int MENU_QUIT = MENU_OK + 1;

	// ===========================================================
	// Fields
	// ===========================================================

	protected Camera mCamera;

	protected Scene mMainScene;


	protected MenuScene mMenuScene;

	private Texture mMenuTexture;
	protected TextureRegion mMenuOkTextureRegion;
	protected TextureRegion mMenuQuitTextureRegion;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public Engine onLoadEngine() {
	    int width = Constants.CAMERA_WIDTH;
	    int height = Constants.getHeight(width, this); 
        this.mCamera = new Camera(0, 0, width, height);
	    
		return new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT, new RatioResolutionPolicy(width, height), this.mCamera));
	}

	@Override
	public void onLoadResources() {
		
		this.mMenuTexture = new Texture(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mMenuOkTextureRegion = TextureRegionFactory.createFromAsset(this.mMenuTexture, this, "gfx/menu_ok.png", 0, 0);
		this.mMenuQuitTextureRegion = TextureRegionFactory.createFromAsset(this.mMenuTexture, this, "gfx/menu_quit.png", 0, 50);
		this.mEngine.getTextureManager().loadTexture(this.mMenuTexture);
	}

	@Override
	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		this.createMenuScene();

		/* Just a simple scene with an animated face flying around. */
		this.mMainScene = new Scene(1);
		this.mMainScene.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));		

        this.mMainScene.setChildScene(this.mMenuScene, false, true, true);

		return this.mMainScene;
	}

	@Override
	public void onLoadComplete() {

	}



	@Override
	public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem, final float pMenuItemLocalX, final float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
			case MENU_OK:
			    Intent i = new Intent(this, BoardsListActivity.class);		        
		        startActivity(i);
				return true;
			case MENU_QUIT:
				/* End Activity. */
				this.finish();
				return true;
			default:
				return false;
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected void createMenuScene() {
		this.mMenuScene = new MenuScene(this.mCamera);

		final SpriteMenuItem okMenuItem = new SpriteMenuItem(MENU_OK, this.mMenuOkTextureRegion);
		okMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		this.mMenuScene.addMenuItem(okMenuItem);
		
		final SpriteMenuItem quitMenuItem = new SpriteMenuItem(MENU_QUIT, this.mMenuQuitTextureRegion);
		quitMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		this.mMenuScene.addMenuItem(quitMenuItem);
		
		this.mMenuScene.buildAnimations();

		this.mMenuScene.setBackgroundEnabled(false);

		this.mMenuScene.setOnMenuItemClickListener(this);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}