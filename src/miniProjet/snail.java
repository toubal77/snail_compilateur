package miniProjet;

import java.awt.*;
import java.awt.event.ActionEvent;
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
    boolean semantique = false;
    boolean syntaxique = false;
    boolean lexical = false;
    List<String> allIden = new ArrayList<String>();
    List<String> idenInt = new ArrayList<String>();
    List<String> idenFloat = new ArrayList<String>();
    functionss fun = new functionss();
    public snail() {
        loadUI();
    }

    String chemin;

    private void loadUI() {

        JLabel jLabel3 = new JLabel();
        JPanel jPanel1 = new JPanel();
        jLabel2 = new javax.swing.JLabel();
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
        jLabel2.setBounds(180, 30, 190, 20);

        charger_un_fichier.setFont(new java.awt.Font("Tahoma", Font.PLAIN, 10)); // NOI18N
        charger_un_fichier.setText("TELECHARGER LE FICHIER");
        charger_un_fichier.addActionListener(this::laodFichier);
        jPanel1.add(charger_un_fichier);
        charger_un_fichier.setBounds(10, 20, 150, 40);

        syntaxe.setText("Syntaxe");
        syntaxe.addActionListener(this::SyntaxeActionPerformed);
        jPanel1.add(syntaxe);
        syntaxe.setBounds(310, 70 , 150, 40);

        semantique.setText("Semantique");
        semantique.addActionListener(this::SemantiqueActionPerformed);
        jPanel1.add(semantique);
        semantique.setBounds(610, 70, 150, 40);

        lexique.setText("Lexique");
        lexique.addActionListener(this::LexiqueActionPerformed);
        jPanel1.add(lexique);
        lexique.setBounds(10, 70, 150, 40);

        Result.setColumns(20);
        Result.setRows(5);
        Result.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(Result);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 130, 775, 330);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE));

        pack();
        setResizable(false);
        setLocation(250, 100);
    }

    private void laodFichier(java.awt.event.ActionEvent evt) {
        checkSnlStart = false;
        checkSnlClose = false;
        checkStart = false;
        checkFinish = false;
        semantique = false;
        syntaxique = false;
        lexical = false;
        allIden.clear();
        idenInt.clear();
        idenFloat.clear();
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        chemin = f.getAbsolutePath();
        jLabel2.setText(chemin);

    }
    private void LexiqueActionPerformed(java.awt.event.ActionEvent evt) {
        this.idenInt.clear();
        this.idenFloat.clear();
        Result.setText("");
        Path path = Paths.get(chemin);
        try {
            lexical = true;
            List<String> lignes = Files.readAllLines(path, StandardCharsets.ISO_8859_1);

            for (String ligne : lignes) {

                ligne = ligne.replace(" , ", ",");
                ligne = ligne.replace(", ", ",");
                ligne = ligne.replace(" ,", ",");

                ligne = ligne.replace("\t","");
                String[] mot = ligne.split("( )|(,)");

                for (int i = 0; i < mot.length; i++) {
                    switch (mot[i]) {
                        case "Snl_Start":
                            if(!mot[0].equals("%..")){
                                checkSnlStart = true;
                                Result.append("Snl_Start : Mot r??serv?? d??but du programme\n");
                            }
                            break;
                        case "Snl_Int":
                            if(!mot[0].equals("%..")){
                                Result.append("Snl_Int : Mot r??serv?? pour d??claration d'un entier\n");
                            }
                            break;
                        case "SnlSt":
                            if(!mot[0].equals("%..")){
                                Result.append("SnlSt : Mot r??serv?? pour d??claration d'une cha??ne de caract??re\n");
                                i++;
                                String chaine_de_caractere = "";
                                chaine_de_caractere = chaine_de_caractere + mot[i + 1] + " ";
                                while (i < mot.length) {
                                    if (("\"".equals(mot[i]) == false) && ("%.".equals(mot[i]) == false)) {
                                        chaine_de_caractere = chaine_de_caractere + mot[i] + " ";
                                    }
                                    i++;
                                }
                                if("%.".equals(mot[i-1])){
                                    Result.append("%. : reserver pour fin ligne.\n");
                                }else if("%..".equals(mot[i-1])){
                                    Result.append("%.. : reserver pour debut d'un commenteur.\n");
                                }else if("%".equals(mot[i-1])){
                                    Result.append("% : parenth??se \n");
                                }
                                Result.append(chaine_de_caractere +" chaine de caractere\n");
                            }
                            break;
                        case "Snl_Real":
                            if(!mot[0].equals("%..")){
                                Result.append("Snl_Real : Mot r??serv?? pour d??claration d'un r??el\n");
                            }
                            break;
                        case "Snl_Close":
                            if(!mot[0].equals("%..")){
                                checkSnlClose = true;
                                Result.append("Snl_Close : Mot r??serv?? fin du programme\n");
                            }
                            break;
                        case "Get":
                            if(!mot[0].equals("%..")){
                                Result.append(
                                        "Get : Mot r??serv?? pour l'affectation d'une valeur de variable a une autre\n");
                            }
                            i++;
                            break;
                        case "Set":
                            if(!mot[0].equals("%..")){
                                Result.append("Set : Mot r??serv?? pour l'affectation d'une valeur a une variable\n");
                            }
                            i++;
                            break;
                        case "If":
                            if(!mot[0].equals("%..")){
                                Result.append("If : Mot r??serv?? pour une condition\n");
                            }
                            i++;
                            break;
                        case "Else":
                            if(!mot[0].equals("%..")) {
                                Result.append("Else : Sinon\n");
                            }
                            break;
                        case "Start":
                            if(!mot[0].equals("%..")){
                                checkStart = true;
                                Result.append("Start : D??but d'un bloc\n");
                            }
                            break;
                        case "Finish":
                            if(!mot[0].equals("%..")){
                                checkFinish = true;
                                Result.append("Finish : Fin d'un bloc\n");
                            }
                            break;
                        case "%.":
                            Result.append("%. : reserver pour fin ligne.\n");
                            break;
                        case "%..":
                            Result.append("%.. : reserver pour debut d'un commentaire.\n");
                            break;
                        case "%":
                            Result.append("% : parenth??se \n");
                            break;
                        case "Snl_Put":
                            if(!mot[0].equals("%..")){
                                Result.append("Snl_Put : Instruction d'affichage\n");
                            }
                            break;
                        default:

                            if (fun.isint(mot[i])) {
                                if(!mot[0].equals("%.."))
                                    Result.append(mot[i] + " : entier\n");
                            } else if (fun.isFloat(mot[i])) {
                                if(!mot[0].equals("%.."))
                                    Result.append(mot[i] + " : r??el\n");
                            }else if (fun.isString(mot[i])) {
                                if(!mot[0].equals("%.."))
                                    Result.append(mot[i] + " : cha??ne de caract??re\n");
                            } else if (fun.symbole(mot[i])) {
                                if(!mot[0].equals("%.."))
                                    Result.append(mot[i] + " : symbole \n");
                            }else if ("\"".equals(mot[i])) {
                                Result.append(mot[i] + " : debut ou fin de chaine de caractere\n");
                            }else if (fun.isSpace(mot[i])) {
                                Result.append(mot[i] + " : espace\n");
                            } else if (fun.isIdent(mot[i]) || !fun.isIdent(mot[i])) {
                                if(!mot[0].equals("%.."))
                                    Result.append(mot[i] + " : identificateur\n");
                            }

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
            if(lexical) {
                syntaxique = true;
                List<String> lignes = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
                if (!checkSnlStart) {
                    Result.append("Vous avez oublie Snl Start\n\n");
                }
                if (!checkSnlClose) {
                    Result.append("Vous avez oublie Snl Close\n\n");
                }
                if (checkStart && (checkFinish == false)) {
                    Result.append("Vous avez oublie Finish\n\n");
                }
                if (checkFinish && (checkStart == false)) {
                    Result.append("Vous avez oublie Start\n\n");
                }
                //      for (String ligne : lignes) {
                for (int j = 0; j < lignes.size(); j++) {
                    lignes.set(j, lignes.get(j).replace("<", " < "));
                    lignes.set(j, lignes.get(j).replace(">", " > "));
                    lignes.set(j, lignes.get(j).replace(" , ", ","));
                    lignes.set(j, lignes.get(j).replace(", ", ","));
                    lignes.set(j, lignes.get(j).replace(" ,", ","));
                    lignes.set(j, lignes.get(j).replace("==", " == "));
                    lignes.set(j, lignes.get(j).replace(">=", " >= "));
                    lignes.set(j, lignes.get(j).replace("<=", " <= "));
                    lignes.set(j,lignes.get(j).replace("\t",""));
                    String[] mot = lignes.get(j).split("( )|(,)");
                    if ("%".equals(mot[0]) || "%.".equals(mot[0])) {
                        Result.append("Vous avez mal d??clar?? le commentaire dans la ligne " + (j+1) + "\n\n");
                    }
                    for (int i = 0; i < mot.length; i++) {
                        switch (mot[i]) {
                            case "Snl_Start":
                                continue;
                            case "Snl_Int":
                                if (!mot[0].equals("%..")) {
                                    i++;
                                    List<String> variable = new ArrayList<>();
                                    while (i < mot.length) {
                                        if (fun.isIdent(mot[i])) {
                                            variable.add(mot[i]);
                                            allIden.add(mot[i]);
                                        } else {
                                            if (!"%".equals(mot[i]) && !"%.".equals(mot[i]))
                                                Result.append(mot[i] + " : ne respecte pas la syntaxe d'un identificateur erreur dans la ligne "  + (j+1) + "\n\n");
                                        }
                                        i++;
                                    }
                                    idenInt = variable;

                                    if (!mot[i - 1].equals("%.")) {
                                        Result.append("Fin de ligne non declare dans la ligne "  + (j+1) + "\n\n");
                                    }
                                }
                                continue;
                            case "Set":
                                i++;
                                if (!mot[0].equals("%..")) {
                                    if (!(fun.isIdent(mot[i]) && (fun.isint(mot[i + 1])))) {
                                        if (!fun.isIdent(mot[i])) {
                                            Result.append(mot[i] + "  : ne respecte pas la syntaxe d'un identificateur erreur dans la ligne "  + (j+1) + "\n\n");
                                        }
                                    }
                                    while (i < mot.length) {
                                        i++;
                                    }
                                    if (!mot[i - 1].equals("%.")) {
                                        Result.append("Fin de ligne non declare dans la ligne"  + (j+1) + "\n\n");
                                    }
                                }
                                continue;
                            case "Snl_Real":
                                if (!mot[0].equals("%..")) {
                                    i++;
                                    List<String> real = new ArrayList<>();
                                    while (i < mot.length) {
                                        if (fun.isIdent(mot[i])) {
                                            real.add(mot[i]);
                                            allIden.add(mot[i]);
                                        } else {
                                            if (!"%".equals(mot[i]) && !"%.".equals(mot[i]))
                                                Result.append(mot[i] + " : ne respecte pas la syntaxe d'un identificateur erreur dans la ligne "  + (j+1) + "\n\n");
                                        }
                                        i++;
                                    }
                                    idenFloat = real;
                                    if (!mot[i - 1].equals("%.")) {
                                        Result.append("Fin de ligne non declare dans la ligne "  + (j+1) + "\n\n");
                                    }
                                }
                                continue;
                            case "If":
                                if (!mot[0].equals("%..")) {
                                    i++;
                                    if (!"%".equals(mot[i])) {
                                        Result.append("Vous avez oublie debut ou fin de parenthese dans la ligne " + (j + 1) + "\n\n");
                                    }
                                    if (!fun.isIdent(mot[i + 1])) {
                                        Result.append(mot[i + 1] + " ne respecte pas la syntaxe d'un identificateur erreur dans la ligne " + (j + 1) + "\n\n");
                                    }
                                    if (!fun.symbole(mot[i + 3])) {
                                        Result.append("Vous avez oublie le symbole de la condition dans la ligne " + (j + 1) + "\n\n");
                                    }
                                    if (!fun.isIdent(mot[i + 5])) {
                                        Result.append(mot[i + 5] + " ne respecte pas la syntaxe d'un identificateur erreur dans la ligne " + (j + 1) + "\n\n");
                                    }
                                    if (!"%".equals(mot[i + 6])) {
                                        Result.append("Vous avez oublie debut ou fin de parenthese dans la ligne " + (j + 1) + "\n\n");
                                    }
                                    if (!"do".equals(mot[i + 7])) {
                                        Result.append("Vous avez oublie 'do' dans la ligne" + (j + 1) + "\n\n");
                                    }
                                }
                                continue;
                            case "Else":

                                continue;
                            case "SnlSt":
                                if (!mot[0].equals("%..")) {
                                    i++;
                                    String chaine_de_caractere = "";
                                    if (!"\"".equals(mot[i]))
                                        Result.append("Vous avez oublie \" au debut de la chaine de caractere dans la ligne "  + (j+1) + "\n\n");
                                    chaine_de_caractere = chaine_de_caractere + mot[i + 1] + " ";
                                    while (i < mot.length) {
                                        if (("\"".equals(mot[i]) == false) && ("%.".equals(mot[i]) == false)) {
                                            chaine_de_caractere = chaine_de_caractere + mot[i] + " ";
                                        }
                                        i++;
                                    }
                                    if (!"%.".equals(mot[i - 1])) {
                                        Result.append("Fin de ligne non declare dans la ligne " + (j + 1) + "\n\n");
                                    }
                                    if (!fun.isChaine(chaine_de_caractere)) {
                                        Result.append("Vous ne respecte pas la syntaxe d'une chaine de caractere dans la ligne " + (j + 1) + "\n\n");
                                    } if (!"\"".equals(mot[i - 2])) {
                                        Result.append("Vous avez oublie \" a la fin de la chaine de caractere dans la ligne" + (j + 1) + "\n\n");
                                    }
                                }
                                continue;
                            case "Start":

                                continue;
                            case "Get":
                                if (!mot[0].equals("%..")) {
                                    i++;
                                    if (fun.isIdent(mot[i]) && "from".equals(mot[i + 1]) && fun.isIdent(mot[i + 2])) {
                                        if (!allIden.contains(mot[i])) {
                                            Result.append(mot[i] + " n'ai pas declare f la ligne "  + (j+1) + "\n\n");
                                        }
                                        if (!allIden.contains(mot[i + 2])) {
                                            Result.append(mot[i] + " n'ai pas declare f la ligne "  + (j+1) + "\n\n");
                                        }
                                        if (idenFloat.contains(mot[i]) && idenInt.contains(mot[i + 2])) {
                                            //     Result.append(mot[i] + " raha real matnajamch tadi int f la ligne"  + (j+1) + "\n\n");
                                        }
                                        if (idenInt.contains(mot[i]) && idenFloat.contains(mot[i + 2])) {
                                            //      Result.append(mot[i] + " raha int matnajamch tadi real f la ligne"  + (j+1) + "\n\n");
                                        }
                                    } else {
                                        if (!"from".equals(mot[i + 1])) {
                                            Result.append("Vous avez oublie 'from' dans la ligne "  + (j+1) + "\n\n");
                                        }
                                        if (!fun.isIdent(mot[i])) {
                                            Result.append(mot[i] + " ne respecte pas la syntaxe d'un identificateur erreur dans la ligne "  + (j+1) + "\n\n");
                                        } else {
                                            if (!allIden.contains(mot[i])) {
                                                Result.append(mot[i] + " n'ai pas declare f la ligne "  + (j+1) + "\n\n");
                                            }
                                        }
                                        if (!fun.isIdent(mot[i + 2])) {
                                            Result.append(mot[i + 2] + " ne respecte pas la syntaxe d'un identificateur erreur dans la ligne "  + (j+1) + "\n\n");
                                        } else {
                                            if (!allIden.contains(mot[i + 2])) {
                                                Result.append(mot[i + 2] + " n'ai pas declare f la ligne "  + (j+1) + "\n\n");
                                            }
                                        }
                                    }
                                    if (!mot[i+3].equals("%.")) {
                                        Result.append(mot[i+3] +"Fin de ligne non declare dans la ligne "  + (j+1) + "\n\n");
                                    }
                                }
                                continue;
                            case "Finish":

                                continue;
                            case "Snl_Put":
                                if (!mot[0].equals("%..")) {
                                    i++;

                                    if (fun.isIdent(mot[i])) {
                                        if (!allIden.contains(mot[i])) {
                                            while (i < mot.length) {
                                                i++;
                                            }
                                            if (!"%.".equals(mot[i - 1])) {
                                                Result.append("Fin de ligne non declare dans la ligne "  + (j+1) + "\n\n");
                                            }
                                            if (!"%.".equals(mot[i - 1]) && !"\"".equals(mot[i - 2])) {
                                                Result.append(mot[i - 2] + " n'ai pas declare f la ligne "  + (j+1) + "\n\n");
                                            }
                                        }
                                    } else {

                                        if (!"\"".equals(mot[i]))
                                            Result.append("Vous avez oublie \" au debut de la chaine de caractere dans la ligne "  + (j+1) + "\n\n");

                                        String chaine_de_caractere = "";
                                        chaine_de_caractere = chaine_de_caractere + mot[i + 1] + " ";
                                        while (i < mot.length) {
                                            if (("\"".equals(mot[i]) == false) && ("%.".equals(mot[i]) == false)) {
                                                chaine_de_caractere = chaine_de_caractere + mot[i] + " ";
                                            }
                                            i++;
                                        }
                                        if (!fun.isChaine(chaine_de_caractere)) {
                                            Result.append("Vous ne respecte pas la syntaxe d'une chaine de caractere dans la ligne " + (j + 1) + "\n\n");
                                        }

                                        if (!"\"".equals(mot[i - 2]))
                                            Result.append(mot[i - 2] +" Vous avez oublie \" a la fin de la chaine de caractere dans la ligne "  + (j+1) + "\n\n");
                                        if (!"%.".equals(mot[i - 1]))
                                            Result.append("Fin de ligne non declare dans la ligne "  + (j+1) + "\n\n");

                                    }
                                }
                                continue;
                            case "%..":

                                continue;
                            case "Snl_Close":

                                break;

                        }
                    }

                }
            }else{
                Result.append("\n\n\tvous devez effectuer l'analyse lexicale");
            }
        } catch (IOException ex) {
            Logger.getLogger(snail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SemantiqueActionPerformed(ActionEvent evt) {
        Result.setText("");
        Path path = Paths.get(chemin);
        try {
            if (syntaxique && lexical) {
                semantique = true;
                List<String> lignes = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
                for (int j = 0; j < lignes.size(); j++) {
                    lignes.set(j, lignes.get(j).replace("<", " < "));
                    lignes.set(j, lignes.get(j).replace(">", " > "));
                    lignes.set(j, lignes.get(j).replace(" , ", ","));
                    lignes.set(j, lignes.get(j).replace(", ", ","));
                    lignes.set(j, lignes.get(j).replace(" ,", ","));
                    lignes.set(j, lignes.get(j).replace("==", " == "));
                    lignes.set(j, lignes.get(j).replace(">=", " >= "));
                    lignes.set(j, lignes.get(j).replace("<=", " <= "));
                    lignes.set(j, lignes.get(j).replace("\t", ""));
                    String[] mot = lignes.get(j).split("( )|(,)");
                    for (int i = 0; i < mot.length; i++) {
                        switch (mot[i]) {
                            case "Snl_Start":

                                continue;
                            case "Snl_Int":
                                break;
                            case "Set":
                                if (!mot[0].equals("%..")) {
                                    i++;
                                    if (!mot[0].equals("%..")) {
                                        if (fun.isIdent(mot[i])) {
                                            if (!allIden.contains(mot[i])) {
                                                Result.append(mot[i] + " n'ai pas declare dans la ligne"  + (j+1) + "\n\n");
                                            }
                                        }
                                        if (idenFloat.contains(mot[i]) && fun.isint(mot[i+1])) {
                                            Result.append("Variable real ne peut pas prendre une valeur int dans la ligne " + (j + 1) + "\n\n");
                                        }
                                        if (idenInt.contains(mot[i]) && fun.isFloat(mot[i+1])) {
                                            Result.append("Variable int ne peut pas prendre une valeur real dans la ligne " + (j + 1) + "\n\n");
                                        }
                                    }
                                }
                                continue;
                            case "Snl_Real":

                                continue;
                            case "Start":

                                continue;
                            case "Get":
                                if (!mot[0].equals("%..")) {
                                    i++;
                                    if (idenFloat.contains(mot[i]) && idenInt.contains(mot[i + 2])) {
                                        Result.append("Variable real ne peut pas prendre une valeur int dans la ligne " + (j + 1) + "\n\n");
                                    }
                                    if (idenInt.contains(mot[i]) && idenFloat.contains(mot[i + 2])) {
                                        Result.append("Variable int ne peut pas prendre une valeur real dans la ligne " + (j + 1) + "\n\n");
                                    }
                                }
                                continue;
                            case "Finish":

                                continue;
                            case "Snl_Put":
                                if (!mot[0].equals("%..")) {
                                    i++;
                                    if (fun.isIdent(mot[i])) {

                                        if (!allIden.contains(mot[i])) {
                                            Result.append(mot[i] + " n'ai pas declare dans la ligne "  + (j+1) + "\n\n");

                                        }
                                    }
                                }
                                continue;
                            case "%..":

                                continue;

                            case "If":
                                if (!mot[0].equals("%..")) {
                                    i++;

                                    if (!allIden.contains(mot[i + 1])) {
                                        Result.append(mot[i + 1] + " n'ai pas desclare dans la ligne " + (j + 1) + "\n\n");
                                    }

                                    if (!allIden.contains(mot[i + 5])) {
                                        Result.append(mot[i + 5] + " n'ai pas desclare dans la ligne " + (j + 1) + "\n\n");
                                    }

                                }
                                continue;
                            case "Else":

                                continue;
                            case "Snl_Close":
                                break;

                        }
                    }

                }
            }else{
                Result.append("\n\n\tvous devez effectuer l'analyse \n \t lexicale & syntaxique");
            }
        }

        catch (IOException ex) {
            Logger.getLogger(snail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new snail().setVisible(true));
    }
}