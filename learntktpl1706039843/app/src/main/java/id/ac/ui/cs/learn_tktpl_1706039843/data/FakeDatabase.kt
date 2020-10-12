package id.ac.ui.cs.learn_tktpl_1706039843.data

class FakeDatabase private constructor() {

    var reminderDao = FakeReminderDAO()
        private set

    companion object {
        @Volatile private var instance: FakeDatabase? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: FakeDatabase().also { instance = it }
            }
    }
}