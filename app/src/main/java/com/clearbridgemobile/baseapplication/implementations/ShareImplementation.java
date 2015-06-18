package com.clearbridgemobile.baseapplication.implementations;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.clearbridgemobile.core.interfaces.ShareInterface;
import com.clearbridgemobile.core.models.GpsModel;
import com.clearbridgemobile.core.models.ShareModel;

public class ShareImplementation implements ShareInterface {

    private Activity activity;
    public ShareImplementation(Activity activity)
    {
        this.activity = activity;
    }

    @Override
    public void nativeShare(final ShareModel shareModel) {
        share(shareModel);
    }

    @Override
    public void openMapWithLocation(GpsModel gpsModel) {
        if(gpsModel == null)
        {
            return;
        }
        String geoLocation = "geo:"+ gpsModel.getLatitude() + "," + gpsModel.getLongitude();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(geoLocation));
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(intent);
        }
    }

    @Override
    public void callPhoneNumber(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(intent);
        }
    }

    @Override
    public void openWebSite(String link) {
        Uri webpage = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(intent);
        }
    }

    private void share(ShareModel shareModel)
    {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra( android.content.Intent.EXTRA_TEXT, shareModel.getMsg()  + "\r\n" + shareModel.getImagePath());

        activity.startActivity(Intent.createChooser(share, ""));

//        List<ResolveInfo> resInfo = activity.getPackageManager().queryIntentActivities(share, 0);
//
//        if (!resInfo.isEmpty()) {
//            List<Intent> targetedShareIntents = new ArrayList<>();
//            Intent targetedShareIntent = null;
//
//            for (ResolveInfo resolveInfo : resInfo) {
//                String packageName = resolveInfo.activityInfo.packageName;
//                targetedShareIntent = new Intent(android.content.Intent.ACTION_SEND);
//                targetedShareIntent.setType("text/plain");
//
//                // Find twitter: com.twitter.android...
//                if ("com.twitter.android".equals(packageName)) {
//                    targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareModel.msg);
//                }
//                else if ("com.google.android.gm".equals(packageName) || "com.android.email".equals(packageName)) {
//                    targetedShareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareModel.msg);
//                    targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareModel.msg + "\r\n" + shareModel.imagePath);
//                } else {
//                    // Rest of Apps
//                    targetedShareIntent.putExtra( android.content.Intent.EXTRA_TEXT, shareModel.msg + "\r\n" + shareModel.imagePath);
//                }
//
//                targetedShareIntent.setPackage(packageName);
//                targetedShareIntents.add(targetedShareIntent);
//            }
//
//            Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), activity.getResources().getString(R.string.abc_shareactionprovider_share_with));
//            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[] {}));
//        }
    }
}
