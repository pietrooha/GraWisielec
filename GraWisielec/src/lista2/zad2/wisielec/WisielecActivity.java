package lista2.zad2.wisielec;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import java.util.*;


public class WisielecActivity extends Activity {
    private ImageView image;
    private Button button;
    private TextView textView;
    private EditText pole;
    private int dlugoscSlowa;
    private int generatedIndex;
    private char zgadywanaLitera;
    private String zgadywanaLitera1;
    private String wylosowaneSlowo;
    private String[] words;
    private char[] literka;
    private String temp = "";
    private int indeks = 0;
    private int liczba_prob;
    private int liczba_pomylek;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		textView = (TextView)findViewById(R.id.randomTextView);
		image = (ImageView) findViewById(R.id.imageView1);

		liczba_prob = 0;
	wylosujSlowo();
	image = (ImageView) findViewById(R.id.imageView1);
	image.setImageResource(R.drawable.wisielec0);
    }

    public void wylosujSlowo() {
	words = getResources().getStringArray(R.array.words);
	Random random = new Random();

        int maxIndex = words.length;
        generatedIndex = random.nextInt(maxIndex);
	dlugoscSlowa = words[generatedIndex].length();
    }

    
    public void rozlozSlowoNaLitery() {
	wylosowaneSlowo = words[generatedIndex];
	
	for (int i = 0; i < wylosowaneSlowo.length(); ++i) // ustawienie temp na same znaki zapytania
		temp += '?';
		
	textView.setText(temp); // wyświetlenie znaków zapytania
	
	literka = wylosowaneSlowo.toCharArray();
    }

    public void clickButton1(View view) {
	button = (Button) findViewById(R.id.sprawdz);
	pole = (EditText)findViewById(R.id.editText1);
	if (!pole.getText().toString().isEmpty()) { // upewnienie sie czy wprowadzono napewno jakis znak
		zgadywanaLitera1 = pole.getText().toString();
		zgadywanaLitera = zgadywanaLitera1.charAt(0);
		czyLiteraZawieraSie();
		pole.setText("");
	}
    }

    public void czyLiteraZawieraSie() {
	rozlozSlowoNaLitery();
	
	String kopia = "";

	boolean zgadnieto = false;
	for (int i = 0; i < wylosowaneSlowo.length(); ++i)
	{
		if (wylosowaneSlowo.charAt(i) == zgadywanaLitera && temp.charAt(i) == '?' )
		{
			kopia += zgadywanaLitera;
			zgadnieto = true;
		}
		else
			kopia += temp.charAt(i);
	}
	
	temp = kopia;
	textView.setText(temp);
	
	if (!zgadnieto) { // jesli nie zgadnieto
	  ++liczba_pomylek;
	  switch (liczba_pomylek) { 			//rysuj szubienice
		  case 1:
				image.setImageResource(R.drawable.wisielec1);
				break;
		  case 2:
				image.setImageResource(R.drawable.wisielec2);
				break;
		  case 3:
				image.setImageResource(R.drawable.wisielec3);
				break;
		  case 4:
				image.setImageResource(R.drawable.wisielec4);
				break;
		  case 5:
				image.setImageResource(R.drawable.wisielec5);
				break;
		  case 6:
				image.setImageResource(R.drawable.wisielec6);
				Toast.makeText(this, "Niestety przegrales!", Toast.LENGTH_SHORT).show();
				break;
	  }
	}
    }
}
