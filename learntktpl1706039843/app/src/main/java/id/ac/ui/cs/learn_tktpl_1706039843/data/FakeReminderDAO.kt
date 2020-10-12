package id.ac.ui.cs.learn_tktpl_1706039843.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeReminderDAO {
    private val reminderList = mutableListOf<Reminder>()
    private val reminders = MutableLiveData<List<Reminder>>()

    init {
        // Immediately connect the now empty quoteList
        // to the MutableLiveData which can be observed
        reminders.value = reminderList
    }

    fun addReminder(reminder: Reminder) {
        reminderList.add(reminder)
        reminders.value = reminderList
    }

    fun getReminders() = reminders as LiveData<List<Reminder>>
}
