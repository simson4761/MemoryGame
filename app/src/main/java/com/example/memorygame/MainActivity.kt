package com.example.memorygame

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.View.INVISIBLE
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.memorygame.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val userName = intent.getStringExtra("Data")
        val decide = intent.getStringExtra("mode")
        binding.start.isClickable
        binding.congratulationText.visibility = INVISIBLE
        binding.pbForStart.visibility = INVISIBLE
        binding.pb.visibility = INVISIBLE
        binding.retry.visibility = INVISIBLE
        val initialTime : Long = 15000

        val locked  = resources.getDrawable(android.R.drawable.ic_lock_lock)
        val one     = resources.getDrawable(R.drawable._one)
        val two     = resources.getDrawable(R.drawable._two)
        val three   = resources.getDrawable(R.drawable._three)
        val four    = resources.getDrawable(R.drawable._four)
        val five    = resources.getDrawable(R.drawable._five)
        val six     = resources.getDrawable(R.drawable._six)
        val seven   = resources.getDrawable(R.drawable._seven)
        val eight   = resources.getDrawable(R.drawable._eight)
        val nine    = resources.getDrawable(R.drawable._nine)
        val randomNumber = arrayListOf(one , two , three , four , five , six , seven , eight , nine )
        val loosingMessage = arrayListOf("AT LEAST YOU TRIED $userName ","DON'T LOOSE HOPE $userName","VIDA MUYARCHI VISHWARUPA VETTRI!!!","THEEYA VELA SEYANUM $userName")
        val winningMessage = arrayListOf("NAMMA JEICHITOM $userName","SABASH BETA","CONGRATS $userName BRO","VETRI MIDHU VETRI VANDHU ENNAI SERUM")
        var previous : Drawable = locked
        var winningStatus = false


        lateinit var image1 : Drawable
        lateinit var image2 : Drawable
        lateinit var image3 : Drawable
        lateinit var image4 : Drawable
        lateinit var image5 : Drawable
        lateinit var image6 : Drawable
        lateinit var image7 : Drawable
        lateinit var image8 : Drawable
        lateinit var image9 : Drawable

        fun showAllImage(){
            binding.imageView1.setImageDrawable(image1)
            binding.imageView2.setImageDrawable(image2)
            binding.imageView3.setImageDrawable(image3)
            binding.imageView4.setImageDrawable(image4)
            binding.imageView5.setImageDrawable(image5)
            binding.imageView6.setImageDrawable(image6)
            binding.imageView7.setImageDrawable(image7)
            binding.imageView8.setImageDrawable(image8)
            binding.imageView9.setImageDrawable(image9)
        }

        fun hideAllImage(){
            binding.imageView1.setImageDrawable(locked)
            binding.imageView2.setImageDrawable(locked)
            binding.imageView3.setImageDrawable(locked)
            binding.imageView4.setImageDrawable(locked)
            binding.imageView5.setImageDrawable(locked)
            binding.imageView6.setImageDrawable(locked)
            binding.imageView7.setImageDrawable(locked)
            binding.imageView8.setImageDrawable(locked)
            binding.imageView9.setImageDrawable(locked)
        }

        fun makeUnclickable(){
            binding.imageView1.isClickable = false
            binding.imageView2.isClickable = false
            binding.imageView3.isClickable = false
            binding.imageView4.isClickable = false
            binding.imageView5.isClickable = false
            binding.imageView6.isClickable = false
            binding.imageView7.isClickable = false
            binding.imageView8.isClickable = false
            binding.imageView9.isClickable = false
        }

        fun makeClickable() {
            binding.imageView1.isClickable = true
            binding.imageView2.isClickable = true
            binding.imageView3.isClickable = true
            binding.imageView4.isClickable = true
            binding.imageView5.isClickable = true
            binding.imageView6.isClickable = true
            binding.imageView7.isClickable = true
            binding.imageView8.isClickable = true
            binding.imageView9.isClickable = true
        }

        fun setImage() {
            randomNumber.shuffle()
            image1 = randomNumber[0]
            image2 = randomNumber[1]
            image3 = randomNumber[2]
            image4 = randomNumber[3]
            image5 = randomNumber[4]
            image6 = randomNumber[5]
            image7 = randomNumber[6]
            image8 = randomNumber[7]
            image9 = randomNumber[8]
            previous = locked
        }

        fun showImage(y : ImageView){
            when(y){
                binding.imageView1 -> binding.imageView1.setImageDrawable(image1)
                binding.imageView2 -> binding.imageView2.setImageDrawable(image2)
                binding.imageView3 -> binding.imageView3.setImageDrawable(image3)
                binding.imageView4 -> binding.imageView4.setImageDrawable(image4)
                binding.imageView5 -> binding.imageView5.setImageDrawable(image5)
                binding.imageView6 -> binding.imageView6.setImageDrawable(image6)
                binding.imageView7 -> binding.imageView7.setImageDrawable(image7)
                binding.imageView8 -> binding.imageView8.setImageDrawable(image8)
                binding.imageView9 -> binding.imageView9.setImageDrawable(image9)
            }
        }


        val mainTimer = object : CountDownTimer(initialTime,1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                binding.pb.visibility = View.VISIBLE
                binding.pb.progress = (millisUntilFinished/100).toInt()
                binding.initialText.text = "TIME REMAINING : "+(millisUntilFinished/1000).toString()
            }
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                showAllImage()
                if (!winningStatus){
                    loosingMessage.shuffle()
                    binding.pbForStart.visibility = INVISIBLE
                    binding.pb.visibility = INVISIBLE
                    binding.initialText.text = loosingMessage[0]
                    Toast.makeText(this@MainActivity,"Time Over ( ͡° ͜ʖ ͡°)",Toast.LENGTH_SHORT).show()
                    binding.retry.visibility = View.VISIBLE
                }
            }
        }

        val initialTimer = object : CountDownTimer(6000,1000){
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                makeUnclickable()
                binding.pb.visibility = INVISIBLE
                binding.congratulationText.visibility = INVISIBLE
                binding.initialText.visibility = View.VISIBLE
                binding.pbForStart.visibility = View.VISIBLE
                winningStatus = false
                showAllImage()
                binding.pbForStart.progress= (millisUntilFinished/100).toInt()
                binding.initialText.text = "GAME STARTS IN : "+(millisUntilFinished/1000).toString()
            }
            override fun onFinish() {
                hideAllImage()
                makeClickable()
                binding.pbForStart.visibility = INVISIBLE
                mainTimer.start()
            }
        }

        @SuppressLint("SetTextI18n")
        fun winGame() {
            winningMessage.shuffle()
            Toast.makeText(this@MainActivity,"you won",Toast.LENGTH_SHORT).show()
            mainTimer.cancel()
            initialTimer.cancel()
            binding.pb.visibility = INVISIBLE
            binding.pbForStart.visibility = INVISIBLE
            binding.initialText.visibility = INVISIBLE
            binding.retry.visibility = View.VISIBLE
            binding.congratulationText.visibility = View.VISIBLE
            binding.congratulationText.text = winningMessage[0]
        }
        fun lostGame(){
            loosingMessage.shuffle()
            showAllImage()
            makeUnclickable()
            Toast.makeText(this@MainActivity,"you lost",Toast.LENGTH_SHORT).show()
            mainTimer.cancel()
            initialTimer.cancel()
            binding.pb.visibility = INVISIBLE
            binding.pbForStart.visibility = INVISIBLE
            binding.initialText.visibility = INVISIBLE
            binding.retry.visibility = View.VISIBLE
            binding.congratulationText.visibility = View.VISIBLE
            binding.congratulationText.text = loosingMessage[0]
        }

        fun checkOrder(x:Drawable, y:ImageView){
            when(x){
                one -> {
                    y.isClickable = false
                    previous = one

                }
                two -> {
                    if (previous==one){
                        y.isClickable = false
                        previous = two
                    }
                    else{
                        if(decide == "hard"){
                            lostGame()
                        }
                        else
                        y.setImageDrawable(locked)
                    }
                }
                three -> {
                    if (previous==two){
                        y.isClickable = false
                        previous = three
                    }
                    else{
                        if(decide == "hard"){
                            lostGame()
                        }
                        else
                        y.setImageDrawable(locked)
                    }
                }
                four -> {
                    if (previous==three){
                        y.isClickable = false
                        previous = four
                    }
                    else{
                        if(decide == "hard"){
                            lostGame()
                        }
                        else
                        y.setImageDrawable(locked)
                    }
                }
                five -> {
                    if(previous==four){
                        y.isClickable = false
                        previous = five
                    }
                    else{
                        if(decide == "hard"){
                            lostGame()
                        }
                        else
                        y.setImageDrawable(locked)
                    }
                }
                six -> {
                    if (previous == five){
                        y.isClickable = false
                        previous = six
                    }
                    else{
                        if(decide == "hard"){
                            lostGame()
                        }
                        else
                        y.setImageDrawable(locked)
                    }
                }
                seven -> {
                    if(previous == six){
                        y.isClickable = false
                        previous = seven
                    }
                    else{
                        if(decide == "hard"){
                            lostGame()
                        }
                        else
                        y.setImageDrawable(locked)
                    }
                }
                eight -> {
                    if (previous == seven){
                        y.isClickable = false
                        previous = eight
                    }
                    else{
                        if(decide == "hard"){
                            lostGame()
                        }
                        else
                        y.setImageDrawable(locked)
                    }
                }
                nine -> {
                    if (previous== eight){
                        y.isClickable = false
                        winningStatus = true
                        winGame()

                    }
                    else{
                        if(decide == "hard"){
                            lostGame()
                        }
                        else
                        y.setImageDrawable(locked)
                    }
                }
            }
        }

        binding.imageView1.setOnClickListener {
            //Toast.makeText(this,"image1",Toast.LENGTH_SHORT).show()
            showImage(binding.imageView1)
            checkOrder(image1,binding.imageView1)
        }

        binding.imageView2.setOnClickListener {
            //Toast.makeText(this,"image1",Toast.LENGTH_SHORT).show()
            showImage(binding.imageView2)
            checkOrder(image2,binding.imageView2)
        }

        binding.imageView3.setOnClickListener {
            //Toast.makeText(this,"image1",Toast.LENGTH_SHORT).show()
            showImage(binding.imageView3)
            checkOrder(image3,binding.imageView3)
        }

        binding.imageView4.setOnClickListener {
            //Toast.makeText(this,"image1",Toast.LENGTH_SHORT).show()
            showImage(binding.imageView4)
            checkOrder(image4,binding.imageView4)
        }

        binding.imageView5.setOnClickListener {
            //Toast.makeText(this,"image1",Toast.LENGTH_SHORT).show()
            showImage(binding.imageView5)
            checkOrder(image5,binding.imageView5)
        }

        binding.imageView6.setOnClickListener {
            //Toast.makeText(this,"image1",Toast.LENGTH_SHORT).show()
            showImage(binding.imageView6)
            checkOrder(image6,binding.imageView6)
        }

        binding.imageView7.setOnClickListener {
            //Toast.makeText(this,"image1",Toast.LENGTH_SHORT).show()
            showImage(binding.imageView7)
            checkOrder(image7,binding.imageView7)
        }

        binding.imageView8.setOnClickListener {
            //Toast.makeText(this,"image1",Toast.LENGTH_SHORT).show()
            showImage(binding.imageView8)
            checkOrder(image8,binding.imageView8)
        }

        binding.imageView9.setOnClickListener {
            //Toast.makeText(this,"image1",Toast.LENGTH_SHORT).show()
            showImage(binding.imageView9)
            checkOrder(image9,binding.imageView9)
        }

        binding.start.setOnClickListener {
            mainTimer.cancel()
            setImage()
            initialTimer.start()
            binding.start.visibility = INVISIBLE
        }
        binding.retry.setOnClickListener {
            mainTimer.cancel()
            setImage()
            initialTimer.start()
            binding.retry.visibility = INVISIBLE
        }
    }
}









