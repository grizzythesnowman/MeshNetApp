package com.example.meshnetapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.snackbar.Snackbar;

public class NodeEditDialog extends AppCompatDialogFragment {
    EditText IP;
    String IPAddress;
    String DeviceName;
    EditText Name;
    Spinner Role;
    Spinner Status;
    NodeActivity Caller;
    private EditDeviceDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()) ;
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_edit_node_dialog, null);

        IPAddress = getArguments().getString("ip");
        DeviceName = getArguments().getString("name");
        IP = view.findViewById(R.id.txtIP);
        Name = view.findViewById(R.id.txtName);
        Role = view.findViewById(R.id.spnRole);
        Status = view.findViewById(R.id.spnStatus);

        IP.setText(IPAddress);
        Name.setText(DeviceName);
        Name.setInputType(InputType.TYPE_CLASS_TEXT);
        //Id.setInputType(InputType.TYPE_CLASS_NUMBER);

        ArrayAdapter<String> rolesAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.roles));
        rolesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Role.setAdapter(rolesAdapter);

        ArrayAdapter<String> StatusAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.status));
        StatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Status.setAdapter(StatusAdapter);

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

                        if(Name.getText().toString().equals("")){
                            Snackbar.make(getActivity().findViewById(R.id.deviceinfo), "Please enter a Name for the device", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            return;
                        }else{
                            new NodeEditTask(IPAddress, Name.getText().toString(), Role.getSelectedItem().toString(), Status.getSelectedItem().toString(), getContext(), getActivity(), listener.onFinishEditDeviceDialog()).execute();
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
