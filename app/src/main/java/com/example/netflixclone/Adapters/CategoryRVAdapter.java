package com.example.netflixclone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
//import android.widget.Toast;

import androidx.annotation.NonNull;
//import androidx.paging.LoadState;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.LifecycleOwner;
import androidx.paging.PagingConfig;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflixclone.Data.CategoryRVModel;
import com.example.netflixclone.Data.VideoRVModel;
import com.example.netflixclone.R;
import com.example.netflixclone.ViewHolder.CategoryViewHolder;
import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;


public class CategoryRVAdapter extends FirestorePagingAdapter<CategoryRVModel, CategoryViewHolder> {

    private TextView categoryTV;
    private Context ctx;
    private ProgressBar loadingPB;

    /**
     * Construct a new FirestorePagingAdapter from the given {@link FirestorePagingOptions}.
     *
     * @param options
     */
    public CategoryRVAdapter(@NonNull FirestorePagingOptions<CategoryRVModel> options,Context ctx, ProgressBar loadingPB) {
        super(options);
        this.ctx = ctx;
        this.loadingPB = loadingPB;
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryViewHolder holder, int position, @NonNull CategoryRVModel model) {
        categoryTV = holder.itemView.findViewById(R.id.idTVCategory);
        holder.setCategoryTV(model.getCategoryName());
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().build();
        firebaseFirestore.setFirestoreSettings(settings);
        FirestorePagingAdapter videoRVAdapter = new VideoRVAdapter((getFirestorePaging(firebaseFirestore,model.getCategoryName())));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx,LinearLayoutManager.HORIZONTAL,false);
        holder.setVideosTv(videoRVAdapter,layoutManager);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_rv_item,parent,false);
        return new CategoryViewHolder(view);
    }

    private Query getChildQuery(FirebaseFirestore firebaseFirestore, String category){
        Query query = firebaseFirestore.collection("Videos").whereEqualTo("videoCategory",category);
        query.orderBy("timestamp",Query.Direction.DESCENDING);
        return query;
    }

    private FirestorePagingOptions<VideoRVModel> getFirestorePaging(FirebaseFirestore firebaseFirestore, String category)
    {
        Query baseQuery = getChildQuery(firebaseFirestore,category);
        PagingConfig config = new PagingConfig(15,15,true);

        return new FirestorePagingOptions.Builder<VideoRVModel>()
                .setLifecycleOwner((LifecycleOwner)ctx)
                .setQuery(baseQuery,config,VideoRVModel.class)
                .build();
    }

//    private void toggleProgressBar(boolean noData,boolean initialLoad){
//        if(initialLoad){
//            loadingPB.setVisibility(View.VISIBLE);
//        }
//        else if(noData){
//            loadingPB.setVisibility(View.GONE);
//            Toast.makeText(ctx,"No Data Found!",Toast.LENGTH_SHORT).show();
//        }
//        else{
//            loadingPB.setVisibility(View.GONE);
//        }
//    }
//
}
