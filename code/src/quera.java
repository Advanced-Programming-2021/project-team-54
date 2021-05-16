import java.util.ArrayList;

public class quera {
    private int a;
    public static boolean l(){
        Card card = new Card("sa","sa",1);
        Card card1 =new Card("aa","aa",2);
        ArrayList<Card> n = new ArrayList<>();
        n.add(card1);
        n.add(card);
        quera q = new quera();
        ArrayList<quera> b = new ArrayList<>();
        b.add(q);
        for (quera c:b) {
            c.a = 12;
            if(c==q)
                return true;

        }
        return  false;

    }

    public static void main(String[] args) {
        quera q = new quera();
        ArrayList<quera> o1 = new ArrayList<>();
        ArrayList<quera> o2 = new ArrayList<>();
        o1.add(q);
        o2.add(new quera());
        System.out.println(l());
    }
}