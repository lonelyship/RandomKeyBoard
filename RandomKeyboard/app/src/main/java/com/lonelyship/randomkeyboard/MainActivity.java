package com.lonelyship.randomkeyboard;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnTouchListener, View.OnClickListener {

    GridView gv = null;
    GridView gv_2 = null;
    EditText editText = null;
    Context m_context = null;
    ArrayList<Integer> al_random = new ArrayList<Integer>();
    ArrayList<String> al_lineTwo = new ArrayList<String>();
    ArrayList<String> al_lineThree = new ArrayList<String>();
    ArrayList<String> al_lineFour = new ArrayList<String>();
    String strPassword = "";
    GridViewAdapter gridViewAdapter = new GridViewAdapter();
    GridViewAdapter_2 gridViewAdapter_2 = new GridViewAdapter_2();
    Boolean isLowerCase = true;
    String strShow = "";
    LinearLayout ll_keyboard = null;
    Button btnClear = null;
    Button btnDone = null;

    View.OnClickListener OCL_changeUL = null;
    View.OnClickListener OCL_backSpace = null;
    View.OnClickListener OCL_clear = null;
    View.OnClickListener OCL_done = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_context = this;

        ll_keyboard = (LinearLayout) findViewById(R.id.keyboard);
        ll_keyboard.setVisibility(View.GONE);
        //ll_keyboard.animate().translationY(1000);

        btnClear = (Button) findViewById(R.id.btn_clear);
        btnDone = (Button) findViewById(R.id.btn_done);

        OCL_clear = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strPassword = "";
                editText.setText("");
            }
        };

        OCL_done = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strShow = "";
                for (int i = 0; i < strPassword.length(); i++) {
                    strShow += "*";
                }
                editText.setText(strShow);

               // ll_keyboard.animate().translationY(1000);
                ll_keyboard.setVisibility(View.GONE);
            }
        };

        btnClear.setOnClickListener(OCL_clear);
        btnDone.setOnClickListener(OCL_done);

        for (int i = 0; i < 10; i++) {
            al_random.add(i);
        }

        for (int i = 0; i < 10; i++) {

            int num = (((int) (Math.random() * 10)));
            int temp = al_random.get(num);
            al_random.set(num, al_random.get(i));
            al_random.set(i, temp);

        }

        al_lineTwo.add("q");
        al_lineTwo.add("w");
        al_lineTwo.add("e");
        al_lineTwo.add("r");
        al_lineTwo.add("t");
        al_lineTwo.add("y");
        al_lineTwo.add("u");
        al_lineTwo.add("i");
        al_lineTwo.add("o");
        al_lineTwo.add("p");

        al_lineThree.add("a");
        al_lineThree.add("s");
        al_lineThree.add("d");
        al_lineThree.add("f");
        al_lineThree.add("g");
        al_lineThree.add("h");
        al_lineThree.add("j");
        al_lineThree.add("k");
        al_lineThree.add("l");

        al_lineFour.add("◎");
        al_lineFour.add("z");
        al_lineFour.add("x");
        al_lineFour.add("c");
        al_lineFour.add("v");
        al_lineFour.add("b");
        al_lineFour.add("n");
        al_lineFour.add("m");
        al_lineFour.add("◄");

        //Log.e("lonelyshipTest", al_random.toString());

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        editText = (EditText) findViewById(R.id.editText);

        editText.setOnTouchListener(this);

        gv = (GridView) findViewById(R.id.gridView);
        gv_2 = (GridView) findViewById(R.id.gridView2);

        gv.setAdapter(gridViewAdapter);
        gv_2.setAdapter(gridViewAdapter_2);

        OCL_changeUL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLowerCase) {
                    isLowerCase = false;
                } else {
                    isLowerCase = true;
                }
                gridViewAdapter.notifyDataSetChanged();
                gridViewAdapter_2.notifyDataSetChanged();
            }
        };

        OCL_backSpace = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (strPassword.length() > 0) {
                    strShow = "";
                    strPassword = strPassword.substring(0, strPassword.length() - 1);
                    for (int i = 0; i < strPassword.length(); i++) {
                        strShow += "*";
                    }
                    editText.setText(strShow);
                }
                Log.e("lonelyshipTest", strPassword);
            }
        };


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
     editText.setInputType(InputType.TYPE_NULL);

     Log.e("lonelyshipTest",editText.getY()+"");

     ll_keyboard.setVisibility(View.VISIBLE);
     ll_keyboard.animate().translationY(editText.getY()+editText.getHeight());
        return false;
    }

    @Override
    public void onClick(View v) {

        Button btnView = (Button) v;

        strPassword += btnView.getText();
        strShow = "";

        char aa[] = new char[strPassword.length()];

        for (int i = 0; i < strPassword.length(); i++) {
            aa[i] = strPassword.charAt(i);
        }

        for (int i = 0; i < strPassword.length() - 1; i++) {
            aa[i] = '*';
        }

        for (int i = 0; i < aa.length; i++) {
            strShow += aa[i];
        }

        editText.setText(strShow);

        Log.e("lonelyshipTest", strPassword);
    }

    private class GridViewAdapter extends BaseAdapter {

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Button btn;

            LayoutInflater layoutInflater = LayoutInflater.from(m_context);
            View view = layoutInflater.inflate(R.layout.img_layout, null);

            if (convertView == null) {
                btn = (Button) view.findViewById(R.id.btn);
                btn.setPadding(5, 0, 5, 0);
            } else {
                btn = (Button) convertView;
            }
            btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_style));
            if (position < 10) {
                btn.setText(al_random.get(position).toString());
            } else if (position >= 10 && position < 20) {
                if (isLowerCase) {
                    btn.setText(al_lineTwo.get(position - 10).toLowerCase().toString());
                } else {
                    btn.setText(al_lineTwo.get(position - 10).toUpperCase().toString());
                }
            }
            btn.setOnClickListener(MainActivity.this);

            return btn;
        }

        @Override
        public long getItemId(int position) {
            //System.out.println("getItemId = " + position);
            return position;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public int getCount() {
            return 20;
        }
    }

    private class GridViewAdapter_2 extends BaseAdapter {

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Button btn;

            LayoutInflater layoutInflater = LayoutInflater.from(m_context);
            View view = layoutInflater.inflate(R.layout.img_layout2, null);

            if (convertView == null) {
                btn = (Button) view.findViewById(R.id.btn);
                btn.setPadding(5, 0, 5, 0);
            } else {
                btn = (Button) convertView;
            }
            btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_style));
            if (position < 9) {
                if (isLowerCase) {
                    btn.setText(al_lineThree.get(position).toLowerCase().toString());
                } else {
                    btn.setText(al_lineThree.get(position).toUpperCase().toString());
                }
            } else if (position >= 9 && position < 18) {
                if (isLowerCase) {
                    btn.setText(al_lineFour.get(position - 9).toLowerCase().toString());
                } else {
                    btn.setText(al_lineFour.get(position - 9).toUpperCase().toString());
                }
            }

            if (position == 9) {
                btn.setOnClickListener(OCL_changeUL);
            } else if (position == 17) {
                btn.setOnClickListener(OCL_backSpace);
            } else {
                btn.setOnClickListener(MainActivity.this);
            }



            return btn;
        }

        @Override
        public long getItemId(int position) {
            //System.out.println("getItemId = " + position);
            return position;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public int getCount() {
            return 18;
        }
    }
}
