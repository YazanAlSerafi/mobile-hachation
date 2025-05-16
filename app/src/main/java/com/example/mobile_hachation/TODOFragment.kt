import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_hachation.NewTaskFragment
import com.example.mobile_hachation.R
import com.example.mobile_hachation.TaskAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TODOFragment : Fragment(), NewTaskFragment.NewTaskListener {

    private val taskList = mutableListOf<Task>()
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_t_o_d_o, container, false)

        // 1. تهيئة RecyclerView وربطه بـ Adapter
        recyclerView = view.findViewById(R.id.taskRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        taskAdapter = TaskAdapter(taskList)
        recyclerView.adapter = taskAdapter

        // 2. زر إضافة مهمة جديدة
        val addButton = view.findViewById<FloatingActionButton>(R.id.addbtn)
        addButton.setOnClickListener {
            val dialog = NewTaskFragment()
            dialog.listener = this
            dialog.show(childFragmentManager, "NewTaskDialog")
        }

        return view
    }

    override fun onTaskCreated(title: String, description: String, time: String) {
        taskList.add(Task(title, description, time))
        taskAdapter.notifyItemInserted(taskList.size - 1)
        recyclerView.scrollToPosition(taskList.size - 1)
    }


}
