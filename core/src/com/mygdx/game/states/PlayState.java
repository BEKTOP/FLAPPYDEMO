package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyDemo;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Tube;

/**
 * Created by User on 11.03.2017.
 */

public class PlayState extends State {

    private static final int TUBE_SPACING =125;
    private static final int TUBE_COUNT =4;
    private static final int GROUND_Y_OFESET=-40;

    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private BitmapFont font;

    private Array<Tube> tubes;

    public int dropsGatchered;

    public PlayState(GameStateManager gsm) {
        super(gsm);
                bird=new Bird(50,300);
        camera.setToOrtho(false, FlappyDemo.WIDTH/2, FlappyDemo.HELGHT/2);
        bg=new Texture("bg.png");
        ground=new Texture("ground.png");
        groundPos1=new Vector2(camera.position.x -camera.viewportWidth/2,GROUND_Y_OFESET);
        groundPos2=new Vector2((camera.position.x -camera.viewportWidth/2)+ground.getWidth(),GROUND_Y_OFESET);

        tubes=new Array<Tube>();

        for (int i=1;i<TUBE_COUNT; i++){
            tubes.add(new Tube(i*(TUBE_SPACING+Tube.TUBE_WIDTH)));
        }
    }



    @Override
    protected void handleImput() {
        if (Gdx.input.justTouched())
         bird.jump();
         camera.position.x=bird.getPosition().x+80;
    }


    @Override
    public void update(float dt) {
       handleImput();
        updateGround();
        bird.update(dt);

        for (int i=0; i<tubes.size; i++){
            Tube tube=tubes.get(i);
            if (camera.position.x-(camera.viewportWidth/2)>tube.getPosTopTube().x+tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x+((tube.TUBE_WIDTH+TUBE_SPACING*TUBE_COUNT)));
                System.out.println("bird ");
            }

             System.out.println("camera "+(camera.position.x)/180);
            System.out.println("tube "+tube.getPosTopTube().x);
            System.out.println("bird "+bird.getPosition().x);
           if (tube.collides(bird.getBounds()))
               gsm.set(new GameOver(gsm));
        }
        camera.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
       // sb.font.draw(sb, "Drops Collected: " + dropsGatchered,0,480);
        sb.draw(bg,camera.position.x-(camera.viewportWidth/2),0);
        sb.draw(bird.getBird(), bird.getPosition().x,bird.getPosition().y);
        for (Tube tube:tubes) {
            sb.draw(tube.getTopTube(),tube.getPosBotTube().x,tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(),tube.getPosBotTube().x,tube.getPosBotTube().y);
         //   System.out.println("tube "+tube.getPosTopTube().x);
        }
        sb.draw(ground,groundPos1.x,groundPos1.y);
        sb.draw(ground,groundPos2.x,groundPos2.y);


        sb.end();

            }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube  tube : tubes)
            tube.dispose();
        System.out.println("PlayState Disposed");
    }

    private void updateGround(){
       if (camera.position.x - (camera.viewportWidth/2)>groundPos1.x+ground.getWidth())
           groundPos1.add(ground.getWidth()*2,0);
        if (camera.position.x - (camera.viewportWidth/2)>groundPos2.x+ground.getWidth())
            groundPos2.add(ground.getWidth()*2,0);


    }

}
