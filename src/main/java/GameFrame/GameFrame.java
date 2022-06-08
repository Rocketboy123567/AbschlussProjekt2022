package GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import Computer.*;
import Computer.JSON.getJSON;
import org.json.simple.JSONObject;

import static Computer.JSON.getJSON.getJSONObject;

public final class GameFrame extends JFrame {
    static int width = 1280;
    static int height = 720;
    Container contentPane = this.getContentPane();
    JFrame frame = this;
    JPanel attackPanel = new JPanel();
    Player player = new Player("Test","Test");
    ArrayList<Pokemon> pokeList = new ArrayList<>();

    public GameFrame() {

        initGameFrame("Pokemon Game", width, height);

        showMainMenu();

        this.setVisible(true);
    }

    private void initGameFrame(String title, int width, int height) {

        this.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(""/*""p3.jpg"*/))).getImage());
        this.setTitle(title);
        this.setLayout(null);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //might be changed if a save on exit system is implemented
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }

    private void showMainMenu() {

        JButton playButton = new JButton("Play");
        playButton.setBounds(540, 335, 200, 50);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton customButton = new JButton("Custom");
        customButton.setBounds(540, 335 + 60, 200, 50);
        customButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeComponents();

                customGame();

                reloadFrame();
            }
        });

        JButton settingsButton = new JButton("Settings");
        settingsButton.setBounds(540, 335 + 60 + 60, 200, 50);
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(540, 335 + 60 + 60 + 60, 200, 50);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        });

        contentPane.add(playButton);
        contentPane.add(customButton);
        contentPane.add(settingsButton);
        contentPane.add(quitButton);

    }

    private void customGame() {

        NPC enemy = new NPC("Test_Enemy","Test",false);

        removeComponents();
        player.choosePokemon(contentPane);
        reloadFrame();

//        removeComponents();
//        enemy.choosePokemon(contentPane);
//        reloadFrame();
//
//        initAttackPanel();
    }

    private void initAttackPanel() {

        attackPanel.setSize(width/3,height/2);
        attackPanel.setBounds(width/3*2,height/2,width/3,height/2);
        attackPanel.setBackground(new Color(0,0,0));
        attackPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton[] attackButtons = {
                new JButton("Fight"),
                new JButton("Bag"),
                new JButton("Pokemon"),
                new JButton("Run")
        };

        for (JButton attackButton : attackButtons) {
            attackPanel.add(attackButton);
        }

        contentPane.add(attackPanel);
    }

    public void quit() { //quit
        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }

    private void removeComponents() {
        //needs to be called if you want to remove all components
        contentPane.removeAll();
    }

    private void reloadFrame() {  //needs to be called everytime a new component is added to the frame during runtime
        frame.revalidate();
        frame.repaint();
    }


//    private void initPokemon(){
//        //TODO
//    }
}

