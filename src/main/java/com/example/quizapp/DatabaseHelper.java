package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuizApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_QUESTIONS = "questions";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_OPTION_A = "option_a";
    private static final String COLUMN_OPTION_B = "option_b";
    private static final String COLUMN_OPTION_C = "option_c";
    private static final String COLUMN_OPTION_D = "option_d";
    private static final String COLUMN_ANSWER = "answer";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_QUESTIONS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_QUESTION + " TEXT, "
                + COLUMN_OPTION_A + " TEXT, "
                + COLUMN_OPTION_B + " TEXT, "
                + COLUMN_OPTION_C + " TEXT, "
                + COLUMN_OPTION_D + " TEXT, "
                + COLUMN_ANSWER + " TEXT)";
        db.execSQL(createTable);

        addInitialData(db);
    }

    private void addInitialData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_QUESTION, "What is the capital of France?");
        values.put(COLUMN_OPTION_A, "Berlin");
        values.put(COLUMN_OPTION_B, "Madrid");
        values.put(COLUMN_OPTION_C, "Paris");
        values.put(COLUMN_OPTION_D, "Rome");
        values.put(COLUMN_ANSWER, "Paris");
        db.insert(TABLE_QUESTIONS, null, values);

        values.put(COLUMN_QUESTION, "Which planet is known as the Red Planet?");
        values.put(COLUMN_OPTION_A, "Earth");
        values.put(COLUMN_OPTION_B, "Mars");
        values.put(COLUMN_OPTION_C, "Jupiter");
        values.put(COLUMN_OPTION_D, "Saturn");
        values.put(COLUMN_ANSWER, "Mars");
        db.insert(TABLE_QUESTIONS, null, values);

        values.put(COLUMN_QUESTION, "How many zones are there in Russia?");
        values.put(COLUMN_OPTION_A, "6");
        values.put(COLUMN_OPTION_B, "11");
        values.put(COLUMN_OPTION_C, "20");
        values.put(COLUMN_OPTION_D, "38");
        values.put(COLUMN_ANSWER, "11");
        db.insert(TABLE_QUESTIONS, null, values);

        values.put(COLUMN_QUESTION, "What is the national flower of Japan ?");
        values.put(COLUMN_OPTION_A, "Lotus");
        values.put(COLUMN_OPTION_B, "Cherry Blossom");
        values.put(COLUMN_OPTION_C, "Plum Blossom");
        values.put(COLUMN_OPTION_D, "Chrysanthemum");
        values.put(COLUMN_ANSWER, "Cherry Blossom");
        db.insert(TABLE_QUESTIONS, null, values);

        values.put(COLUMN_QUESTION, "Who invented Telephone?");
        values.put(COLUMN_OPTION_A, "Graham Bell");
        values.put(COLUMN_OPTION_B, "Einstein");
        values.put(COLUMN_OPTION_C, "Edison");
        values.put(COLUMN_OPTION_D, "None of the above");
        values.put(COLUMN_ANSWER, "Graham Bell");
        db.insert(TABLE_QUESTIONS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }

    @SuppressLint("Range")
    public List<Question> getAllQuestions() {
        List<Question> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_QUESTIONS,
                null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION)));
                question.setOptionA(cursor.getString(cursor.getColumnIndex(COLUMN_OPTION_A)));
                question.setOptionB(cursor.getString(cursor.getColumnIndex(COLUMN_OPTION_B)));
                question.setOptionC(cursor.getString(cursor.getColumnIndex(COLUMN_OPTION_C)));
                question.setOptionD(cursor.getString(cursor.getColumnIndex(COLUMN_OPTION_D)));
                question.setAnswer(cursor.getString(cursor.getColumnIndex(COLUMN_ANSWER)));
                questionsList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionsList;
    }
}
