/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ldumay.main;

import fr.ldumay.others.Console;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import starwars.classes.Acces;
import starwars.dao.DAOLogin;

/**
 *
 * @author ldumay
 */

/**
 * Class - ViewLogin
 * <br>
 * <br>Constructor :
 * <br>- ViewLogin()
 * <br>
 * <br>Functions :
 * <br> - createLeftPanel()
 * <br> |--> JPanel
 * <br>
 * <br> - createRightPanel
 * <br> |--> JPanel
 * <br>
 * <br>Basic getter :
 * <br>- getUserConnecte()
 * <br>- getUser()
 * <br>
 * <br>Basic setter :
 * <br>- setUserConnecte()
 * <br>- setUser()
 * <br>
 * <br>End.
 */
public class ViewLogin extends JFrame implements ActionListener{
    
    private Acces user;
    private boolean userConnecte;
    //-
    private JPanel contentPanel;
    //-
    private String messageString;
    private JLabel messageLabel;
    private String loginString;
    private JLabel loginLabel;
    //-
    private JPanel contentLeftPanel;
    private String passwordString;
    private JLabel passwordLabel;
    //-
    private JPanel contentRightPanel;
    private String loginRightString;
    private JTextField loginRightTextField;
    //-
    private String passwordRightString;
    private JPasswordField passwordRightTextField;
    //-
    private String connexionString;
    private JButton connexionButton;
    //-
    private JPanel contentBottomPanel;
    private String filmsListButtonString;
    private JButton filmsListButton;
    private String usersListButtonString;
    private JButton usersListButton;
    //-
    private String deconnexionButtonString;
    private JButton deconnexionButton;
    //-
    private ViewFilmsList filmsList;
    private ViewUsersList usersList;
    
    /**
     * Constructor
     */
    public ViewLogin(){
        super("Star Wars");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(500, 300));
        this.setLocationRelativeTo(null);
        
        userConnecte = false;
        
        contentPanel = (JPanel) this.getContentPane();
        contentPanel.setLayout(new FlowLayout());
        
        contentPanel.add(createLeftPanel());
        contentPanel.add(createRightPanel());
        contentPanel.add(createBottomPanel());
    }
    
    /**
     * Création d'un panneau qui serait placé à gauche.
     * 
     * @return JPanel
     */
    private JPanel createLeftPanel(){
        contentLeftPanel = new JPanel(new BorderLayout());
        
        loginString = "Identifiant";
        loginString.toUpperCase();
        loginLabel = new JLabel(loginString);
        loginLabel.setPreferredSize(new Dimension(150, 40));
        contentLeftPanel.add(loginLabel, BorderLayout.NORTH);
        
        passwordString = "Mot de passe";
        passwordString.toUpperCase();
        passwordLabel = new JLabel(passwordString);
        passwordLabel.setPreferredSize(new Dimension(150, 40));
        contentLeftPanel.add(passwordLabel, BorderLayout.SOUTH);
        
        return contentLeftPanel;
    }
    
    /**
     * Création d'un panneau qui serait placé à droite.
     * 
     * @return JPanel
     */
    private JPanel createRightPanel(){
        contentRightPanel = new JPanel(new BorderLayout());
        
        loginRightString = "Identifiant";
        loginRightString.toUpperCase();
        loginRightTextField = new JTextField(loginRightString);
        loginRightTextField.setPreferredSize(new Dimension(250, 30));
        contentRightPanel.add(loginRightTextField, BorderLayout.NORTH);
        
        passwordRightString = "Mot de passe";
        passwordRightString.toUpperCase();
        passwordRightTextField = new JPasswordField(passwordRightString);
        passwordRightTextField.setPreferredSize(new Dimension(250, 30));
        contentRightPanel.add(passwordRightTextField, BorderLayout.WEST);
        
        connexionString = "Se connecter";
        connexionString.toUpperCase();
        connexionButton = new JButton(connexionString);
        connexionButton.addActionListener(this);
        connexionButton.setPreferredSize(new Dimension(150, 40));
        contentRightPanel.add(connexionButton, BorderLayout.SOUTH);
        
        return contentRightPanel;
    }
    
    /**
     * Création d'un panneau qui serait placé en bas.
     * 
     * @return JPanel
     */
    private JPanel createBottomPanel(){
        contentBottomPanel = new JPanel(new BorderLayout());
        
        messageString = "Erreur de login / Mot de Passe";
        messageString.toUpperCase();
        messageLabel = new JLabel(messageString, SwingConstants.CENTER);
        messageLabel.setPreferredSize(new Dimension(450, 40));
        messageLabel.setVisible(false);
        contentPanel.add(messageLabel);
        
        filmsListButtonString = "Liste des films";
        filmsListButtonString.toUpperCase();
        filmsListButton = new JButton(filmsListButtonString);
        filmsListButton.addActionListener(this);
        filmsListButton.setVisible(false);
        contentPanel.add(filmsListButton);
        
        usersListButtonString = "Liste des users";
        usersListButtonString.toUpperCase();
        usersListButton = new JButton(usersListButtonString);
        usersListButton.addActionListener(this);
        usersListButton.setVisible(false);
        contentPanel.add(usersListButton);
        
        deconnexionButtonString = "Déconnexion";
        deconnexionButtonString.toUpperCase();
        deconnexionButton = new JButton(deconnexionButtonString);
        deconnexionButton.addActionListener(this);
        deconnexionButton.setVisible(false);
        contentPanel.add(deconnexionButton);
        
        return contentBottomPanel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        //Click button connexionButton
        if( (JButton)event.getSource() == connexionButton){
            Console.print("[Demande de connexion]");
            DAOLogin daoLogin = new DAOLogin();
            String login = loginRightTextField.getText();
            String password = passwordRightTextField.getText();
            //String password = Arrays.toString(passwordRightTextField.getPassword());
            Console.print("Login : "+login+" - Password : "+password);
            try {
                userConnecte = true;
                if(userConnecte==true){
                    user = daoLogin.checkPassword(login, password);
                    if(user!=null){
                        Console.print("user : "+user.getLogin()+" / "+user.getPassword());
                        messageLabel.setText("Connecté");
                        messageLabel.setVisible(true);
                        filmsListButton.setVisible(true);
                        usersListButton.setVisible(true);
                        deconnexionButton.setVisible(true);
                        contentLeftPanel.setVisible(false);
                        contentRightPanel.setVisible(false);
                    } else {
                        messageLabel.setVisible(true);
                    }
                } else { messageLabel.setText("Déjà connecté"); }
            } catch (SQLException ex) {
                Logger.getLogger(ViewLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Click button filmsListButton
        if( (JButton)event.getSource() == filmsListButton && userConnecte==true){
            try {
                filmsList = new ViewFilmsList();
            } catch (SQLException ex) {
                Logger.getLogger(ViewLogin.class.getName()).log(Level.SEVERE, null, ex); }
        }
        //Click button usersListButton
        if( (JButton)event.getSource() == usersListButton && userConnecte==true){
            try {
                usersList = new ViewUsersList();
            } catch (SQLException ex) {
                Logger.getLogger(ViewLogin.class.getName()).log(Level.SEVERE, null, ex); }
        }
        
        //Click button deconnexionButton
        if( (JButton)event.getSource() == deconnexionButton){
            messageLabel.setText("Déconnecté");
            filmsListButton.setVisible(false);
            usersListButton.setVisible(false);
            userConnecte = false;
            user = null;
            deconnexionButton.setVisible(false);
            contentLeftPanel.setVisible(true);
            contentRightPanel.setVisible(true);
        }
    }

    // The methods of basic getter below.
    public boolean getUserConnecte() { return userConnecte; }
    public Acces getUser() { return user; }
    
    // The methods of basic setter below.
    public void setUserConnecte(boolean userConnecte) { this.userConnecte = userConnecte; }
    public void setUser(Acces user) { this.user = user; }
}