package com.example.meshnetapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.snackbar.Snackbar;

public class NodeEditDialog extends AppCompatDialogFragment {
    EditText txtId;
    EditText txtMac;
    EditText txtName;
    String NodeId;
    String NodeMac;
    String NodeName;
    NodeActivity Caller;
    private EditDeviceDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()) ;
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_node_edit_dialog, null);

        NodeId = getArguments().getString("id");
        NodeMac = getArguments().getString("mac");
        NodeName = getArguments().getString("name");

        txtId = view.findViewById(R.id.txtId);
        txtName = view.findViewById(R.id.txtName);
        txtMac = view.findViewById(R.id.txtMac);

        txtId.setText(NodeId);
        txtMac.setText(NodeMac);
        txtName.setText(NodeName);
        txtName.setInputType(InputType.TYPE_CLASS_TEXT);
        //txtId.setInputType(InputType.TYPE_CLASS_NUMBER);

        builder.setView(view);

        builder.setTitle("Edit")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(txtName.getText().toString().equals("")){
                            Snackbar.make(getActivity().findViewById(R.id.nodeActivity), "Please enter a txtName for the device", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            return;
                        }else{
                            new NodeEditTask(NodeId, NodeMac, txtName.getText().toString(), getContext(), getActivity(), listener.onFinishEditDeviceDialog()).execute();
                        }


                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try{
            listener = (EditDeviceDialogListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement editdevicedialog");
        }
    }

    public interface EditDeviceDialogListener {
        NodeActivity onFinishEditDeviceDialog();
    }

}
