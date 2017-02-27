package com.bonrita.navigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;


public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    List<DrawerListItem> listItems = Collections.emptyList();
    private LayoutInflater inflater;
    Context context;
    private AdapterClickListener adapterClickListener;

    public DrawerAdapter(List<DrawerListItem> listItems, Context context) {
        this.listItems = listItems;
        inflater = LayoutInflater.from(context);
        context = context;
    }

    public void setAdapterClickListener(AdapterClickListener adapterClickListener) {
        this.adapterClickListener = adapterClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.drawer_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        DrawerListItem current = listItems.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);

        // https://www.udemy.com/android-material-design-zero-to-hero/learn/v4/t/lecture/2664252
        // Time: 8:40
        // The code below works but is not advised to do on clicks within our adapter.
        //Simply because we are tying the functionality to the adapter.
        //Instead we need to do the onlcik of the item within the fragment that is calling
        //this adapter.
//        holder.title.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(),"Hold me "+ (position + 1), Toast.LENGTH_LONG).show();
//            }
//        });

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(),"Hold me "+ (position + 1), Toast.LENGTH_LONG).show();

                if (null != adapterClickListener) {
                    adapterClickListener.itemClicked(v, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView title;
        private LinearLayout container;

        public ViewHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.title);
            this.icon = (ImageView) itemView.findViewById(R.id.icon);
            this.container = (LinearLayout) itemView.findViewById(R.id.container);
        }

    }

    // Interface to control click listeners from outside the adapter.
    // https://www.udemy.com/android-material-design-zero-to-hero/learn/v4/t/lecture/2664252
    // Time: 4:20
    public interface AdapterClickListener {
        public void itemClicked(View view, int position);
    }

}
