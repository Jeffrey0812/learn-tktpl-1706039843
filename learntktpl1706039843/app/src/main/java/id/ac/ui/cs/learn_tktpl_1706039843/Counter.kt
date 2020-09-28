package id.ac.ui.cs.learn_tktpl_1706039843

open class Counter() {

    private var counter = 0

    fun countChecker(): Boolean {
        if (counter < 1) {
            return false
        }
        return true
    }

    fun incrementCount() {
        counter++
    }
    fun decrementCount() {
        counter--
    }
    fun resetCount() {
        counter = 0
    }

    fun getCount(): Int {
        return counter
    }
}