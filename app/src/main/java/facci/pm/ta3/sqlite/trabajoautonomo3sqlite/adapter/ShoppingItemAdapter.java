package facci.pm.ta3.sqlite.trabajoautonomo3sqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import facci.pm.ta3.sqlite.trabajoautonomo3sqlite.R;
import facci.pm.ta3.sqlite.trabajoautonomo3sqlite.database.model.ShoppingItem;
import java.util.ArrayList;

public class ShoppingItemAdapter extends ArrayAdapter<ShoppingItem> {

    private Context context;
    private ArrayList<ShoppingItem> shoppingItems;

    public ShoppingItemAdapter(Context context, ArrayList<ShoppingItem> shoppingItems) {
        super(context, R.layout.item_list_shopping);
        this.context = context;
        this.shoppingItems = shoppingItems;
    }

    @Override
    public int getCount() {
        return shoppingItems.size();
    }

    @Override
    public ShoppingItem getItem(int position) {
        return  shoppingItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return shoppingItems.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        final ViewHolder viewHolder;

        if (convertView == null || convertView.getTag() == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_list_shopping, parent, false);
            viewHolder.mItemName = view.findViewById(R.id.shopping_tv_item_name);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            view = convertView;
        }

        // Set text with the item name
        viewHolder.mItemName.setText(shoppingItems.get(position).getName());

        return view;
    }

    static class ViewHolder {
        TextView mItemName;
    }
}
