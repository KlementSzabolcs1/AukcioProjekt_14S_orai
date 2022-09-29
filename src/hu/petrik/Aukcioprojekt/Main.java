package hu.petrik.Aukcioprojekt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Festmeny> festmenyek = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        festmenyekHozzaadasa();
        try{
            festmenyekFelveteleKonzolal();
        } catch (RuntimeException e){
            System.out.println("A darabszám csak a természetes számlehet");
        }
        String fajlNev = "festmenyek.csv";
        try {
            festmenyekFelveteleSzovegesAllomanybol(fajlNev);
        } catch (FileNotFoundException e) {
            System.out.println("Hiba! nem talalhato az alabbi fajl");
        } catch (IOException e){
            System.out.println("Ismeretlen Hiba történt a forrás fájl ovasása után");
            System.out.println(e.getMessage());
        }
        veletlenszeruLicit();
        try {
            konzolosLicitalas();
        }catch (NumberFormatException e){
            System.out.println("Nem sorszámot adott meg");
        }


        for (Festmeny festmeny : festmenyek) {


        }
    }

    private static void konzolosLicitalas() {
        System.out.println("Kérem adja meg a festmény sorszámát (kilépéshez adjon 0-t :)");
        int sorszam = -1;
                while (sorszam != 0){
                    sorszam = Integer.parseInt(sc.nextLine());
                }
        if (sorszam < 0){
            throw new NumberFormatException();
        }
        if (sorszam > festmenyek.size()){
            System.out.println("A listában nincs ennyi elem., a lista elemszáma: " + festmenyek.size());
        } else {
            Festmeny festmeny = festmenyek.get(sorszam -1);
            if(festmeny.isElkelt()){
                System.out.println("Ez a festmény elkellt");
            }else {
                System.out.println("Kérem adja meg, hogy milyen mértékkel szeretne licitálni (10-100%): ");
                String mertek = sc.nextLine();
                if (mertek.isEmpty()){
                    festmeny.licit();
                }else {
                    festmeny.licit(Integer.parseInt(mertek));
                }
            }
        }
    }

    private static void veletlenszeruLicit() {
        for (int i = 0; i < 20; i++) {
            int index =(int) (Math.random() * festmenyek.size());
            festmenyek.get(index).licit();
        }
    }

    private static void festmenyekFelveteleSzovegesAllomanybol(String fajlNev) throws IOException {
        FileReader fr = new FileReader(fajlNev);
        BufferedReader br = new BufferedReader(fr);
            String sor = br.readLine();
            while (sor != null && !sor.isEmpty()){
                String[] adatok = sor.split(";");
                Festmeny festmeny = new Festmeny(adatok[0], adatok[1], adatok[2]);
                festmenyek.add(festmeny);
                sor = br.readLine();
            }
        br.close();
        fr.close();
    }

    private static void festmenyekFelveteleKonzolal(){
        System.out.println("Kérem adjon meg, hogy hány festményt szeretne felvenni:");
        int db = Integer.parseInt(sc.nextLine());
        if(db < 0){
            throw new NumberFormatException("");
        }
        for (int i = 0; i < db ; i++){
            System.out.printf("Kérem adjon meg a(z) %d fetmény címét: \n",(i + 1));
            String cim = sc.nextLine();
            System.out.printf("Kérem adjon meg a(z) %d fetmény festőjét: \n",(i + 1));
            String festo = sc.nextLine();
            System.out.printf("Kérem adjon meg a(z) %d fetmény stilusat: \n",(i + 1));
            String stilus = sc.nextLine();
            Festmeny festmeny = new Festmeny(cim,festo,stilus);
            festmenyek.add(festmeny);
        }
    }

    private static void festmenyekHozzaadasa(){
        Festmeny festmeny = new Festmeny("Uj festmeny","Kiki","futurisztikus");
        Festmeny festmeny2 = new Festmeny("Masik festmeny","Kiki","futurisztikus");
        festmenyek.add(festmeny);
        festmenyek.add(festmeny2);
    }
}



