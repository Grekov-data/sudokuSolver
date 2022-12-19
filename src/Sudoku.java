import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//Класс будет представлять из себя поле судоку.
public class Sudoku {
    private List<Integer> grid;     //Само поле судоку.
    public String fileName;

    public Sudoku(List<Integer> grid) {
        this.grid = grid;
    }

    public Sudoku(String fileName) {
        this.grid = new ArrayList<>();
        File initialization = new File(fileName);
        try {
            Scanner scan = new Scanner(initialization);
            int i = 0;
            while (scan.hasNextInt()) {
                this.grid.add(i, scan.nextInt());
                i++;
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }


    //Получаем index в нашем grid по номеру строки и столбца
    private int getIndex(int row, int column) {
        return row * 9 + column;
    }

    //Возвращает true, если схема судоку валидная, false в ином случае
    /*
    Нужно сделать 3 проверки:
    1. Проверка уникальности чисел в каждой строке
    2. Проверка уникальности чисел в каждом столбце
    3. Проверка уникальности чисел в каждом квадрате 3х3

     */
    public boolean isValid() {
        //1, 2. Проверка уникальности по строкам и столбцам.
        for(int i = 0; i < 9; ++i) {
            Set<Integer> numbersInRow = new HashSet<>();
            Set<Integer> numbersInColumn = new HashSet<>();
            for(int j = 0; j < 9; ++j) {
                int numberInRow = grid.get(getIndex(i, j));
                int numberInColumn = grid.get(getIndex(j, i));
                if(numberInRow != 0) {
                    //Возникло повторение
                    if(numbersInRow.contains(numberInRow)) {
                        return false;
                    }
                    numbersInRow.add(numberInRow);
                }
                if(numberInColumn != 0) {
                    //Возникло повторение
                    if(numbersInColumn.contains(numberInColumn)) {
                        return false;
                    }
                    numbersInColumn.add(numberInColumn);
                }
            }
        }

        //3. Проверка уникальности внутри квадратов 3х3
        for(int i = 0; i < 9; i += 3) {
            for(int j = 0; j < 9; j += 3) {
                //На каждом шаге мы имеем какой-то левый верхний угол квадрата 3х3.
                Set<Integer> numbersInSquare = new HashSet<>();
                //Перебираем всевозможные клетки внутри квадрата
                for(int deltaRow = 0; deltaRow < 3; deltaRow++) {
                    for(int deltaColumn = 0; deltaColumn < 3; deltaColumn++) {
                        int row = i + deltaRow;
                        int column = j + deltaColumn;
                        int number = grid.get(getIndex(row, column));
                        if(number != 0) {
                            if(numbersInSquare.contains(number)) {
                                return false;
                            }
                            numbersInSquare.add(number);
                        }
                    }
                }
            }
        }
        return true;
    }

    //Даёт нам значение, которое лежит в позиции row, column
    public int get(int row, int column) {
        int index = getIndex(row, column);
        return grid.get(index);
    }

    //Устанавливает в позиции row, column значение value
    public void set(int row, int column, int value) {
        int index = getIndex(row, column);
        grid.set(index, value);
    }

    public void printState() {
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                System.out.print(get(i, j) + " ");
            }
            System.out.println();
        }
    }

    public void writeToFile(String fileName) {

        try (FileWriter result = new FileWriter(fileName)) {

            int j = 0;
            for (int i = 0; i < grid.size(); i++) {
                j++;
                String otvet = Integer.toString(grid.get(i));
                result.write(otvet + " ");
                if (j % 9 == 0)
                    result.write("\n");
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
