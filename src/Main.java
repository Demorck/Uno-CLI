import uno.jeu.Uno;
import java.util.Objects;

public class Main
{
    public static void main(String[] args)
    {

        Uno u;
        if (args.length > 0 && Objects.equals(args[0], "--debug"))
        {
            u = new Uno(true);
        }
        else
        {
            u = new Uno();
        }


        int nbBot = u.getDlc().promptNumberOfBots();
        u.initialize(nbBot);

        u.getDlc().react();
        u.getDlc().closeScanner();
    }
}
