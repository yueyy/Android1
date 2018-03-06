/* 
2.public是公共的，private是私有，如果main函数用public的话，
3.out表明是输出，in是输入，如果没有out的话没法识别吧

*/

public class DrawingTablet {

    int counter;
    
    public class DrawingTablets implements Countable
    {
        int counter;
        DrawingTablet d = new DrawingTablet();
        @Override
        public void count()
        {
            counter = counter + 1;
        }
    }

    public static void main(String[] args) {

        DrawingTablet drawingTablet = new DrawingTablet();
        Line line = new Line();
        line.Dr(drawingTablet);
        System.out.println(drawingTablet.counter);
    }
}


public class Line extends Graphics
{

    public void Dr(DrawingTablet d)
    {
        for (int i = 1; i <= 10; i++) {
            Line line = new Line();
            d.counter +=1;
        }
    }
}


public class Graphics {
    int width;
    int height;

    public void DrawingTablet(int width, int height) {
        Graphics Graphic = new Graphics();
        this.width = width;
        this.height = height;
    }
}


public interface Countable {
    void count();
}
