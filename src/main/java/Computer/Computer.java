package Computer;

import GameFrame.GameFrame;
import com.sun.tools.javac.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import static Computer.JSON.getJSON.getJSONObject;

public abstract class Computer {
    String name;
    Pokemon pokemon;

    public Computer(String name) {
        this.name = name;
    }

    public void displayPoke(){
        System.out.println("Pokemon: "+this.pokemon.name);
        System.out.println("Type 1: "+this.pokemon.type[0]);
        System.out.println("Type 2: "+this.pokemon.type[1]);
        System.out.println("Health: "+this.pokemon.stats.health);
        System.out.println("Damage: "+this.pokemon.stats.attack);
        System.out.println("SpecialDamage: "+this.pokemon.stats.specialAttack);
        System.out.println("Defence: "+this.pokemon.stats.defence);
        System.out.println("SpecialDefence: "+this.pokemon.stats.specialDefence);
        System.out.println("Speed: "+this.pokemon.stats.speed);
    }

    public void setPokemon(JSONObject pokemon) {
        JSONObject species = (JSONObject) pokemon.get("species");
        String name = (String) species.get("name");

        JSONArray types = (JSONArray) pokemon.get("types");
        String[] type = {null,null};
        JSONObject type1 = (JSONObject) types.get(0);
        type1 = (JSONObject) type1.get("type");
        type[0] = (String) type1.get("name");

        if(types.size()==2){
            JSONObject type2 = (JSONObject) types.get(1);
            type2 = (JSONObject) type2.get("type");
            type[1] = (String) type2.get("name");
        }

        JSONArray stats = (JSONArray) pokemon.get("stats");
        JSONObject health = (JSONObject) stats.get(0);

        JSONObject attack= (JSONObject) stats.get(1);

        JSONObject specialAttack= (JSONObject) stats.get(3);

        JSONObject defence= (JSONObject) stats.get(2);

        JSONObject specialDefence= (JSONObject) stats.get(4);

        JSONObject speed= (JSONObject) stats.get(5);

        this.pokemon = new Pokemon(name,type,(long) health.get("base_stat"),(long) attack.get("base_stat"),(long) specialAttack.get("base_stat"),(long) defence.get("base_stat"),(long) specialDefence.get("base_stat"),(long) speed.get("base_stat"));
    }

    public void choosePokemon(Container contentpane)  {

        Random rand = new Random();

        String[] pokemons = Pokemon.allPokemon;
        JSONObject[][] pokemon = new JSONObject[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int randomNumb = rand.nextInt(1,930);/*898+32*/
                if(randomNumb>898){
                    randomNumb = (randomNumb-898)+10000;
                }
                System.out.println("Requested: " + randomNumb);
                pokemon[i][j] = getJSONObject("https://pokeapi.co/api/v2/pokemon/" + randomNumb);
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                try {
//                    Button pokebutton = new JButton("" + pokemon[i][j].get("name"));



                    JSONObject sprites = (JSONObject) pokemon[i][j].get("sprites");
                    JSONObject poke = pokemon[i][j];
                    URL url = new URL((String) sprites.get("front_default"));
                    Image img = ImageIO.read(url);
                    Icon icon = new ImageIcon(img);

                    JButton pokebutton = new JButton();

                    pokebutton.setIcon(icon);
                    pokebutton.setBounds((j+1) * 150 + 175, (i+1) * 125 + 10, 100,100);
                    pokebutton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            setPokemon(poke);
                            displayPoke();
                            GameFrame.picked = true;
                        }
                    });
                    contentpane.add(pokebutton);
                } catch(IOException ie){
                    ie.printStackTrace();
                }
//
            }
        }

    }
}
