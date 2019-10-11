package com.gunar.ventasarchivos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Productos {

    //manera de ofuscar codigo
    public static final String COD = "codigo";
    public static final String PROD = "producto";
    public static final String UNV = "univen";
    public static final String UNE = "uniem";
    public static final String LIN = "linea";
    public static final String EXIS = "existencia";
    private static final String NBD = "Ventas.db";
    private static final String NTBPRO = "productos";

    public static final String CAT = "categoria";
    public static final String FECH = "fecha";
    public static final String PRE = "precio";
    private static final String NTBPRE = "precios";


    public static final String CAN = "cantidad";
    public static final String COS = "costo";
    private static final String NTBVEN= "ventas";


    public static final int version = 8; // por cualquier cambio en la estructura se cambia la varsion
    private Creactua1 Control;
    private Context nContexto;
    private SQLiteDatabase pBD;

    private static class Creactua1 extends SQLiteOpenHelper {

        public Creactua1(Context context) {
            super(context, NBD, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + NTBPRO + " (" +
                    COD + " INTEGER PRIMARY KEY, " +
                    PROD + " TEXT NOT NULL, " +
                    UNV + " TEXT NOT NULL, " +
                    UNE + " TEXT NOT NULL, " +
                    LIN + " TEXT NOT NULL, " +
                    EXIS + " INTEGER NOT NULL);"
            );
            db.execSQL("CREATE TABLE " + NTBPRE + " (" +
                    COD + " INTEGER NOT NULL, " +
                    CAT + " TEXT NOT NULL, " +
                    FECH + " DATE NOT NULL, " +
                    PRE + " INTEGER NOT NULL);"
            );
            db.execSQL("CREATE TABLE " + NTBVEN + " (" +
                    COD + " INTEGER NOT NULL, " +
                    CAN + " INTEGER NOT NULL, " +
                    COS + " INTEGER NOT NULL); "
            );

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int a, int n) {
            db.execSQL("DROP TABLE IF EXISTS " + NTBPRO);
            db.execSQL("DROP TABLE IF EXISTS " + NTBPRE);
            db.execSQL("DROP TABLE IF EXISTS " + NTBVEN);
            onCreate(db);
        }
    }


    public Productos(Context c) {
        nContexto = c;
    }

    public Productos apertura() throws Exception {
        Control = new Creactua1(nContexto);
        pBD = Control.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        Control.close();
    }

    public long insertarProductos(String qCod, String qProd, String qUnv, String qUne, String qLin, String qExis) {
        ContentValues reg = new ContentValues();
        reg.put(COD,qCod);
        reg.put(PROD,qProd);
        reg.put(UNV,qUnv);
        reg.put(UNE,qUne);
        reg.put(LIN,qLin);
        reg.put(EXIS,qExis);
        return pBD.insert(NTBPRO, null, reg);
    }
    public String listarProductos() {
        String[] columnas = new String[] {COD, PROD, UNV, UNE, LIN, EXIS};
        Cursor c = pBD.query(NTBPRO, columnas, null, null, null, null, null);
        String res = "";
        int iCod = c.getColumnIndex(COD);
        int iProd = c.getColumnIndex(PROD);
        int iUnv = c.getColumnIndex(UNV);
        int iUne = c.getColumnIndex(UNE);
        int iLin = c.getColumnIndex(LIN);
        int iExis = c.getColumnIndex(EXIS);


        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            res = res + c.getString(iCod) + " " + c.getString(iProd) + " " + c.getString(iUnv)+ " " + c.getString(iUne) + " " + c.getString(iLin) + " " + c.getString(iExis) + "\n";
        }

        return res;
    }

    public long insertarPrecios(String qCod, String qCat, String qFech, String qPre) {
        ContentValues reg = new ContentValues();
        reg.put(COD,qCod);
        reg.put(CAT,qCat);
        reg.put(FECH,qFech);
        reg.put(PRE,qPre);
        return pBD.insert(NTBPRE, null, reg);
    }

    public String listarPrecios() {
        String[] columnas = new String[] {COD, CAT, FECH, PRE};
        Cursor c = pBD.query(NTBPRE, columnas, null, null, null, null, null);
        String res = "";
        int iCod = c.getColumnIndex(COD);
        int iCat = c.getColumnIndex(CAT);
        int iFech = c.getColumnIndex(FECH);
        int iPre = c.getColumnIndex(PRE);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            res = res + c.getString(iCod) + " " + c.getString(iCat) + " " + c.getString(iFech)+ " " + c.getString(iPre) + "\n";
        }

        return res;
    }

    public long insertarVentas(String qCod, String qCan, String qCos) {
        ContentValues reg = new ContentValues();
        reg.put(COD,qCod);
        reg.put(CAN,qCan);
        reg.put(COS,qCos);
        return pBD.insert(NTBVEN, null, reg);
    }

    public String listarVentas() {
        String[] columnas = new String[] {COD, CAN, COS};
        Cursor c = pBD.query(NTBVEN, columnas, null, null, null, null, null);
        String res = "";
        int iCod = c.getColumnIndex(COD);
        int iCan = c.getColumnIndex(CAN);
        int iCos = c.getColumnIndex(COS);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            res = res + c.getString(iCod) + " " + c.getString(iCan) + " " + c.getString(iCos) + "\n";
        }

        return res;
    }

    public String listarVentasGanancias() {
        String[] columnas = new String[] {COD, CAN, COS};
        Cursor c = pBD.query(NTBVEN, columnas, null, null, null, null, null);
        String res = "";
        int iCod = c.getColumnIndex(COD);
        int iCan = c.getColumnIndex(CAN);
        int iCos = c.getColumnIndex(COS);

        res += "Cod \t\t\t\t\t Cant \t\t\t\t\t PrecioV \t\t\t Costo \t\t\t\t\t\t Ganan \n";

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            String max = obtenerMax(c.getString(iCod));
            float gan = Float.parseFloat(max) - Float.parseFloat(c.getString(iCos));
            gan*=Float.parseFloat(c.getString(iCan));
            res = res + c.getString(iCod) + "\t\t\t\t\t" + c.getString(iCan) + "\t\t\t\t\t\t\t" + max + "\t\t\t\t\t\t\t" + c.getString(iCos) + "\t\t\t\t\t\t" + gan + "\n";
        }

        return res;
    }

    public String obtenerCod (String cod){
        String[] columnas = new String[] {COD, PROD, UNV, UNE, LIN, EXIS};
        Cursor c = pBD.query(NTBPRO, columnas, COD + "=?", new String[] { String.valueOf(cod) }, null, null, null, null);

        if (c != null)
            c.moveToFirst();

        String res = "";

        res = res + c.getString(0) + ";" + c.getString(1) + ";" + c.getString(2)+ ";" + c.getString(3) + ";" + c.getString(4) + ";" + c.getString(5)+";";
        return res;
    }

    public String obtenerMax (String cod){
        Cursor c = pBD.rawQuery("SELECT max(fecha), precio FROM precios WHERE codigo=?", new String[] {cod + ""});
        if (c != null)
            c.moveToFirst();

        String res = "";
        res = res + c.getString(1);
        return res;
    }



    public boolean borrarProductos()
    {
        pBD.execSQL("delete from "+ NTBPRO);
        return true;
    }

    public boolean borrarPrecios()
    {
        pBD.execSQL("delete from "+ NTBPRE);
        return true;
    }

    public boolean borrarVentas()
    {
        pBD.execSQL("delete from "+ NTBVEN);
        return true;
    }

}
