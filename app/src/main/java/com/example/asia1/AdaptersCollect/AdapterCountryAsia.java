package com.example.asia1.AdaptersCollect;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.asia1.ModelClasses.ModelAsiaCountry;
import com.example.asia1.R;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;


import java.util.ArrayList;

public class AdapterCountryAsia extends RecyclerView.Adapter<AdapterCountryAsia.InformationHolder>{


    Context context;
    ArrayList<ModelAsiaCountry>modelAsiaCountryArrayList;

    public AdapterCountryAsia(Context context, ArrayList<ModelAsiaCountry> modelAsiaCountryArrayList) {
        this.context = context;
        this.modelAsiaCountryArrayList = modelAsiaCountryArrayList;
    }

    @NonNull
    @Override
    public InformationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_asiainformationcountry,parent,false);
        return new InformationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InformationHolder holder, int position) {

        ModelAsiaCountry modelAsiaCountry=modelAsiaCountryArrayList.get(position);
        String one=modelAsiaCountry.getName();
        String twp=modelAsiaCountry.getCapital();
        String three=modelAsiaCountry.getSubregion();
        String four=modelAsiaCountry.getRegion();
        String five=modelAsiaCountry.getFlag();
        String six=modelAsiaCountry.getPopulation();
        String seven=modelAsiaCountry.getBorder();
        String eight=modelAsiaCountry.getLanguages();
        holder.capitalName.setText("Country: "+one);
        holder.countryName.setText("Capital: "+twp);
        holder.subregionName.setText("Subregion: "+three);
        holder.regionName.setText("Region: "+four);
        holder.populationCountry.setText("Population: "+six);
        holder.bordersNmae.setText("Borders: "+seven);
        holder.languagesName.setText("Languages: "+eight);

        Uri myUri = Uri.parse(five);
        GlideToVectorYou.justLoadImage((Activity) context, myUri, holder.flagImage);

    }

    @Override
    public int getItemCount() {
        return modelAsiaCountryArrayList.size();
    }

    public class InformationHolder extends RecyclerView.ViewHolder {

        ImageView flagImage;
        TextView countryName,capitalName,regionName,subregionName,populationCountry,bordersNmae,languagesName;
        public InformationHolder(@NonNull View itemView) {
            super(itemView);
            flagImage=itemView.findViewById(R.id.flagImage);
            countryName=itemView.findViewById(R.id.countryName);
            capitalName=itemView.findViewById(R.id.capitalName);
            regionName=itemView.findViewById(R.id.regionName);
            subregionName=itemView.findViewById(R.id.subregionName);
            populationCountry=itemView.findViewById(R.id.populationCountry);
            bordersNmae=itemView.findViewById(R.id.bordersNmae);
            languagesName=itemView.findViewById(R.id.languagesName);
        }
    }


}
