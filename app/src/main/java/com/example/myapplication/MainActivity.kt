package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import com.example.myapplication.databinding.ActivityMainBinding
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Realm.init(applicationContext)
        binding.btnLogin.setOnClickListener {

            var validationResult = validateUserInput()
            if (validationResult.first) {
                startActivity(Intent(this, PersonActivity::class.java))
                finish()
            } else
                showValidationErrors(validationResult.second)
        }
    }


    private fun validateCredentials(mail: String, password: String): Pair<Boolean, String> {
        var result = Pair(true, "")

        if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(password)) {
            result = Pair(false, "Please provide the credentials")

        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            result = Pair(false, "Email is invalid")

        }
        else if (!TextUtils.isEmpty(password) && password.length <= 5) {
            result = Pair(false, "Password length should be greater than 5")
        }
        return result
    }

    private fun showValidationErrors(error: String) {
        binding.txtError.text =
            String.format(resources.getString(R.string.txt_error_message, error))

    }
    fun validateUserInput():Pair<Boolean,String>{
        var mail = binding.edtEmail.text.toString()
        var password = binding.txtPassword.text.toString()
        return validateCredentials(mail,password)
    }
}