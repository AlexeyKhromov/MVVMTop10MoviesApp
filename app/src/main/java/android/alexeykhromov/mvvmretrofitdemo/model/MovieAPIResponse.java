package android.alexeykhromov.mvvmretrofitdemo.model;

import java.util.ArrayList;
import java.util.List;


import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MovieAPIResponse implements Parcelable
{

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    public final static Parcelable.Creator<MovieAPIResponse> CREATOR = new Creator<MovieAPIResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieAPIResponse createFromParcel(android.os.Parcel in) {
            return new MovieAPIResponse(in);
        }

        public MovieAPIResponse[] newArray(int size) {
            return (new MovieAPIResponse[size]);
        }

    }
            ;

    protected MovieAPIResponse(android.os.Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.results, (android.alexeykhromov.mvvmretrofitdemo.model.Result.class.getClassLoader()));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public MovieAPIResponse() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<android.alexeykhromov.mvvmretrofitdemo.model.Result> getResults() {
        return (ArrayList<Result>) results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeList(results);
        dest.writeValue(totalPages);
        dest.writeValue(totalResults);
    }

    public int describeContents() {
        return 0;
    }

}
