package com.nsb.myproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class EditFoods : AppCompatActivity() {
    private lateinit var tvEditFoodName : TextView
    private lateinit var tvEditFoodQty : TextView
    private lateinit var tvEditFoodExQty : TextView
    private lateinit var tvTitle : TextView
    private lateinit var btnUpdate : Button
    private lateinit var btnDelete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_foods)

        initView()
        setValuesToViews()

        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("foodId").toString()
            )
        }
    }

    private fun deleteRecord(id : String){
        val dbRef = FirebaseDatabase.getInstance().getReference("Foods").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Food Data Deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, ViewFood::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener {error ->
            Toast.makeText(this, "Deleting Error : ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        tvEditFoodName = findViewById(R.id.edit_name)
        tvEditFoodQty = findViewById(R.id.edit_qty)
        tvEditFoodExQty = findViewById(R.id.edit_ex_aty)
        tvTitle = findViewById(R.id.edit_food_topic)
        btnDelete = findViewById(R.id.btn_delete)

    }

    private fun setValuesToViews() {
        tvEditFoodName.text = intent.getStringExtra("foodName")
        tvEditFoodQty.text = intent.getStringExtra("foodQty")
        tvEditFoodExQty.text = intent.getStringExtra("foodExQty")
        tvTitle.text = intent.getStringExtra("foodName")
    }
}