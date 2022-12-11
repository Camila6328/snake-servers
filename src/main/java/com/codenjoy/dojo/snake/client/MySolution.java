package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;

import java.util.ArrayList;
import java.util.List;

public class MySolution {
    private List<Board> previousBoards = new ArrayList<>();
    public Direction solve(Board b) {

        Point head = b.getHead();
        Point stone = b.getStones().get(0);
        Point apple = b.getApples().get(0);
        boolean besideXAppleUp = apple.getX() == stone.getX() && head.getX() == apple.getX() && stone.getY() - 1 == apple.getY();
        boolean besideXStoneUp = apple.getX() == stone.getX() && head.getX() == apple.getX() && stone.getY() + 1 == apple.getY();
        boolean sameLineX = stone.getX() == head.getX();
        boolean sameLineY = stone.getY() == head.getY();

        if (head == null) return b.getSnakeDirection();

        if (     besideXAppleUp == true && head.getY() < apple.getY() && head.getY() != stone.getY() - 1)
            return Direction.DOWN;
        else if (besideXAppleUp == true && head.getY() < apple.getY() && head.getY() == stone.getY() - 1)
            return Direction.RIGHT;
        else if (besideXStoneUp == true && head.getY() < apple.getY() && head.getY() == stone.getY() - 1)
            return Direction.RIGHT;
        else if (besideXAppleUp == true && head.getY() > apple.getY() && head.getY() == stone.getY() + 1)
            return Direction.RIGHT;
        else if (besideXAppleUp == true && head.getY() > apple.getY() && head.getY() == stone.getY() + 1 && apple.getX()==b.size()-1)
            return Direction.LEFT;

        else if (sameLineX == true && apple.getY()<head.getY() && Direction.getValues().equals("UP")) return Direction.UP;
        else if (sameLineX == true && apple.getY()>head.getY() && Direction.getValues().equals("DOWN")) return Direction.DOWN;
        else if (sameLineX == true && apple.getY()<head.getY() && Direction.getValues().equals("DOWN")) return Direction.RIGHT;
        else if (sameLineX == true && apple.getX()>head.getX() && apple.getY()>head.getY() && Direction.getValues().equals("UP")) return Direction.RIGHT;

         if (head.getX() < apple.getX()) return Direction.RIGHT;
        else if (head.getX() > apple.getX()) return Direction.LEFT;
        else if (head.getY() > apple.getY()) return Direction.DOWN;
        else return CheckSameLine(b);

    }
    public Direction SameLineXHeadStone (Board b){
        Point head = b.getHead();
        Point stone = b.getStones().get(0);
        Point apple = b.getApples().get(0);

        boolean sameLineX = stone.getX() == head.getX();

        return solve(b) ;
    }

    public Direction SameLineYHeadStone (Board b){
        Point head = b.getHead();
        Point stone = b.getStones().get(0);
        Point apple = b.getApples().get(0);

        boolean sameLineY = stone.getY() == head.getY();

        return solve(b) ;
    }
    public Direction CheckSameLine(Board b) {
        Point head = b.getHead();
        Point apple = b.getApples().get(0);
        for (int i = 0; i < b.getSnake().size(); i++) {
            Point tail = b.getSnake().get(i);
            System.out.println(tail);
            boolean sameLineTailX = tail.getX() == head.getX();
            boolean sameLineTailY = tail.getY() == head.getY();

            if (sameLineTailX == true && head.getX()==apple.getX() && head.getX() >= tail.getX() && apple.getY()< head.getY()) return Direction.RIGHT;
            else if (sameLineTailX == true && head.getX() >= tail.getX() && apple.getY()< head.getY()) return Direction.RIGHT;
            else if (sameLineTailY == true && head.getY()==apple.getY() && head.getX() >= tail.getX() && apple.getX()< head.getX()) return Direction.DOWN;
            else if (sameLineTailY == true && head.getX() >= tail.getX() && apple.getY()< head.getY()) return Direction.DOWN;
            else if (sameLineTailY == true && head.getX()<tail.getX() && apple.getX()> head.getX() && apple.getY()<head.getY()) return Direction.UP;
            else if (sameLineTailY == true && head.getX()<tail.getX() && apple.getX()> head.getX() && apple.getY()<head.getY()) return Direction.DOWN;
            else if (sameLineTailY == true && head.getX()<tail.getX() && apple.getX()> head.getX() && apple.getY()>head.getY()) return Direction.DOWN;
            else if (sameLineTailX == true && head.getX()<tail.getX() && apple.getX()> tail.getX() && apple.getY()<head.getY()) return Direction.DOWN;
            else if (sameLineTailX == true && apple.getY()> tail.getX() && apple.getY()>head.getY() && Direction.getValues().equals("DOWN")) return Direction.RIGHT;
            else if (sameLineTailX == true && Direction.getValues().equals("UP")) return Direction.UP;
            else if (head.getY() > apple.getY()) return Direction.DOWN;
            else if (sameLineTailX == true && head.getX() > tail.getX()) return Direction.RIGHT;
            else if (sameLineTailX == true && head.getX() < tail.getX()) return Direction.LEFT;
            else if (sameLineTailY == true && head.getY() > tail.getY()) return Direction.DOWN;
            else if (sameLineTailY == true && head.getY() < tail.getY()) return Direction.UP;
            else if (sameLineTailX == true && head.getX() == tail.getX() - 1) return Direction.RIGHT;
            else if (sameLineTailX == true && head.getX() == tail.getX() + 1) return Direction.RIGHT;
            else if (sameLineTailY == true && head.getY() == tail.getY() - 1) return Direction.RIGHT;
            else if (sameLineTailY == true && head.getX() > tail.getX() && apple.getY()>head.getY() && apple.getX()< head.getX()) return Direction.DOWN;
            else if (sameLineTailY == true && head.getX() > tail.getX() && apple.getY()<head.getY() && apple.getX()< head.getX()) return Direction.UP;
            else if (sameLineTailY == true && head.getY() == tail.getY() + 1) return Direction.RIGHT;

        }
        return TailCheck(b);//Direction.UP;
    }
    public Direction TailCheck(Board b)
    {
        Point head = b.getHead();
        Point apple = b.getApples().get(0);
        for (int i = 0; i < b.getSnake().size(); i++) {
            Point tail = b.getSnake().get(i);
            System.out.println(tail);
            if (     apple.getY() < head.getY() && Direction.getValues().equals("DOWN"))
                return Direction.DOWN;
            else if (apple.getY() < head.getY() && Direction.getValues().equals("UP")) return Direction.UP;
            else if (apple.getX() < head.getX() && Direction.getValues().equals("LEFT")) return Direction.LEFT;
            else if (apple.getX() < head.getX() && Direction.getValues().equals("RIGHT")) return Direction.RIGHT;
            else if (apple.getX()<tail.getX() && tail.getX()+1==head.getX() && Direction.getValues().equals("UP")) return Direction.UP;
        }
        return Direction.UP;
    }
}
