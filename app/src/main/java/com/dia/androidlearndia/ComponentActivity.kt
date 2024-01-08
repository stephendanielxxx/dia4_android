package com.dia.androidlearndia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.dia.androidlearndia.bottomnavigation.HomeActivity
import com.dia.androidlearndia.databinding.ActivityComponentActivityBinding
import com.google.android.material.snackbar.Snackbar

class ComponentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityComponentActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComponentActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get view using findViewById -> the old way
//        val textView = findViewById<TextView>(R.id.tvText)

        val textContent = binding.tvText.text
        Toast.makeText(this@ComponentActivity, "Isi textview = $textContent", Toast.LENGTH_SHORT).show()

        binding.btnChangeTv.setOnClickListener {
            val phone = binding.etInput.text.toString() // to get data from edittext
            binding.tvText.text = phone
            binding.tvText.setTextColor(getColor(R.color.Green))
            binding.ivImage.setImageResource(R.drawable.pokemon)
        }

        binding.cbButton.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.btnChangeTv.isEnabled = isChecked
        }

        binding.rbRed.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                binding.btnChangeTv.setBackgroundColor(getColor(R.color.Red))
            }
        }

        binding.rbBlue.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                binding.btnChangeTv.setBackgroundColor(getColor(R.color.Blue))
            }
        }

        binding.swDark.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                binding.llRoot.setBackgroundColor(getColor(R.color.Black))
            }else{
                binding.llRoot.setBackgroundColor(getColor(R.color.White))
            }
        }

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(binding.btnSnackBar, "Snack bar is showed", Snackbar.LENGTH_LONG).setAction("OKE"){
                binding.tvText.text = "Snack bar is clicked"
            }.show()
        }

        binding.btnFragment.setOnClickListener {
            val intent = Intent(this@ComponentActivity, FragmentActivity::class.java)
            startActivity(intent)
        }

        binding.btnBottomNavigation.setOnClickListener {
            val homeIntent = Intent(this@ComponentActivity, HomeActivity::class.java)
            startActivity(homeIntent)
        }
    }
}