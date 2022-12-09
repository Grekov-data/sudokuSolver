import java.util.List;

//Этот класс будет решать схему судоку.
public class SudokuSolver {
    private Sudoku sudoku;
    private boolean solved;

    public SudokuSolver(Sudoku sudoku) {
        this.sudoku = sudoku;
        this.solved = false;
    }

    public void solve() {
        if(!sudoku.isValid()) {
            return;
        }

        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                int curValue = sudoku.get(i, j);
                if(curValue != 0)
                    continue;
                for(int value = 1; value <= 9; ++value) {
                    sudoku.set(i, j, value);
                    solve();
                }
            }
        }

    }




}
