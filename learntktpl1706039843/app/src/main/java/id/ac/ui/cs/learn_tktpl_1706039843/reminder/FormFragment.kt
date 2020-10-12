package id.ac.ui.cs.learn_tktpl_1706039843.reminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.learn_tktpl_1706039843.R
import id.ac.ui.cs.learn_tktpl_1706039843.data.Reminder
import id.ac.ui.cs.learn_tktpl_1706039843.utilities.InjectorUtils
import kotlinx.android.synthetic.main.fragment_form.*


class FormFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_list).setOnClickListener {
            findNavController().navigate(R.id.action_form_to_list)
        }

        val factory = InjectorUtils.provideRemindersViewModelFactory()

        val viewModel = ViewModelProviders.of(this, factory)
            .get(RemindersViewModel::class.java)

        button_add_reminder.setOnClickListener {
            val reminder = Reminder(editText_reminder.text.toString(), editText_date.text.toString())
            viewModel.addReminder(reminder)
            editText_reminder.setText("")
            editText_date.setText("")
        }
    }
}