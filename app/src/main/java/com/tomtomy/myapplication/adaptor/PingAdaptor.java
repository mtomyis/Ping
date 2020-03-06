package com.tomtomy.myapplication.adaptor;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomtomy.myapplication.R;
import com.tomtomy.myapplication.model.PingModel;

import java.io.IOException;
import java.util.ArrayList;

public class PingAdaptor extends RecyclerView.Adapter<PingAdaptor.PingViewHolder> {

    int tersabung;
    private Context mContext;
    private ArrayList<PingModel> mPingModel;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public PingAdaptor(Context context, ArrayList<PingModel> pingModels) {
        mContext = context;
        mPingModel = pingModels;
    }

    @NonNull
    @Override
    public PingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
        return new PingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PingViewHolder holder, int position) {

        PingModel currentitem = mPingModel.get(position);

        String ip = currentitem.getIp();
        String gedung = currentitem.getGedung();
        String status = currentitem.getStatus();
        String result = "";
        if (status.equals("0")){
            result = "Terkoneksi";
            holder.ip.setText(ip);
            holder.gedung.setText(gedung);
            holder.status.setText(result);
            holder.status.setTextColor(Color.BLACK);
            holder.imgwifi.setImageResource(R.drawable.wifi);
        } else if (status.equals(1)){
            result = "Terputus";
            holder.ip.setText(ip);
            holder.gedung.setText(gedung);
            holder.status.setText(result);
            holder.status.setTextColor(Color.RED);
            holder.imgwifi.setImageResource(R.drawable.wifimati);
        }
        else {
            result = "Melemah";
            holder.ip.setText(ip);
            holder.gedung.setText(gedung);
            holder.status.setText(result);
            holder.status.setTextColor(Color.YELLOW);
            holder.imgwifi.setImageResource(R.drawable.wifilemah);
        }
//        if (status.equals("1")){
//            Runtime runtime = Runtime.getRuntime();
//            try {
//                Process ipProcess = runtime.exec("/system/bin/ping -c 1 "+ip);
//                tersabung = ipProcess.waitFor();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            if (tersabung==0){
//                result = "Terkoneksi";
//                holder.ip.setText(ip);
//                holder.gedung.setText(gedung);
//                holder.status.setText(result);
//                holder.status.setTextColor(Color.BLACK);
//                holder.imgwifi.setImageResource(R.drawable.wifi);
//
//            } else {
//                result = "Melemah";
//                holder.ip.setText(ip);
//                holder.gedung.setText(gedung);
//                holder.status.setText(result);
//                holder.status.setTextColor(Color.YELLOW);
//                holder.imgwifi.setImageResource(R.drawable.wifimati);
//                Toast.makeText(mContext,"Mohon Cek Jaringan "+gedung, Toast.LENGTH_LONG).show();
//            }
//
////            result = "Terkoneksi";
////            holder.ip.setText(ip);
////            holder.gedung.setText(gedung);
////            holder.status.setText(result);
////            holder.status.setTextColor(Color.BLACK);
////            holder.imgwifi.setImageResource(R.drawable.wifilemah);
//        }
//        else {
//            Runtime runtime = Runtime.getRuntime();
//            try {
//                Process ipProcess = runtime.exec("/system/bin/ping -c 1 "+ip);
//                tersabung = ipProcess.waitFor();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            if (tersabung==0){
//                result = "Melemah";
//                holder.ip.setText(ip);
//                holder.gedung.setText(gedung);
//                holder.status.setText(result);
//                holder.status.setTextColor(Color.YELLOW);
//                holder.imgwifi.setImageResource(R.drawable.wifilemah);
//                Toast.makeText(mContext,"Mohon Cek Jaringan "+gedung, Toast.LENGTH_LONG).show();
//
//            } else {
//                result = "Terputus";
//                holder.ip.setText(ip);
//                holder.gedung.setText(gedung);
//                holder.status.setText(result);
//                holder.status.setTextColor(Color.RED);
//                holder.imgwifi.setImageResource(R.drawable.wifimati);
//                Toast.makeText(mContext,"Jaringan "+gedung+" Terputus", Toast.LENGTH_LONG).show();
//            }
//
//        }

    }

    @Override
    public int getItemCount() {
        return mPingModel.size();
    }

    public class PingViewHolder extends RecyclerView.ViewHolder {
        public TextView ip, gedung, status;
        public ImageView imgwifi;

        public PingViewHolder(@NonNull View itemView) {
            super(itemView);
            ip = itemView.findViewById(R.id.idtvipp);
            gedung = itemView.findViewById(R.id.idtvgedungp);
            status = itemView.findViewById(R.id.idtvresultp);
            imgwifi = itemView.findViewById(R.id.imgwifi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener !=null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
