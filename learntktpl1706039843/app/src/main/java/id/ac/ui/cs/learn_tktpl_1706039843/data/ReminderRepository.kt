package id.ac.ui.cs.learn_tktpl_1706039843.data

class ReminderRepository private constructor(private val reminderDao: FakeReminderDAO) {

    fun addReminder(reminder: Reminder) {
        reminderDao.addReminder(reminder)
    }

    fun getReminders() = reminderDao.getReminders()

    companion object {
        @Volatile
        private var instance: ReminderRepository? = null

        fun getInstance(reminderDao: FakeReminderDAO) =
            instance ?: synchronized(this) {
                instance ?: ReminderRepository(reminderDao).also { instance = it }
            }
    }
}