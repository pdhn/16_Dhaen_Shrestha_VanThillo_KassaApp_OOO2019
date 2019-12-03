package database;

import model.Artikel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate {

    private HashMap<Integer, Artikel> artikelen;
    private static final String FILE_PATH = "C:\\Users\\pimdh\\Documents\\Semester_1_2020\\1_OO Ontwerpen (6)\\Group Werk\\KassaSysteem\\src\\bestanden\\artikel.txt";

    public ArtikelTekstLoadSave() {
        artikelen = new HashMap();
    }

    @Override
    public ArrayList<Artikel> load(String tekstBestand) {
        String bestandAlsTekst;

        try {
            Scanner scannerForLines = new Scanner(new File(tekstBestand));

            while (scannerForLines.hasNextLine()) {
                String currentLine;
                try { // End of line exception handling
                    currentLine = scannerForLines.next();
                } catch (NoSuchElementException e) {
                    scannerForLines.close();
                    return new ArrayList<Artikel>(artikelen.values());
                }

                Scanner scannerForLine = new Scanner(currentLine);
                scannerForLine.useDelimiter(",");

                int artikelCode = scannerForLine.nextInt();
                String omschrijving = scannerForLine.next();
                String artikelGroep = scannerForLine.next();
                double prijs = scannerForLine.nextDouble();
                int voorraad = scannerForLine.nextInt();

                Artikel artikel = new Artikel(artikelCode, omschrijving, artikelGroep, prijs, voorraad);

                artikelen.put(artikelCode, artikel);

                scannerForLine.close();
            }

            scannerForLines.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return new ArrayList<Artikel>(artikelen.values());
    }

    @Override
    public void save(ArrayList<Artikel> bestanden) {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(FILE_PATH), "utf-8"))) {

            for (Artikel a : bestanden) {
                writer.write(a.getArtikelCode() + "," + a.getOmschrijving() + ","
                        + a.getArtikelGroep() + "," + a.getPrijs() + "," + a.getVoorraad() + "\n");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
