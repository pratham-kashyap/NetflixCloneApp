package com.example.netflixclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagingConfig;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.netflixclone.Adapters.CategoryRVAdapter;
import com.example.netflixclone.Data.CategoryRVModel;
import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.idRVCategories);
        loadingPB = findViewById((R.id.idPBLoading));
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();

        firebaseFirestore.setFirestoreSettings(settings);
        FirestorePagingAdapter categoryRVAdapter = new CategoryRVAdapter(getFirestorePagingOptions(firebaseFirestore),this,loadingPB);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(categoryRVAdapter);
    }

    private Query getQuery(FirebaseFirestore firebaseFirestore){
        return firebaseFirestore.collection("Categories").orderBy("categoryName");

    }

    private FirestorePagingOptions<CategoryRVModel> getFirestorePagingOptions(FirebaseFirestore firebaseFirestore){
        Query baseQuery = getQuery(firebaseFirestore);
        PagingConfig config = new PagingConfig(15,15,true);
        return new FirestorePagingOptions.Builder<CategoryRVModel>()
                .setLifecycleOwner(this)
                .setQuery(baseQuery,config,CategoryRVModel.class)
                .build();

    }
}