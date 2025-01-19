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
    Boolean randomVertical = false;

    public Board aiBoard;
    public PlaceShips shipPlacer; //для других кодов

    public ai(Board board, PlaceShips shipPlace)
    {
        this.aiBoard = board;
        this.shipPlacer = shipPlace;
    } //настраивает

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
        Xhit = random.nextInt(aiBoard.getSize());
        Yhit = random.nextInt(aiBoard.getSize());

        aiBoard.setHit(Xhit, Yhit);
        //if(aiBoard.)
    }




    



    //оно должно отдавать отчет о 
}
