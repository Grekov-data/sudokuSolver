import java.util.List;

//Этот класс будет решать схему судоку.
public class SudokuSolver {
    private Sudoku sudoku;
    private boolean solved;

    private static int ITERATIONS = 0;

    public SudokuSolver(Sudoku sudoku) {
        this.sudoku = sudoku;
        this.solved = false;
    }

    public void solve() {
        ++ITERATIONS;
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                //Здесь мы ищем первую пустую клетку.
                int curValue = sudoku.get(i, j);
                if(curValue != 0)
                    continue;
                for(int value = 1; value <= 9; ++value) {
                    sudoku.set(i, j, value);
                    if(sudoku.isValid()) {
                        solve();
                    }
                    sudoku.set(i, j, 0);
                }
                return;     //Мы попробовали поставить все цифры в данную клетку,
                // больше ничего поставить не можем, дальше не идем, идём на шаг назад.
            }
        }

        //Все клетки заняты и судоку валидное.
        sudoku.printState();
        System.out.println("Iterations: " + ITERATIONS);
    }
}
