package com.example.listviews;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Model: Record (intents=puntuació, nom)
    class Record {
        public int intents;
        public String nom;

        public Record(int _intents, String _nom) {
            intents = _intents;
            nom = _nom;
        }
    }

    private String[] noms = {"Marc", "Laura", "Pau", "Maria", "Anna", "Jordi", "Sara", "Joan", "Eva", "Carla", "Alex", "Emma", "Marta", "Bruno", "Clara"};
    private String[] cognoms = {"Garcia", "Martinez", "Lopez", "Perez", "Gomez", "Sanchez", "Rodriguez", "Fernandez", "Gonzalez", "Romero", "Soler", "Torres", "Navarro", "Jimenez", "Ruiz"};

    private int[] imatges = {R.drawable.aurelionsolsquare, R.drawable.akalisquare, R.drawable.alistarsquare}; // Afegeix les teves imatges

    ArrayList<Record> records;
    ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        records = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int randomIntents = random.nextInt(100) + 1;
            String randomNom = noms[random.nextInt(noms.length)] + " " + cognoms[random.nextInt(cognoms.length)];
            records.add(new Record(randomIntents, randomNom));
        }

        adapter = new ArrayAdapter<Record>(this, R.layout.list_item, records) {
            @Override
            public View getView(int pos, View convertView, ViewGroup container) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }

                ImageView imageView = convertView.findViewById(R.id.imageView);

                // Obtenir una imatge aleatòria de la llista d'imatges
                int randomImage = imatges[new Random().nextInt(imatges.length)];
                imageView.setImageResource(randomImage);

                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));
                return convertView;
            }
        };

        ListView lv = findViewById(R.id.recordsView);
        lv.setAdapter(adapter);

        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 3; i++) {
                    int randomIntents = random.nextInt(100) + 1;
                    String randomNom = noms[random.nextInt(noms.length)] + " " + cognoms[random.nextInt(cognoms.length)];
                    records.add(new Record(randomIntents, randomNom));
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
