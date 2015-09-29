package magicpot.hr.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.LinkedList;

public class StateManager {

    private LinkedList<State> states;
    private State previousState;

    public StateManager(){
        states = new LinkedList<State>();
    }

    public void render(SpriteBatch sb){ states.peek().render(sb);}
    public void update(){states.peek().update();}

    public void pushState(State s){states.push(s);}
    public void popState(){ previousState = states.pop();}

    public void renderPrevious(SpriteBatch sb){
        State s = states.pop();
        states.peek().render(sb);
        states.push(s);
    }

    public State getPreviousState(){return previousState;}
}
