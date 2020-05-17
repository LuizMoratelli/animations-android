package br.com.luizmoratelli.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private AnimationDrawable animationDrawable;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView = findViewById(R.id.imageView);
        imgView.setBackgroundResource(R.drawable.sprite_pokeball);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                } else {
                    animationDrawable.start();
                }
            }
        });

        animationDrawable = (AnimationDrawable) imgView.getBackground();
        onLoadAddListeners();
    }

    private void onLoadAddListeners() {
        Button buttonHide = findViewById(R.id.button_hide);
        Button buttonShow = findViewById(R.id.button_show);
        Button buttonMoveLeft = findViewById(R.id.button_move);
        Button buttonMoveRight = findViewById(R.id.button_move2);

        buttonHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(imgView,
                        "alpha", 0);
                animator.setDuration(2000);
                animator.start();
            }
        });

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(imgView,
                        "alpha", 1);
                animator.setDuration(2000);
                animator.start();
            }
        });

        buttonMoveLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(imgView,
                        "translationX", -400);
                animator.setDuration(2000);
                animator.start();
            }
        });
        buttonMoveRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(imgView,
                        "translationX", 400);
                animator.setDuration(2000);
                animator.start();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_right);
    }
}
