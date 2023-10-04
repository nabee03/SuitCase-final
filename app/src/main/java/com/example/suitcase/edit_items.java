package com.example.suitcase;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class edit_items extends AppCompatActivity {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String IS_PURCHASED = "purchased";

    private DatabaseHelper databaseHelper;

    private EditText editTextName;
    private EditText editTextPrice;
    private EditText editTextDescription;
    private CircleImageView imageView;

    private Uri imageUri;
    private int id;
    private boolean isPurchased;

    public static Intent getIntent(Context context, Item item) {
        Intent intent = new Intent(context, edit_items.class);
        intent.putExtra(ID, item.getId());
        intent.putExtra(NAME, item.getName());
        intent.putExtra(PRICE, item.getPrice().toString());
        intent.putExtra(DESCRIPTION, item.getDescription());
        intent.putExtra(IMAGE, item.getImage().toString());
        intent.putExtra(IS_PURCHASED, item.isPurchased());

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        databaseHelper = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt(items_details.ID);
        isPurchased = bundle.getBoolean(items_details.IS_PURCHASED);
        String name = bundle.getString(items_details.NAME);
        String price = bundle.getString(items_details.PRICE);
        String description = bundle.getString(items_details.DESCRIPTION);
        imageUri = Uri.EMPTY;
        try {
            imageUri = Uri.parse(bundle.getString(items_details.IMAGE));
        } catch (NullPointerException e) {
            Toast.makeText(
                    this,
                    "Error occurred in identifying image resource!",
                    Toast.LENGTH_SHORT
            ).show();
        }

        Button buttonEditItem = findViewById(R.id.btnEdit);
        editTextName = findViewById(R.id.editItemName);
        editTextPrice = findViewById(R.id.editItemPrice);
        editTextDescription = findViewById(R.id.editItemDescription);
        imageView = findViewById(R.id.edit_item_image);

        editTextName.setText(name);
        editTextDescription.setText(description);
        editTextPrice.setText(price);
        imageView.setImageURI(imageUri);

        imageView.setOnClickListener(this::pickImage);
        buttonEditItem.setOnClickListener(this::saveItem);
    }

    private void pickImage(View view) {
        ImagePickUtility.pickImage(view, edit_items.this);
    }

    private void saveItem(View view) {
        String name = editTextName.getText().toString();
        if (name.isEmpty()) {
            editTextName.setError("Name field is empty");
            editTextName.requestFocus();
        }
        double price = 0;
        try {
            price = Double.parseDouble(editTextPrice.getText().toString());
        } catch (NullPointerException e) {
            Toast.makeText(
                    getApplicationContext(),
                    "Something wrong with price.",
                    Toast.LENGTH_SHORT
            ).show();
        } catch (NumberFormatException e) {
            Toast.makeText(
                    getApplicationContext(),
                    "Price should be a number",
                    Toast.LENGTH_SHORT
            ).show();
        }
        if (price <= 0) {
            editTextPrice.setError("Price should be greater than 0.");
            editTextPrice.requestFocus();
        }
        String description = editTextDescription.getText().toString();
        if(description.isEmpty()) {
            editTextDescription.setError("Description is empty.");
            editTextDescription.requestFocus();
        }
        Log.d("EditItem", "saving: {" + "id: "+ id + ", name: "+ name +", price: "+ price +", description: "+ description +", imageUri: "+ imageUri.toString() +", isPurchased: "+ isPurchased +"}");
        if (databaseHelper.update(id, name, price, description, imageUri.toString(), isPurchased)) {
            Toast.makeText(getApplicationContext(), "Saved Successfully",
                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), "Failed to save", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (data != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void finish() {
        super.finish();
    }
}


