package center.rodrigo.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;

public class Impressora {

    private Doc doc;
    private DocPrintJob job;
    private PrintService ps;
    private PrintRequestAttributeSet pras;

    public void run() {
        try {
            FileInputStream fin = new FileInputStream("homer.png");
            imprimir(fin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void imprimir(FileInputStream fin) {

        try {
            pras = new HashPrintRequestAttributeSet();
            pras.add(new Copies(1));
            pras.add(MediaSizeName.ISO_A4);
            pras.add(OrientationRequested.PORTRAIT);

            ps = PrintServiceLookup.lookupDefaultPrintService();
            System.out.println("Impressora padrão:\n" + ps.getName());

            doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.PNG, null);
            job = ps.createPrintJob();
            job.print(doc, pras);
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
