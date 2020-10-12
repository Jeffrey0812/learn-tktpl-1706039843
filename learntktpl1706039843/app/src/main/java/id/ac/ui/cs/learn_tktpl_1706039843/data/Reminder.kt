package id.ac.ui.cs.learn_tktpl_1706039843.data

data class Reminder(val reminderText: String,
                 val date: String) {

    override fun toString(): String {
        return "$reminderText - $date"
    }
}