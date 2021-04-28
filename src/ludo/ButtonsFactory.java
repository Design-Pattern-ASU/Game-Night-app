/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author abdog
 */
public class ButtonsFactory
{
    public GameMenuButtons MakeButton(String btn_name) throws SlickException
    {
        GameMenuButtons btn = null;
        
        if(btn_name.toLowerCase() == "ludo")
            return new LudoButton();
        else
            return new SnakeLadderButton();
    }
    
    
 
    
}
