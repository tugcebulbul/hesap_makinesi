package com.tugcebulbul.hesapmakinesi

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tugcebulbul.hesapmakinesi.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Binding ile layout elemanlarına erişimi sağlama
        binding.apply {
            //Butonlara tıklama olayını ekleme
            btn0.clickEkle("0")
            btn1.clickEkle("1")
            btn2.clickEkle("2")
            btn3.clickEkle("3")
            btn4.clickEkle("4")
            btn5.clickEkle("5")
            btn6.clickEkle("6")
            btn7.clickEkle("7")
            btn8.clickEkle("8")
            btn9.clickEkle("9")
            btnbol.clickEkle("/")
            btncarp.clickEkle("*")
            btncikar.clickEkle("-")
            btntopla.clickEkle("+")
            btnVirgul.clickEkle(".")
            btnParantezAcKapat.clickEkle("(")
            //Parantez kapatma butonuna uzun tıklandığında çalışır
            btnParantezAcKapat.setOnLongClickListener {
                binding.islemTextView.append(")")
                true
            }
            btnAC.setOnClickListener {
                binding.islemTextView.text = null // islem alanını temizler
                binding.sonucTextView.text = null // sonuc alanını temizler
            }

            btnSil.setOnClickListener {
                var islem = binding.islemTextView.text.toString()
                if (islem.isNotEmpty()) {
                    binding.islemTextView.text = islem.substring(0, islem.length - 1)  //son karakteri siler
                }
            }
            btnEsittir.setOnClickListener {
                hesapla()
            }
        }
    }
    // Tıklama işlemini ekleyen fonksiyon
    private fun View.clickEkle(string: String) {
        setOnClickListener {
            binding.islemTextView.append(string)
        }
    }

    private fun hesapla() {
        try {
            val islem = binding.islemTextView.text.toString()
            val expression = ExpressionBuilder(islem).build()
            val sonuc = expression.evaluate()
            val longSonuc = sonuc.toLong()
            if (sonuc == longSonuc.toDouble()) {
                binding.sonucTextView.text = longSonuc.toString()
            } else {
                binding.sonucTextView.text = sonuc.toString()
            }
        } catch (e: Exception) {
            binding.sonucTextView.text = "Hata"
        }
    }
}