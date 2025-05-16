package com.example.mobile_hachation

import TODOFragment
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class TaskActivity : AppCompatActivity() {
    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private val taskList = mutableListOf<Task>()

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_task)
        val SummaryFragment=SummaryFragment()
        val TODOFragment=TODOFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, TODOFragment())
            .commit()
        bottomNavigationView.setOnItemSelectedListener { item ->
            val selectedFragment = when (item.itemId) {
                R.id.summary -> SummaryFragment()
                R.id.todo ->TODOFragment()
                R.id.Settings ->SettingsFragment()
                else -> null
            }
            selectedFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, it)
                    .commit()}


            true }


    }
    // ⬛ هاي إعداد القائمة الجانبية
    val toolbar: Toolbar = findViewById(R.id.toolbar)
    setSupportActionBar(toolbar)
    drawerLayout = findViewById(R.id.drawer_layout)
    toggle = ActionBarDrawerToggle(
    this, drawerLayout,
    R.string.navigation_drawer_open,
    R.string.navigation_drawer_close
    )
    drawerLayout.addDrawerListener(toggle)
    toggle.syncState()
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    val navigationView: NavigationView = findViewById(R.id.navigation_view)
    navigationView.setNavigationItemSelectedListener(this)

    // 📌 إعداد RecyclerView
    taskRecyclerView = findViewById(R.id.taskRecyclerView)
    taskRecyclerView.layoutManager = LinearLayoutManager(this)
    taskAdapter = TaskAdapter(taskList)
    taskRecyclerView.adapter = taskAdapter

    // ➕هاض زر إضافة مهمة
    val addTaskButton: Button = findViewById(R.id.addTaskButton)
    addTaskButton.setOnClickListener {
        val newTask = Task("مهمة جديدة", " وصف لمهمة جديدة.")
        taskList.add(newTask)
        taskAdapter.notifyItemInserted(taskList.size - 1)
    }


}
// 🔄  هاض لفتح القائمة عند الضغط على زر ☰
override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (toggle.onOptionsItemSelected(item)) {
        return true
    }
    return super.onOptionsItemSelected(item)
}
// ✅ استجابة لاختيارات القائمة الجانبية
override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
        R.id.nav_tasks -> {
            Toast.makeText(this, "قائمة المهام", Toast.LENGTH_SHORT).show()
        }
        R.id.nav_settings -> {
            Toast.makeText(this, "الإعدادات", Toast.LENGTH_SHORT).show()
        }
    }
    drawerLayout.closeDrawers()
    return true
}
