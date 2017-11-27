package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyDemo;

/**
 * Created by User on 10.03.2017.
 */

public class GameOver extends State {
    private Texture background;
    private Texture gameover;


    public GameOver(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false,FlappyDemo.WIDTH/2,FlappyDemo.HELGHT/2);
        background=new Texture("bg.png");
        gameover=new Texture("gameover.png");
    }

    @Override
    protected void handleImput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));

        }
    }

    @Override
    public void update(float dt) {
        handleImput();


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background,0,0);
        sb.draw(gameover, camera.position.x-gameover.getWidth()/2,camera.position.y);
        sb.end();


    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();
        System.out.println("Gameover Disposed");
    }
}
