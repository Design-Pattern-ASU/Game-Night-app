/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;

import java.util.ArrayList;
import java.util.Vector;
import javafx.util.Pair;
import org.newdawn.slick.Image;
import org.lwjgl.input.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Mohamed Amr
 */
class ludoboard extends BoardStrategy
{  
    
     private int n = 58;   // length of array 
     public Coordinates path[] = new Coordinates[n];    // Array of Pair 
     int Xarr[]=new int[n];//white path
     int Yarr[]=new int[n];
     public final int [] mousepathX,mousepathY; // all board ma3ada elly bara
     public final int [] mouseyellow_x,mousered_x,mousegreen_x,mouseblue_x;  // mouse bara
      public final int [] mouseyellow_y,mousered_y,mousegreen_y,mouseblue_y;
     public final int [] yend_x,gend_x,bend_x,rend_x;  //end path bensba lel soora
     public final int [] yend_y,gend_y,bend_y,rend_y;  //end path
     public final int ystart,bstart,gstart,rstart; // index el 7agat di f el soora ->xarr
     public final int yend,bend,gend,rend;
     public final int safe1,safe2,safe3,safe4;
     public final int safe5,safe6,safe7,safe8;
     public final int [] home_x,home_y;   //home of each player // 7agat elly bara benesba lel soora
    Vector<Float> full_mouse_path_x;
    Vector<Float> full_mouse_path_y;
    Vector<Float> full_path_x;
    Vector<Float> full_path_y;
    int  YELLOWX[]=new int[n];
    int YELLOWY[]=new int[n];
    int BLUEX[]=new int[n];
    int BLUEY[]=new int[n];
    int RedX[]=new int[n];
    int RedY[]=new int[n];
    int GREENX[]=new int[n];
    int GREENY[]=new int[n];
    boolean safe_zone[] ;
    private static ludoboard instance=new ludoboard();
     
    Vector cell_pieces[];  // 92
     public Image board_image;
    // public Coordinates[] home;
  //   Pair<Integer, Integer>h;
    private ludoboard()
    {       
       ystart=0;
         bstart=39;
         gstart=13;
         rstart=26;
         yend=50;bend=37;gend=11;rend=24;
         safe1=8;safe2=21;safe3=34;safe4=47;
         safe5=0;safe6=39;safe7=13;safe8=26;
         mousepathX=new int[100];
         mousepathY=new int[100];
         home_x=new int[16];
         home_y=new int[16]; 
         yend_x=new int[6];
         yend_y=new int[6];
         rend_x=new int[6];
         rend_y=new int[6];
         gend_x=new int[6];
         gend_y=new int[6];
         bend_x=new int[6];
         bend_y=new int[6];
         mouseblue_x=new int[16];
         mouseblue_y=new int[4];mousered_x=new int[4];mousered_y=new int[4];mousegreen_x=new int[4];
         mousegreen_y=new int[4];mouseyellow_x=new int[4];mouseyellow_y=new int[4];
         
         full_mouse_path_x = new Vector();
         full_mouse_path_y = new Vector();
         full_path_x = new Vector<Float>();
         full_path_y = new Vector<Float>();
         safe_zone = new boolean[92];
                 
    }
    
    public static ludoboard getinstance()
    {
        return instance;
    }
    
    void cells()
    {
        //filling the colored path that leads to the end
    //yellow
        home_x[0]=270;
        home_y[0]=735;
        full_path_x.add((float) (270));
        full_path_y.add((float)735);

        home_x[1]=365;
        home_y[1]=735;
        
        full_path_x.add((float)365);
        full_path_y.add((float)735);
        
        home_x[2]=365;
        home_y[2]=642;
        
        full_path_x.add((float)365);
        full_path_y.add((float)642);

        home_x[3]=270;
        home_y[3]=642;
        
        full_path_x.add((float)270);
        full_path_y.add((float)642);
         //red
         
        home_x[4]=705;
        home_y[4]=295;
        
        full_path_x.add((float)705);
        full_path_y.add((float)295);
     
        home_x[5]=798;
        home_y[5]=295;
        
        full_path_x.add((float)798);
        full_path_y.add((float)295);
    
        home_x[6]=798;
        home_y[6]=200;
        
        full_path_x.add((float)798);
        full_path_y.add((float)200);
        
        home_x[7]=705;
        home_y[7]=200;
        
        full_path_x.add((float)705);
        full_path_y.add((float)200);
        
         //green
         
        home_x[8]=270;
        home_y[8]=295;
        
        full_path_x.add((float)270);
        full_path_y.add((float)295);

        home_x[9]=365;
        home_y[9]=295;
        
        full_path_x.add((float)365);
        full_path_y.add((float)295);
     
        home_x[10]=365;
        home_y[10]=200;
        
        full_path_x.add((float)365);
        full_path_y.add((float)200);
     
        home_x[11]=270;
        home_y[11]=200;
        
        full_path_x.add((float)270);
        full_path_y.add((float)200);
         //blue
         
        home_x[12]=705;
        home_y[12]=735;
        
        full_path_x.add((float)705);
        full_path_y.add((float)735);

        home_x[13]=798;
        home_y[13]=735;
        
        full_path_x.add((float)798);
        full_path_y.add((float)735);
    
        home_x[14]=798;
        home_y[14]=642;
        
        full_path_x.add((float)798);
        full_path_y.add((float)642);
  
        home_x[15]=705;
        home_y[15]=642;
        
        full_path_x.add((float)705);
        full_path_y.add((float)642);
        //filling all the white pathes in the game
         int y;
        int counter=0;
         int Xindex=0,Yindex=0;
        y=755;
        for(int i=0;i<5;i++)
        {
            Xarr[Xindex]=485;
            Yarr[Yindex]=y;
          
            
            full_path_x.add((float)485);
            full_path_y.add((float)y);
            Xindex++;
            Yindex++;
            y-=50;
            counter+=1;
        }
        ////////////////
         y=437;
        for(int i=0;i<6;i++)
        {
            Xarr[Xindex]=y;
            Yarr[Yindex]=520;
         
            full_path_x.add((float)y);
            full_path_y.add((float)520);
            Xindex++;
            Yindex++;
            y-=45;
            counter+=1;
        }
        ///////////////////
         y=470;
        for(int i=0;i<2;i++)
        {
            Xarr[Xindex]=200;
            Yarr[Yindex]=y;
        
            full_path_x.add((float)200);
            full_path_y.add((float)y);
            Xindex++;
            Yindex++;
            y-=50;
            counter+=1;
        }
        /////////////////
          y=247;
        for(int i=0;i<5;i++)
        {
            Xarr[Xindex]=y;
            Yarr[Yindex]=415;
           
            full_path_x.add((float)y);
            full_path_y.add((float)415);
            Xindex++;
            Yindex++;
            y+=45;
            counter+=1;
        }
        
        y=370;
        for(int i=0;i<6;i++)
        {
            Xarr[Xindex]=483;
            Yarr[Yindex]=y;
          
            full_path_x.add((float)483);
            full_path_y.add((float)y);
            Xindex++;
            Yindex++;
            y-=50;
            counter+=1;
        }
        
        y=533;
        for(int i=0;i<2;i++)
        {
            Xarr[Xindex]=y;
            Yarr[Yindex]=135;
          
            full_path_x.add((float)y);
            full_path_y.add((float)135);
            Xindex++;
            Yindex++;
            y+=50;
            counter+=1;
        }
        
        y=173;
        for(int i=0;i<5;i++)
        {
            Xarr[Xindex]=586;
            Yarr[Yindex]=y;
            full_path_x.add((float)586);
            full_path_y.add((float)y);
            Xindex++;
            Yindex++;
            y+=45;
            counter+=1;
        }
        
          y=630;
        for(int i=0;i<6;i++)
        {
            Xarr[Xindex]=y;
            Yarr[Yindex]=422;
           
            full_path_x.add((float)y);
            full_path_y.add((float)422);
            Xindex++;
            Yindex++;
            y+=45;
            counter+=1;
        }
        
         y=472;
        for(int i=0;i<2;i++)
        {
            Xarr[Xindex]=870;
            Yarr[Yindex]=y;
             
            full_path_x.add((float)870);
            full_path_y.add((float)y);
            Xindex++;
            Yindex++;
            y+=50;
            counter+=1;
        }
        
         y=820;
        for(int i=0;i<5;i++)
        {
            Xarr[Xindex]=y;
            Yarr[Yindex]=520;
           
            full_path_x.add((float)y);
            full_path_y.add((float)520);
            Xindex++;
            Yindex++;
            y-=50;
            counter+=1;
        }
        
         y=565;
        for(int i=0;i<6;i++)
        {
            Xarr[Xindex]=584;
            Yarr[Yindex]=y;
            full_path_x.add((float)584);
            full_path_y.add((float)y);
            Xindex++;
            Yindex++;
            y+=50;
            counter+=1;
        }
        
         y=530;
        for(int i=0;i<2;i++)
        {
            Xarr[Xindex]=y;
            Yarr[Yindex]=807;
           full_path_x.add((float)y);
           full_path_y.add((float)807); 
            Xindex++;
            Yindex++;
            y-=50;
            counter+=1;
        }
        
        
        
        //filling the colored path that leads to the end
         y=755;
        int a;
        for(int i=0;i<6;i++)
        {
            yend_x[i]=536;
            yend_y[i]=y;
            full_path_x.add((float)536);
            full_path_y.add((float)y);
            y-=50;
        }
        int r=173;
        for(int i=0;i<6;i++)
        {
             rend_x[i]=536;
            rend_y[i]=r;
            full_path_x.add((float)536);
            full_path_y.add((float)r);
            
            r+=50;
        }
        
        int g=247;
        for(int i=0;i<6;i++)
        {
             gend_x[i]=g;
            gend_y[i]=465;
            full_path_x.add((float)g);
            full_path_y.add((float)465);
            g+=50;
        }
         
        int b=820;
        for(int i=0;i<6;i++)
        {
            bend_x[i]=b;
            bend_y[i]=465;
            full_path_x.add((float)b);
            full_path_y.add((float)465);
            b-=50; 
        }
        
            cell_pieces = new Vector[92];
            for(int i=0;i<cell_pieces.length;i++)
            {
                cell_pieces[i]=new Vector<Integer>();
            }
        

    }
     void mousepositions()
    {
        mouseyellow_x[0]=284;
        mouseyellow_y[0]=231;
        mouseyellow_x[1]=377;
        mouseyellow_y[1]=231;
        mouseyellow_x[2]=377;
        mouseyellow_y[2]=329;
        mouseyellow_x[3]=267;
        mouseyellow_y[3]=329;
        
          full_mouse_path_x.add((float)284);
          full_mouse_path_y.add((float)231);
          full_mouse_path_x.add((float)377);
          full_mouse_path_y.add((float)231);
          full_mouse_path_x.add((float)377);
          full_mouse_path_y.add((float)329);
          full_mouse_path_x.add((float)267);
          full_mouse_path_y.add((float)329);
          
        
        
        
        mousered_x[0]=717;
        mousered_y[0]=670;
        mousered_x[1]=810;
        mousered_y[1]=670;
        mousered_x[2]=810;
        mousered_y[2]=763;
        mousered_x[3]=717;
        mousered_y[3]=763;
        
        full_mouse_path_x.add((float)717);
        full_mouse_path_y.add((float)670);
        full_mouse_path_x.add((float)810);
        full_mouse_path_y.add((float)670);
        full_mouse_path_x.add((float)810);
        full_mouse_path_y.add((float)763);
        full_mouse_path_x.add((float)717);
        full_mouse_path_y.add((float)763);
        
          
        
        mousegreen_x[0]=284;
        mousegreen_y[0]=670;
        mousegreen_x[1]=377;
        mousegreen_y[1]=670;
        mousegreen_x[2]=377;
        mousegreen_y[2]=763;
        mousegreen_x[3]=284;
        mousegreen_y[3]=763;
        
          full_mouse_path_x.add((float)284);
        full_mouse_path_y.add((float)670);
        full_mouse_path_x.add((float)377);
        full_mouse_path_y.add((float)670);
        full_mouse_path_x.add((float)377);
        full_mouse_path_y.add((float)763);
        full_mouse_path_x.add((float)284);
        full_mouse_path_y.add((float)763);
        
        mouseblue_x[0]=717;
        mouseblue_y[0]=231;
        mouseblue_x[1]=810;
        mouseblue_y[1]=231;
        mouseblue_x[2]=810;
        mouseblue_y[2]=329;
        mouseblue_x[3]=717;
        mouseblue_y[3]=329;
        
          full_mouse_path_x.add((float)717);
        full_mouse_path_y.add((float)231);
        full_mouse_path_x.add((float)810);
        full_mouse_path_y.add((float)231);
        full_mouse_path_x.add((float)810);
        full_mouse_path_y.add((float)329);
        full_mouse_path_x.add((float)717);
        full_mouse_path_y.add((float)329);
        
        
        
       
        int index=0;
      
          //filling all the white pathes in the game
        int y=211;
        for(int i=0;i<5;i++)
        {
            mousepathX[index]=498;
            mousepathY[index]=y;
            full_mouse_path_x.add((float)498);
            full_mouse_path_y.add((float)y);
            
            y+=50;
            index+=1;
        }
        ////////////////
         y=450;
        for(int i=0;i<6;i++)
        {
            mousepathX[index]=y;
            mousepathY[index]=450;
             full_mouse_path_x.add((float)y);
            full_mouse_path_y.add((float)450);
            y-=45;
            index+=1;
        }
        ///////////////////
         y=501;
        for(int i=0;i<2;i++)
        {
            mousepathX[index]=213;
            mousepathY[index]=y;
             full_mouse_path_x.add((float)213);
            full_mouse_path_y.add((float)y);
            y+=50;
            index+=1;
        }
        /////////////////
          y=262;
        for(int i=0;i<5;i++)
        {
            mousepathX[index]=y;
            mousepathY[index]=550;
             full_mouse_path_x.add((float)y);
            full_mouse_path_y.add((float)550);
            y+=45;
            index+=1;
        }
        
        y=598;
        for(int i=0;i<6;i++)
        {
            mousepathX[index]=498;
            mousepathY[index]=y;
             full_mouse_path_x.add((float)498);
            full_mouse_path_y.add((float)y);
            y+=50;
            index+=1;
        }
        
        y=550;
        for(int i=0;i<2;i++)
        {
            mousepathX[index]=y;
            mousepathY[index]=835;
             full_mouse_path_x.add((float)y);
            full_mouse_path_y.add((float)835);
            y+=50;
            index+=1;
        }
        
        y=785;
        for(int i=0;i<5;i++)
        {
            mousepathX[index]=600;
            mousepathY[index]=y;
             full_mouse_path_x.add((float)600);
            full_mouse_path_y.add((float)y);
            y-=45;
            index+=1;
        }
        
          y=644;
        for(int i=0;i<6;i++)
        {
            mousepathX[index]=y;
            mousepathY[index]=550;
             full_mouse_path_x.add((float)y);
            full_mouse_path_y.add((float)550);
            y+=45;
            index+=1;
        }
        
         y=500;
        for(int i=0;i<2;i++)
        {
            mousepathX[index]=885;
            mousepathY[index]=y;
             full_mouse_path_x.add((float)885);
            full_mouse_path_y.add((float)y);
            y-=50;
            index+=1;
        }
        
         y=835;
        for(int i=0;i<5;i++)
        {
            mousepathX[index]=y;
            mousepathY[index]=450;
             full_mouse_path_x.add((float)y);
            full_mouse_path_y.add((float)450);
            y-=50;
            index+=1;
        }
        
         y=402;
        for(int i=0;i<6;i++)
        {
            mousepathX[index]=599;
            mousepathY[index]=y;
             full_mouse_path_x.add((float)599);
            full_mouse_path_y.add((float)y);
            y-=50;
            index+=1;
        }
        
         y=548;
        for(int i=0;i<2;i++)
        {
            mousepathX[index]=y;
            mousepathY[index]=161;
             full_mouse_path_x.add((float)y);
            full_mouse_path_y.add((float)161);
            y-=50;
            index+=1;
        }
          y=211;
        for(int i=0;i<6;i++)
        {
            mousepathX[index]=548;
            mousepathY[index]=y;
             full_mouse_path_x.add((float)548);
            full_mouse_path_y.add((float)y);
            index++;
            y+=50;
        }
        
        int b=835;
        for(int i=0;i<6;i++)
        {
            mousepathX[index]=b;
            mousepathY[index]=500;
             full_mouse_path_x.add((float)b);
            full_mouse_path_y.add((float)500);
            index++;
            b-=50;
        }
        
        int g=262;
        for(int i=0;i<6;i++)
        {
            mousepathX[index]=g;
            mousepathY[index]=500;
             full_mouse_path_x.add((float)g);
            full_mouse_path_y.add((float)500);
             index++;
            g+=50;
        }
        
        int r=785;
        for(int i=0;i<6;i++)
        {
            mousepathX[index]=550;
            mousepathY[index]=r;
             full_mouse_path_x.add((float)550);
            full_mouse_path_y.add((float)r);
             index++;
            r-=50;
        }
    }
     
   
     
   public   void bluepath()
    {
        int y=820;
        int Xindex=0,Yindex=0;
        for(int i=0;i<5;i++)
        {
            BLUEX[Xindex]=y;
            BLUEY[Yindex]=520;
            Xindex++;
            Yindex++;
            y-=50;
        }
        
         y=565;
        for(int i=0;i<6;i++)
        {
            BLUEX[Xindex]=584;
            BLUEY[Yindex]=y;
            Xindex++;
            Yindex++;
            y+=50;
          
        }
        
         y=530;
        for(int i=0;i<2;i++)
        {
            BLUEX[Xindex]=y;
            BLUEY[Yindex]=807;
            Xindex++;
            Yindex++;
            y-=50;
        }
        int counter=0;
        y=755;
        for(int i=0;i<5;i++)
        {
            BLUEX[Xindex]=485;
            BLUEY[Yindex]=y;
            Xindex++;
            Yindex++;
            y-=50;
            counter+=1;
        }
        ////////////////
         y=437;
        for(int i=0;i<6;i++)
        {
            BLUEX[Xindex]=y;
            BLUEY[Yindex]=520;
            Xindex++;
            Yindex++;
            y-=45;
            counter+=1;
        }
        ///////////////////
         y=470;
        for(int i=0;i<2;i++)
        {
            BLUEX[Xindex]=200;
            BLUEY[Yindex]=y;
            Xindex++;
            Yindex++;
            y-=50;
            counter+=1;
        }
        /////////////////
          y=247;
        for(int i=0;i<5;i++)
        {
            BLUEX[Xindex]=y;
            BLUEY[Yindex]=415;
            Xindex++;
            Yindex++;
            y+=45;
            counter+=1;
        }
        
        y=370;
        for(int i=0;i<6;i++)
        {
            BLUEX[Xindex]=483;
            BLUEY[Yindex]=y;
            Xindex++;
            Yindex++;
            y-=50;
            counter+=1;
        }
        
        y=533;
        for(int i=0;i<2;i++)
        {
            BLUEX[Xindex]=y;
            BLUEY[Yindex]=135;
            Xindex++;
            Yindex++;
            y+=50;
            counter+=1;
        }
        
        y=173;
        for(int i=0;i<5;i++)
        {
            BLUEX[Xindex]=586;
            BLUEY[Yindex]=y;
            Xindex++;
            Yindex++;
            y+=45;
            counter+=1;
        }
        
          y=630;
        for(int i=0;i<6;i++)
        {
            BLUEX[Xindex]=y;
            BLUEY[Yindex]=422;
            Xindex++;
            Yindex++;
            y+=45;
            counter+=1;
        }
        
         y=472;
        for(int i=0;i<1;i++)
        {
            BLUEX[Xindex]=870;
            BLUEY[Yindex]=y;
            Xindex++;
            Yindex++;
            y+=50;
            counter+=1;
        }
        int b=820;
        for(int i=0;i<6;i++)
        {
            BLUEX[Xindex]=b;
            BLUEY[Yindex]=465;
            Xindex++;
            Yindex++;
            b-=50; 
        }
    }
 public void redpath()
    {
       int y=173;
       int Xindex=0,Yindex=0;
        for(int i=0;i<5;i++)
        {
            RedX[Xindex]=586;
            RedY[Yindex]=y;
            Xindex++;
            Yindex++;
            y+=45;
        
        }
        
          y=630;
        for(int i=0;i<6;i++)
        {
            RedX[Xindex]=y;
            RedY[Yindex]=422;
            Xindex++;
            Yindex++;
            y+=45;
        }
        
         y=472;
        for(int i=0;i<2;i++)
        {
            RedX[Xindex]=870;
            RedY[Yindex]=y;
            Xindex++;
            Yindex++;
            y+=50;
        }
        ///////////
         y=820;
        for(int i=0;i<5;i++)
        {
            RedX[Xindex]=y;
            RedY[Yindex]=520;
            Xindex++;
            Yindex++;
            y-=50;
        }
        
         y=565;
        for(int i=0;i<6;i++)
        {
            RedX[Xindex]=584;
            RedY[Yindex]=y;
            Xindex++;
            Yindex++;
            y+=50;
        }
        
         y=530;
        for(int i=0;i<2;i++)
        {
            RedX[Xindex]=y;
            RedY[Yindex]=807;
            Xindex++;
            Yindex++;
            y-=50;
        } 
          y=755;
        for(int i=0;i<5;i++)
        {
            RedX[Xindex]=485;
            RedY[Yindex]=y;
            Xindex++;
            Yindex++;
            y-=50;
        }
        ////////////////
         y=437;
        for(int i=0;i<6;i++)
        {
            RedX[Xindex]=y;
            RedY[Yindex]=520;
            Xindex++;
            Yindex++;
            y-=45;
        }
        ///////////////////
         y=470;
        for(int i=0;i<2;i++)
        {
            RedX[Xindex]=200;
            RedY[Yindex]=y;
            Xindex++;
            Yindex++;
            y-=50;
        }
        /////////////////
          y=247;
        for(int i=0;i<5;i++)
        {
            RedX[Xindex]=y;
            RedY[Yindex]=415;
            Xindex++;
            Yindex++;
            y+=45;
        }
        
        y=370;
        for(int i=0;i<6;i++)
        {
            RedX[Xindex]=483;
            RedY[Yindex]=y;
            Xindex++;
            Yindex++;
            y-=50;
        }
        
        y=533;
        for(int i=0;i<1;i++)
        {
            RedX[Xindex]=y;
            RedY[Yindex]=135;
            Xindex++;
            Yindex++;
            y+=50;
        }
        int r=173;
        for(int i=0;i<6;i++)
        {
            RedX[Xindex]=536;
            RedY[Yindex]=r;
            Xindex++;
            Yindex++;
            r+=50;
        }
    }
  public  void greenpath()
    {
        int y=247;
        int Xindex=0,Yindex=0;
        for(int i=0;i<5;i++)
        {
            GREENX[Xindex]=y;
            GREENY[Yindex]=415;
            Xindex++;
            Yindex++;
            y+=45;
        }
        
        y=370;
        for(int i=0;i<6;i++)
        {
            GREENX[Xindex]=483;
            GREENY[Yindex]=y;
            Xindex++;
            Yindex++;
            y-=50;
        }
        
        y=533;
        for(int i=0;i<2;i++)
        {
            GREENX[Xindex]=y;
            GREENY[Yindex]=135;
            Xindex++;
            Yindex++;
            y+=50;
        }
        y=173;
        for(int i=0;i<5;i++)
        {
            GREENX[Xindex]=586;
            GREENY[Yindex]=y;
            Xindex++;
            Yindex++;
            y+=45;
        }
        
          y=630;
        for(int i=0;i<6;i++)
        {
            GREENX[Xindex]=y;
            GREENY[Yindex]=422;
            Xindex++;
            Yindex++;
            y+=45;
        }
        
         y=472;
        for(int i=0;i<2;i++)
        {
            GREENX[Xindex]=870;
            GREENY[Yindex]=y;
            Xindex++;
            Yindex++;
            y+=50;
        }
        ///////////
         y=820;
        for(int i=0;i<5;i++)
        {
            GREENX[Xindex]=y;
            GREENY[Yindex]=520;
            Xindex++;
            Yindex++;
            y-=50;
        }
        
         y=565;
        for(int i=0;i<6;i++)
        {
            GREENX[Xindex]=584;
            GREENY[Yindex]=y;
            Xindex++;
            Yindex++;
            y+=50;
        }
        
         y=530;
        for(int i=0;i<2;i++)
        {
            GREENX[Xindex]=y;
            GREENY[Yindex]=807;
            Xindex++;
            Yindex++;
            y-=50;
        }
        y=755;
        for(int i=0;i<5;i++)
        {
            GREENX[Xindex]=485;
            GREENY[Yindex]=y;
            Xindex++;
            Yindex++;
            y-=50;
        }
        ////////////////
         y=437;
        for(int i=0;i<6;i++)
        {
            GREENX[Xindex]=y;
            GREENY[Yindex]=520;
            Xindex++;
            Yindex++;
            y-=45;
        }
        ///////////////////
         y=470;
        for(int i=0;i<1;i++)
        {
            GREENX[Xindex]=200;
            GREENY[Yindex]=y;
            Xindex++;
            Yindex++;
            y-=50;
        }
        int g=247;
        for(int i=0;i<6;i++)
        {
            GREENX[Xindex]=g;
            GREENY[Yindex]=465;
            Xindex++;
            Yindex++;
            g+=50;
        }
    }
  public void yellowpath()
  {
      int y;
        int counter=0;
         int Xindex=0,Yindex=0;
        y=755;
        for(int i=0;i<5;i++)
        {
            YELLOWX[Xindex]=485;
            YELLOWY[Yindex]=y;
            Xindex++;
            Yindex++;
            y-=50;
            counter+=1;
        }
        ////////////////
         y=437;
        for(int i=0;i<6;i++)
        {
            YELLOWX[Xindex]=y;
            YELLOWY[Yindex]=520;
            Xindex++;
            Yindex++;
            y-=45;
            counter+=1;
        }
        ///////////////////
         y=470;
        for(int i=0;i<2;i++)
        {
            YELLOWX[Xindex]=200;
            YELLOWY[Yindex]=y;
            Xindex++;
            Yindex++;
            y-=50;
            counter+=1;
        }
        /////////////////
          y=247;
        for(int i=0;i<5;i++)
        {
            YELLOWX[Xindex]=y;
            YELLOWY[Yindex]=415;
            Xindex++;
            Yindex++;
            y+=45;
            counter+=1;
        }
        
        y=370;
        for(int i=0;i<6;i++)
        {
            YELLOWX[Xindex]=483;
            YELLOWY[Yindex]=y;
            Xindex++;
            Yindex++;
            y-=50;
            counter+=1;
        }
        
        y=533;
        for(int i=0;i<2;i++)
        {
            YELLOWX[Xindex]=y;
            YELLOWY[Yindex]=135;
            Xindex++;
            Yindex++;
            y+=50;
            counter+=1;
        }
        y=173;
        for(int i=0;i<5;i++)
        {
            YELLOWX[Xindex]=586;
            YELLOWY[Yindex]=y;
            Xindex++;
            Yindex++;
            y+=45;
            counter+=1;
        }
        
          y=630;
        for(int i=0;i<6;i++)
        {
            YELLOWX[Xindex]=y;
            YELLOWY[Yindex]=422;
            Xindex++;
            Yindex++;
            y+=45;
            counter+=1;
        }
        
         y=472;
        for(int i=0;i<2;i++)
        {
            YELLOWX[Xindex]=870;
            YELLOWY[Yindex]=y;
            Xindex++;
            Yindex++;
            y+=50;
            counter+=1;
        }
        ///////////
         y=820;
        for(int i=0;i<5;i++)
        {
            YELLOWX[Xindex]=y;
            YELLOWY[Yindex]=520;
            Xindex++;
            Yindex++;
            y-=50;
            counter+=1;
        }
        
         y=565;
        for(int i=0;i<6;i++)
        {
            YELLOWX[Xindex]=584;
            YELLOWY[Yindex]=y;
            Xindex++;
            Yindex++;
            y+=50;
            counter+=1;
        }
        
         y=530;
        for(int i=0;i<1;i++)
        {
            YELLOWX[Xindex]=y;
            YELLOWY[Yindex]=807;
            Xindex++;
            Yindex++;
            y-=50;
            counter+=1;
        }
        y=755;
        for(int i=0;i<6;i++)
        {
            YELLOWX[Xindex]=536;
            YELLOWY[Yindex]=y;
            y-=50;
             Xindex++;
            Yindex++;
        }
  }
}
     


abstract class BoardStrategy {
    protected Image board;
    abstract void cells();
}

class snakeboard extends BoardStrategy
{
    public final int [] path_x,path_y;
    public final int [] mousepath_x,mousepath_y;
    int[] head,tail,down,up;
    int snake;
    int ladder;
    private static snakeboard instance=new snakeboard();
    private snakeboard()
    {
        path_x=new int[101];
        path_y=new int[101];
        mousepath_x=new int[101];
        mousepath_y=new int[101];
        head=new int[8];
        tail=new int[8];
        down=new int[8];
        up=new int[8];
    }
    public static snakeboard getinstance()
    {
        return instance;
    }
    void cells()
    {
        int index=1;
        path_x[0]=126;
        path_y[0]=800;
        ///1
     int x=206;
     for(int i=0;i<10;i++)
     {
         path_x[index]=x;
         path_y[index]=800;
         x+=73;
         index++;
     }
     ////2
     x-=73;
     for(int i=0;i<10;i++)
     {
         path_x[index]=x;
         path_y[index]=730;
         x-=73;
         index++;
     }
     ////3
     x+=73;
     for(int i=0;i<10;i++)
     {
         path_x[index]=x;
         path_y[index]=660;
         x+=73;
         index++;
     }
     ////4
     x-=73;
     for(int i=0;i<10;i++)
     {
         path_x[index]=x;
         path_y[index]=590;
         x-=73;
         index++;
     }
     ////5
     x+=73;
     for(int i=0;i<10;i++)
     {
         path_x[index]=x;
         path_y[index]=517;
         x+=73;
         index++;
     }
     ////6
     x-=73;
     for(int i=0;i<10;i++)
     {
         path_x[index]=x;
         path_y[index]=447;
         x-=73;
         index++;
     }
      ////7
     x+=73;
     for(int i=0;i<10;i++)
     {
         path_x[index]=x;
         path_y[index]=368;
         x+=73;
         index++;
     }
       ////8
     x-=73;
     for(int i=0;i<10;i++)
     {
         path_x[index]=x;
         path_y[index]=300;
         x-=73;
         index++;
     }
      ////9
     x+=73;
     for(int i=0;i<10;i++)
     {
         path_x[index]=x;
         path_y[index]=225;
         x+=73;
         index++;
     }
      ////10
     x-=73;
     for(int i=0;i<10;i++)
     {
         path_x[index]=x;
         path_y[index]=155;
         x-=73;
         index++;
     }
     int m=0;
     head[m]=16;tail[m]=6;
     down[m]=0;up[m]=37;
     m++;
     //1
     head[m]=61;tail[m]=18;
     down[m]=3;up[m]=13;
     m++;
     //2
     head[m]=53;tail[m]=33;
     down[m]=8;up[m]=30;
     m++;
     //3
     head[m]=63;tail[m]=59;
     down[m]=20;up[m]=41;
     m++;
     //4
     head[m]=86;tail[m]=23;
     down[m]=27;up[m]=83;
     m++;
     //5
     head[m]=92;tail[m]=72;
     down[m]=50;up[m]=66;
     m++;
     //6
     head[m]=94;tail[m]=74;
     down[m]=70;up[m]=90;
     m++;
     //7
     head[m]=97;tail[m]=78;
     down[m]=79;up[m]=99;
    }
    void mousepositions()
     {
        int index=0;
        ///1
     int x=220;
     for(int i=0;i<10;i++)
     {
         mousepath_x[index]=x;
         mousepath_y[index]=170;
         x+=70;
         index++;
     }
     ////2
     x-=70;
     for(int i=0;i<10;i++)
     {
         mousepath_x[index]=x;
         mousepath_y[index]=240;
         x-=70;
         index++;
     }
     ////3
     x+=70;
     for(int i=0;i<10;i++)
     {
         mousepath_x[index]=x;
         mousepath_y[index]=310;
         x+=70;
         index++;
     }
     ////4
     x-=70;
     for(int i=0;i<10;i++)
     {
         mousepath_x[index]=x;
         mousepath_y[index]=380;
         x-=70;
         index++;
     }
     ////5
     x+=70;
     for(int i=0;i<10;i++)
     {
         mousepath_x[index]=x;
         mousepath_y[index]=450;
         x+=70;
         index++;
     }
     ////6
     x-=70;
     for(int i=0;i<10;i++)
     {
         mousepath_x[index]=x;
         mousepath_y[index]=520;
         x-=70;
         index++;
     }
      ////7
     x+=70;
     for(int i=0;i<10;i++)
     {
         mousepath_x[index]=x;
         mousepath_y[index]=590;
         x+=70;
         index++;
     }
       ////8
     x-=70;
     for(int i=0;i<10;i++)
     {
         mousepath_x[index]=x;
         mousepath_y[index]=660;
         x-=70;
         index++;
     }
      ////9
     x+=70;
     for(int i=0;i<10;i++)
     {
         mousepath_x[index]=x;
         mousepath_y[index]=730;
         x+=70;
         index++;
     }
      ////10
     x-=70;
     for(int i=0;i<10;i++)
     {
         mousepath_x[index]=x;
         mousepath_y[index]=800;
         x-=70;
         index++;
     }
    }
}
