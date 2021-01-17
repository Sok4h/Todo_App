package com.sokah.todoapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class FolderDialog extends AppCompatDialogFragment {

    private EditText folderName;
    private DialogListener dialogListener;
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflator = getActivity().getLayoutInflater();
        View view = inflator.inflate(R.layout.folder_dialog,null);
        builder.setView(view)
                .setTitle("Create Folder")
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                })
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String name = folderName.getText().toString();
                        dialogListener.CreateFolder(name);
                    }
                });
        folderName =view.findViewById(R.id.folderName);
        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dialogListener= (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"Must implement interface");
        }
    }

    public interface DialogListener{

        void CreateFolder(String folderName);
    }
}
