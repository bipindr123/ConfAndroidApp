package com.example.bipin.conftest6;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseUser;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


public class NavDrawer {
    public static void getDrawer(final Activity activity, Toolbar toolbar) {
        Log.d("stad", "getDrawer: ");
        //if you want to update the items at a later time it is recommended to keep it in a variable
;
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home").withIcon(GoogleMaterial.Icon.gmd_home);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Events").withIcon(FontAwesome.Icon.faw_calendar_alt2);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("FeedBack").withIcon(FontAwesome.Icon.faw_connectdevelop);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Journal").withIcon(FontAwesome.Icon.faw_book_open);

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Welcome "+ MainActivity.logedInUser).withEmail(MainActivity.logedInEmail).withIcon(R.drawable.profile3)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(headerResult)
                .withActionBarDrawerToggleAnimated(true)
                .withCloseOnClick(true)
                .withSelectedItem(-1)
                .addDrawerItems(
                        item1,item2, new DividerDrawerItem(), item3, item4
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 2) {
                            // load tournament screen
                            Intent intent = new Intent(activity, MainActivity2.class);

                            view.getContext().startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 3) {
                            // load tournament screen
                            Intent intent = new Intent(activity, feedback.class);
                            view.getContext().startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 4) {
                            // load tournament screen
                            Intent intent = new Intent(activity, Journal.class);
                            view.getContext().startActivity(intent);
                        }
                        return true;
                    }
                })
                .build();
    }
}