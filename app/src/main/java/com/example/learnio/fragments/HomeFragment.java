package com.example.learnio.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.learnio.R;
import com.example.learnio.actions.CategoryActionListner;
import com.example.learnio.actions.CoursesActionListner;
import com.example.learnio.adapter.CategoryAdapter;
import com.example.learnio.adapter.CoursesAdapter;
import com.example.learnio.model.Category;
import com.example.learnio.model.Courses;
import com.example.learnio.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class HomeFragment extends Fragment implements CoursesActionListner, CategoryActionListner {
    private RecyclerView rvCategory, rvCourses;
    private CategoryAdapter categoryAdapter;
    private CoursesAdapter coursesAdapter;

    private RecyclerView.LayoutManager categoryLayoutManger, coursesLayoutManager;
    private ImageView ivProfile;

    private FirebaseAuth firebaseAuth;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userRef = db.collection("USERS");
    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCategory();
        initCourses();

        getUser(firebaseAuth.getCurrentUser());
    }

    public void getUser(FirebaseUser firebaseUser) {
        userRef.document(firebaseUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    User db_user = documentSnapshot.toObject(User.class);
                    setIvProfile(db_user);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void initCategory() {
        rvCategory = getView().findViewById(R.id.rv_category);
        categoryAdapter = new CategoryAdapter(this);
        categoryLayoutManger = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        categoryAdapter.setCategories(Category.getCategories());

        rvCategory.setAdapter(categoryAdapter);
        rvCategory.setLayoutManager(categoryLayoutManger);
        rvCategory.setHasFixedSize(true);
    }

    public void initCourses() {
        rvCourses = getView().findViewById(R.id.rv_courses);

        coursesAdapter = new CoursesAdapter(getContext(), this);


        coursesLayoutManager = new GridLayoutManager(getContext(), 2);


        coursesAdapter.setCourses(Courses.getCourses());

        rvCourses.setAdapter(coursesAdapter);
        rvCourses.setLayoutManager(coursesLayoutManager);
        rvCourses.setHasFixedSize(true);
    }

    public void setIvProfile(User user) {

        ivProfile = getView().findViewById(R.id.iv_profile);
        Glide
                .with(ivProfile)
                .load(user.getImageUrl())
                .placeholder(R.drawable.dummy)
                .into(ivProfile);
    }

    @Override
    public void onCourseItemClick(Courses courses) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.host_fragment, new CourseDetailFragment(courses))
                .addToBackStack("")
                .commit();
    }

    @Override
    public void onCategoryItemClicked(String category) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.host_fragment, new CategoryCoursesFragment(category))
                .addToBackStack("")
                .commit();
    }
}
