package hu.petrik.Aukcioprojekt;

public class Main {
    public static void main(String[] args){
        Festmeny festmeny = new Festmeny("Uj festmeny","Kiki","futurisztikus");
        for (int i = 0; i < 100; i++) {
            festmeny.licit();
            System.out.println(festmeny.getLegmagasabbLicit());
        }
    }
}
