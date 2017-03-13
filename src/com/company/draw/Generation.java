package com.company.draw;


import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alif on 13.3.17.
 */
public class Generation {

    List<Mouse> mice;

    private static Generation instance = null;

    public Generation(){
        this.mice = new ArrayList<>();
        for(int i=0;i<50;i++){
            this.mice.add(Mouse.getNewMouse());
        }
        Generation.instance = this;
    }

    public static final Generation getGeneration(){
        if(Generation.instance == null) {
            Generation.instance = new Generation();
        }
        return Generation.instance;

    }

    private static void crossoverGeneration(){
        Generation.instance.mice = Generation.instance.mice.stream().sorted((mouse1,mouse2)-> Double.compare(mouse1.getFitness(), mouse2.getFitness())).limit(Generation.instance.mice.size()).collect(Collectors.toList());
        List<Mouse> firstHalf = Generation.instance.mice.stream().limit(Generation.instance.mice.size()).collect(Collectors.toList());
        List<Mouse> secondHalf = Generation.instance.mice.stream().skip(Generation.instance.mice.size()).collect(Collectors.toList());
        for(int i=0;i<((firstHalf.size()>=secondHalf.size())?firstHalf.size():secondHalf.size());i++){
            Generation.instance.mice.addAll(Arrays.asList(Mouse.getDecendentMouse(firstHalf.get(i),secondHalf.get(i))));
        }
    }

    private static boolean doGenerationStep(){
        if(Generation.instance.mice.stream().filter((mouse)->mouse.getDead()).count()!=0){
            Generation.instance.mice.stream().forEach(mouse -> mouse.MakeNextMove(MazeMap.getMap().getWallsForPosition(mouse.getPosition())));
            return true;
        }
        return false;
    }



}
