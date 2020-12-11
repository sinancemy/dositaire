package com.sierox.dositaire;

import android.os.Bundle;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAppOptions;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.sierox.dositaire.ads.AdHandler;
import com.sierox.dositaire.util.Constants;

public class AndroidLauncher extends AndroidApplication implements AdHandler {
	private static final String AD_UNIT_ID_INTERSTITIAL = "ca-app-pub-3410475000245062/3882773997";
	private static final String APP_AD_ID = "ca-app-pub-3410475000245062~1979499533";
	private InterstitialAd adMobAd;

	private static final String ADCOLONY_APP_ID = "appd992dd0abe2b49a6a3";
	private static final String ADCOLONY_TEST_ZONE_ID = "vzb525401f0fb44e6aa6";
	private static final String ADCOLONY_ZONE_ID = "vz1fa664169b344634a4";
	AdColonyInterstitialListener adColonyListener;
	private AdColonyInterstitial adColonyAd;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initAdMob();
		initAdColony();

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Dositaire(this), config);
	}

	private void initAdColony(){
		AdColonyAppOptions options = new AdColonyAppOptions()
                .setGDPRConsentString("1")
                .setGDPRRequired(true)
                .setKeepScreenOn(true);
        AdColony.configure(this, options, ADCOLONY_APP_ID, ADCOLONY_ZONE_ID);
		adColonyListener = new AdColonyInterstitialListener() {
			@Override
			public void onRequestFilled(AdColonyInterstitial ad) {
				adColonyAd = ad;
			}

            @Override
            public void onClosed(AdColonyInterstitial ad) {
                Gdx.app.log(Constants.TAG, "AdColony closed.");
                loadAdColonyAd();
            }
        };
	}

    private void loadAdColonyAd(){
        AdColony.requestInterstitial(ADCOLONY_ZONE_ID, adColonyListener);
        Gdx.app.log(Constants.TAG, "AdColony loaded.");
    }

	@Override
	public void showOrLoadAdColony() {
		if(adColonyAd == null)
		    loadAdColonyAd();
		else {
			adColonyAd.show();
            Gdx.app.log(Constants.TAG, "AdColony shown.");
		}
	}

	/////////////////////////////////////////////// ADMOB ///////////////////////////////////////


    private void initAdMob(){
        MobileAds.initialize(this, APP_AD_ID);
        initializeForView(new Dositaire(this));

        adMobAd = new InterstitialAd(this);
        adMobAd.setAdUnitId(AD_UNIT_ID_INTERSTITIAL);
        adMobAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {}

            @Override
            public void onAdClosed() {
                loadAdMobAd();
            }
        });

        loadAdMobAd();
    }

    private void loadAdMobAd(){
        AdRequest interstitialRequest = new AdRequest.Builder().build();
        adMobAd.loadAd(interstitialRequest);
    }

	@Override
	public void showOrLoadAdMob() {
		runOnUiThread(new Runnable() {
			public void run() {
				if (adMobAd.isLoaded())
					adMobAd.show();
				else
					loadAdMobAd();
			}
		});
	}


}
