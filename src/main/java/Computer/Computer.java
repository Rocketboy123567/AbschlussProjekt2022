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
        System.out.println();
        System.out.println("Attacke 1: "+this.pokemon.attacks[0].name);
        System.out.println("Typ: "+this.pokemon.attacks[0].type);
        System.out.println("Stärke: "+this.pokemon.attacks[0].basePower);
        System.out.println("Art: "+this.pokemon.attacks[0].attackType);
        System.out.println("PP: "+this.pokemon.attacks[0].pp);
        System.out.println();
        System.out.println("Attacke 2: "+this.pokemon.attacks[1].name);
        System.out.println("Typ: "+this.pokemon.attacks[1].type);
        System.out.println("Stärke: "+this.pokemon.attacks[1].basePower);
        System.out.println("Art: "+this.pokemon.attacks[1].attackType);
        System.out.println("PP: "+this.pokemon.attacks[1].pp);
        System.out.println();
        System.out.println("Attacke 3: "+this.pokemon.attacks[2].name);
        System.out.println("Typ: "+this.pokemon.attacks[2].type);
        System.out.println("Stärke: "+this.pokemon.attacks[2].basePower);
        System.out.println("Art: "+this.pokemon.attacks[2].attackType);
        System.out.println("PP: "+this.pokemon.attacks[2].pp);
        System.out.println();
        System.out.println("Attacke 4: "+this.pokemon.attacks[3].name);
        System.out.println("Typ: "+this.pokemon.attacks[3].type);
        System.out.println("Stärke: "+this.pokemon.attacks[3].basePower);
        System.out.println("Art: "+this.pokemon.attacks[3].attackType);
        System.out.println("PP: "+this.pokemon.attacks[3].pp);
    }

    public void setPokemon(JSONObject pokemon) {

        Random rand = new Random();
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

        JSONArray moves = (JSONArray)  pokemon.get("moves");


        Attacks[] z = new Attacks[4];
        for (int i = 0; i < 4; i++) {
            int randatt = rand.nextInt(1,moves.size());
            JSONObject move = (JSONObject) moves.get(randatt);
            move = (JSONObject) move.get("move");
            String url = (String) move.get("url");
            move = getJSONObject(url);
            JSONObject meta = (JSONObject) move.get("meta");
            JSONObject category = (JSONObject) meta.get("category");
            String test = (String) category.get("name");
            String q = "damage";
            if(q.contains(test))
            {

                long accuracy = (long) move.get("accuracy");
                JSONObject dc = (JSONObject) move.get("damage_class");
                String attacktype = (String) dc.get("name");
                String nm = (String) move.get("name");
                System.out.println(nm);
                long power = (long) move.get("power");
                long pp = (long) move.get("pp");
                z[i]= new Attacks(accuracy, power, null, attacktype, nm, pp);
            }
            else{
                i--;
                System.out.println(test);
                System.out.println(q);
                System.out.println(i);
            }
        }
        this.pokemon = new Pokemon(name,type,(long) health.get("base_stat"),(long) attack.get("base_stat"),(long) specialAttack.get("base_stat"),(long) defence.get("base_stat"),(long) specialDefence.get("base_stat"),(long) speed.get("base_stat"));
    }

    public void choosePokemon(Container contentpane, NPC enemy)  {

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
                            if(GameFrame.picked==false){
                                GameFrame.picked = true;
                                contentpane.removeAll();
                                enemy.choosePokemon(contentpane,enemy);
                                contentpane.revalidate();
                                contentpane.repaint();
                            }
                            else{
                                GameFrame.dontinue();
                            }
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
