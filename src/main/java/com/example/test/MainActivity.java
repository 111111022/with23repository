package com.example.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btn1, btn2, btn3;
    ListView listView;
    int num;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        listView = findViewById(R.id.listView);

        ActionBar ac=getSupportActionBar();
        ac.setTitle("2320 정수인");

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, list);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);

        for (int i = 0; i < 5; i++) {
            list.add("리스트 데이터" + (i + 1));
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                num = i;
                check = 1;
                textView.setText(list.get(num));
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("리스트 데이터" + (list.size() + 1));
                textView.setText(list.get(num));
                adapter.notifyDataSetChanged();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check == 0) {
                    Toast.makeText(getApplicationContext(), "리스트를 선택해주세요!", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this);
                        a.setTitle("리스트 아이템 수정");
                        a.setMessage("현재 데이터 : " + list.get(num));
                        a.setIcon(R.mipmap.ic_launcher_round);

                        final EditText et = new EditText(getApplicationContext());
                        a.setView(et);
                        a.setNegativeButton("취소", null);
                        a.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                list.set(num, et.getText().toString());
                                textView.setText(list.get(num));
                            }
                        });
                        a.show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "리스트를 선택해주세요!", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check == 0) {
                    Toast.makeText(getApplicationContext(), "리스트를 선택해주세요!", Toast.LENGTH_LONG).show();
                } else if (list.size() == 0) {
                    Toast.makeText(getApplicationContext(), "더이상 삭제할 게 없네요!", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        list.remove(num);
                        adapter.notifyDataSetChanged();
                        textView.setText(list.get(num));
                    } catch (Exception e) {

                    }
                }

            }
        });
    }
}
