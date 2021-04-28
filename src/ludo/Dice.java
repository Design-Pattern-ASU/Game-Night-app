/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;
import java.util.Random; 
/**
 *
 * @author marawan
 */
public interface Dice
{
 public int throw_dice();
}

class onedice implements Dice
{
    int number;
 Random rand;

    public onedice() 
    {
        number =0;
       rand = new Random();
    }
    
    public int throw_dice()
  {
     number= rand.nextInt(6)+1;
     return number;
  }
}
class twodice implements Dice
{
    int number;
 Random rand;
    public twodice() 
    {
        number =0;
       rand = new Random();
    }
    
    public int throw_dice()
  {
     number= rand.nextInt(12)+1;
     return number;
  }
}
class dicestrategy
{
    private Dice strategy;
    void setstrategy(Dice strategy)
    {
        this.strategy=strategy;
    }
    public int throwdice()
    {
       return strategy.throw_dice();
    }
}

