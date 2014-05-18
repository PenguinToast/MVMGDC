package com.mgdc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mgdc.game.Global;
import com.mgdc.game.objects.AnimationObject;
import com.mgdc.game.objects.MapGrid;
import com.mgdc.game.objects.animation.ObjectAnimation;

public class CombatScreen extends BaseScreen
{
    private OrthographicCamera combatCamera;
    private OrthographicCamera backgroundCamera;
    private Stage combatStage;
    private Stage background;
    private float parallax = 10;
    private Vector2 touchLoc;
    private Vector2 touchOffset;
    
    public CombatScreen()
    {
        combatStage = new Stage();
        background = new Stage();
        combatCamera = new OrthographicCamera(500, 300);
        backgroundCamera = new OrthographicCamera(500, 300);
        combatStage.setCamera(combatCamera);
        background.setCamera(backgroundCamera);

        InputMultiplexer multi = new InputMultiplexer(stage, combatStage, new GameInputProcessor());
        Gdx.input.setInputProcessor(multi);
        
        touchOffset = new Vector2();
        combatStage.addActor(new MapGrid());
    }
    
    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        float mult = 1;
        float dx = delta * touchOffset.x * mult;
        float dy = delta * touchOffset.y * -mult;
        combatCamera.translate(dx, dy);
        backgroundCamera.translate(dx/parallax, dy/parallax);
        combatCamera.update();
        backgroundCamera.update();
        background.draw();
        combatStage.act(delta);
        combatStage.draw();

        stage.act(delta);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height)
    {
        super.resize(width, height);
    }
    
    public class GameInputProcessor implements InputProcessor {
        @Override
        public boolean keyDown(int keycode) {
            if (keycode == Keys.SPACE) {
                Gdx.input.getTextInput(new TextInputListener() {

                    @Override
                    public void input(String text) {
                        AnimationObject actor = new AnimationObject(new ObjectAnimation(Global.assets.get(Global.assetMap.get(text),
                                ObjectAnimation.class)));
                        actor.setPosition(20, 20);
                        combatStage.addActor(actor);
                    }

                    @Override
                    public void canceled() {

                    }
                }, "Enter an animation to spawn: ", "");
            }
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            touchLoc = new Vector2(screenX, screenY);
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            touchOffset = new Vector2();
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            touchOffset = new Vector2(screenX, screenY).sub(touchLoc);
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            // TODO Auto-generated method stub
            return false;
        }
    }
}
