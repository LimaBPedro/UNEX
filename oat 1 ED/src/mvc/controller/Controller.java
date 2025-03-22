package mvc.controller;

import mvc.model.Player;
import mvc.model.SquareGame;
import mvc.utils.ControllerUtils;

import java.util.*;

public class Controller {
    private final Integer BLACK = 2;
    public Player createPlayer(String name,String color,Integer score){
        Player player = new Player();
        player.setColor(color);
        player.setName(name);
        player.setScore(score);
        return player;
    }

    public  Map<String,SquareGame> createTabGame(Integer sizeTab){
        verifySizeGame(sizeTab);
        Map<String,SquareGame> mapGame = createTab4x4();
        return mapGame;
    }

private void verifySizeGame(Integer sizeTab){
        if(!sizeTab.equals(ControllerUtils.SIZE16)){
            throw new RuntimeException(ControllerUtils.TABSIZE_EXECEPTION);
        }
    }

    private Map<String,SquareGame> createTab4x4(){
        Integer controlador = ControllerUtils.SIZE16;
        Double blueAndRed = ControllerUtils.SIZE16*0.5;
        Integer yellow = ControllerUtils.SIZE16 - BLACK - blueAndRed.intValue();
        Map<String,SquareGame> mapGame = new HashMap<>();
        ArrayList<Integer> line = generateArrayLine(ControllerUtils.SIZE16);
        ArrayList<String> column = generateArrayColumm(ControllerUtils.SIZE16);
        ArrayList<String> uniqueLocationList = new ArrayList<>();
        while(!controlador.equals(0) ){
            SquareGame squareGame = generateSquareGame(column,line,uniqueLocationList);
            if (controlador > blueAndRed){
                if (controlador%2 == 0){
                    squareGame.setColor(ControllerUtils.BLUE);
                }else{
                    squareGame.setColor(ControllerUtils.RED);
                }
            }else if(controlador > yellow){
                squareGame.setColor(ControllerUtils.BLACK);
            }else{
                squareGame.setColor(ControllerUtils.YELLOW);
            }
            mapGame.put(squareGame.getlocation(),squareGame);
            controlador--;
        }
        return mapGame;
    }

    private ArrayList<Integer> generateArrayLine(Integer size){
        ArrayList<Integer> line = new ArrayList<>();
        for (int i = 1;i<=size/2;i++){
            line.add(i);
        }
        return line;
    }

    public ArrayList<String> generateArrayColumm(Integer size) {
        ArrayList<String> column = new ArrayList<>();
        for (int i = 0; i < size/2; i++) {
            column.add(generateValueToTheColumn(i));
        }
        return column;
    }

    private String generateValueToTheColumn(int index) {
        StringBuilder sb = new StringBuilder();
        while (index >= 0) {
            sb.insert(0, (char) ('A' + index % 26));
            index = index / 26 - 1;
        }
        return sb.toString();
    }

    private SquareGame generateSquareGame(ArrayList<String> column,ArrayList<Integer> line, ArrayList<String> uniqueLocationList){

        SquareGame squareGame = new SquareGame();
        Random random = new Random();
        String location = "" + column.get(random.nextInt(column.size()));
        location += line.get(random.nextInt(line.size()));
        if (uniqueLocationList.isEmpty()){
            uniqueLocationList.add(location);
        }else if(uniqueLocationList.size() == 16){
            return null;
        }else{
            Integer controlador = uniqueLocationList.size();
            while(controlador <= uniqueLocationList.size()){
                location = "" + column.get(random.nextInt(column.size()));
                location += line.get(random.nextInt(line.size()));
                boolean exist = false;
                for (String g: uniqueLocationList){
                    if (g.equals(location)){
                        exist = true;
                    }
                }
                if (!exist){
                    uniqueLocationList.add(location);
                }
            }
        }
        squareGame.setlocation(location);
        return squareGame;
    }
}
