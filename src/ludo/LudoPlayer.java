/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;

import org.newdawn.slick.SlickException;

/**
 * @author Mohamed Amr
 */
public class LudoPlayer 
{
 String name;
 String type;
 ludoboard board;
 Piece []pieces;
 Dice dice;
 int no_of_kills;
 int no_of_pieces_home;

    public LudoPlayer(char color) throws SlickException // THIS
    {
        pieces = new Piece[4];
        for(int i=0;i<4;i++)
        {
            pieces[i]=new Piece(color,i);
            //pieces[i] = new Piece();
            //pieces[i].color = color;
        }
        no_of_kills=0;
        type = "human";
       no_of_pieces_home=0;
    }
    public LudoPlayer(String name,char color) throws SlickException
    {
         this.name = name;
         for(int i=0;i<4;i++)
        {
            pieces[i] = new Piece(color,i);
        }
         no_of_kills=0;
         type = "human";
         no_of_pieces_home=0;
    }
    public LudoPlayer(String name,char color,String type) throws SlickException
    {
         this.name = name;
         for(int i=0;i<4;i++)
        {
            pieces[i] = new Piece(color,i);
        }
         no_of_kills=0;
         this.type = type;
         no_of_pieces_home=0;
    }
 
  LudoPlayer(String name,char color,ludoboard board,Dice dice) throws SlickException 
  {
      this.name = name;
      this.board = board;
      this.dice = dice;
      for(int i=0;i<4;i++)
        {
            pieces[i] = new Piece(color,i);
        }
      no_of_kills=0;
       no_of_pieces_home=0;
   
  }

}

class Human_Ludo extends LudoPlayer
{
   Human_Ludo(String name,char color,ludoboard board,Dice dice) throws SlickException
   {
       super(name,color,board,dice);
   }
   Human_Ludo(String name,char color) throws SlickException
   {
    super(name,color);   
   }
   Human_Ludo(char color) throws SlickException
   {
       super(color);
   }
}
