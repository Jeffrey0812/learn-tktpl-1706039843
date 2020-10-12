package id.ac.ui.cs.learn_tktpl_1706039843.reminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.learn_tktpl_1706039843.R
import id.ac.ui.cs.learn_tktpl_1706039843.utilities.InjectorUtils
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_form).setOnClickListener {
            findNavController().navigate(R.id.action_list_to_form)
        }

        val factory = InjectorUtils.provideRemindersViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory)
            .get(RemindersViewModel::class.java)

        viewModel.getReminders().observe(this, Observer { reminders ->
            val stringBuilder = StringBuilder()
            reminders.forEach { reminder ->
                stringBuilder.append("$reminder\n\n")
            }
            textView_reminders.text = stringBuilder.toString()
        })
    }

}