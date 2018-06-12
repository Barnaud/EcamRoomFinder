package com.example.benoitarnaud.ecamroomfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static DrawStuff drawing;
    public static HashMap<String, Salle> salles;
    public static Point e0b1, e0h1, e0h2, e0h3, e0h4, e0b2, e0b3, e0s1, e0s2, e1b1, e1h1, e1h2, e1h3, e1h4, e1h5, e1s1, e1s2, e2h1, e2s2;
    public static Spinner salleDepart, salleArrival;
    private Button submit;
    public static Salle[] sallesComplexes;
    public static HashMap<Salle, Salle> stairLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
stairLink = new HashMap<Salle, Salle>();

        salles = new HashMap<String, Salle>();
        drawing = new DrawStuff(this);
        e0b1 = new Point("e0b1", 0, new float[]{45, 17});
        e0h1 = new Point("e0h1", 0, new float[]{51, 20});
        e0h2 = new Point("e0h2", 0, new float[]{51, 47});
        e0h3 = new Point("e0h3", 0, new float[]{51, 70});
        e0h4 = new Point("e0h4", 0, new float[]{51, 78});
        e0b2 = new Point("e0b2", 0, new float[]{50, 64});
        e0b3 = new Point("e0b3", 0, new float[]{50, 79});
        e0s1 = new Point("e0s1", 0, new float[]{45, 28});
        e0s2 = new Point("e0s2", 0, new float[]{50, 49});
        e1b1 = new Point("e1b1", 1, new float[]{48, 29});
        e1h1 = new Point("e1h1", 1, new float[]{66, 29});
        e1h2 = new Point("e1h2", 1, new float[]{67, 33});
        e1h3 = new Point("e1h3", 1, new float[]{66, 46});
        e1h4 = new Point("e1h4", 1, new float[]{73, 58});
        e1h5 = new Point("e1h5", 1, new float[]{73, 80});
        e1s1 = new Point("e1s1", 1, new float[]{73, 46});
        e1s2 = new Point("e1s2", 1, new float[]{73, 51});
        e2h1 = new Point("e2h1", 2, new float[]{60, 48});
        e2s2 = new Point("e2s2", 2, new float[]{64, 38});
        salles.put("Amphithéâtre", new Salle("Amphithéâtre", 0, new float[]{25, 19}, e0b1));
        salles.put("Salle d'examen", new Salle("Salle d'examen", 0, new float[]{44, 65}, e0b2));
        salles.put("Salle 12", new Salle("Salle 12", 0, new float[]{40, 82}, e0b3));
        salles.put("Labo 1", new Salle("Labo 1", 0, new float[]{68, 18}, e0h1));
        salles.put("Labo 2", new Salle("Labo 2", 0, new float[]{76, 19}, e0h1));
        salles.put("Labo 3", new Salle("Labo 3", 0, new float[]{68, 22}, e0h1));
        salles.put("Labo 4", new Salle("Labo 4", 0, new float[]{68, 48}, e0h2));
        salles.put("Labo 5", new Salle("Labo 5", 0, new float[]{65, 68}, e0h3));
        salles.put("Labo 6", new Salle("Labo 6", 0, new float[]{65, 70}, e0h3));
        salles.put("Labo 7", new Salle("Labo 7", 0, new float[]{80, 80}, e0h4));
        salles.put("Atelier de recherche", new Salle("Atelier de recherche", 0, new float[]{67, 80}, e0h4));
        salles.put("101", new Salle("101", 1, new float[]{66, 27}, e1h1));
        salles.put("102", new Salle("102", 1, new float[]{46, 11}, e1b1));
        salles.put("103", new Salle("103", 1, new float[]{43, 16}, e1b1));
        salles.put("104", new Salle("104", 1, new float[]{40, 29}, e1b1));
        salles.put("105", new Salle("105", 1, new float[]{63, 33}, e1h2));
        salles.put("106", new Salle("106", 1, new float[]{62, 56}, e1h4));
        salles.put("107", new Salle("107", 1, new float[]{62, 59}, e1h4));
        salles.put("108", new Salle("108", 1, new float[]{52, 82}, e1h5));
        salles.put("109", new Salle("109", 1, new float[]{46, 82}, e1h5));
        salles.put("112", new Salle("112", 1, new float[]{46, 76}, e1h5));
        salles.put("207", new Salle("207", 2, new float[]{55, 48}, e2h1));


        sallesComplexes =new Salle[]{salles.get("101"), salles.get("102"), salles.get("103"), salles.get("104"), salles.get("105")};
        //setContentView(drawing);
        List<String> listSalles = new ArrayList<String>(salles.keySet());
        //Pour pas que les escaliers apparaissent dans les listes
        salles.put("e0s1", new Salle("e0s1", 0, new float[]{44, 32}, e0s1));
        salles.put("e0s2", new Salle("e0s2", 0, new float[]{44, 50}, e0s2));
        salles.put("e1s1", new Salle("e1s1", 1, new float[]{73, 40}, e1s1));
        salles.put("e1s2", new Salle("e1s2", 1, new float[]{65, 51}, e1s2));
        salles.put("e2s2", new Salle("e2s2", 2, new float[]{55, 38}, e2s2));
        salles.put("blank", new Salle("blank", 1, new float[]{0,0}, e1h3));
        e0b1.setNrstStairs(salles.get("e0s1"));
        e0h1.setNrstStairs(salles.get("e0s1"));
        e0h2.setNrstStairs(salles.get("e0s2"));
        e0h3.setNrstStairs(salles.get("e0s2"));
        e0b2.setNrstStairs(salles.get("e0s2"));
        e0b3.setNrstStairs(salles.get("e0s2"));
        e1b1.setNrstStairs(salles.get("e1s1"));
        e1h1.setNrstStairs(salles.get("e1s1"));
        e1h2.setNrstStairs(salles.get("e1s1"));
        e1h3.setNrstStairs(salles.get("e1s1"));
        e1h4.setNrstStairs(salles.get("e1s2"));
        e1h5.setNrstStairs(salles.get("e1s2"));
        e2h1.setNrstStairs(salles.get("e2s2"));
        stairLink.put(salles.get("e0s1"), salles.get("e1s1"));
        stairLink.put(salles.get("e1s1"), salles.get("e0s1"));
        stairLink.put(salles.get("e0s2"),salles.get("e1s2"));
        stairLink.put(salles.get("e1s2"),salles.get("e0s2"));
        Collections.sort(listSalles);
        salleDepart = (Spinner) findViewById(R.id.spinner1);
        salleArrival = (Spinner) findViewById((R.id.spinner2));
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listSalles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        salleDepart.setAdapter(adapter);
        salleArrival.setAdapter(adapter);
        submit = (Button) findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (salleDepart.getSelectedItem() != salleArrival.getSelectedItem()) {
                    Intent mapActivityIntent = new Intent(MainActivity.this, MapActivity.class);
                    startActivity(mapActivityIntent);
                }
            }

        });

    }
}

