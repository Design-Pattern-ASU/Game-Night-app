/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;
import java.awt.Button;
import javafx.util.Pair; 
import java.util.ArrayList; 
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Mohamed Amr
 */
public class Piece 
{
    ludoboard board;
    int x_pos;
    int ypos;
    int pos;
    char color;
    int size_factor;
    private boolean inGame;
    private boolean reached_home;
    Image piece_image;
    int id; // 0....15
  public  int full_path_id;
    public int color_path_id;
    static int k=0;
    int width=30;
    int height=50;
    Piece()
    {
        color='a';
    }
    Piece(char color,int id) throws SlickException
    {
          this.color = color;
          inGame=false;
          reached_home=false;
          this.id = k;
          k++;
          if(color=='R')
          {
              //System.out.println("I am in");
              piece_image = new Image("res/Red_piece_5050.png");
               //System.out.println("I am in");
                       
             
          }
          else if(color=='Y')
          {
               piece_image = new Image("res/Yellow_piece_5050.png");
          }
          else if(color=='B')
          {
               piece_image = new Image("res/Blue_piece_5050.png");
          }
          else if(color=='G')
          {
               piece_image = new Image("res/Green_piece_5050.png");
          }
          
    }
    public boolean get_inGame()
    {
        return inGame;
    }
    public void set_inGame()
    {
        inGame = true;
    }
    public boolean get_reached_home()
    {
        return reached_home;
    }
    public void set_reached_home()
    {
        reached_home=true;
    }
}
