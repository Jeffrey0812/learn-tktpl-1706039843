package id.ac.ui.cs.learn_tktpl_1706039843.reminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.learn_tktpl_1706039843.data.ReminderRepository

class RemindersViewModelFactory(private val reminderRepository: ReminderRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RemindersViewModel(reminderRepository) as T
    }
}