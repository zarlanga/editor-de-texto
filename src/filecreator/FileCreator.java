/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filecreator;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tyt
 */
public class FileCreator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //separarHexagramas1();
        //insertarSaltosDeLinea();
       // borrarNumeros();
       borrarEspaciosVacios();
    }
    
    public static void borrarEspaciosVacios() {
        char[] pepe= new char[1];
        for (int i = 1; i <=64; i++) {
            for (int j = 0; j < 7; j++) {

                FileReader fr = null;
                try {
                    
                    String nombreFile = "Hex";
                    nombreFile += j != 0 ? i + "lin" + j : "a" + i;
                    nombreFile += ".txt";
                    File file = new File(nombreFile);
                    //System.out.println(nombreFile);
                    //String comparador = "";
                    String testo = "";
                    
                    fr = new FileReader(file);
                    char[] a = new char[5000];
                    fr.read(a);
                    fr.close();
                    
                    int k = 0;
                    while (a[k] != pepe[0]) {
                       testo+=a[k];
                       k++;
                    }
                    System.out.println(k);
                    /*for (int k = 0; k < a.length-2; k++) {
                        String comparador = "";
                        comparador +=a[k];
                        
                        
                       testo+=a[k];
                    }*/
                   FileWriter writer = new FileWriter(file);
                writer.write(testo);
                writer.flush();
                writer.close();
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(FileCreator.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        fr.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FileCreator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
    }

    public static void borrarNumeros() {
        
        int cont = 9;
        for (int i = 1; i <= 64; i++) {
            for (int j = 0; j < 7; j++) {

                FileReader fr = null;
                try {
                    
                    String nombreFile = "Hex";
                    nombreFile += j != 0 ? i + "lin" + j : "a" + i;
                    nombreFile += ".txt";
                    File file = new File(nombreFile);
                    //System.out.println(nombreFile);
                    //String comparador = "";
                    String testo = "";
                    
                    fr = new FileReader(file);
                    char[] a = new char[5000];
                    fr.read(a);
                    fr.close();
                    
                    
                    for (int k = 0; k < a.length-2; k++) {
                        String comparador = "";
                        comparador +=a[k];
                        if (cont >=10) comparador += a[k+1];
                        if (cont >=100) comparador += a[k+2];
                        
                        if (comparador.equals(cont+"")) {
                            
                            System.out.println(nombreFile);
                            System.out.println(cont);
                            //System.out.println(comparador.length());
                            cont++;
                            k+= comparador.length();
                        }
                       testo+=a[k];
                    }
                   FileWriter writer = new FileWriter(file);
                writer.write(testo);
                writer.flush();
                writer.close();
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(FileCreator.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        fr.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FileCreator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        System.out.println(cont);
    }

    public static void insertarSaltosDeLinea() {

        for (int i = 1; i <= 64; i++) {

            File file = new File("hexa" + i + ".txt");
            System.out.println("hexa" + i + ".txt");
            String testo = "";
            try {
                FileReader fr = new FileReader(file);
                char[] a = new char[5000];
                fr.read(a);
                String comparador = "";
                int cont = 0;
                boolean banderaUnd = false;
                for (int j = 0; j < a.length - 1; j++) {

                    comparador = "";
                    comparador += a[j];
                    comparador += a[j + 1];
                    if ((comparador.equals("LA") || comparador.equals("EL") && j > 50)) {
                        cont++;
                        testo += "<br/><br/>";
                        System.out.println("entro");
                        if (cont >= 3) {
                            System.out.println("encontro los tres del hexa" + i);

                        }
                    }

                    testo += a[j];
                }
                fr.close();
                FileWriter writer = new FileWriter(file);
                writer.write(testo);
                writer.flush();
                writer.close();

            } catch (IOException ex) {
                Logger.getLogger(FileCreator.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static void separarHexagramas1() {
        File file = new File("test1.txt");
        String testo = "";
        int hexa = 2;
        String compHex = "";
        try {
            FileReader fr = new FileReader(file);
            char[] a = new char[320000];
            fr.read(a);
            boolean linea = true;
            int nroLinea = 0;
            String comparador;
            compHex = hexa + ".-";
            for (int i = 0; i < a.length - 3; i++) {
                comparador = "";

                for (int j = 0; j < 3; j++) {
                    comparador += a[i + j];
                }

                if (comparador.equals("Nue") || comparador.equals("Sei") || comparador.equals(compHex)) {

                    String nombreFile = "Hex";

                    nombreFile += nroLinea != 0 ? (hexa - 1) + "lin" + nroLinea : "a" + (hexa - 1);

                    nombreFile += ".txt";
                    System.out.println(nombreFile);
                    File fileT = new File(nombreFile);
                    fileT.createNewFile();
                    FileWriter writer = new FileWriter(fileT);
                    writer.write(testo);
                    writer.flush();
                    writer.close();
                    testo = "";
                    nroLinea++;
                    if (nroLinea == 7) {
                        nroLinea = 0;
                        hexa++;
                        compHex = hexa < 10 ? hexa + ".-" : hexa + ".";
                    }
                }

                testo += a[i];
            }

            String nombreFile = "Hex";

            nombreFile += nroLinea != 0 ? (hexa - 1) + "lin" + nroLinea : "a" + (hexa - 1);

            nombreFile += ".txt";
            System.out.println(nombreFile);
            File fileT = new File(nombreFile);
            fileT.createNewFile();
            FileWriter writer = new FileWriter(fileT);
            writer.write(testo);
            writer.flush();
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(FileCreator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*public static void separarHexagramas() {
        
        //*****roto********
        File file = new File("test1.txt");
        try {
            FileReader fr = new FileReader(file);
            char[] a = new char[100000];
            fr.read(a);   // reads the content to the array
            String testo = "";
            int hexa = 1;
            String compHex = "";
            boolean linea = false;
            String lasLineas = "LAS LINEAS:";
            int cont = 0;
            for (int i = 0; i < a.length - 2; i++) {
                String comparador = "";
                compHex = hexa + ".";

                if (cont >=6) {
                    comparador += a[i];        //(String) a[i+1] + (String) a[i+2];
                    comparador += a[i + 1];

                    //(String) a[i+1] + (String) a[i+2];
                    if (hexa >= 10) {
                        comparador += a[i + 2];        //(String) a[i+1] + (String) a[i+2];
                      //System.out.println(cont + "///" + comparador + "///" + compHex);
                    }
                    
                } else if (linea) {
                    for (int j = 0; j < 4; j++) {
                        comparador += a[i+j];
                    }
                }
                
                else if (i > lasLineas.length() ) {
                    
                    
                        for (int j = lasLineas.length(); j > 0; j--) {
                        //for (int j = 0; j <lasLineas.length(); j++) {
                            comparador += a[i - j];

                        }
                        
                    
                    //System.out.println("test"+ comparador);

                }
                
                if(comparador.equals(lasLineas) || comparador.contains(compHex) || 
                        comparador.equals("Nuev") || comparador.equals("Seis")) {
                      
                    //cont++;
                    //System.out.println("entro" + cont);
                    String nombreFile = "Hexa" + (hexa )   ; 
                    nombreFile +=  linea ? ("lin" + cont):"";
                    nombreFile += ".txt";
                    
                    if (!linea || cont > 0) {
                    File fileT = new File(nombreFile);
                    fileT.createNewFile();
                    FileWriter writer = new FileWriter(fileT);
                    writer.write(testo);
                    writer.flush();
                    writer.close();
                    //System.out.println(testo);
                    }
                    testo = "";
                    System.out.println("hexa" + hexa +" cont:" +cont);
                    System.out.println(comparador);
                    if (linea) {
                        cont++;
                        if (cont == 7) {
                            cont=0;
                            linea=!linea;
                              //hexa++;
                              
                        }
                        if (cont == 6) hexa++;
                        
                    } else {
                        
                        linea = !linea;
                      
                    }
                    
                }
                
                testo += a[i];

            }


        } catch (IOException ex) {
            Logger.getLogger(FileCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
/*

315983 

import java.io.*;
public class FileRead {

   public static void main(String args[])throws IOException {
      File file = new File("Hello1.txt");
      
      // creates the file
      file.createNewFile();
      
      // creates a FileWriter Object
      FileWriter writer = new FileWriter(file); 
      
      // Writes the content to the file
      writer.write("This\n is\n an\n example\n"); 
      writer.flush();
      writer.close();

      // Creates a FileReader Object
      FileReader fr = new FileReader(file); 
      char [] a = new char[50];
      fr.read(a);   // reads the content to the array
      
      for(char c : a)
         System.out.print(c);   // prints the characters one by one
      fr.close();
   }
}*/
