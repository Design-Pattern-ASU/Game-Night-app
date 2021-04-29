/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ludo;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.lang.NullPointerException;
/**
 *
 * @author New Laptop
 */
public class Menu extends BasicGameState{
    public String mouse="No input yet!";
    Image rec;
    public Image start,end,help;
    public Menu(int state)
        {
            
        }
        public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
        {
            rec = new Image("res/gnbg.png");
            start = new Image("res/start_button.png");
            help = new Image("res/instructions_button.png");
            end = new Image("res/exit_button.png");
//              start = new Image("D:\\My Applications\\Ludo\\build\\classes\\ludo\\st..jpg ");
//              help = new Image("D:\\My Applications\\Ludo\\build\\classes\\ludo\\help.png");
//              end = new Image("D:\\My Applications\\Ludo\\build\\classes\\ludo\\exit.png");
        }
        public void render (GameContainer gc, StateBasedGame sbg,Graphics g)throws SlickException
        {
            //g.scale(1.25f, 0.9f);
            //g.drawImage(rec, 0, 0);
            rec.draw(0,0);
            g.scale(1.6f, 1.6f);
            start.draw(485, 150);
            help.draw(485,300);
            end.draw(485, 450);
            g.drawString(mouse,50 , 50);
           
           
        }
        public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
        {
            int xpos=Mouse.getX();
            int ypos=Mouse.getY();
            mouse="Mouse Position x: "+xpos+" y: "+ypos;
            //Start Button
            if (( xpos>=782 && xpos <= 982) && (ypos>=680 && ypos <=753))
            {
                if(Mouse.isButtonDown(0))
                {
                    sbg.enterState(3);
                }
            }
            //Help Button
            if((xpos>=782 &&xpos<=982)&&(ypos>=443 &&ypos<=510))
            {
                if(Mouse.isButtonDown(0))
                {
                    sbg.enterState(2);
                
            }
                }
            //Exit Button
            if ((xpos>=782 && xpos <= 982)&& (ypos>=204 && ypos<=273))
            {
                if(Mouse.isButtonDown(0))
                {
                    System.exit(0);
                }
            }
        }
        public int getID()
        {
         return 0;
        }
    
}
