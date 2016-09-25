package com.aladdin.apps.questionbank.data.local;

/**
 * Created by AndySun on 2016/9/24.
 */
import android.provider.BaseColumns;
/**
 * The contract used for the db to save the tasks locally.
 */
public final class CommonPersistenceContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private CommonPersistenceContract(){};

     /* Inner class that defines the table contents */
     public static abstract class QuestionBankEntry implements BaseColumns {
         public static final String TABLE_NAME = "questionBank";
         public static final String COLUMN_NAME_ENTRY_ID = "entryid";
         public static final String COLUMN_NAME_TITLE = "title";
         public static final String COLUMN_NAME_DESCRIPTION = "description";
         public static final String COLUMN_NAME_COMPLETED = "completed";
     }

}
