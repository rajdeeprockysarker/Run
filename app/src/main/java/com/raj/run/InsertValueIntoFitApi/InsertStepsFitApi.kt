package com.raj.run.InsertValueIntoFitApi

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.*
import com.google.android.gms.fitness.data.Field.FIELD_VOLUME
import com.google.android.gms.tasks.Task
import com.raj.run.Log
import com.raj.run.TAG
import java.util.*
import java.util.concurrent.TimeUnit


class InsertStepsFitApi(cntx: Context, mFitnessOptions: FitnessOptions) {

    lateinit var cntx:Context
    lateinit var mFitnessOptions: FitnessOptions

    init {
        this.cntx=cntx
        this.mFitnessOptions=mFitnessOptions
    }


    private fun getGoogleAccount() = GoogleSignIn.getAccountForExtension(cntx, mFitnessOptions)

    /** Creates a {@link DataSet} and inserts it into user's Google Fit history. */
    fun insertData(steps:Int): Task<Void> {
        // Create a new dataset and insertion request.
        val dataSet = insertFitnessDataSteps(steps)

        // Then, invoke the History API to insert the data.
        Log.i(
            TAG,
            "Inserting the dataset in the History API."
        )
        return Fitness.getHistoryClient(cntx, getGoogleAccount())
            .insertData(dataSet)
            .addOnSuccessListener {
                Log.i(
                    TAG,
                    "Data insert was successful!"
                )
            }
            .addOnFailureListener { exception ->
                Log.e(
                    TAG,
                    "There was a problem inserting the dataset.",
                    exception
                )
            }
    }

    /**
     * Creates and returns a {@link DataSet} of step count data for insertion using the History API.
     */
    private fun insertFitnessDataSteps(steps:Int): DataSet {
        Log.i(
            TAG,
            "Creating a new data insert request."
        )

        // [START build_insert_data_request]
        // Set a start and end time for our data, using a start time of 1 hour before this moment.
//        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
//        val now = Date()
//        calendar.time = now
//        val endTime = calendar.timeInMillis
//        calendar.add(Calendar.HOUR_OF_DAY, -1)
//        val startTime = calendar.timeInMillis

        val cal = Calendar.getInstance()
        val now = Date()
        cal.time = now
        cal.add(Calendar.MINUTE, 0)
        val endTime = cal.timeInMillis
        cal.add(Calendar.MINUTE, -
        1)
        val startTime = cal.timeInMillis




        // Create a data source
        val dataSourceStep = DataSource.Builder()
            .setAppPackageName(cntx)
            .setDataType(DataType.TYPE_STEP_COUNT_DELTA)
            .setStreamName("$TAG - step count")
            .setType(DataSource.TYPE_RAW)
            .build()

        // Create a data set
        val stepCountDelta = 10
        return DataSet.builder(dataSourceStep)
            .add(
                DataPoint.builder(dataSourceStep)
                    .setField(Field.FIELD_STEPS, stepCountDelta)
                    .setTimeInterval(startTime, endTime, TimeUnit.MILLISECONDS)
                    .build()
            ).build()
        // [END build_insert_data_request]
    }


  ///  --------------------------------------------------




    /** Creates a {@link DataSet} and inserts it into user's Google Fit history. */
    fun insertWight(): Task<Void> {
        // Create a new dataset and insertion request.
        val dataSet = insertDataWight()

        // Then, invoke the History API to insert the data.
        Log.i(
            TAG,
            "Inserting the dataset in the History API."
        )
        return Fitness.getHistoryClient(cntx, getGoogleAccount())
            .insertData(dataSet)
            .addOnSuccessListener {
                Log.i(
                    TAG,
                    "Data insert was successful!"
                )
            }
            .addOnFailureListener { exception ->
                Log.e(
                    TAG,
                    "There was a problem inserting the dataset.",
                    exception
                )
            }
    }

    /**
     * Creates and returns a {@link DataSet} of step count data for insertion using the History API.
     */
    private fun insertDataWight(): DataSet {
        Log.i(
            TAG,
            "Creating a new data insert request."
        )

        // [START build_insert_data_request]
        // Set a start and end time for our data, using a start time of 1 hour before this moment.
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        val now = Date()
        calendar.time = now
        val endTime = calendar.timeInMillis
        calendar.add(Calendar.HOUR_OF_DAY, -1)
        val startTime = calendar.timeInMillis

        // Create a data source
        val dataSourceStep = DataSource.Builder()
            .setAppPackageName(cntx)
            .setDataType(DataType.TYPE_WEIGHT)
            .setStreamName("$TAG - step count")
            .setType(DataSource.TYPE_RAW)
            .build()





        return DataSet.builder(dataSourceStep)
            .add(
                DataPoint.builder(dataSourceStep)
                    .setFloatValues(65.0f)
                    .setTimeInterval(startTime, endTime, TimeUnit.MILLISECONDS)
                    .build()
            ).build()
        // [END build_insert_data_request]
    }

    /*************
     *
     */

    /** Creates a {@link DataSet} and inserts it into user's Google Fit history. */
    fun insertHyd(): Task<Void> {
        // Create a new dataset and insertion request.
        val dataSet = insertDataHyd()

        // Then, invoke the History API to insert the data.
        Log.i(
            TAG,
            "Inserting the dataset in the History API."
        )
        return Fitness.getHistoryClient(cntx, getGoogleAccount())
            .insertData(dataSet)
            .addOnSuccessListener {
                Log.i(
                    TAG,
                    "Data insert was successful!"
                )
            }
            .addOnFailureListener { exception ->
                Log.e(
                    TAG,
                    "There was a problem inserting the dataset.",
                    exception
                )
            }
    }

    /**
     * Creates and returns a {@link DataSet} of step count data for insertion using the History API.
     */
    private fun insertDataHyd(): DataSet {
        Log.i(
            TAG,
            "Creating a new data insert request."
        )

        // [START build_insert_data_request]
        // Set a start and end time for our data, using a start time of 1 hour before this moment.
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        val now = Date()
        calendar.time = now
        val endTime = calendar.timeInMillis
        calendar.add(Calendar.MINUTE, -1)
        val startTime = calendar.timeInMillis

        // Create a data source
        val dataSourceStep = DataSource.Builder()
            .setAppPackageName(cntx)
            .setDataType(DataType.TYPE_HYDRATION)
            .setStreamName("$TAG - Hyd ")
            .setType(DataSource.TYPE_RAW)
            .build()


        val hydration = DataPoint.create(dataSourceStep)
        hydration.setTimestamp(endTime, TimeUnit.MILLISECONDS)
        hydration.getValue(FIELD_VOLUME).setFloat(0.3f)

        return DataSet.builder(dataSourceStep)
            .add(
                hydration
            ).build()
        // [END build_insert_data_request]
    }

}