package com.example.myapplication.singletonData

class DataStore {
    // private volatile instance variable to hold the singleton instance
    @Volatile
    private var INSTANCE: DataStore? = null
    // public function to retrieve the singleton instance
    fun getInstance(): DataStore? {
        // Check if the instance is already created
        if (INSTANCE == null) {
            // synchronize the block to ensure only one thread can execute at a time
            synchronized(this) {
                // check again if the instance is already created
                if (INSTANCE == null) {
                    // create the singleton instance
                    INSTANCE = DataStore()
                }
            }
        }
        // return the singleton instance
        return INSTANCE
    }
}