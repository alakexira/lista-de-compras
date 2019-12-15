package facci.pm.ta3.sqlite.trabajoautonomo3sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import facci.pm.ta3.sqlite.trabajoautonomo3sqlite.adapter.ShoppingItemAdapter;
import facci.pm.ta3.sqlite.trabajoautonomo3sqlite.database.entities.ShoppingItemDB;
import facci.pm.ta3.sqlite.trabajoautonomo3sqlite.database.model.ShoppingItem;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private ShoppingItemDB shoppingItemDB;
    private ShoppingItemAdapter shoppingItemAdapter;
    private ArrayList<ShoppingItem> shoppingItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.shopping_list_view);

        shoppingItemDB = new ShoppingItemDB(this);

        shoppingItemDB.clearAllItems();

        insertProducts();

        shoppingItems.addAll(shoppingItemDB.getAllItems());

        shoppingItemAdapter = new ShoppingItemAdapter(this, shoppingItems);
        listView.setAdapter(shoppingItemAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_update_list) {
            updateProducts();
            updateList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void insertProducts() {
        shoppingItemDB.insertElement("Tomatoes");
        shoppingItemDB.insertElement("Water");
        shoppingItemDB.insertElement("Apples");
        shoppingItemDB.insertElement("Soup");
        shoppingItemDB.insertElement("Coffee");
        shoppingItemDB.insertElement("Bread");
        shoppingItemDB.insertElement("Juice");
        shoppingItemDB.insertElement("Pizza");
        shoppingItemDB.insertElement("Mozzarella");
        shoppingItemDB.insertElement("Onion");
        shoppingItemDB.insertElement("Milk");
        shoppingItemDB.insertElement("Eggs");
        shoppingItemDB.insertElement("Bananas");
        shoppingItemDB.insertElement("Toilet rolls");
        shoppingItemDB.insertElement("Butter");
        shoppingItemDB.insertElement("Carrots");
    }

    private void updateProducts() {
        if (shoppingItems.size() >= 15) {

            shoppingItems.get(8).setName("Cheese");
            shoppingItems.get(9).setName("Jam");
            shoppingItemDB.updateItem(shoppingItems.get(8));
            shoppingItemDB.updateItem(shoppingItems.get(9));

            shoppingItemDB.deleteItem(shoppingItems.get(0));
            shoppingItemDB.deleteItem(shoppingItems.get(1));
            shoppingItemDB.deleteItem(shoppingItems.get(2));
        }
    }

    private void updateList() {
        shoppingItems.clear();
        shoppingItems.addAll(shoppingItemDB.getAllItems());
        shoppingItemAdapter.notifyDataSetChanged();
    }
}
