package sample;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Drawing {

    public static void draw(boolean[] currentTimeStep, Canvas canvas, int iteration) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double squareSize = 3;
        int currentColumn = 0;
        for (boolean oneCell : currentTimeStep) {
            if (oneCell) {
                double x = squareSize * currentColumn + 0.2;
                double y = squareSize * iteration + 0.2;
                gc.fillRect(x, y, squareSize, squareSize);
            }
            currentColumn++;
        }
    }
}
