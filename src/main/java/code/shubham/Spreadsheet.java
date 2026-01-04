package code.shubham;

public class Spreadsheet {

    int[][] V;
    public Spreadsheet(int rows) {
        V = new int[rows][26];
    }

    public void setCell(String cell, int value) {
        V[row(cell)][col(cell)] = value;
    }

    public void resetCell(String cell) {
        setCell(cell, 0);
    }

    public int getValue(String formula) {
        String[] a = formula.split("=");
        return V[row(a[0], 1)][col(a[0], 2)] + V[row(a[1])][col(a[1])];
    }

    int row(String cell, Integer... pos) {
        return Integer.valueOf(cell.substring(pos[0] == null ? 1 : pos[0]));
    }

    int col(String cell, Integer... pos) {
        return Integer.valueOf(cell.charAt(pos[0] == null ? 0 : pos[0]));
    }

    public static void main(String[] args) {

         // Your Spreadsheet object will be instantiated and called as such:
         Spreadsheet obj = new Spreadsheet(3);
         System.out.println(obj.getValue("=5+7"));
         obj.setCell("A1", 10);
         System.out.println(obj.getValue("=A1+6"));
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */