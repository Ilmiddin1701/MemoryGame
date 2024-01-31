package com.example.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var image1: ImageView
    private lateinit var image2: ImageView
    private lateinit var image3: ImageView
    private lateinit var image4: ImageView
    private lateinit var image5: ImageView
    private lateinit var image6: ImageView
    private val imageIndex = arrayOfNulls<Int>(2)
    private val imageId = arrayOfNulls<Int>(2)
    private var openImage = 0
    private val listImageOpenClosed = arrayOf(false, false, false, false, false, false)
    private var animationDoing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image1 = findViewById(R.id.image_1)
        image2 = findViewById(R.id.image_2)
        image3 = findViewById(R.id.image_3)
        image4 = findViewById(R.id.image_4)
        image5 = findViewById(R.id.image_5)
        image6 = findViewById(R.id.image_6)

        image1.setOnClickListener{
            imageClick(image1, R.drawable.nature1, 0)
        }
        image2.setOnClickListener{
            imageClick(image2, R.drawable.nature2, 1)
        }
        image3.setOnClickListener{
            imageClick(image3, R.drawable.nature3, 2)
        }
        image4.setOnClickListener{
            imageClick(image4, R.drawable.nature1, 3)
        }
        image5.setOnClickListener{
            imageClick(image5, R.drawable.nature2, 4)
        }
        image6.setOnClickListener{
            imageClick(image6, R.drawable.nature3, 5)
        }

    }
    private fun imageClick(imageView: ImageView, image: Int, index: Int){
        if (!animationDoing) {
            if (!listImageOpenClosed[index]) {
                openingAnimation(imageView, image, index)
            } else {
                closingAnimation(imageView, image, index)
            }
        }
    }
    private fun openingAnimation(imageView: ImageView, image: Int, index: Int){
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(image)
                animation2.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        listImageOpenClosed[index] = true
                        imageIndex[openImage] = index
                        imageId[openImage] = image
                        openImage++
                        if (openImage == 2){
                            if (imageId[0] == imageId[1]){
                                imageViewDetermination(imageIndex[0]).visibility = View.INVISIBLE
                                openImage--
                                imageViewDetermination(imageIndex[1]).visibility = View.INVISIBLE
                                openImage--
                            }else{
                                closingAnimation(imageViewDetermination(imageIndex[0]), -1, imageIndex[0])
                                closingAnimation(imageViewDetermination(imageIndex[1]), -1, imageIndex[1])
                            }
                        }
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
    }
    private fun closingAnimation(imageView: ImageView, image: Int, index: Int?){
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(R.drawable.star)
                animation2.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        listImageOpenClosed[index!!] = false
        openImage--
    }
    private fun imageViewDetermination(index: Int?):ImageView{
        var imageView: ImageView? = null
        when (index){
            0-> imageView = image1
            1-> imageView = image2
            2-> imageView = image3
            3-> imageView = image4
            4-> imageView = image5
            5-> imageView = image6
        }
        return imageView!!
    }
}