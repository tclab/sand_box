package android.com.calculator;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class calculator extends Activity {
	EditText primernumero;
	EditText segundonumero;
	EditText resultado;
	private static final int SALIR = Menu.FIRST;
	ListView myOperator;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(Menu.NONE, SALIR, 0, R.string.salir);
		return true;
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		primernumero = (EditText) findViewById(R.id.primernumero);
		myOperator = (ListView) findViewById(R.id.myOperator);
		final ArrayList<String> operatorArrayList = new ArrayList<String>();
		operatorArrayList.add("Add");
		operatorArrayList.add("Substract");
		operatorArrayList.add("Multiply");
		operatorArrayList.add("Divide");
		final ArrayAdapter<String> operatorAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_expandable_list_item_1,
				operatorArrayList);
		myOperator.setAdapter(operatorAdapter);
		segundonumero = (EditText) findViewById(R.id.segundonumero);
		resultado = (EditText) findViewById(R.id.resultado);
		// Button calcular = (Button) findViewById(R.id.calcular);
		Button salir = (Button) findViewById(R.id.salir);

		// final TextView campo=new TextView(this);
		// campo.setTag("Calculando...");

		// setContentView(campo);

		myOperator.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				resultado.setText("");
				double numero1 = Double.parseDouble(primernumero.getText()
						.toString());
				double numero2 = Double.parseDouble(segundonumero.getText()
						.toString());
				try {
					String operador = myOperator.getSelectedItem().toString();
					if (operador.equals("Add")) {
						resultado.append("=" + (numero1 + numero2));
					} else if (operador.equals("Substract")) {
						resultado.append("=" + (numero1 - numero2));
					} else if (operador.equals("Multiply")) {
						resultado.append(" = " + (numero1 * numero2));
					} else if (operador.equals("Divide")) {
						resultado.append(" = " + (numero1 / numero2));
					}

				} catch (Exception e) {
					System.out.println(e);
				}

			}

		});

		salir.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				f_salir();
			}
		});
	}

	public void f_salir() {
		setResult(RESULT_OK);
		finish();
	}

}