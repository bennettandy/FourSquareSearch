package uk.co.avsoftware.foursquaresearch.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uk.co.avsoftware.foursquaresearch.R;
import uk.co.avsoftware.foursquaresearch.model.Venue;

/**
 * Created by andy on 07/07/2017.
 */

public class VenueViewAdapter extends RecyclerView.Adapter<VenueViewAdapter.VenueViewHolder>{
    private List<Venue> mVenues;

    VenueViewAdapter() {
        this.mVenues = new ArrayList<Venue>();
    }

    void setVenues(List<Venue> venues){
        mVenues = venues;
        notifyDataSetChanged();
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.venue_card, null);
        return new VenueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VenueViewHolder venueViewHolder, int i) {
        Venue venue = mVenues.get(i);
        venueViewHolder.nameView.setText(venue.name());
        venueViewHolder.idView.setText(venue.contact().toString());
        venueViewHolder.addressView.setText(Arrays.toString(venue.location().formattedAddress().toArray()));
    }

    @Override
    public int getItemCount() {
        return (null != mVenues ? mVenues.size() : 0);
    }

    class VenueViewHolder extends RecyclerView.ViewHolder {
         TextView nameView;
         TextView idView;
         TextView contactView;
        TextView addressView;

        VenueViewHolder(View view) {
            super(view);
            this.nameView = (TextView) view.findViewById(R.id.name_view);
            this.idView = (TextView) view.findViewById(R.id.id_view);
            this.contactView = (TextView) view.findViewById(R.id.contact_view);
            this.addressView = (TextView) view.findViewById(R.id.address_view);
        }
    }
}
