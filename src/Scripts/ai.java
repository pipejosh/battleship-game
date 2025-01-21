package Scripts;

import Board.Board;
import Ship.PlaceShips;
import java.util.Random;

public class ai {
    //надо расставить корабли с помощью ии
    //если вертикально то надо чтобы оно ставило от 1 до 10 по иксу и от 1 до (6,7,8,9,10) по игрику потому что у нас корабли разных размеров
    //тоже самое с горизонтально от 1 до 10 по игрику и от 1 до (6,7,8,9,10) по иксу по той же причине
    //надо сделать ии так чтобы если попало хоть раз то оно в следующий раз било рядом как настоящий человек
    //первый выстрел делается через рандом, рандомный х и рандомный у
    //после попадения оно стреляет в ближайшие 4 клетки которые прилогают к попадению,
    //можно сделать через x+1 или x-1 или y+1 или y-1
    //надо сделать так чтобы оно отправляло в другой скрипт (скорее всего) свои данные о выстреле
    //и сделать так чтобы оно ходило после игрока
    //и присвоить один полигон ии-шке
    //дальше отдам на полировку напарнику
    //так же если оно делает выстрел и попадает, дальше стреляет в правильном направлении и корабль не уничтожается (типо там конец) то оно возвращается на прошлую точку и стреляет-
    //- в противоположном направлении и уничтожает корабль
    // оно не должно выходить за рамки полигона.
 
    Random random = new Random();
    
    int Xplace = 0;
    int Yplace = 0;
    int Xhit = 0;
    int Yhit = 0;
    int Rotation = 0;
    int hitPointX = 0;
    int hitPointY = 0;
    Boolean shipDestroyed = false;
    Boolean randomVertical = false;

    public Board aiBoard;
    public PlaceShips shipPlacer; //для других кодов

    public ai(Board board, PlaceShips shipPlace)
    {
        this.aiBoard = board;
        this.shipPlacer = shipPlace;
    } //настраивает

    public ai()
    {
        this.aiBoard = new Board();
        this.shipPlacer = new PlaceShips(aiBoard);
    }

    public void setShip()
    {
        while (shipPlacer.getShipsLeft() > 0)
        { 
            Xplace = random.nextInt(aiBoard.getSize());
            Yplace = random.nextInt(aiBoard.getSize());
            randomVertical = random.nextBoolean();

            shipPlacer.setIsHoriazontal(randomVertical);
            shipPlacer.placeShips(Xplace, Yplace);
        }
    }

    public void setHit()
    {   
        //хочу сделать точку возрата то бишь Yhit и Xhit и после них hitPointX и hitPointY с помощью которых ии будет стрелять точно в цель
        //к примеру ии попадает в корабль и ставить контрольные точки Yhit и Xhit, после этого он выбирает рандомную точку для выстрела (главное не за границы) и стреляет - 
        // - оставляя координаты нового выстрела с помощью hitPoint-ов
        //если мимо то он возвращается на контрольные точки и стреляет в другую сторону и повторяет историю с коордами
        //если он стрелял успешно, но вдруг корабль оборвался по пути и не уничтожился то он опять возвращается на контрольные точки и стреляет в противоположную сторону

        Xhit = random.nextInt(10);
        Yhit = random.nextInt(10);
        
        aiBoard.setHit(Xhit, Yhit);

        if (aiBoard.getBoardPosition(Xhit, Yhit).equals("HIT"))
        
        {
            System.out.println("123");

            shipDestroyed = false;
            while(!shipDestroyed){

                if (aiBoard.getBoardPosition(Xhit, Yhit).equals("DESTROYED")){
                    
                }else{
                    Rotation = random.nextInt(4);
                

            if (Rotation == 0){
                if(Xhit +1 == 10){

                }else{
                    hitPointX = Xhit + 1;
                aiBoard.setHit(hitPointX, Yhit);
                    
                if (aiBoard.getBoardPosition(hitPointX, Yhit).equals("HIT")){

                    if (aiBoard.getBoardPosition(hitPointX, Yhit).equals("DESTROYED")){
                    shipDestroyed = true;
                   }else{
                    hitPointX = hitPointX + 1;
                    aiBoard.setHit(hitPointX, Yhit);

                        if (aiBoard.getBoardPosition(hitPointX, Yhit).equals("DESTROYED")){
                        shipDestroyed = true;
                        }else if(aiBoard.getBoardPosition(hitPointX, Yhit).equals("HIT")){
                            hitPointX = hitPointX + 1;
                            aiBoard.setHit(hitPointX, Yhit);

                        }else{
                            hitPointX = Xhit - 1;
                            aiBoard.setHit(hitPointX, Yhit);
                            if (aiBoard.getBoardPosition(hitPointX, Yhit).equals("DESTROYED")){
                                shipDestroyed = true;
                            }else{
                                hitPointX = hitPointX - 1;
                                aiBoard.setHit(hitPointX, Yhit);

                            }
                        }
                   }

                }else{

                }

                

                }


            } else if (Rotation == 1){
                if(Xhit -1 == -1){

                }else{
                    hitPointX = Xhit - 1;
                aiBoard.setHit(hitPointX, Yhit);
                }


            }else if (Rotation == 2){
                if(Yhit + 1 == 10){

                }else{
                    hitPointY = Yhit + 1;
                aiBoard.setHit(Xhit, hitPointY);
                }


            }else if (Rotation == 3){
                if(Yhit -1 == -1){

                }else{
                    hitPointY = Yhit - 1;
                aiBoard.setHit(Xhit, hitPointY);
                }


            }else{
                
            }


          }

                

       }
            

        
    }


   }

   public void printBoard()
   {
    aiBoard.printBoard();
   }

   public static void main(String[] args) 
   {
        ai test = new ai();

        test.printBoard();
        test.setShip();
        test.printBoard();
        test.setHit();
        test.printBoard();
    }
        

 }







