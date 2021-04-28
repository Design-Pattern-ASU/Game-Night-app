package ludo;


import java.util.Vector;
import org.lwjgl.input.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class Play  extends BasicGameState
{
    int init_game = 0;
    Image two, three, four;
    
    public Music music;
    int[] w,h;
    ludoboard b ;
    LudoPlayer players[];
    Piece p;
    int number_of_players;
    dicestrategy dice;
    private String mouse=" Yellow";
    
    char[] colors = {'Y','R','G','B'};
    float arr_x[];
    float arr_y[];
    String winner[];
    int m,n;
   int ii,jj,kk;
   String s;
   Image im;
   char turn='Y';
   boolean turnn=false;
   boolean is_win=false;
   
   Animation dice1,Roll2;
    int[] duration = {500,500};
    float diceposX = 0;
    float diceposY = 0;
    float shiftx = diceposX +550;
    float shifty = diceposY +500;
    int output=5;
    boolean quit=false;
    int step=2;
    String momo="";
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    boolean supermanFly = false;
    Image img_superDice, img_hulkFist, img_capShield;
    float superX=450, superY=1500;
    boolean superDice;
    int flash_maxX = 235, flash_maxY = 86, flash_minX = 162, flash_minY = 30,
         hulk_maxX = 330, hulk_maxY = 89, hulk_minX = 280, hulk_minY = 24;
    float flashX=1050, flashY=5100;
    float btn_hulkX=5500 ,btn_hulkY=18595 ;
    float capX=6000 ,capY=14290 ;
    
    int superNumber = -1;
    
    public Play(int state,int n)throws SlickException 
    {
       number_of_players= n; 
        arr_x = new float[16];
      arr_y = new float[16];
      //players = new LudoPlayer('R');
     /* for(int i=0;i<n;i++)
      {
          players[i]=new LudoPlayer(colors[i]);
      }*/
      
      arr_x[0]=270;arr_x[1]=365;arr_x[2]=365;arr_x[3]=270; // yellow x
      arr_y[0]=735;arr_y[1]=735;arr_y[2]=642;arr_y[3]=642; // yelllow Y
      
      arr_x[4]=705;arr_x[5]=798;arr_x[6]=798;arr_x[7]=705; // Red X
      arr_y[4]=295;arr_y[5]=295;arr_y[6]=200; arr_y[7]=200; // Red Y    
      
      arr_x[8]=270;arr_x[9]=365;arr_x[10]=365;arr_x[11]=270; // Green X
      arr_y[8]=295;arr_y[9]=295;arr_y[10]=200;arr_y[11]=200; // Green Y
      
      arr_x[12]=705;arr_x[13]=798; arr_x[14]=798;arr_x[15]=705; //Blue x
      arr_y[12]=735;arr_y[13]=735;arr_y[14]=642;arr_y[15]=642; // blue Y


        /*
          y_1=new Coordinates(270,735);
        y_2=new Coordinates(365,735);
        y_3=new Coordinates(365,642);
        y_4=new Coordinates(270,642);
         b_1=new Coordinates(705,735);
        b_2=new Coordinates(798,735);
        b_3=new Coordinates(798,642);
        b_4=new Coordinates(705,642);
        
        g_1=new Coordinates(270,295);
        g_2=new Coordinates(365,295);
        g_3=new Coordinates(365,200);
        g_4=new Coordinates(270,200);
        
        r_1=new Coordinates(705,295);
        r_2=new Coordinates(798,295);
        r_3=new Coordinates(798,200);
        r_4=new Coordinates(705,200);
        */
                              
       
       
      w=new int[16];
      h=new int[16];           
       
       
      
       
     
      
    }
    @Override
    public int getID()
    {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
    {
        init_game = 0;
        two = new Image("res/num2.png");
        three = new Image("res/num3.png");
        four = new Image("res/num4.png");
        
        
      //  b.mousePositions();
        //b.cells();
       winner = new String[3];
       for(int i=0;i<3;i++)
       {
           winner[i]="";
       }
         dice=new dicestrategy();
           dice.setstrategy(new onedice());
           b = ludoboard.getinstance();
           b.cells();
           b.mousepositions();
           b.yellowpath();
           b.redpath();
           b.bluepath();
           b.greenpath();
          
         for(int i=0;i<92;i++)
         {
             b.cell_pieces[i].clear();
         }
       for(int i=0;i<16;i++)
       {
           b.cell_pieces[i].add(i);  // Each cell has a piece
       }
            
         for(int i=0;i<92;i++)
         {
             if(i==16||i==24||i==29||i==37||i==42||i==50||i==55||i==63)//safe zone cells
             {
                 b.safe_zone[i]=true;
                 
             }
             else
             b.safe_zone[i]=false;
         }
          
          
       Image[] roll2=   {new Image("res/1.jpeg"),new Image("res/2.jpeg"),new Image("res/3.jpeg"),new Image("res/4.jpeg"),new Image("res/5.jpeg"),new Image ("res/6.jpeg")};
            Roll2 = new Animation(roll2,400);
            dice1=Roll2;
          
          turnn=true;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException 
    {
        im = new Image("res/Background.jpeg");
        grphcs.drawImage(im, 0, 0);
        grphcs.scale(1, 1);
        grphcs.drawString(mouse, 150, 10);
       if(init_game == 0)
       {
           grphcs.scale(0.5f, 0.5f);
           grphcs.drawImage(two, 50, 850);
           grphcs.drawImage(three, 800, 850);
           grphcs.drawImage(four, 1550, 850);
       }
       else
       {
           // im = new Image("D:\\My Applications\\Ludo\\build\\classes\\ludo\\Green_piece_5050.png");
           grphcs.scale(1, 1);
        grphcs.drawString(winner[0], 150, 25);
        grphcs.drawString(winner[1], 150, 50);
        grphcs.drawString(winner[2], 150, 75);
        grphcs.drawString(momo, 0, 0);
       //"D:\\Net Beans\\Ludo\\build\\classes\\ludo\\Red_piece_5050.png"
        //"D:\\Net Beans\\Ludo\\build\\classes\\ludo\\Yellow_piece_5050.png"
        //"D:\\Net Beans\\Ludo\\build\\classes\\ludo\\Blue_piece_5050.png"
        //"D:\\Net Beans\\Ludo\\build\\classes\\ludo\\Green_piece_5050.png"
       
       // Image board=new Image("D:\\Net Beans\\Ludo\\build\\classes\\ludo\\ludo.PNG");
       // grphcs.drawImage(board, 175, 125);
       b.board_image = new Image("res/ludo.png");
        grphcs.drawImage(b.board_image, 175, 125);
       // players[ii].pieces[jj].piece_image.draw(arr_x[kk],arr_y[kk]);
        if(init_game > 1)
        {
            int k=0;
          for(int i=0;i<number_of_players;i++)
        {
            for(int j=0;j<4;j++)
            {
             //players[i].board=new board();
              //  players[i].board.cells();
               players[i].pieces[j].piece_image.draw(arr_x[k],arr_y[k],w[k],h[k]);
               //playes[i].pieces[j].cells = -k;
               k++;
               
            }
        }
        }
        dice1.draw(shiftx,shifty);
        //grphcs.drawString("Dice X:"+diceposX+"Dice Y: "+diceposY, 700,20);
            dice1.stop();
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
            
            
        //  Superman with Numbers
        img_superDice = new Image("res/SuperDice.png");
        grphcs.scale(0.7f, 0.7f);
        grphcs.drawImage(img_superDice, superX, superY);
        
        
        //  Superman Button
        img_superDice = new Image("res/superman_logo.png");
        grphcs.scale(0.25f, 0.25f);
        grphcs.drawImage(img_superDice, flashX, flashY);

        if(supermanFly && superY>940)
        {
            flashY+=6;
            btn_hulkY+=20;
            capY+=20;
            superY-=5;
        }
        else if(!supermanFly && superY < 1500)
        {
            flashY-=6;
            btn_hulkY-=20;
            capY-=20;
            superY+=5;
        }
       }
    }
  

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {
        Input input = gc.getInput();
        
        if(init_game == 0)
        {
            if(input.isKeyPressed(Keyboard.KEY_2) || (Mouse.getX() >= 43 && Mouse.getX() <= 304 && Mouse.getY() >= 300 && Mouse.getY() <= 556 && input.isMousePressed(0)))
            {
                number_of_players = 2;
                init_game++;
            }
            else if(input.isKeyPressed(Keyboard.KEY_3) || (Mouse.getX() >= 419 && Mouse.getX() <= 680 && Mouse.getY() >= 300 && Mouse.getY() <= 556 && input.isMousePressed(0)))
            {
                number_of_players = 3;
                init_game++;
            }
            else if(input.isKeyPressed(Keyboard.KEY_4) || (Mouse.getX() >= 796 && Mouse.getX() <= 1054 && Mouse.getY() >= 300 && Mouse.getY() <= 556 && input.isMousePressed(0)))
            {
                number_of_players = 4;
                init_game++;
            }
            
        }
        if(init_game == 1)
        {
            players = new LudoPlayer[number_of_players];
         for(int z=0;z<number_of_players;z++)
        {
            players[z] = new LudoPlayer(colors[z]);
        }
         int x=0;
         for(int z=0;z<number_of_players;z++)
         {
             for(int j=0;j<4;j++)
             {
                players[z].pieces[j].id=x;
                x++;
             }
         }
         int k=0;
          for(int z=0;z<number_of_players;z++)
        {
            for(int j=0;j<4;j++)
            {
             w[k]=players[z].pieces[j].width;
             h[k]=players[z].pieces[j].height;
               k++;
               
            }
        }
          init_game++;
        }
        
        
        System.out.println(superNumber);
        int xpos=Mouse.getX();
        int ypos=Mouse.getY();
        momo = " xpos= "+xpos+" ypos= "+ypos;
      //  mouse=" xpos= "+xpos+" ypos= "+ypos;
      Input inp = gc.getInput();
      
      int src_cell_indexi=-1;
      int targ_cell_indexi=-1;
      int piece_id=-1;
      int factor=35;
    // output=5;
     
     if(Mouse.isButtonDown(0))
         if(Mouse.getX() >= 185 && Mouse.getX() <= 275 && Mouse.getY() <= 100 && Mouse.getY() >= 33)
            supermanFly = true;
     
     if(supermanFly)
     {
         if(input.isKeyPressed(Keyboard.KEY_1))
         {
             superNumber = 1;
             supermanFly = false;
         }
         if(input.isKeyPressed(Keyboard.KEY_2))
         {
             superNumber = 2;
             supermanFly = false;
         }
         if(input.isKeyPressed(Keyboard.KEY_3))
         {
             superNumber = 3;
             supermanFly = false;
         }
         if(input.isKeyPressed(Keyboard.KEY_4))
         {
             superNumber = 4;
             supermanFly = false;
         }
         if(input.isKeyPressed(Keyboard.KEY_5))
         {
             superNumber = 5;
             supermanFly = false;
         }
         if(input.isKeyPressed(Keyboard.KEY_6))
         {
             superNumber = 6;
             supermanFly = false;
         }
     }
     
      for(int j=0;j<b.full_mouse_path_x.size();j++)
      {
          
          
          
         // System.out.println(turn);
          float cmpx = b.full_mouse_path_x.get(j);
          float cmpy = b.full_mouse_path_y.get(j);
          float xmin = cmpx-25,xmax=cmpx+25,ymin=cmpy-25,ymax=cmpy+25;
          float cmpx1=980,cmpy1=508;
          float xmin1 = 980-40,xmax1=980+40,ymin1=508-40,ymax1=540+40;
          if(xpos>=xmin&&xpos<=xmax &&ypos>=ymin &&ypos<=ymax&&Mouse.isButtonDown(0)) 
          {
              Mouse.setCursorPosition(1050, 800);
               src_cell_indexi=j;// get cell index in the full path
          }
          
          if(superNumber != -1)
          {
              //System.out.println("I'm here ghonaim :)");
              output = superNumber;
              dice1.setCurrentFrame(output-1);
              step=output;
             // System.out.println(output);
              turn=turncolor(turn);
              turnn=false;
          }
          else
          {
              if(xpos>=xmin1&&xpos<=xmax1 &&ypos>=ymin1 &&ypos<=ymax1&&Mouse.isButtonDown(0)) 
          {
               Mouse.setCursorPosition(1050, 800);
            if(turnn==true)
          {
              
              dice1=Roll2;
              dice1.start();
              output=dice.throwdice();
              if(output==1)
              {
                  dice1.stop();
                  dice1.setCurrentFrame(0);
              }
             else if(output==2)
              {
                  dice1.stop();
                  dice1.setCurrentFrame(1);
              }
             else if(output==3)
              {
                  dice1.stop();
                  dice1.setCurrentFrame(2);
              }
             else if(output==4)
              {
                  dice1.stop();
                  dice1.setCurrentFrame(3);
              }
            else  if(output==5)
              {
                  dice1.stop();
                  dice1.setCurrentFrame(4);
              }
            else if(output==6)
              {
                  dice1.stop();
                  dice1.setCurrentFrame(5);
              }
              step=output;
             // System.out.println(output);
              turn=turncolor(turn);
              turnn=false;
          }
          if(turnn==true)
          {
              turn=turncolor(turn);
              turnn=false;
          }
          
          }
          }
      }
   
              if(turn=='Y' || turn=='y')
              {
                    // System.out.println("I am at yellow ....step = " + step);
                  if(src_cell_indexi<16 && src_cell_indexi!=-1&&step==6)  // still out of the game
                  {
                      // System.out.println("HELLO I am at if");
                      //get piece id
                      int k=-1;
                      for(k=0;k<b.cell_pieces[src_cell_indexi].size();k++)
                      {
                           piece_id = (int)b.cell_pieces[src_cell_indexi].get(k); //get piece id
                      
                          if(piece_id>=0 && piece_id<=3)    //found true
                          {
                             break; 
                          }
                      }
                        if(piece_id>=0 && piece_id<=3)    //found true
                          {
                            
                            int targ_x = b.YELLOWX[0];   // get positions of the target cell;
                            int targ_y = b.YELLOWY[0];
                            
                            int minx = targ_x-factor,maxx=targ_x+factor;
                            int miny = targ_y-factor,maxy=targ_y+factor;
                            
                            float posx=-1;
                            float posy=-1;
                            int z=-1;
                            for( z=0;z<92;z++)
                            {
                                 posx = (float)b.full_path_x.get(z);
                                 posy=(float) b.full_path_y.get(z);
                                 
                                // System.out.println("pos x  " + posx+ " " + "posY " +posy  );
                                
                                 if(posx>=minx &&posx<=maxx &&posy>=miny &&posy<=maxy )
                                 {
                                     targ_cell_indexi = z; 
                                     
                                     break;
                                 }
                            }
                           // System.out.println(z);
                            if(true) //check the cell number
                            {
                               
                                b.cell_pieces[src_cell_indexi].remove(k);  //remove from piece from the sourc cell
                                b.cell_pieces[targ_cell_indexi].add(piece_id);//add piece to the target cell
                                arr_x[piece_id]=b.YELLOWX[0];
                                arr_y[piece_id]=b.YELLOWY[0];
                                // set target cell 
                                for(int ii=0;ii<4;ii++)
                                {
                                    if(players[0].pieces[ii].id ==piece_id)
                                    {
                                        players[0].pieces[ii].color_path_id = 0;
                                    }
                                }
                                turnn=true;
                                superNumber = -1;
                            }
                            else // not applicable -> safe zone
                            {
                                
                            }
                            
                          }
                        
                  }
                  else if(src_cell_indexi>=16 && src_cell_indexi!=-1)
                  {
                      System.out.println("HELLO I am at else");
                       //get piece id
                      if(src_cell_indexi!=-1)
                      {
                      int k=-1;
                      for(k=0;k<b.cell_pieces[src_cell_indexi].size();k++)
                      {
                           piece_id = (int)b.cell_pieces[src_cell_indexi].get(k); //get piece id
                           System.out.println(piece_id);
                      
                          if(piece_id>=0 && piece_id<=3)    //found true
                          {
                              //System.out.println("piece id " + piece_id);
                             break; 
                          }
                      }
                      if(piece_id>=0 &&piece_id<=3)
                      {
                        int curr_index;
                        int targ_index=-1;
                        
                          for(int ii=0;ii<4;ii++)  // get piece current location on the colored path
                          {
                               //System.out.println("Players[0].pieces id : " +players[0].pieces[ii].id );
                              if(players[0].pieces[ii].id==piece_id)
                              {
                               
                                  curr_index = players[0].pieces[ii].color_path_id;
                                  targ_index=curr_index +step;
                                //  System.out.println("curr_index on the full path" + curr_index );
                                  
                                  break;
                              }
                          }
                          if(targ_index!=-1&&targ_index<=56)
                          {
                              if(targ_index==56)
                              {
                                  players[0].no_of_pieces_home++;
                              }
                              if(players[0].no_of_pieces_home==4)
                              {
                                 for(int f=0;f<3;f++)
                                 {
                                     if(winner[f]=="")
                                     {
                                         winner[f]="Winner " + (f+1) + ": Yellow";
                                         break;
                                     }
                                 }
                              }
                          int targ_x = b.YELLOWX[targ_index];
                          int targ_y = b.YELLOWY[targ_index];
                          int minx = targ_x-factor,maxx=targ_x+factor;
                          int miny = targ_y-factor,maxy=targ_y+factor;
                          
                            float posx=-1; // pos x in the full path
                            float posy=-1; //pos y in the full path
                            int z=-1;
                            for( z=0;z<92;z++)
                            {
                                 posx = (float)b.full_path_x.get(z);
                                 posy=(float) b.full_path_y.get(z);
                                
                                 if(posx>=minx &&posx<=maxx &&posy>=miny &&posy<=maxy )
                                 {
                                     targ_cell_indexi = z;
                                     
                                     break;
                                 }
                            }
                            if(b.cell_pieces[targ_cell_indexi].size()==1&&!b.safe_zone[targ_cell_indexi]) // attack condition
                            {
                                 turnn=true;
                                 superNumber = -1;
                                int enemy_id =(int) b.cell_pieces[targ_cell_indexi].get(0);
                                if(enemy_id>=0 && enemy_id<=3) // not enemy
                                {
                                    b.cell_pieces[src_cell_indexi].remove(k);  //remove from piece from the sourc cell
                                    b.cell_pieces[targ_cell_indexi].add(piece_id);//add piece to the target cell
                                    w[piece_id]=20;h[piece_id]=35;
