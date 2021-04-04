package net.etna.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MenuAdapter extends BaseAdapter {

    private Context context;
    private List<Menu> menusList;

    public MenuAdapter(Context context, List menusList){
        this.context = context;
        this.menusList = menusList;
    }

    @Override
    public int getCount() {
        return menusList.size();
    }

    @Override
    public Object getItem(int position) {
        return menusList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_listview_menu, null);
        }

        Menu menu = menusList.get(position);

        TextView textViewRestaurantName = (TextView) convertView.findViewById(R.id.menuName);
        TextView textViewRestaurantDescription = (TextView) convertView.findViewById(R.id.menuDescription);

        textViewRestaurantName.setText(menu.getMenuName());
        textViewRestaurantDescription.setText(menu.getMenuDescription());

        return convertView;
    }

}
