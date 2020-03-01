package com.example.amanelshark.view;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.R;
import com.example.amanelshark.databinding.ActivityDetailsServicesBinding;
import com.example.amanelshark.model.centerDetails.CenterDetails;
import com.example.amanelshark.model.requestwarranty.RequestWarranty;
import com.example.amanelshark.model.uploadimage.UploadImage;
import com.example.amanelshark.model.warrantyresponse.WarrantyResponse;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Objects;

import javax.inject.Inject;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class DetailsServicesActivity extends AppCompatActivity {
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST = 1888;
    private static int RESULT_LOAD_IMG = 1;
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    AmanElsharkViewModel userViewModel;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ActivityDetailsServicesBinding activityDetailsServicesBinding;
    String token;
    int package_id, center_id, car_id, request_id;
    String selectedImagePath;
    File f;
    Intent intent1;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_services);
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        Intent intent = getIntent();
        package_id = intent.getIntExtra("package_id", 0);
        center_id = intent.getIntExtra("id_event", 0);
        car_id = intent.getIntExtra("client_car_id", 0);
        request_id = intent.getIntExtra("request_id", 0);

        token = sharedPref.getString(getString(R.string.token), "null");
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        activityDetailsServicesBinding = DataBindingUtil.setContentView(this, R.layout.activity_details_services);
        userViewModel = new ViewModelProvider(this, viewModelProvider).get(AmanElsharkViewModel.class);
        userViewModel.getCentersDetailsRequests(getApplicationContext(), token, center_id).observe(this, centerDetails -> {
            activityDetailsServicesBinding.nameDetails.setText(centerDetails.getDataCenter().getName());
            activityDetailsServicesBinding.ratingstarDetails.setRating(centerDetails.getDataCenter().getRate());
            activityDetailsServicesBinding.toolbarServiestext.setText(centerDetails.getDataCenter().getName());
            activityDetailsServicesBinding.phoneDetails.setText(centerDetails.getDataCenter().getPhone());
            activityDetailsServicesBinding.descriptionDetails.setText(centerDetails.getDataCenter().getDescription());
            activityDetailsServicesBinding.workinghourDeatils.setText(centerDetails.getDataCenter().getWorkingDays() + "  -  " + centerDetails.getDataCenter().getWorkingHours());
        });
        activityDetailsServicesBinding.imagebackDetails.setOnClickListener(v -> finish());
        if (package_id == 0) {
            activityDetailsServicesBinding.scanDetails.setVisibility(View.VISIBLE);
            activityDetailsServicesBinding.requestDetails.setVisibility(View.GONE);

        }
        activityDetailsServicesBinding.requestDetails.setOnClickListener(v -> {

            userViewModel.makeWarantyRequests(getApplicationContext(), token, package_id, center_id, car_id).observe(this, requestWarranty -> {
                Toast.makeText(DetailsServicesActivity.this, "done", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(), MainHomeActivity.class);
                intent1.putExtra("viewpager_position", 0);

                finish();
                startActivity(intent1);
            });

        });
        activityDetailsServicesBinding.scanDetails.setOnClickListener(v -> {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
            }
            else {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
                 intent1 = new Intent(getApplicationContext(),MainHomeActivity.class);
                intent.putExtra("viewpager_position",0);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {

            Uri imageUri = data.getData();
            selectedImagePath = getPath(imageUri);

            f = new File(selectedImagePath);
          //  File file = new File(Objects.requireNonNull(imageUri.getPath()));


            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), f);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", f.getName(), requestBody);
            userViewModel.uploadImageRequests(getApplication(), token, 2, fileToUpload).observe(this, new Observer<UploadImage>() {
                @Override
                public void onChanged(UploadImage uploadImage) {
                    finish();
                    startActivity(intent1);

                }
            });


        } else {
            Toast.makeText(getApplicationContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    public String getPath(Uri uri) {
        // just some safety built in
        if (uri == null) {
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = {MediaStore.Images.Media.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null,
                null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }
}
