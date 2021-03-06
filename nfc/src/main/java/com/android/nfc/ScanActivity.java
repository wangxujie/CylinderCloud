package com.android.nfc;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;



public class ScanActivity extends AppCompatActivity implements ActionBar.OnNavigationListener {

    private FragmentManager fragManager;
    private FragmentTransaction fragTran;

    private Fragment baseFindCard;
    private Fragment iso14443A;
    private Fragment iso15693;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanrfid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
            getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
            getSupportActionBar().setListNavigationCallbacks(new ArrayAdapter<String>(this,
                            android.R.layout.simple_dropdown_item_1line,
                            android.R.id.text1,
                            new String[]{"基 本 寻 卡", " ISO14443A ", " ISO15693 "}),
                    this);
        }
        fragManager = getFragmentManager();
        baseFindCard = new BaseFindCardFragment();
        iso14443A = new ISO14443aFragment();
        iso15693 = new ISO15693Fragment();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        switch (itemPosition) {
            case 0:
                repleaceFragment(baseFindCard);
                break;
            case 1:
                repleaceFragment(iso14443A);
                break;
            case 2:
                repleaceFragment(iso15693);
                break;
            case 3:

                break;
            case 4:

                break;

            default:
                break;
        }

        return false;
    }

    //�л�Fragment
    private void repleaceFragment(Fragment fragment) {
        fragTran = fragManager.beginTransaction();
        fragTran.replace(R.id.mainLayout, fragment);
        fragTran.commit();
    }

    //���Fragment
    private void removeAllFragment() {
        fragTran = fragManager.beginTransaction();
        fragTran.remove(baseFindCard);
        fragTran.remove(iso14443A);
        fragTran.commit();
    }

    @Override
    protected void onDestroy() {
//		removeAllFragment();
        super.onDestroy();
    }

}
