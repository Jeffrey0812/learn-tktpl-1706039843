package id.ac.ui.cs.learn_tktpl_1706039843.reminder

import androidx.lifecycle.ViewModel
import id.ac.ui.cs.learn_tktpl_1706039843.data.Reminder
import id.ac.ui.cs.learn_tktpl_1706039843.data.ReminderRepository

class RemindersViewModel(private val reminderRepository: ReminderRepository)
    : ViewModel() {

    fun getReminders() = reminderRepository.getReminders()

    fun addReminder(reminder: Reminder) = reminderRepository.addReminder(reminder)
}
