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
public class SnakeLadderButton extends GameMenuButtons
{
    
    public SnakeLadderButton() throws SlickException {
        super(new Image("res/snakeladder_logo.png"), 1200, 800);
    }
}
