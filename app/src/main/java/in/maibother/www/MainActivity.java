package in.maibother.www;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/*
* NAME : ALOK MANI TRIPAHTI
* EMAIL : alok_cs@outlook.com
* Description: This App have Crud control .
* */


public class MainActivity extends AppCompatActivity {
    DataBaseHelper mydb;
    EditText editid, editname, editsurname, editrollcode, edithindi, editeng, editmath, editcom;
    Button submit, show, update, delete,allDelete;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editid = findViewById(R.id.editid);
        editname = findViewById(R.id.editname);
        editsurname = findViewById(R.id.editsurname);
        editrollcode = findViewById(R.id.editrollcode);
        edithindi = findViewById(R.id.edithindi);
        editeng = findViewById(R.id.editenglish);
        editmath = findViewById(R.id.editmath);
        editcom = findViewById(R.id.editcomputer);
        submit = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        show = findViewById(R.id.button2);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        allDelete = findViewById(R.id.alldelete);
        mydb = new DataBaseHelper(MainActivity.this);
        addData();
        viewall();
        update();
        delete();
        allDeletemethod();
    }

    public void delete()  {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (editid.length() == 0) {
                    editid.setError("Without id You can't Delete");
                } else {
                    showDialogForDelete(MainActivity.this, "Are You Sure?", "Are You Want To Delete This item To Click Yes! Otherwise No!");
                }
            }
        });

    }

    public void update() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editid.length() == 0) {
                    editid.setError("Without id You can't Update");
                } else {

                    showDialogForUpdate(MainActivity.this, "Are You Sure?", "Are You Want To Update This item To Click Yes! Otherwise No!");

                }


            }

        });
    }

    public void addData() {
        submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (editname.length() == 0) {
                    editname.setError("Needed");
                }
                if (editsurname.length() == 0) {
                    editsurname.setError("Needed");
                }
                if (editrollcode.length() == 0) {
                    editrollcode.setError("Needed");
                }


                if (edithindi.length() == 0) {
                    edithindi.setError("Needed");
                }
                if (editeng.length() == 0) {
                    editeng.setError("Needed");
                }
                if (editcom.length() == 0) {
                    editcom.setError("Needed");
                }
                if (editmath.length() == 0) {
                    editmath.setError("Needed");
                } else {
                    boolean inserted = mydb.insetData(editname.getText().toString(), editsurname.getText().toString(), editrollcode.getText().toString(), edithindi.getText().toString(), editeng.getText().toString(), editmath.getText().toString(), editcom.getText().toString());
                    if (inserted) {
                        Toast.makeText(MainActivity.this, "Your Data Is Successfully Submitted!", Toast.LENGTH_SHORT).show();
                        //     textView.setText("Your Data Is Successfully Submitted!");
                        editname.setText(null);
                        editsurname.setText(null);
                        editrollcode.setText(null);
                        edithindi.setText(null);
                        editmath.setText(null);
                        editeng.setText(null);
                        editcom.setText(null);
                        editid.setText(null);
                    } else {
                        Toast.makeText(MainActivity.this, "Something Went Wrong Please Try again!", Toast.LENGTH_SHORT).show();
                        //   textView.setText("Something Went Wrong Please Try again!");
                    }
                }

            }
        });
    }

    public void viewall() {
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = mydb.getallData();
                if (res.getCount() == 0) {
                    //show message to the user
                    showMessage("Error", "Nothing found!");
                    return;
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    while (res.moveToNext()) {
                        stringBuffer.append("Id : " + res.getString(0) + "\n");
                        stringBuffer.append("Name : " + res.getString(1) + "\n");
                        stringBuffer.append("Surname : " + res.getString(2) + "\n");
                        stringBuffer.append("Roll Code : " + res.getString(3) + "\n");
                        stringBuffer.append("Hindi : " + res.getString(4) + "\n");
                        stringBuffer.append("English : " + res.getString(5) + "\n");
                        stringBuffer.append("Math : " + res.getString(6) + "\n");
                        stringBuffer.append("Computer : " + res.getString(7) + "\n\n");


                    }
                    //show all data
                    showMessage("Data", stringBuffer.toString());


                }
            }
        });
    }


    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();


    }


    public void showDialogForDelete(Context context, String title, String msg) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // set title
        alertDialogBuilder.setTitle(title);
        // set dialog message
        alertDialogBuilder.setMessage(msg).setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        Integer isDeleted = mydb.deleteData(editid.getText().toString());

                        if (isDeleted > 0) {

                            editid.setText(null);
                            Toast.makeText(MainActivity.this, "Your Data Is Successfully Deleted!", Toast.LENGTH_SHORT).show();

                        } else {
                            editid.setText(null);
                            Toast.makeText(MainActivity.this, "Something Went Wrong Please Try again!", Toast.LENGTH_SHORT).show();

                        }
                        //  MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    public void showDialogForUpdate(Context context, String title, String msg) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // set title
        alertDialogBuilder.setTitle(title);
        // set dialog message
        alertDialogBuilder.setMessage(msg).setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        boolean isUpdate = mydb.updateData(editid.getText().toString(), editname.getText().toString(), editsurname.getText().toString(), editrollcode.getText().toString(), edithindi.getText().toString(), editeng.getText().toString(), editmath.getText().toString(), editcom.getText().toString());
                        if (isUpdate) {
                            Toast.makeText(MainActivity.this, "Your Data Is Successfully Updated!", Toast.LENGTH_SHORT).show();
                            //     textView.setText("Your Data Is Successfully Submitted!");
                            editname.setText(null);
                            editsurname.setText(null);
                            editrollcode.setText(null);
                            edithindi.setText(null);
                            editmath.setText(null);
                            editeng.setText(null);
                            editcom.setText(null);
                            editid.setText(null);
                        } else {
                            Toast.makeText(MainActivity.this, "Something Went Wrong Please Try again!", Toast.LENGTH_SHORT).show();
                            //   textView.setText("Something Went Wrong Please Try again!");

                        }
                        //  MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    public void allDeletemethod(){
        allDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogForDeleteAll(MainActivity.this, "Please Attention ! Are You Sure?", "Are You Want To All Delete This item To Click Yes! Otherwise No!");

            }
        });
    }

    public void showDialogForDeleteAll(Context context, String title, String msg) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // set title
        alertDialogBuilder.setTitle(title);
        // set dialog message
        alertDialogBuilder.setMessage(msg).setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                     Integer allDeleted= mydb.deleteDataAll();
                     Toast.makeText(MainActivity.this, "Your All Data Is Successfully Deleted!", Toast.LENGTH_SHORT).show();

                        //  MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}