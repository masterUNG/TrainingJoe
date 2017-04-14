package appewtc.masterung.trainingjoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        try {

            String urlPHP = "http://swiftcodingthai.com/joe/getFood.php";
            GetUser getUser = new GetUser(ServiceActivity.this);
            getUser.execute(urlPHP);
            String strJSON = getUser.get();
            Log.d("14AprilV2", "JSoN ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            String[] foodStrings = new String[jsonArray.length()];
            String[] priceStrings = new String[jsonArray.length()];
            String[] iconStrings = new String[jsonArray.length()];

            for (int i=0;i<jsonArray.length();i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                foodStrings[i] = jsonObject.getString("Food");
                priceStrings[i] = jsonObject.getString("Price");
                iconStrings[i] = jsonObject.getString("Source");

            }   // for

            FoodAdapter foodAdapter = new FoodAdapter(ServiceActivity.this,
                    foodStrings, priceStrings, iconStrings);
            ListView listView = (ListView) findViewById(R.id.livFood);
            listView.setAdapter(foodAdapter);

        } catch (Exception e) {
            Log.d("14AprilV2", "e Service ==> " + e.toString());
        }


    }   // Main Method

}   // Main Class
