package com.company.draw;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by alif on 13.3.17.
 */
public class Mouse {

    private Dimension position;

    private List<Move> moveList = new ArrayList<>();

    private Boolean isDead = false;

    private double fitness = 0.0d;

    private Iterator<Move> moveIterator;

    private Mouse(List<Move> moveList){
        this.moveList = moveList;
        this.moveIterator = this.moveList.iterator();
        this.position = new Dimension(1,1);
    }

    public Boolean MakeNextMove(Map<Move,Boolean> walls){

        if(this.moveIterator.hasNext())
        {
            Move nextMove = this.moveIterator.next();
            if(walls.get(nextMove))
            {
                switch (nextMove){
                    case DOWN: position.height =- 1;
                                break;
                    case UP: position.height += 1;
                                break;
                    case LEFT: position.width -= 1;
                                break;
                    case RIGHT: position.width += 1;
                                break;
                }
                fitness+=0.1;
            }else{
                fitness-=0.05;
            }
        }
        else{
            this.isDead = true;
        }
        return isDead;
    }

    private static List<Move> generateMoves(){
        Random rand = new Random();
        int numberOfMoves = 10+ rand.nextInt(100);
        List<Move> moves = new ArrayList<>();
        for(int i=0;i<numberOfMoves;i++)
        {
            int nextMove = 1 + rand.nextInt(4);
            Move move = (nextMove == 1)? Move.UP: (nextMove==2) ? Move.DOWN : (nextMove==3)? Move.LEFT: Move.RIGHT;
            moves.add(move);
        }
        return moves;
    }

    public static Mouse getNewMouse(){
        return new Mouse(generateMoves());
    }

    public static Mouse[] getDecendentMouse(Mouse father, Mouse mother){
        List<Move> child1moves = new ArrayList<>();
        List<Move> child2moves = new ArrayList<>();
        child1moves.addAll(father.moveList.stream().limit(father.moveList.size()/2).collect(Collectors.toList()));
        child1moves.addAll(mother.moveList.stream().skip(mother.moveList.size()/2).collect(Collectors.toList()));
        child2moves.addAll(mother.moveList.stream().limit(father.moveList.size()/2).collect(Collectors.toList()));
        child2moves.addAll(father.moveList.stream().skip(mother.moveList.size()/2).collect(Collectors.toList()));
        return new Mouse[]{new Mouse(child1moves), new Mouse(child2moves)};
    }

    public Dimension getPosition() {
        return position;
    }

    public void setPosition(Dimension position) {
        this.position = position;
    }

    public Boolean getDead() {
        return isDead;
    }

    public void setDead(Boolean dead) {
        isDead = dead;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

}
