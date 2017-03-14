package android.net;

import android.annotation.Nullable;
import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * Holds metadata about a discovered network scorer/recommendation application.
 *
 * TODO: delete this class once robolectric supports Android O
 */
public final class NetworkScorerAppData implements Parcelable {
    /** UID of the scorer app. */
    public final int packageUid;
    private final ComponentName mRecommendationService;
    /** User visible label in Settings for the recommendation service. */
    private final String mRecommendationServiceLabel;
    /**
     * The {@link ComponentName} of the Activity to start before enabling the "connect to open
     * wifi networks automatically" feature.
     */
    private final ComponentName mEnableUseOpenWifiActivity;

    public NetworkScorerAppData(int packageUid, ComponentName recommendationServiceComp,
            String recommendationServiceLabel, ComponentName enableUseOpenWifiActivity) {
        this.packageUid = packageUid;
        this.mRecommendationService = recommendationServiceComp;
        this.mRecommendationServiceLabel = recommendationServiceLabel;
        this.mEnableUseOpenWifiActivity = enableUseOpenWifiActivity;
    }

    protected NetworkScorerAppData(Parcel in) {
        packageUid = in.readInt();
        mRecommendationService = ComponentName.readFromParcel(in);
        mRecommendationServiceLabel = in.readString();
        mEnableUseOpenWifiActivity = ComponentName.readFromParcel(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(packageUid);
        ComponentName.writeToParcel(mRecommendationService, dest);
        dest.writeString(mRecommendationServiceLabel);
        ComponentName.writeToParcel(mEnableUseOpenWifiActivity, dest);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NetworkScorerAppData> CREATOR =
            new Creator<NetworkScorerAppData>() {
                @Override
                public NetworkScorerAppData createFromParcel(Parcel in) {
                    return new NetworkScorerAppData(in);
                }

                @Override
                public NetworkScorerAppData[] newArray(int size) {
                    return new NetworkScorerAppData[size];
                }
            };

    public String getRecommendationServicePackageName() {
        return mRecommendationService.getPackageName();
    }

    public ComponentName getRecommendationServiceComponent() {
        return mRecommendationService;
    }

    @Nullable
    public ComponentName getEnableUseOpenWifiActivity() {
        return mEnableUseOpenWifiActivity;
    }

    @Nullable
    public String getRecommendationServiceLabel() {
        return mRecommendationServiceLabel;
    }

    @Override
    public String toString() {
        return "NetworkScorerAppData{" +
                "packageUid=" + packageUid +
                ", mRecommendationService=" + mRecommendationService +
                ", mRecommendationServiceLabel=" + mRecommendationServiceLabel +
                ", mEnableUseOpenWifiActivity=" + mEnableUseOpenWifiActivity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetworkScorerAppData that = (NetworkScorerAppData) o;
        return packageUid == that.packageUid &&
                Objects.equals(mRecommendationService, that.mRecommendationService) &&
                Objects.equals(mRecommendationServiceLabel, that.mRecommendationServiceLabel) &&
                Objects.equals(mEnableUseOpenWifiActivity, that.mEnableUseOpenWifiActivity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageUid, mRecommendationService, mRecommendationServiceLabel,
                mEnableUseOpenWifiActivity);
    }
}
