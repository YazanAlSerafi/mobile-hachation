import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobile_hachation.NewTaskFragment
import com.example.mobile_hachation.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TODOFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_t_o_d_o, container, false)

        val addButton = view.findViewById<FloatingActionButton>(R.id.addbtn)
        addButton.setOnClickListener {
            val dialog = NewTaskFragment()
            dialog.show(childFragmentManager, "NewTaskDialog")
        }

        return view
    }
}
