package com.example.geolocationgta;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText editTextId, editTextAddress, editTextLatitude, editTextLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        editTextId = findViewById(R.id.editTextId);  // EditText for location ID (for delete and update operations)
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextLatitude = findViewById(R.id.editTextLatitude);
        editTextLongitude = findViewById(R.id.editTextLongitude);

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(view -> addLocation());

        Button buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(view -> deleteLocation());

        Button buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(view -> updateLocation());

        Button buttonQuery = findViewById(R.id.buttonQuery);
        buttonQuery.setOnClickListener(view -> queryLocation());
    }

    private void addLocation() {
        String address = editTextAddress.getText().toString().trim();
        String latStr = editTextLatitude.getText().toString().trim();
        String lonStr = editTextLongitude.getText().toString().trim();

        if (address.isEmpty() || latStr.isEmpty() || lonStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double latitude = Double.parseDouble(latStr);
        double longitude = Double.parseDouble(lonStr);

        if (dbHelper.addLocation(address, latitude, longitude)) {
            Toast.makeText(this, "Location added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error adding location", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteLocation() {
        String idStr = editTextId.getText().toString().trim();

        if (idStr.isEmpty()) {
            Toast.makeText(this, "Please enter an ID to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = Integer.parseInt(idStr);

        if (dbHelper.deleteLocation(id)) {
            Toast.makeText(this, "Location deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error deleting location or ID not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateLocation() {
        String idStr = editTextId.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String latStr = editTextLatitude.getText().toString().trim();
        String lonStr = editTextLongitude.getText().toString().trim();

        if (idStr.isEmpty() || address.isEmpty() || latStr.isEmpty() || lonStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = Integer.parseInt(idStr);
        double latitude = Double.parseDouble(latStr);
        double longitude = Double.parseDouble(lonStr);

        if (dbHelper.updateLocation(id, address, latitude, longitude)) {
            Toast.makeText(this, "Location updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error updating location or ID not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void queryLocation() {
        String address = editTextAddress.getText().toString().trim();

        if (address.isEmpty()) {
            Toast.makeText(this, "Please enter an address to query", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor cursor = dbHelper.getLocation(address);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") double latitude = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_LATITUDE));
            @SuppressLint("Range") double longitude = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_LONGITUDE));
            editTextLatitude.setText(String.valueOf(latitude));
            editTextLongitude.setText(String.valueOf(longitude));
            Toast.makeText(this, "Location found", Toast.LENGTH_SHORT).show();
            cursor.close();
        } else {
            Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();
        }
    }
}
