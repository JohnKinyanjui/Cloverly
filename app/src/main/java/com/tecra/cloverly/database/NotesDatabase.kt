package com.tecra.cloverly.database
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
class NotesDatabase {
        val dbName = "MyNotes"
        val dbTable = "Notes"
        private val colID = "ID"
        private val color ="Color"
        private val colTitle ="Title"
        private val colDes = "Description"
        private val colDate = "Date"
        private val colType = "Type"
        val dbVersion=1
        //CREATE TABLE IF NOT EXISTS MyNotes (ID INTEGER PRIMARY KEY,title TEXT, Description TEXT);"
        val sqlCreateTable=" CREATE TABLE  IF NOT EXISTS " + dbTable +" (" + colID + " INTEGER PRIMARY KEY,"+
                color +" Text NOT NULL DEFAULT 'white'," + colTitle + " TEXT NOT NULL DEFAULT 'Title',"+ colDes +" TEXT NOT NULL DEFAULT 'Description', "+colDate + " TEXT, " + colType + " TEXT);"
        var sqlDB: SQLiteDatabase?  = null

        constructor(context: Context){
            var db=DatabaseHelperNotes(context)
            sqlDB=db.writableDatabase
        }

        inner class  DatabaseHelperNotes: SQLiteOpenHelper {
            var context: Context?=null
            constructor(context: Context):super(context,dbName,null,dbVersion){
                this.context=context
            }
            override fun onCreate(p0: SQLiteDatabase?) {
                p0!!.execSQL(sqlCreateTable)

            }
            override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
                p0!!.execSQL("Drop table IF EXISTS $dbTable")
            }
        }
        fun Insert(values: ContentValues):Long{

            val ID= sqlDB!!.insert(dbTable,"",values)
            return ID
        }
        fun  Query(projection:Array<String>,selection:String,selectionArgs:Array<String>,sorOrder:String): Cursor {

            val qb= SQLiteQueryBuilder()
            qb.tables=dbTable
            val cursor=qb.query(sqlDB,projection,selection,selectionArgs,null,null,sorOrder)
            return cursor
        }
        fun Delete(selection:String,selectionArgs:Array<String>):Int{

            val count=sqlDB!!.delete(dbTable,selection,selectionArgs)
            return  count
        }
        fun Update(values: ContentValues, selection:String, selectionargs:Array<String>):Int{

            val count=sqlDB!!.update(dbTable,values,selection,selectionargs)
            return count
        }
    }

