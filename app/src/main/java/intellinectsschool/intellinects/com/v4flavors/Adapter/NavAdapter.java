package intellinectsschool.intellinects.com.v4flavors.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import intellinectsschool.intellinects.com.v4flavors.Models.NavItems;
import intellinectsschool.intellinects.com.v4flavors.R;


import java.util.ArrayList;

/**
 * Created by Indrajeet  on 25-05-2017
 */

public class NavAdapter extends RecyclerView.Adapter<NavAdapter.RecyclerViewHolder> {

    private ArrayList<NavItems> arrayList = new ArrayList<>();
    private Context context;
    private DrawerLayout drawerLayout;
    private int drawerCheck;

    public NavAdapter(ArrayList<NavItems> arrayList, Context context, DrawerLayout drawerLayout, int drawerCheck){
        this.arrayList = arrayList;
        this.context = context;
        this.drawerLayout = drawerLayout;
        this.drawerCheck = drawerCheck;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_item_layout, parent, false);
        return new RecyclerViewHolder(view, context, arrayList);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        if (position == 0){
            holder.linearLayout.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.colorPrimaryDark, null));
            holder.textView.setTextColor(ResourcesCompat.getColor(context.getResources(), R.color.white, null));
            holder.textView.setCompoundDrawablesWithIntrinsicBounds(ResourcesCompat.getDrawable(context.getResources(),
                    arrayList.get(holder.getAdapterPosition()).getNav_drawableWhite(), null), null, null, null);
        }else {
            holder.linearLayout.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.white, null));
            holder.textView.setTextColor(ResourcesCompat.getColor(context.getResources(), R.color.black, null));
            holder.textView.setCompoundDrawablesWithIntrinsicBounds(ResourcesCompat.getDrawable(context.getResources(),
                    arrayList.get(holder.getAdapterPosition()).getNav_drawable(), null), null, null, null);
        }
        holder.textView.setText(arrayList.get(position).getNav_text());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        drawerLayout.closeDrawers();
                    }
                },200);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        LinearLayout linearLayout;
        ArrayList<NavItems> list = new ArrayList<>();
        Context ctx;

        RecyclerViewHolder(View itemView, Context ctx, ArrayList<NavItems> navItems) {
            super(itemView);
            this.list = navItems;
            this.ctx = ctx;
            textView = (TextView) itemView.findViewById(R.id.nav_title);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.nav_layout);
        }
    }
}
