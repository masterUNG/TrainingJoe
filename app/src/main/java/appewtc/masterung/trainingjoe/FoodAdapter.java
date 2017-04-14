package appewtc.masterung.trainingjoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by masterUNG on 4/14/2017 AD.
 */

public class FoodAdapter extends BaseAdapter{

    private Context context;
    private String[] foodStrings, priceStrings, iconStrings;
    private TextView foodTextView, priceTextView;
    private ImageView imageView;

    public FoodAdapter(Context context,
                       String[] foodStrings,
                       String[] priceStrings,
                       String[] iconStrings) {
        this.context = context;
        this.foodStrings = foodStrings;
        this.priceStrings = priceStrings;
        this.iconStrings = iconStrings;
    }

    @Override
    public int getCount() {
        return foodStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.food_layout, viewGroup, false);

        //Initial View
        foodTextView = (TextView) view1.findViewById(R.id.txtFood);
        priceTextView = (TextView) view1.findViewById(R.id.txtPrice);
        imageView = (ImageView) view1.findViewById(R.id.imvIcon);

        //Show View
        foodTextView.setText(foodStrings[i]);
        priceTextView.setText(priceStrings[i]);
        Picasso.with(context).load(iconStrings[i]).into(imageView);

        return view1;
    }
}   // Main Class
