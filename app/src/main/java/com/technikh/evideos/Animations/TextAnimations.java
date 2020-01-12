package com.technikh.evideos.Animations;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;

public class TextAnimations {
    private static AnimatorSet mAnimatorSet;

    public static Techniques getAnimationsTech(String animationName) {
        Techniques techniques1 = null;
        switch (animationName) {
            case "ZoomIn":
                techniques1 = Techniques.ZoomIn;
                break;
            case "ZoomInDown":
                techniques1 = Techniques.ZoomInDown;
                break;
            case "ZoomInLeft":
                techniques1 = Techniques.ZoomInLeft;
                break;
            case "ZoomInRight":
                techniques1 = Techniques.ZoomInRight;
                break;case "ZoomInUp":
                techniques1 = Techniques.ZoomIn;
                break;
            case "ZoomOut":
                techniques1 = Techniques.ZoomOutDown;
                break;
            case "ZoomOutLeft":
                techniques1 = Techniques.ZoomOutRight;
                break;
            case "ZoomOutUp":
                techniques1 = Techniques.ZoomOutUp;
                break;
            case "SlideInLeft":
                techniques1 = Techniques.SlideInLeft;
                break;
            case "SlideInRight":
                techniques1 = Techniques.SlideInRight;
                break;
            case "SlideInUp":
                techniques1 = Techniques.SlideInUp;
                break;
            case "SlideInDown":
                techniques1 = Techniques.SlideInDown;
                break;
            case "SlideOutLeft":
                techniques1 = Techniques.SlideOutLeft;
                break;
            case "SlideOutRight":
                techniques1 = Techniques.SlideOutRight;
                break;
            case "SlideOutUp":
                techniques1 = Techniques.SlideOutUp;
                break;
            case "RotateIn":
                techniques1 = Techniques.RotateIn;
                break;
            case "RotateInDownLeft":
                techniques1 = Techniques.RotateInDownLeft;
                break;
            case "RotateInDownRight":
                techniques1 = Techniques.RotateInDownRight;
                break;

            case "RotateInUpLeft":
                techniques1 = Techniques.RotateInUpLeft;
                break;
            case "RotateInUpRight":
                techniques1 = Techniques.RotateInUpRight;
                break;
            case "RotateOut":
                techniques1 = Techniques.RotateOut;
                break;
            case "RotateOutDownLeft":
                techniques1 = Techniques.RotateOutDownLeft;
                break;
            case "RotateOutDownRight":
                techniques1 = Techniques.RotateOutDownRight;
                break;
            case "RotateOutUpLeft":
                techniques1 = Techniques.RotateOutUpLeft;
                break;
            case "RotateOutUpRight":
                techniques1 = Techniques.RotateOutUpRight;
                break;
            case "FlipInX":
                techniques1 = Techniques.FlipInX;
                break;
            case "FlipOutX":
                techniques1 = Techniques.FlipOutX;
                break;
            case "FlipOutY":
                techniques1 = Techniques.FlipOutY;
                break;

            case "Flash":
                techniques1 = Techniques.Flash;
                break;
            case "Pulse":
                techniques1 = Techniques.Pulse;
                break;
            case "RubberBand":
                techniques1 = Techniques.Shake;
                break;
            case "Swing":
                techniques1 = Techniques.Swing;
                break;
            case "Wobble":
                techniques1 = Techniques.Wobble;
                break;
            case "Bounce":
                techniques1 = Techniques.Bounce;
                break;
            case "Tada":
                techniques1 = Techniques.Tada;
                break;
            case "StandUp":
                techniques1 = Techniques.StandUp;
                break;
            case "Wave":
                techniques1 = Techniques.Wave;
                break;
            case "Hinge":
                techniques1 = Techniques.Hinge;
                break;


            case "RollIn":
                techniques1 = Techniques.RollIn;
                break;
            case "RollOut":
                techniques1 = Techniques.RollOut;
                break;
            case "Landing":
                techniques1 = Techniques.Landing;
                break;
            case "TakingOff":
                techniques1 = Techniques.TakingOff;
                break;
            case "DropOut":
                techniques1 = Techniques.DropOut;
                break;
            case "BounceIn":
                techniques1 = Techniques.BounceIn;
                break;
            case "BounceInDown":
                techniques1 = Techniques.BounceInDown;
                break;
            case "BounceInLeft":
                techniques1 = Techniques.BounceInLeft;
                break;
            case "BounceInRight":
                techniques1 = Techniques.BounceInRight;
                break;
            case "BounceInUp":
                techniques1 = Techniques.BounceInUp;
                break;


            case "FadeIn":
                techniques1 = Techniques.FadeIn;
                break;
            case "FadeInUp":
                techniques1 = Techniques.FadeInUp;
                break;
            case "FadeInDown":
                techniques1 = Techniques.FadeInDown;
                break;
            case "FadeInLeft":
                techniques1 = Techniques.FadeInLeft;
                break;
            case "FadeInRight":
                techniques1 = Techniques.FadeInRight;
                break;
            case "FadeOut":
                techniques1 = Techniques.FadeOut;
                break;
            case "FadeOutDown":
                techniques1 = Techniques.FadeOutDown;
                break;
            case "FadeOutLeft":
                techniques1 = Techniques.FadeOutLeft;
                break;
            case "FadeOutRight":
                techniques1 = Techniques.FadeOutRight;
                break;
            case "FadeOutUp":
                techniques1 = Techniques.FadeOutUp;
                break;

        }

        return techniques1;
    }

    public static void FadeOutDown(int duration, View target){
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setDuration(duration);
        mAnimatorSet.start();

        mAnimatorSet.playTogether(
                ObjectAnimator.ofFloat(target, "alpha", 1, 0),
                ObjectAnimator.ofFloat(target, "translationY", 0, target.getHeight() / 4)
        );
    }

}
