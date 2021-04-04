package net.etna.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class restaurantAdapter extends BaseAdapter {

    private Context context;
    private List<Restaurants> restaurantsList;

    public restaurantAdapter(Context context, List restaurantsList){
        this.context = context;
        this.restaurantsList = restaurantsList;
    }

    @Override
    public int getCount() {
        return restaurantsList.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_listview, null);
        }

        Restaurants restaurants = restaurantsList.get(position);

        TextView textViewRestaurantName = (TextView) convertView.findViewById(R.id.restaurantname);
        TextView textViewRestaurantDescription = (TextView) convertView.findViewById(R.id.restaurantDescription);

        textViewRestaurantName.setText(restaurants.getRestaurantName());
        textViewRestaurantDescription.setText(restaurants.getDescription());

        return convertView;
    }
}
