/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;
import org.lwjgl.input.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
/**
 *
 * @author Mohamed Amr
 */
public class Coordinates
{
    int x; 
    int y; 
  
    // Constructor 
    public Coordinates()
    {
        x=0;
        y=0;
    }
   public Coordinates(int x, int y) 
    { 
        this.x = x; 
        this.y = y; 
    } 
   public int get_x()
   {
       return x;
   }
   public int get_y()
   {
       return y;
   }
   public void set_x(int x)
   {
       this.x= x;
   }
   public void set_y(int y)
   {
       this.y=y;
   }
}

