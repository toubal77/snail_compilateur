package miniProjet;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.*;


public class snail extends javax.swing.JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    private javax.swing.JTextArea Result;
    private javax.swing.JLabel jLabel2;

    boolean checkSnlStart = false;
    boolean checkSnlClose = false;
    boolean checkStart = false;
    boolean checkFinish = false;
    List<String> allIden = new ArrayList<String>();
    List<String> idenInt = new ArrayList<String>();
    List<String> idenFloat = new ArrayList<String>();

    public boolean isIdent(String str) {
        char e = str.charAt(0);
        if (!Character.isLetter(e)) {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
                if (c != '_') {
                    return false;
                } else {
                    if (i + 1 >= str.length()) {
                        return false;
                    } else {
                        i++;
                        c = str.charAt(i);
                        if (c == '_') {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    static boolean isint(String str) {
        // byte[] bytes = str.getBytes();
        // for (byte aByte : bytes) {
        //     if (!Character.isDigit((char) aByte))
        //         return false;
        // }
        // return true;
        String regex = "[0-9]+";
        return Pattern.matches(regex, str);

    }

    boolean isSpace(String str) {
        try {
            //  Float.parseFloat(str);
            String regex = " +";
            return Pattern.matches(regex, str);
        } catch (NumberFormatException e) {
            return false;
        }
        //  return true;
    }
    boolean isFloat(String str) {
        try {
            //  Float.parseFloat(str);
            String regex = "[0-9]+.[0-9]+";
            return Pattern.matches(regex, str);
        } catch (NumberFormatException e) {
            return false;
        }
        //  return true;
    }
    boolean isString(String str) {
        try {
            //  Float.parseFloat(str);
            String regex = "[a-zA-Z0-9\\'-]+[a-zA-Z0-9]+";
            return Pattern.matches(regex, str);
        } catch (NumberFormatException e) {
            return false;
        }

    }

    /**
     * Creates new form snail
     */
    public snail() {


        initComponents();
        setResizable(false);
        setLocation(250, 100);
    }

    String chemin;

    boolean symbole(String str) {
        boolean bool;

        bool = "<".equals(str) || ">".equals(str) || "<=".equals(str) || ">=".equals(str) || "==".equals(str);

        return bool;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLabel jLabel3 = new JLabel();
        JPanel jPanel1 = new JPanel();
        jLabel2 = new javax.swing.JLabel();
        // Variables declaration - do not modify//GEN-BEGIN:variables
        JButton charger_un_fichier = new JButton();
        JButton syntaxe = new JButton();
        JButton semantique = new JButton();
        JButton lexique = new JButton();
        JScrollPane jScrollPane1 = new JScrollPane();
        Result = new javax.swing.JTextArea();
        JLabel jLabel1 = new JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel2.setText("nom de fichier");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(180, 50, 190, 20);

        charger_un_fichier.setFont(new java.awt.Font("Tahoma", Font.PLAIN, 10)); // NOI18N
        charger_un_fichier.setText("CHARGER LE FICHIER");
        charger_un_fichier.addActionListener(this::Charger_un_fichierActionPerformed);
        jPanel1.add(charger_un_fichier);
        charger_un_fichier.setBounds(10, 40, 150, 40);

        syntaxe.setText("A.SYNTAXIQUE");
        syntaxe.addActionListener(this::SyntaxeActionPerformed);
        jPanel1.add(syntaxe);
        syntaxe.setBounds(10, 230, 150, 40);

        semantique.setText("A.SEMENTIQUE");
        semantique.addActionListener(this::SemantiqueActionPerformed);
        jPanel1.add(semantique);
        semantique.setBounds(10, 320, 150, 40);

        lexique.setText("A.LEXICAL");
        lexique.addActionListener(this::LexiqueActionPerformed);
        jPanel1.add(lexique);
        lexique.setBounds(10, 140, 150, 40);

        Result.setColumns(20);
        Result.setRows(5);
        Result.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(Result);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(416, 6, 380, 470);

        jLabel1.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/miniprojet/snailm.png")))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(-6, -6, 830, 510);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE));

        pack();
    }

    private void Charger_un_fichierActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        //String path;
        chemin = f.getAbsolutePath();
        jLabel2.setText(chemin);
        //try {

        //    BufferedReader br = new BufferedReader(new FileReader(f));
        //    String str = br.readLine();

        //} catch (IOException ex) {
        //    Logger.getLogger(snail.class.getName()).log(Level.SEVERE, null, ex);
        //}

    }
    private void LexiqueActionPerformed(java.awt.event.ActionEvent evt) {
        this.idenInt.clear();
        this.idenFloat.clear();
        Result.setText("");
        Path path = Paths.get(chemin);
        try {

            List<String> lignes = Files.readAllLines(path, StandardCharsets.ISO_8859_1);

            for (String ligne : lignes) {
                ligne = ligne.replace("<", " < ");
                ligne = ligne.replace(" , ", ",");
                ligne = ligne.replace(", ", ",");
                ligne = ligne.replace(" ,", ",");
                ligne = ligne.replace("=", " = ");
                ligne = ligne.replace("=", " = ");
                String[] mot = ligne.split("( )|(,)");

                for (int i = 0; i < mot.length; i++) {
                    switch (mot[i]) {
                        case "Snl_Start":
                            checkSnlStart = true;
                            Result.append("Snl_Start:Mot réservé début du programme\n");
                            break;
                        case "Snl_Int":
                            Result.append("Snl_Int    :Mot réservé pour déclaration d'un entier\n");
                            break;
                        case "SnlSt":
                            Result.append("SnlSt    :Mot réservé pour déclaration d'une chaîne de caractère\n");
                            break;
                        case "Snl_Real":
                            Result.append("Snl_Real    :Mot réservé pour déclaration d'un réel\n");
                            break;
                        case "Snl_Close":
                            checkSnlClose = true;
                            Result.append("Snl_Close   :Mot réservé fin du programme\n");
                            break;
                        case "Get":
                            Result.append(
                                    "Get      :Mot réservé pour l'affectation d'une valeur de variable a une autre\n");
                            i++;
                            break;
                        case "Set":
                            Result.append("Set     :Mot réservé pour l'affectation d'une valeur a une variable\n");
                            i++;
                            break;
                        case "If":
                            Result.append("If      :Mot réservé pour une condition\n");
                            i++;
                            break;
                        case "Else":
                            Result.append("Else   :Sinon\n");
                            break;
                        case "Start":
                            checkStart = true;
                            Result.append("Start  :Début d'un bloc\n");
                            break;
                        case "Finish":
                            checkFinish = true;
                            Result.append("Finish   :Fin d'un bloc\n");
                            break;
                        case "%.":
                            Result.append("%.  : reserver pour fin ligne.\n");
                            break;
                        case "%..":
                            Result.append("%..  : reserver pour debut d'un commenteur.\n");
                            break;
                        case "%":
                            Result.append("%   : parenthèse \n");
                            break;
                        case "Snl_Put":
                            Result.append("Snl_Put   :Instruction d'affichage\n");

                            break;
                        default:

                            if (snail.isint(mot[i])) {
                                Result.append(mot[i] + "  :entier\n");
                            } else if (this.isFloat(mot[i])) {
                                Result.append(mot[i] + "  :réel\n");
                            }else if (this.isString(mot[i])) {
                                Result.append(mot[i] + "  :chaîne de caractère\n");
                            } else if (this.symbole(mot[i])) {
                                Result.append(mot[i] + "  :symbole \n");
                            }else if (this.isIdent(mot[i]) || !this.isIdent(mot[i])) {
                                Result.append(mot[i] + "  :identificateur\n");
                            }

                            //else {
                            //    Result.append(mot[i] + ":erreur\n");
                            //}

                    }

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(snail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void SyntaxeActionPerformed(java.awt.event.ActionEvent evt) {
        Result.setText("");
        Path path = Paths.get(chemin);
        try {

            List<String> lignes = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
            if(!checkSnlStart){
                Result.append("rak nasi Snl Start ya wahd lahmar\n");
            }
            if(!checkSnlClose){
                Result.append("rak nasi Snl Close ya wahd lahmar\n");
            }
            if(checkStart && (checkFinish == false)){
                Result.append("w bloc tabdah b Start w chkon adi ykamlah b finish nanak par exemple ? \n");
            }
            if(checkFinish && (checkStart == false)){
                Result.append("sahbi baghi nsa9sik min tkamal haja sama surement bditha aya dir tcho Start \n");
            }
            for (String ligne : lignes) {
                ligne = ligne.replace("<", " < ");
                ligne = ligne.replace(" , ", ",");
                ligne = ligne.replace(", ", ",");
                ligne = ligne.replace(" ,", ",");
                ligne = ligne.replace("=", " = ");
                ligne = ligne.replace("=", " = ");
                String[] mot = ligne.split("( )|(,)");
                if("%".equals(mot[0])||"%.".equals(mot[0])){
                    Result.append("roh t3alam taktab commentaire ki syadak\n");
                }
                for (int i = 0; i < mot.length; i++) {
                    switch(mot[i]){
                        case "Snl_Start":
//                            Result.append(ligne + "    :Mot réservé début du programme\n");
                            continue;
                        case "Snl_Int":
                            i++;
                            List<String> variable = new ArrayList<>();
                            while(i < mot.length){
                                if(this.isIdent(mot[i])){
                                    variable.add(mot[i]);
                                    allIden.add(mot[i]);
                                }
                                i++;
                            }
                            //while (this.isIdent(mot[i])) {
                            //    variable.add(mot[i]);
                            //    i++;
                            //}
                            idenInt = variable;
                            System.out.println("howa madahli int");
                            System.out.println(idenInt);
                            System.out.println("adi ga3 f int");
                            System.out.println(allIden);

                            if (this.isIdent(mot[i - 1])) {
                                if (mot[i].equals("%.")) {
                                    //    Result.append(ligne + " :Déclaration de " + variable.size() + " entiers\n");
                                }
                            } else {
                                Result.append(mot[i - 1] + " :ne respecte pas la syntaxe d'un identificateur\n");
                            }
                            continue;
                        case "Set":
                            i++;
                            if (this.isIdent(mot[i]) && (snail.isint(mot[i + 1]))) {
                                // if(!idenInt.contains(mot[i])){
                                //     Result.append(mot[i] + " n'ai pas declare\n");
                                // }else{
                                if(allIden.contains(mot[i])) {
                                    if (!idenInt.contains(mot[i])) {
                                        Result.append(mot[i] + "    :tadi i real hada makan ya wahd lahmar \n");
                                    }
                                }else{
                                    Result.append(mot[i] + " n'ai pas declare\n");
                                }                              //  }
                                //  Result.append(ligne + "    :Affectation de la valeur " + mot[i + 1] + " a " + mot[i] + "\n");
                            } else {

                                if (this.isIdent(mot[i]) && !allIden.contains(mot[i])) {
                                    Result.append(mot[i] + " n'ai pas declare\n");
                                    //    Result.append(mot[i] + "rani f int => ne respecte pas la syntaxe d'un identificateur\n");
                                }
                            }

                            if (this.isIdent(mot[i]) && (this.isFloat(mot[i + 1]))) {
                                // if(!idenInt.contains(mot[i])){
                                //     Result.append(mot[i] + " n'ai pas declare\n");
                                // }else{
                                if(allIden.contains(mot[i])) {
                                    if (!idenFloat.contains(mot[i])) {
                                        Result.append(mot[i] + "    :tadi i int hada makan ya wahd lahmar \n");
                                    }
                                }else{
                                    Result.append(mot[i] + " n'ai pas declare\n");
                                }                              //  }
                                //  Result.append(ligne + "    :Affectation de la valeur " + mot[i + 1] + " a " + mot[i] + "\n");
                            } else {

                                if (this.isIdent(mot[i]) && !allIden.contains(mot[i])) {
                                    Result.append(mot[i] + " n'ai pas declare\n");
                                    //    Result.append(mot[i] + "rani f int => ne respecte pas la syntaxe d'un identificateur\n");
                                }
                            }

                            continue;
                        case "Snl_Real":
                            i++;
                            List<String> real = new ArrayList<>();
                            while(i < mot.length){
                                if(this.isIdent(mot[i])){
                                    real.add(mot[i]);
                                    allIden.add(mot[i]);
                                }
                                i++;
                            }
                            //while (this.isIdent(mot[i])) {
                            //    real.add(mot[i]);
                            //    i++;
                            //}
                            idenFloat = real;
                            System.out.println("howa madahli real");
                            System.out.println(idenFloat);
                            System.out.println("adi ga3 f real");
                            System.out.println(allIden);
                            if (this.isIdent(mot[i - 1])) {
                                if (mot[i].equals("%.")) {
                                    //    Result.append(ligne + "     :Déclaration de " + real.size() + " reel\n");
                                }
                            }
                            // else {
                            //    Result.append(ligne + " erreur\n");
                            //}
                            continue;
                        case "if":
                            i++;
                            if (isIdent(mot[i])) {
                                //                    Result.append(ligne + "   :condition\n ");
                            } else {
                                //          Result.append(ligne + " erreur\n");
                            }
                            continue;
                        case "Else":
                            //    Result.append(ligne + "  :sinon \n");
                            continue;
                        case "Start":
                            // Result.append(ligne + "   :debut d'un block\n");
                            continue;
                        case "Get":
                            i++;
                            if (this.isIdent(mot[i]) && "from".equals(mot[i + 1]) && this.isIdent(mot[i + 2])) {
                                if (!allIden.contains(mot[i]) ) {
                                         Result.append(mot[i] + " n'ai pas declare\n");
                                }
                                if (!allIden.contains(mot[i+2]) ) {
                                    Result.append(mot[i] + " n'ai pas declare\n");
                                }
                                if (idenFloat.contains(mot[i]) && idenInt.contains(mot[i+2])) {
                                    Result.append(mot[i] + " raha real matnajamch tadi int\n");
                                }
                                if (idenInt.contains(mot[i]) && idenFloat.contains(mot[i+2])) {
                                    Result.append(mot[i] + " raha int matnajamch tadi real\n");
                                }
                            } else {
                                if(!"from".equals(mot[i + 1])){
                                     Result.append("get variable from variable ya wahd lahmar\n");
                                }
                                if (!this.isIdent(mot[i])) {
                                    Result.append(mot[i] + " ne respecte pas la syntaxe\n");
                                }else{
                                    if (!allIden.contains(mot[i]) ) {
                                        Result.append(mot[i] + " n'ai pas declare\n");
                                    }
                                }
                                if (!this.isIdent(mot[i+2])) {
                                    Result.append(mot[i+2] + " ne respecte pas la syntaxe\n");
                                }else{
                                    if (!allIden.contains(mot[i+2]) ) {
                                        Result.append(mot[i+2] + " n'ai pas declare\n");
                                    }
                                }
                            }

                            continue;
                        case "Finish":
                            // Result.append(ligne + "  :fin d'un block\n");
                            continue;
                        case "Snl_Put":
                            i++;

                            if (this.isIdent(mot[i])) {
                                if(!allIden.contains(mot[i])) {
                                    while (i < mot.length) {
                                        i++;
                                    }
                                    if ("\"".equals(mot[i - 2])) {
                                        Result.append("nta tkamal katba bsh matabdach b \"\n");
                                    }
                                    if (!"%.".equals(mot[i - 1])){
                                        Result.append("fin de ligne chkon ydirha ya kharaj lawla w talya ta3ak\n");
                                    }
                                    if (!"%.".equals(mot[i - 1]) && !"\"".equals(mot[i - 2])){
                                        Result.append(mot[i-2] + " n'ai pas declare\n");
                                    }
                                }
                            } else {

                                if(!"\"".equals(mot[i]))
                                Result.append(mot[i] + " syntaxe ya lahbib\n");

                                while(i<mot.length){
                                    i++;
                                }
                                if(!"\"".equals(mot[i-2]))
                                    Result.append(mot[i-2]+" wsh chikh min tabda katba kamalha b \"\n");
                                if(!"%.".equals(mot[i-1]))
                                    Result.append(mot[i-1]+" fin de ligne chkon ydirha ya kharaj\n");

                            }

                            continue;
                        case "%..":
                            //        Result.append(ligne + "     :ligne reserver pour comenteur\n");
                            continue;
                        case "Snl_Close":
                            //  Result.append(ligne + "       :fin programme\n ");
                            break;

                    }
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(snail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SemantiqueActionPerformed(java.awt.event.ActionEvent evt) {
        Result.setText("");
        Path path = Paths.get(chemin);
        try {
            List<String> lignes = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
            for (String ligne : lignes) {
                ligne = ligne.replace("<", " < ");
                ligne = ligne.replace(" , ", ",");
                ligne = ligne.replace(", ", ",");
                ligne = ligne.replace(" ,", ",");
                ligne = ligne.replace("=", " = ");
                ligne = ligne.replace("=", " = ");
                String[] mot = ligne.split("( )|(,)");
                for (int i = 0; i < mot.length; i++) {
                    switch(mot[i]) {
                        case "Snl_Start":
                            Result.append("main(){\n");
                            continue;
                        case "Snl_Int":
                            i++;
                            List<String> sint = new ArrayList<>();
                            while (this.isIdent(mot[i])) {
                                sint.add(mot[i]);
                                i++;
                            }
                            if (this.isIdent(mot[i - 1])) {
                                if (mot[i].equals("%.")) {
                                    Result.append("int ");
                                    for (int j = 0; j < sint.size(); j++) {
                                        if (j == sint.size() - 1) {
                                            Result.append(sint.get(j));
                                        } else {
                                            Result.append(sint.get(j) + ",");
                                        }
                                    }
                                    Result.append(";\n");
                                }
                            }
                            break;
                        case "Set":
                            i++;
                            if (this.isIdent(mot[i])
                                    && (snail.isint(mot[i + 1]) || this.isFloat(mot[i + 1]))) {
                                Result.append(mot[i] + " = " + mot[i + 1] + ";\n");
                            }
                            continue;
                        case "Snl_Real":
                            i++;
                            List<String> sreal = new ArrayList<>();
                            while (this.isIdent(mot[i])) {
                                sreal.add(mot[i]);
                                i++;
                            }
                            if (this.isIdent(mot[i - 1])) {
                                if (mot[i].equals("%.")) {
                                    Result.append("float ");
                                    for (int j = 0; j < sreal.size(); j++) {
                                        if (j == sreal.size() - 1) {
                                            Result.append(sreal.get(j));
                                        } else {
                                            Result.append(sreal.get(j) + ",");
                                        }
                                    }
                                    Result.append(";\n");
                                }
                            }
                            continue;
                        case "Start":
                            Result.append("{\n");
                            continue;
                        case "Get":
                            i++;
                            if (this.isIdent(mot[i]) && "from".equals(mot[i + 1])
                                    && this.isIdent(mot[i + 2])) {
                                Result.append(mot[i] + " = " + mot[i + 2] + ";\n");
                            }
                            continue;
                        case "Finish":
                            Result.append("}\n");
                            continue;
                        case "Snl_Put":
                            i++;
                            if (this.isIdent(mot[i])) {
                                Result.append("System.out.println(" + mot[i] + ");\n");
                            } else {
                                Result.append("System.out.println(" + mot[i] + ");\n");
                            }

                            continue;
                        case "%..":
                            i++;
                            Result.append("//" + Arrays.toString(ligne.split("%..")) + "\n");
                            continue;

                        case "If":
                            i++;
                            Result.append("if(" + mot[i + 1] + mot[i + 2] + mot[i + 3] + ")\n");
                            continue;
                        case "Else":
                            Result.append("else\n");
                            continue;
                        case "Snl_Close":
                            Result.append("} ");
                            break;

                    }
                }

            }
        }

        catch (IOException ex) {
            Logger.getLogger(snail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(snail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new snail().setVisible(true));
    }


}