package id.ac.ui.cs.learn_tktpl_1706039843

open class Counter() {

    init {
        System.loadLibrary("native-lib")
    }

    private external fun incrementN(x: Int): Int
    private external fun decrementN(x: Int): Int

    private var counter = 0

    fun countChecker(): Boolean {
        if (counter < 1) {
            return false
        }
        return true
    }

    fun incrementCount() {
        counter = incrementN(counter)
    }
    fun decrementCount() {
        counter = decrementN(counter)
    }
    fun resetCount() {
        counter = 0
    }

    fun getCount(): Int {
        return counter
    }
}