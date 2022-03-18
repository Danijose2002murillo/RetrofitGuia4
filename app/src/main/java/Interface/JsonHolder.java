package Interface;

import Modelo.Post;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface JsonHolder {
    @GET("users")
    Call<List<Post>> getpost();
}
