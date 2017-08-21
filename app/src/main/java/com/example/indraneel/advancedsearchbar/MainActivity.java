package com.example.indraneel.advancedsearchbar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MaterialSearchView searchView;

    String[] books = {
            "Let Us C",
            "Let Us C++",
            "Let Us Java",
            "C++: The Complete Reference",
            "Java: The Complete Refernece",
            "Network Security Attacks and Countermeasures",
            "Applied Network Security",
            "Crytography and Network Security: Principle and Practice",
            "Protocols and Architecture for Wireless Sensor Networks",
            "Computer and Communication Networks",
            "Ethernet: The Definitive Guide, Designing and Managing Local Area Networks",
            "Social Network Analysis for Startups: Finding connections on the social web",
            "Bio-Inspired Artificial Intelligence: Theories, Methods, and Technologies",
            "Computational Intelligence",
            "Swarm Intelligence: The Morgan Kaufmann Series in Evolutionary Computation",
            "Algorithm Design and Applications",
            "Fundamental of Algorithmics",
            "Data Mining: Concepts and Techniques",
            "Large-Scale Parallel Data Mining",
            "Parallel Data Mining Algorithms for Association Rules and Clustering",
            "Mining the Social Web,:Data Mining Facebook, Twitter, LinkedIn, Google+, GitHub, and More",
            "Reinforcement and Systemic Machine Learning for Decision Making",
            "Deep Learning",
            "Handbook of Parallel Computing Models Algorithms and Applications",
            "CUDA by Example - An Introduction to General-Purpose GPU Programming",
            "CUDA Programming: A Developer's Guide to Parallel Computing with GPUs",
            "Discrete Mathematics and its Applications",
            "Advanced Engineering Mathematics",
            "Modern Digital Electronics",
            "Integrated Electronics-Analog and Digital Circuits and Systems",
            "The 8051 Microcontroller and Embedded Systems using Assembly and C",
            "Computer Architecture and Organization",
            "Fundamentals of Data Structures in C++",
            "Data Structures and Algorithms"



    };

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Material Search");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        lv = (ListView)findViewById(R.id.listView);
        ArrayAdapter adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,books);
        lv.setAdapter(adapter);


        searchView = (MaterialSearchView)findViewById(R.id.search_view);

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener(){


            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                //if search view is closed lv returns to default
                lv = (ListView)findViewById(R.id.listView);
                ArrayAdapter adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,books);
                lv.setAdapter(adapter);
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null && !newText.isEmpty())
                {
                    List<String> lstFound = new ArrayList<String>();
                    for(String item : books)
                    {
                        if(item.contains(newText))
                            lstFound.add(item);
                    }
                    ArrayAdapter adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,lstFound);
                    lv.setAdapter(adapter);
                }
                else
                {
                    // if search text is null
                    //return default
                    ArrayAdapter adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,books);
                    lv.setAdapter(adapter);
                }
                return  true;
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);


        return true;
    }
}
