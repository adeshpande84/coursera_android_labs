package course.examples.modernartui;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private ImageView im1;
    private ImageView im2;
    private ImageView im3;
    private ImageView im4;
    private ImageView im5;

    private int im1Color;
    private int im2Color;
    private int im3Color;
    private int im5Color;

    private DialogFragment mDialog;

    // Class that creates the Dialog thats presented when More information button is pressed in menu
    public static class AlertDialogFragment extends DialogFragment {

        public static AlertDialogFragment newInstance() {
            return new AlertDialogFragment();
        }

        // Build Alert Dialog
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setMessage("Click below for more information")

                            // User can dismiss dialog by hitting back button
                    .setCancelable(true)

                            // Not now button - does not do anything
                    .setNegativeButton("Not Now",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            })

                            // Visit MoMA button - creates a new intent and starts the browser activity
                    .setPositiveButton("Visit MoMA",
                            new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog, int id) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
                                    startActivity(browserIntent);
                                }
                            }).create();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get reference to seekbar element
        seekBar = (SeekBar)findViewById(R.id.seekBar);

        //get reference to each of the imageviews
        im1 = (ImageView)findViewById(R.id.imageView);
        im2 = (ImageView)findViewById(R.id.imageView2);
        im3 = (ImageView)findViewById(R.id.imageView3);
        im4 = (ImageView)findViewById(R.id.imageView4);
        im5 = (ImageView)findViewById(R.id.imageView5);

        //get the original background color of each of the imageviews
        //these will be changed on moving the seekbar
        im1Color = ((ColorDrawable) im1.getBackground()).getColor();
        im2Color = ((ColorDrawable) im2.getBackground()).getColor();
        im3Color = ((ColorDrawable) im3.getBackground()).getColor();
        im5Color = ((ColorDrawable) im5.getBackground()).getColor();

        //listener for the seekbar change event
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                //subtract the original color of each element by progress*2
                //the number 2 is randomly chosen to give more impression of change in color
                //color of imageView4 is not changed because white color needs to remain the same throughout
                ((ColorDrawable) im1.getBackground()).setColor(im1Color - progress*2);
                ((ColorDrawable) im2.getBackground()).setColor(im2Color - progress*2);
                ((ColorDrawable) im3.getBackground()).setColor(im3Color - progress*2);
                ((ColorDrawable) im5.getBackground()).setColor(im5Color - progress*2);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showDialogFragment();
        }

        return super.onOptionsItemSelected(item);
    }

    void showDialogFragment() {
        // Create a new AlertDialogFragment
        mDialog = AlertDialogFragment.newInstance();

        // Show AlertDialogFragment
        mDialog.show(getFragmentManager(), "Alert");


    }

}
