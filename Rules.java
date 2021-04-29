/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludo;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
/**
 *
 * @author New Laptop
 */
public class Rules extends BasicGameState{
    public Image rules;
        public Rules(int state)
        {
            
        }
        public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
        {
            rules = new Image("res/rules1.jpg");
        }
        public void render (GameContainer gc, StateBasedGame sbg,Graphics g)throws SlickException
        {
            rules.draw(0,0);
        }
        public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
        {
            
        }
        public int getID()
        {
         return 2;
        }
    
}
