package com.example.mobile_hachation

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class NewTaskFragment : DialogFragment() {

    interface NewTaskListener {
        fun onTaskCreated(title: String, description: String, time: String)
    }

    var listener: NewTaskListener? = null
    private var selectedTime: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleEditText = view.findViewById<TextInputEditText>(R.id.textname)
        val descriptionEditText = view.findViewById<TextInputEditText>(R.id.date)
        val timeTextView = view.findViewById<TextView>(R.id.timeTextView)
        val pickTimeButton = view.findViewById<MaterialButton>(R.id.pickTimeBtn)
        val saveButton = view.findViewById<MaterialButton>(R.id.saveBtn)

        pickTimeButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(requireContext(),
                { _, selectedHour, selectedMinute ->
                    selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                    timeTextView.text = "الوقت: $selectedTime"
                },
                hour, minute, true
            )

            timePickerDialog.show()
        }

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val description = descriptionEditText.text.toString().trim()

            if (title.isNotEmpty()) {
                listener?.onTaskCreated(title, description, selectedTime)
                dismiss()
            } else {
                titleEditText.error = "عنوان المهمة مطلوب"
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.9).toInt(),
            (resources.displayMetrics.heightPixels * 0.7).toInt()
        )
    }
}
