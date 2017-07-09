package uk.co.avsoftware.foursquaresearch.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uk.co.avsoftware.foursquaresearch.R;
import uk.co.avsoftware.foursquaresearch.model.Contact;
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
        Context context = venueViewHolder.itemView.getContext();

        Venue venue = mVenues.get(i);
        venueViewHolder.nameView.setText(venue.name());
        venueViewHolder.idView.setText(venue.id());
        venueViewHolder.addressView.setText(formatAddress(venue.location().formattedAddress(), context));
        venueViewHolder.contactView.setText(formatContacts(venue.contact(), context ));
    }

    private Spanned formatAddress(List<String> address, Context ctx) {
        StringBuilder builder = new StringBuilder();
        for(String item : address){
            builder.append(item);
            builder.append("<br/>");
        }
        return spannableFromHtml(builder.toString());
    }

    private Spanned spannableFromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY);
        } else {
            //noinspection deprecation
            result = Html.fromHtml(html);
        }

        return result;
    }

    private Spanned formatContacts(Contact contact, Context ctx) {
        StringBuilder builder = new StringBuilder();
        if(!TextUtils.isEmpty(contact.facebook())){
            builder.append(ctx.getString(R.string.contact_facebook, contact.facebook()));
        }
        if(!TextUtils.isEmpty(contact.instagram())){
            builder.append(ctx.getString(R.string.contact_instagram, contact.instagram()));
        }
        if(!TextUtils.isEmpty(contact.twitter())){
            builder.append(ctx.getString(R.string.contact_twitter, contact.twitter()));
        }
        if(!TextUtils.isEmpty(contact.formattedPhone())){
            builder.append(ctx.getString(R.string.contact_phone, contact.formattedPhone()));
        }

        return spannableFromHtml(builder.toString());
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
