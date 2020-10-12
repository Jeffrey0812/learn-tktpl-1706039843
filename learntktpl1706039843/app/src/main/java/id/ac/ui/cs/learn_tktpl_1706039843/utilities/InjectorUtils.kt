package id.ac.ui.cs.learn_tktpl_1706039843.utilities

import id.ac.ui.cs.learn_tktpl_1706039843.data.FakeDatabase
import id.ac.ui.cs.learn_tktpl_1706039843.data.ReminderRepository
import id.ac.ui.cs.learn_tktpl_1706039843.reminder.RemindersViewModelFactory

object InjectorUtils {
    fun provideRemindersViewModelFactory(): RemindersViewModelFactory {
        val reminderRepository = ReminderRepository.getInstance(FakeDatabase.getInstance().reminderDao)
        return RemindersViewModelFactory(reminderRepository)
    }
}