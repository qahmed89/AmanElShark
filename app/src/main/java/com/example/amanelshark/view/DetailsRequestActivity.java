package com.example.amanelshark.view;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.R;
import com.example.amanelshark.databinding.ActivityAddWarrantyPeriodBinding;
import com.example.amanelshark.databinding.ActivityDetailsRequestBinding;
import com.example.amanelshark.model.detailsrequest.DetailsRequest;
import com.example.amanelshark.model.packagedetails.PackageDetails;
import com.example.amanelshark.model.uploadimage.UploadImage;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;

import java.io.File;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class DetailsRequestActivity extends AppCompatActivity {
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int MY_WRITE_PERMISSION_CODE = 100;
    private static int RESULT_LOAD_IMG = 1;
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    AmanElsharkViewModel userViewModel;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ActivityDetailsRequestBinding activityDetailsRequestBinding;
    String token;
    int id_request, package_id;
    String selectedImagePath;
    File f;
    Intent intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_request);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        activityDetailsRequestBinding = DataBindingUtil.setContentView(this, R.layout.activity_details_request);
        userViewModel = new ViewModelProvider(this, viewModelProvider).get(AmanElsharkViewModel.class);
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        token = sharedPref.getString(getString(R.string.token), "null");
        Intent intent = getIntent();
        id_request = intent.getIntExtra("id_request", 0);
        package_id = intent.getIntExtra("package_id", 0);
        activityDetailsRequestBinding.toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
        userViewModel.getPackagesDetailsRequests(getApplicationContext(), token, package_id).observe(this, details -> activityDetailsRequestBinding.packagenameDetailsrequest.setText(details.getData().getName()));
        userViewModel.getrequestDetails(getApplicationContext(), token, id_request).observe(this, detailsRequest -> {
            activityDetailsRequestBinding.centernDetailsrequest.setText(detailsRequest.getData().getCenterName());
            activityDetailsRequestBinding.statusDetailsrequest.setText(detailsRequest.getData().getStatus());
            activityDetailsRequestBinding.requestidDetailsrequest.setText(String.valueOf(detailsRequest.getData().getId()));
        });
        activityDetailsRequestBinding.ScaninvoiceDeatilsrequest.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            }
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //  ActivityCompat. requestPermissions(DetailsRequestActivity.this,new String[]{Manifest.permission.CAMERA},MY_CAMERA_PERMISSION_CODE);
                ActivityCompat.requestPermissions(DetailsRequestActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_WRITE_PERMISSION_CODE);

            } else {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);

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
            userViewModel.uploadImageRequests(getApplication(), token, id_request, fileToUpload).observe(this, new Observer<UploadImage>() {
                @Override
                public void onChanged(UploadImage uploadImage) {
                    intent1 = new Intent(getApplicationContext(), MainHomeActivity.class);
                    intent1.putExtra("viewpager_position", 0);
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
