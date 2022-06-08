package Computer;

import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import static Computer.JSON.getJSON.getJSONObject;

public abstract class Computer {
    String name;
    String gender;
    Pokemon pokemon;

    public Computer(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void choosePokemon(Container contentpane)  {
        Random rand = new Random();

        String[] pokemons = Pokemon.allPokemon;
        JSONObject[][] pokemon = new JSONObject[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int randomNumb = rand.nextInt(0,930);/*898+32*/
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
                    URL url = new URL((String) sprites.get("front_default"));
                    Image img = ImageIO.read(url);
                    Icon icon = new ImageIcon(img);

                    JButton pokebutton = new JButton();
                    pokebutton.setIcon(icon);
                    pokebutton.setBounds((j+1) * 150 + 175, (i+1) * 125 + 10, 100,100);
                    contentpane.add(pokebutton);
                } catch(IOException ie){
                    ie.printStackTrace();
                }
//                J
            }
        }




    }
}
