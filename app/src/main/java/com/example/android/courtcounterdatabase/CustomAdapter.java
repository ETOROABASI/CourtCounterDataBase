package com.example.android.courtcounterdatabase;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.courtcounterdatabase.data.GameListContract;

import org.w3c.dom.Text;

import static android.R.attr.name;

/**
 * Created by ETORO on 10/05/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.GuestViewHolder> {

    // Holds on to the cursor to display the waitlist
    private Cursor mCursor;
    private Context mContext;

    /**
     * Constructor using the context and the db cursor
     * @param context the calling context/activity
     * @param cursor the db cursor with waitlist data to display
     */
    public CustomAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.game_list_view, parent, false);
        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuestViewHolder holder, int position) {
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null

        // Update the view holder with the information needed to display
        String teamA = mCursor.getString(mCursor.getColumnIndex(GameListContract.GameListEntry.COLUMN_TEAM_A));
        int teamAScore = mCursor.getInt(mCursor.getColumnIndex(GameListContract.GameListEntry.COLUMN_TEAM_A_SCORE));
        String teamB = mCursor.getString(mCursor.getColumnIndex(GameListContract.GameListEntry.COLUMN_TEAM_B));
        int teamBScore = mCursor.getInt(mCursor.getColumnIndex(GameListContract.GameListEntry.COLUMN_TEAM_B_SCORE));

        // COMPLETED (6) Retrieve the id from the cursor and
        long id = mCursor.getLong(mCursor.getColumnIndex(GameListContract.GameListEntry._ID));

        // Display the guest name
        holder.teamATextView.setText(teamA);
        holder.teamBTextView.setText(teamB);
        // Display the party count
        holder.teamAScoreTextView.setText(String.valueOf(teamAScore));
        holder.teamBScoreTextView.setText(String.valueOf(teamBScore));
        holder.gameNumber.setText(String.valueOf(id));
        // COMPLETED (7) Set the tag of the itemview in the holder to the id
        holder.itemView.setTag(id);
    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    /**
     * Swaps the Cursor currently held in the adapter with a new one
     * and triggers a UI refresh
     *
     * @param newCursor the new cursor that will replace the existing one
     */
    public void swapCursor(Cursor newCursor) {      //this gets a new getAllList for the cursor since an item had been deleted or added, so kinda, gets the new updated list and
        // pastes it on your recyclerView, for more check your onSwipe method and addToWaitList method in mainActivity
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;

        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    /**
     * Inner class to hold the views needed to display a single item in the recycler-view
     */
    class GuestViewHolder extends RecyclerView.ViewHolder {

        // Will display the guest name
        TextView teamATextView;
        TextView teamBTextView;
        TextView teamAScoreTextView;
        TextView teamBScoreTextView;
        TextView gameNumber;
        // Will display the party size number


        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews
         *
         * @param itemView The View that you inflated in
         *                 {@link CustomAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public GuestViewHolder(View itemView) {
            super(itemView);
            teamATextView = (TextView) itemView.findViewById(R.id.tv_teamANameList);
            teamBTextView = (TextView) itemView.findViewById(R.id.tv_teamBNameList);
            teamAScoreTextView = (TextView) itemView.findViewById(R.id.tv_teamAscoreList);
            teamBScoreTextView = (TextView) itemView.findViewById(R.id.tv_teamBscoreList);
            gameNumber = (TextView) itemView.findViewById(R.id.tv_serial_number);
        }

    }
}