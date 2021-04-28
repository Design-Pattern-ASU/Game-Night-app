/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;
import org.lwjgl.input.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.gui.*;
import org.lwjgl.*;
/**
 *
 * @author acer
 */
public class Snake extends BasicGameState{
    Music music;
    Image board,im,start;
    int[] winner,arrX,arrY;
    Piece[] p;
    String mouse="start";
    boolean[] next;
    int[] w,h;
    snakeboard b;
    char turn='Y';
    int steps=-1;
    dicestrategy dice;
    Animation dice1,Roll2;
    boolean quit = false;
    boolean turnn=false;
    int[] duration = {500,500};
    float diceposX = 0;
    float diceposY = 0;
    float shiftx = diceposX +500;
    float shifty = diceposY +920;
     public Snake(int state)throws SlickException 
     {
        arrX=new int[4];
        arrY=new int[4];
     }
    @Override
    public int getID() {
        return 4;
       }
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //music=new Music("res/chill.wav");
        //music.loop();
        dice=new dicestrategy();
        dice.setstrategy(new onedice());
        b=snakeboard.getinstance();
        b.cells();
        b.mousepositions();
        p=new Piece[4];
        p[0]=new Piece('Y', 0);p[0].pos=0;
        p[1]=new Piece('R',1);p[1].pos=0;
        p[2]=new Piece('G', 2);p[2].pos=0;
        p[3]=new Piece('B',3);p[3].pos=0;
        next=new boolean[4];
        w=new int[4];
        h=new int[4];
        winner=new int[4];
        for(int i=0;i<4;i++)
        {
            winner[i]=0;
        }
        for(int i=0;i<4;i++)
        {
            next[i]=true;
        }
        for(int i=0;i<4;i++)
        {
            w[i]=p[i].width;
            h[i]=p[i].height;
        }
        arrX[0]=b.path_x[0]+15;arrX[1]=b.path_x[0]+5;arrX[2]=b.path_x[0]-5;arrX[3]=b.path_x[0]-15;
        arrY[0]=b.path_y[0];arrY[1]=b.path_y[0];arrY[2]=b.path_y[0];arrY[3]=b.path_y[0];
         Image[] roll2=   {new Image("res/1.jpeg"),new Image("res/2.jpeg"),new Image("res/3.jpeg"),new Image("res/4.jpeg"),new Image("res/5.jpeg"),new Image ("res/6.jpeg")};
            Roll2 = new Animation(roll2,400);
            dice1=Roll2;
            dice1.stop();
       }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        im=new Image("res/bg - Copy.png");
       board=new Image("res/snake.jpg");
       start=new Image("res/snake_start.png");
       grphcs.drawImage(im, 0, 0);
       grphcs.drawImage(start,b.path_x[0]-22,b.path_y[0]-6);
        grphcs.drawImage(board, 175, 125);
        grphcs.drawString(mouse, 150, 10);
        p[0].piece_image.draw(arrX[0],arrY[0],w[0],h[0]);
        p[1].piece_image.draw(arrX[1],arrY[1],w[1],h[1]);
        p[2].piece_image.draw(arrX[2],arrY[2],w[2],h[2]);
        p[3].piece_image.draw(arrX[3],arrY[3],w[3],h[3]);
        
         dice1.draw(shiftx,shifty);
            if(quit==true)
            {
                grphcs.drawString("Resume game(R)", 250, 100);
                grphcs.drawString("Mainmenu (M)", 250, 150);
                grphcs.drawString("Quit game(Q))", 250, 200);
                if(quit==false)
                {
                    grphcs.clear();
                }
            }
      }
 
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {
      int xpos=Mouse.getX();
      int ypos=Mouse.getY();
      mouse=" xpos= "+xpos+" ypos= "+ypos;
      int current=-1;
      int target=-1;
     if(turnn==true)
      {
        turn=turncolor(turn);
        turnn=false;
      }
      if(xpos>=500&&xpos<=550&&ypos>=30&&ypos<=50&&Mouse.isButtonDown(0))
      {
          Mouse.setCursorPosition(1050, 800);
           
           steps=dice.throwdice();
              if(steps==1)
              {
                  dice1.start();
                  dice1.stop();
                  dice1.setCurrentFrame(0);
              }
              if(steps==2)
              {
                  dice1.start();
                  dice1.stop();
                  dice1.setCurrentFrame(1);
              }
              if(steps==3)
              {
                  dice1.start();
                  dice1.stop();
                  dice1.setCurrentFrame(2);
              }
              if(steps==4)
              {
                  dice1.start();
                  dice1.stop();
                  dice1.setCurrentFrame(3);
              }
              if(steps==5)
              {
                  dice1.start();
                  dice1.stop();
                  dice1.setCurrentFrame(4);
              }
              if(steps==6)
              {
                  dice1.start();
                  dice1.stop();
                  dice1.setCurrentFrame(5);
              }
      }
      if(steps!=-1)
      {
          int output=steps;
          steps=-1;
          if(turn=='Y')
          {
              current=p[0].pos;
              target=p[0].pos+output;
              if(target>100)
              {
                  turnn=true;
              }
              else
              {
               for(int j=current;j<target+1;j++)
              {
                  arrX[0]=b.path_x[j]+15;
                  arrY[0]=b.path_y[j];
              }
              for(int j=0;j<8;j++)
              {
                  if(target==b.down[j]+1)
                  {
                      target=b.up[j]+1;
                  }
                  else if(target==b.head[j]+1)
                  {
                      target=b.tail[j]+1;
                  }
              }
             
                  arrX[0]=b.path_x[target]+15;
                  arrY[0]=b.path_y[target];
              
              p[0].pos=target;
              if(p[0].pos==100)
              {
                  winner[0]=1;
                  next[0]=false;
              }
              turnn=true;
              }
          }
          else if(turn=='R')
          {
              current=p[1].pos;
              target=p[1].pos+output;
              if(target>100)
              {
                  turnn=true;
              }
              else
              {
               for(int j=current;j<target+1;j++)
              {
                  arrX[1]=b.path_x[j]+5;
                  arrY[1]=b.path_y[j];
              }
              for(int j=0;j<8;j++)
              {
                  if(target==b.down[j]+1)
                  {
                      target=b.up[j]+1;
                  }
                  else if(target==b.head[j]+1)
                  {
                      target=b.tail[j]+1;
                  }
              }
            
                  arrX[1]=b.path_x[target]+5;
                  arrY[1]=b.path_y[target];
              
              p[1].pos=target;
              if(p[1].pos==100)
              {
                  winner[1]=1;
                  next[1]=false;
              }
              turnn=true;
              }
          }
          else if(turn=='G')
          {
               current=p[2].pos;
              target=p[2].pos+output;
              if(target>100)
              {
                  turnn=true;
              }
              else
              {
               for(int j=current;j<target+1;j++)
              {
                  arrX[2]=b.path_x[j]-5;
                  arrY[2]=b.path_y[j];
              }
              for(int j=0;j<8;j++)
              {
                  if(target==b.down[j]+1)
                  {
                      target=b.up[j]+1;
                  }
                  else if(target==b.head[j]+1)
                  {
                      target=b.tail[j]+1;
                  }
              }
             
                  arrX[2]=b.path_x[target]-5;
                  arrY[2]=b.path_y[target];
              
              p[2].pos=target;
              if(p[2].pos==100)
              {
                  winner[2]=1;
                  next[2]=false;
              }
              turnn=true;
              }
          }
          else if(turn=='B')
          {
               current=p[3].pos;
              target=p[3].pos+output;
              if(target>100)
              {
                  turnn=true;
              }
              else
              {
               for(int j=current;j<target+1;j++)
              {
                  arrX[3]=b.path_x[j]-15;
                  arrY[3]=b.path_y[j];
              }
              for(int j=0;j<8;j++)
              {
                  if(target==b.down[j]+1)
                  {
                      target=b.up[j]+1;
                  }
                  else if(target==b.head[j]+1)
                  {
                      target=b.tail[j]+1;
                  }
              }
                  arrX[3]=b.path_x[target]-15;
                  arrY[3]=b.path_y[target];
              p[3].pos=target;
              if(p[3].pos==100)
              {
                  winner[3]=1;
                  next[3]=false;
              }
              turnn=true;
              }
          }
      }
        }
     public char turncolor(char color)
  {
      char nextcolor='A';
      int index=0;
       if(color=='Y')
      {
          for(int i=1;i<4;i++)
          {
              if(next[i]==true)
              { index=i;
                break;
              }
          }
           if(index==1)
           {
               nextcolor='R';
                //mouse="red";
           }
           else if(index==2)
           {
               nextcolor='G';
               // mouse="green";
           }
           else{
               nextcolor='B';
                //mouse="blue";
           }
         
      }
       else if(color=='B')
      {
          for(int i=0;i<3;i++)
          {
              if(next[i]==true)
              { index=i;
                break;
              }
          }
          if(index==1)
           {
               nextcolor='R';
               // mouse="red";
           }
           else if(index==2)
           {
               nextcolor='G';
               // mouse="green";
           }
           else{
               nextcolor='Y';
               // mouse="Yellow";
           }
      }
     else if(color=='R')
      {
          int j=2;
          while(j<=3)
          {
              if(j==1)
              {
                  continue;
              }
              if(next[j]==true)
              {
                  index=j;
                  break;
              }
              if(j==3)
              {
                  j=0;
              }
              else
              {
                  j++;
              }
          }
           if(index==3)
           {
               nextcolor='B';
            //    mouse="blue";
           }
           else if(index==2)
           {
               nextcolor='G';
                //mouse="green";
           }
           else{
               nextcolor='Y';
              //  mouse="Yellow";
           }
      }
     else if(color=='G')
      {
         int j=3;
          while(j<=3)
          {
              if(j==2)
              {
                  continue;
              }
              if(next[j]==true)
              {
                  index=j;
                  break;
              }
              if(j==3)
              {
                  j=0;
              }
              else
              {
                  j++;
              }
          }
          if(index==3)
           {
               nextcolor='B';
              //  mouse="blue";
           }
           else if(index==1)
           {
               nextcolor='R';
              //  mouse="red";
           }
           else
           {
               nextcolor='Y';
               // mouse="Yellow";
           }
      }
      return nextcolor;
  }
}
