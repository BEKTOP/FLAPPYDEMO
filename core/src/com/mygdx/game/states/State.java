package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by User on 10.03.2017.
 */

public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;
    protected BitmapFont font;

    public  State (GameStateManager gsm){
        this.gsm=gsm;
        camera=new OrthographicCamera();
        mouse=new Vector3();
        font = new BitmapFont();

    }
protected abstract void handleImput();
public abstract void update(float dt);
public abstract void render (SpriteBatch sb);
public abstract void dispose();



}
