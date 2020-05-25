package br.com.luizmoratelli.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Interpolator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {
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
        onLoadAddListeners(this);
    }

    private void onClickScaleButton() {
        Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.scale);
        animation.setAnimationListener(this);

        imgView.requestLayout();
        imgView.setAnimation(animation);

        animation.start();
    }

    private void onLoadAddListeners(final Context context) {
        Button buttonHide = findViewById(R.id.button_hide);
        Button buttonShow = findViewById(R.id.button_show);
        Button buttonMoveLeft = findViewById(R.id.button_move);
        Button buttonMoveRight = findViewById(R.id.button_move2);
        Button buttonRotation = findViewById(R.id.button_rotation);
        Button buttonScale = findViewById(R.id.button_scale);

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
                ObjectAnimator animatorLeft = ObjectAnimator.ofFloat(imgView,
                        "translationX", -400);
                ObjectAnimator animatorRight = ObjectAnimator.ofFloat(imgView, "translationX", 400);
                ObjectAnimator animatorCenter = ObjectAnimator.ofFloat(imgView, "translationX", 0);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(animatorLeft).before(animatorRight);
                animatorSet.play(animatorRight).before(animatorCenter);
                animatorSet.setDuration(500);
                animatorSet.start();
            }
        });

        buttonMoveRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animatorLeft = ObjectAnimator.ofFloat(imgView,
                        "translationX", -400);
                ObjectAnimator animatorRight = ObjectAnimator.ofFloat(imgView, "translationX", 400);
                ObjectAnimator animatorCenter = ObjectAnimator.ofFloat(imgView, "translationX", 0);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(animatorRight).before(animatorLeft);
                animatorSet.play(animatorLeft).before(animatorCenter);
                animatorSet.setDuration(500);
                animatorSet.start();
            }
        });

        buttonRotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Animator> animators = new ArrayList<Animator>();
                ObjectAnimator animatorRotationRight = ObjectAnimator.ofFloat(imgView, "rotation", 30);
                ObjectAnimator animatorRotationLeft = ObjectAnimator.ofFloat(imgView, "rotation", -30);
                ObjectAnimator animatorRotationZero = ObjectAnimator.ofFloat(imgView, "rotation", 0);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(400);
                animatorSet.playSequentially(animatorRotationRight, animatorRotationLeft, animatorRotationZero);
                animatorSet.start();
            }
        });

        buttonScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickScaleButton();
            }
        });
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
