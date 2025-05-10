package com.tugcebulbul.hesapmakinesi

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tugcebulbul.hesapmakinesi.databinding.ActivityMainBinding

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
                    binding.islemTextView.text = islem.substring(0, islem.length - 1)  //sonu karakteri siler
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
            var islem = binding.islemTextView.text.toString()
            val sonuc = when {
                islem.contains("+") -> {
                    val sayilar = islem.split("+")
                    sayilar[0].toDouble() + sayilar[1].toDouble()
                }

                islem.contains("-") -> {
                    val sayilar = islem.split("-")
                    sayilar[0].toDouble() - sayilar[1].toDouble()
                }

                islem.contains("*") -> {
                    val sayilar = islem.split("*")
                    sayilar[0].toDouble() * sayilar[1].toDouble()
                }

                islem.contains("/") -> {
                    val sayilar = islem.split("/")
                    sayilar[0].toDouble() / sayilar[1].toDouble()
                }

                else -> {
                    throw IllegalArgumentException("Geçersiz ifade")
                }
            }
            binding.sonucTextView.text= sonuc.toString()
            //Hata oluştuğunda yapılacak işlem
        } catch (e:Exception){
            binding.sonucTextView.text="Hata: ${e.message}"
        }

    }
}