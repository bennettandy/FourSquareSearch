package uk.co.avsoftware.foursquaresearch.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uk.co.avsoftware.foursquaresearch.R;
import uk.co.avsoftware.foursquaresearch.model.Venue;

/**
 * Created by andy on 07/07/2017.
 */

public class VenueViewAdapter extends RecyclerView.Adapter<VenueViewAdapter.VenueViewHolder>{
    private List<Venue> mVenues;

    public VenueViewAdapter() {
        this.mVenues = new ArrayList();
    }

    public void setVenues(List<Venue> venues){
        mVenues = venues;
        notifyDataSetChanged();
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.venue_card, null);
        VenueViewHolder viewHolder = new VenueViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VenueViewHolder venueViewHolder, int i) {
        Venue venue = mVenues.get(i);
        venueViewHolder.nameView.setText(venue.name());
        venueViewHolder.idView.setText(venue.id());
    }

    @Override
    public int getItemCount() {
        return (null != mVenues ? mVenues.size() : 0);
    }

    class VenueViewHolder extends RecyclerView.ViewHolder {
        protected TextView nameView;
        protected TextView idView;


        public VenueViewHolder(View view) {
            super(view);
            this.nameView = (TextView) view.findViewById(R.id.name_view);
            this.idView = (TextView) view.findViewById(R.id.id_view);
        }
    }
}
