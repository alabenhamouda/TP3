package com.example.tp3_p1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Switch
import com.example.tp3_p1.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ActionMode.Callback {
    private lateinit var binding: ActivityMainBinding
    private lateinit var actionMode: ActionMode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.setTimeBtn.setOnClickListener { view -> this.setTime(view) }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, FragmentClock(),null)
            .addToBackStack(null)
            .commit()

        binding.setTimeBtn.setOnLongClickListener {
            actionMode = this@MainActivity.startActionMode(this@MainActivity)!!
            return@setOnLongClickListener true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_switch)
        {
            binding.digitalSwitch.isChecked = !binding.digitalSwitch.isChecked
            setTime(null)
        }
        return super.onOptionsItemSelected(item)
    }

    fun setTime(view: View?){
        var fragmentManager = supportFragmentManager
        var transaction = fragmentManager.beginTransaction()
        var fragmentClock = FragmentClock()
        var bundle = Bundle()
        bundle.putBoolean("digitalOK", binding.digitalSwitch.isChecked)
        fragmentClock.arguments = bundle
        transaction.replace(R.id.fragment, fragmentClock)
        transaction.commit()
    }

    override fun onCreateActionMode(actionMode: ActionMode, menu: Menu?): Boolean {
        val inflater: MenuInflater = actionMode.menuInflater
        inflater.inflate(R.menu.context_mode_menu, menu)
        return true
    }

    override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(actionMode: ActionMode?, menuItem: MenuItem?): Boolean {
        return when (menuItem?.itemId) {
            R.id.action_color -> {
                binding.setTimeBtn.setBackgroundColor(this.getRandomColor())
                actionMode?.finish()
                true
            }
            else -> false
        }
    }

    override fun onDestroyActionMode(p0: ActionMode?) {
    }

    fun getRandomColor(): Int = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

}